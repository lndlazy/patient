package com.ylean.cf_hospitalapp.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.home.bean.SearchListEntry;
import com.ylean.cf_hospitalapp.home.presenter.ISearchPres;
import com.ylean.cf_hospitalapp.home.view.ISearchView;
import com.ylean.cf_hospitalapp.inquiry.adapter.SearchValueAdapter;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 搜索中界面
 * Created by linaidao on 2019/1/10.
 */

public class SearchingAcivity extends BaseActivity implements View.OnClickListener, ISearchView {

    private android.widget.TextView tvStyle;
    private android.widget.LinearLayout llSearchStyle;
    private android.widget.EditText etSearchInfo;
    private android.widget.ImageView ivClear;
    private android.widget.TextView tvcancle;
    private android.widget.ImageView ivDelete;
    private android.widget.RelativeLayout rlHistory;
    private android.support.v7.widget.RecyclerView recyclerView;
    private Set<String> historySet;
    private SearchValueAdapter searchValueAdapter;
    private PopupWindow choosePopupWindow;
    private ISearchPres iSearchPres = new ISearchPres(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_searching);

        //设置医院id
        iSearchPres.setHospitalId((String) SaveUtils.get(this, SpValue.HOSPITAL_ID, ""));
        iSearchPres.setToken((String) SaveUtils.get(this, SpValue.TOKEN, ""));

        initView();

        tvStyle.setText("文章");

        iSearchPres.setName(etSearchInfo.getText().toString());
        iSearchPres.setPage(1);
        iSearchPres.setSearchType(3);
//        beginSearch();

    }

    private void initView() {

        this.rlHistory = (RelativeLayout) findViewById(R.id.rlHistory);
        this.ivDelete = (ImageView) findViewById(R.id.ivDelete);
        this.tvcancle = (TextView) findViewById(R.id.tvcancle);
        this.ivClear = (ImageView) findViewById(R.id.ivClear);
        this.etSearchInfo = (EditText) findViewById(R.id.etSearchInfo);
        this.llSearchStyle = (LinearLayout) findViewById(R.id.llSearchStyle);
        this.tvStyle = (TextView) findViewById(R.id.tvStyle);


        initRecyclerView();

        ivDelete.setOnClickListener(this);
        ivClear.setOnClickListener(this);
        llSearchStyle.setOnClickListener(this);
        tvcancle.setOnClickListener(this);

        etSearchInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ivClear.setVisibility(TextUtils.isEmpty(s) ? View.INVISIBLE : View.VISIBLE);
                setHistoryInfo();
            }
        });

        etSearchInfo.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    // 获得焦点
                    setHistoryInfo();

                } else {
                    // 失去焦点
                    hideHistory();
                }

            }

        });

        etSearchInfo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    iSearchPres.setName(etSearchInfo.getText().toString());
                    beginSearch();
                }

                return false;

            }
        });
    }

    private void setHistoryInfo() {

        historySet = SaveUtils.getSet(getApplicationContext(), SpValue.SEARCH_HISTORY, null);

        if (historySet == null)
            return;

        for (String value : historySet) {
            Logger.d("存储的值::" + value);
        }

        if (historySet != null) {
            showHistory();

            if (searchValueAdapter == null) {
                searchValueAdapter = new SearchValueAdapter(SearchingAcivity.this, historySet);
                recyclerView.setAdapter(searchValueAdapter);
            } else {
                searchValueAdapter.notifyDataSetChanged();
            }

        } else {
            hideHistory();
        }
    }

    private void hideHistory() {
        rlHistory.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        isShowHistory();

    }

    private void isShowHistory() {
        historySet = SaveUtils.getSet(getApplicationContext(), SpValue.SEARCH_HISTORY, null);

        if (historySet == null) {
            //没有搜索历史记录
            hideHistory();
        } else {
            showHistory();
        }
    }

    private void showHistory() {
        rlHistory.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView() {

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.llSearchStyle://搜索类型

                showSearchList();

                break;

            case R.id.ivClear://清空当前搜索的关键字
                etSearchInfo.setText("");
                break;

            case R.id.ivDelete://删除历史记录

                historySet.clear();
                if (searchValueAdapter != null)
                    searchValueAdapter.notifyDataSetChanged();
                SaveUtils.putSet(getApplicationContext(), SpValue.SEARCH_HISTORY, null);
                break;

            case R.id.tvcancle://取消搜索
                finish();
                break;

            case R.id.tvdepartment://问诊

                tvStyle.setText("问诊");
                setAllFalse(4);

                break;
            case R.id.tvdoctor://医生

                tvStyle.setText("医生");
                setAllFalse(1);

                break;
            case R.id.tvhospital://医院
//            case R.id.tvHosp:

                tvStyle.setText("医院");
                setAllFalse(0);

                break;
//            case R.id.tvdepartment://问诊
//
//                tvStyle.setText("问诊");
//                setAllFalse(2);
//
//                break;
            case R.id.tvarticle://文章
//            case R.id.tvart:

                tvStyle.setText("文章");
                setAllFalse(3);

                break;
        }

    }

    //  0://医院    1://医生    2://科室   3://文章    4://问诊

    private void setAllFalse(int i) {

        if (choosePopupWindow != null)
            choosePopupWindow.dismiss();

        switch (i) {

            case 3:

                tvStyle.setText("文章");
                break;
            case 4:

                tvStyle.setText("问诊");
                break;
            case 1:

                tvStyle.setText("医生");
                break;
            case 0:

                tvStyle.setText("医院");
                break;

//            case 2:
//                tvStyle.setText("科室");
//                break;
        }

        iSearchPres.setName(etSearchInfo.getText().toString());
        iSearchPres.setPage(1);
        iSearchPres.setSearchType(i);
        beginSearch();

    }


    private void beginSearch() {

        historySet = SaveUtils.getSet(getApplicationContext(), SpValue.SEARCH_HISTORY, null);

        if (historySet == null)
            historySet = new LinkedHashSet<>();

        historySet.add(etSearchInfo.getText().toString());
        SaveUtils.putSet(getApplicationContext(), SpValue.SEARCH_HISTORY, historySet);

        iSearchPres.startSearch();
    }

    private void showSearchList() {

        View view = View.inflate(this, R.layout.dialog_search_choose, null);
        choosePopupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        choosePopupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        choosePopupWindow.setFocusable(true);
        choosePopupWindow.showAsDropDown(llSearchStyle);

        CommonUtils.darkenBackground(0.7f, this);

        TextView tvarticle = view.findViewById(R.id.tvarticle);
        TextView tvhospital = view.findViewById(R.id.tvhospital);
        TextView tvdoctor = view.findViewById(R.id.tvdoctor);
        TextView tvdepartment = view.findViewById(R.id.tvdepartment);

        tvarticle.setOnClickListener(this);
        tvhospital.setOnClickListener(this);
        tvdepartment.setOnClickListener(this);
        tvdoctor.setOnClickListener(this);

        choosePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                CommonUtils.darkenBackground(1f, SearchingAcivity.this);
            }
        });


    }

    @Override
    public void setSearchInfo(List<SearchListEntry.DataBean> data) {


    }

    @Override
    public void setSearchInfo(SearchListEntry basebean) {
        Intent m = new Intent(this, SearchResultActivity.class);
        m.putExtra("data", basebean);
        m.putExtra("name", iSearchPres.getName());
        m.putExtra("type", iSearchPres.getSearchType());
        startActivity(m);
    }

}