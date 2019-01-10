package com.ylean.cf_hospitalapp.home.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.home.adapter.SearchAdapter;
import com.ylean.cf_hospitalapp.home.bean.SearchListEntry;
import com.ylean.cf_hospitalapp.home.presenter.ISearchPres;
import com.ylean.cf_hospitalapp.home.view.ISearchView;
import com.ylean.cf_hospitalapp.inquiry.adapter.SearchValueAdapter;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchResultActivity extends BaseActivity implements View.OnClickListener, ISearchView {

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
    private TextView tvart;
    private TextView tvInqu;
    private TextView tvDoct;
    private TextView tvHosp;
    private List<SearchListEntry.DataBean> searchList = new ArrayList<>();

    private ISearchPres iSearchPres = new ISearchPres(this);
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        SearchListEntry resultbean = getIntent().getParcelableExtra("data");

        //设置医院id
        iSearchPres.setHospitalId((String) SaveUtils.get(this, SpValue.HOSPITAL_ID, ""));
        iSearchPres.setToken((String) SaveUtils.get(this, SpValue.TOKEN, ""));

        initView();
        rlHistory.setVisibility(View.GONE);
        tvart.setSelected(true);


        iSearchPres.setName(getIntent().getStringExtra("name"));
        iSearchPres.setPage(1);
        iSearchPres.setSearchType(getIntent().getIntExtra("type", -1));
        setAllFalse(getIntent().getIntExtra("type", -1), false);

        if (resultbean != null) {
            searchList.addAll(resultbean.getData());
            if (searchAdapter != null)
                searchAdapter.notifyDataSetChanged();
        }

    }

    private void initView() {

        this.rlHistory = (RelativeLayout) findViewById(R.id.rlHistory);
        this.ivDelete = (ImageView) findViewById(R.id.ivDelete);
        this.tvcancle = (TextView) findViewById(R.id.tvcancle);
        this.ivClear = (ImageView) findViewById(R.id.ivClear);
        this.etSearchInfo = (EditText) findViewById(R.id.etSearchInfo);
        this.llSearchStyle = (LinearLayout) findViewById(R.id.llSearchStyle);
        this.tvStyle = (TextView) findViewById(R.id.tvStyle);

        tvart = findViewById(R.id.tvart);
        tvInqu = findViewById(R.id.tvInqu);
        tvDoct = findViewById(R.id.tvDoct);
        tvHosp = findViewById(R.id.tvHosp);

        initRecyclerView();

        ivDelete.setOnClickListener(this);
        ivClear.setOnClickListener(this);
        llSearchStyle.setOnClickListener(this);
        tvcancle.setOnClickListener(this);

        tvart.setOnClickListener(this);
        tvInqu.setOnClickListener(this);
        tvDoct.setOnClickListener(this);
        tvHosp.setOnClickListener(this);

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

    private void initRecyclerView() {

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        searchAdapter = new SearchAdapter(this, searchList);
        recyclerView.setAdapter(searchAdapter);

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

            case R.id.tvInqu://问诊

                tvStyle.setText("问诊");
                setAllFalse(4, true);

                break;
            case R.id.tvdoctor://医生
            case R.id.tvDoct:
                tvStyle.setText("医生");
                setAllFalse(1, true);

                break;
            case R.id.tvhospital://医院
            case R.id.tvHosp:

                tvStyle.setText("医院");
                setAllFalse(0, true);

                break;
            case R.id.tvdepartment://科室

                tvStyle.setText("科室");
                setAllFalse(2, true);

                break;
            case R.id.tvarticle://文章
            case R.id.tvart:

                tvStyle.setText("文章");
                setAllFalse(3, true);

                break;
        }

    }

    //  0://医院    1://医生    2://科室   3://文章    4://问诊

    private void setAllFalse(int i, boolean loadData) {

        if (choosePopupWindow != null)
            choosePopupWindow.dismiss();

        tvInqu.setSelected(false);
        tvDoct.setSelected(false);
        tvHosp.setSelected(false);
        tvart.setSelected(false);

        switch (i) {

            case 3:
                tvart.setSelected(true);
                tvStyle.setText("文章");
                break;
            case 4:
                tvInqu.setSelected(true);
                tvStyle.setText("问诊");
                break;
            case 1:
                tvDoct.setSelected(true);
                tvStyle.setText("医生");
                break;
            case 0:
                tvHosp.setSelected(true);
                tvStyle.setText("医院");
                break;

            case 2:
                tvStyle.setText("科室");
                break;
        }

        iSearchPres.setName(etSearchInfo.getText().toString());
        iSearchPres.setPage(1);
        iSearchPres.setSearchType(i);

        if (loadData)
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
                CommonUtils.darkenBackground(1f, SearchResultActivity.this);
            }
        });


    }

    @Override
    public void setSearchInfo(List<SearchListEntry.DataBean> data) {

        if (iSearchPres.getPage() == 1)
            searchList.clear();

        searchList.addAll(data);
        if (searchAdapter != null)
            searchAdapter.notifyDataSetChanged();

    }

    @Override
    public void setSearchInfo(SearchListEntry basebean) {

    }

//    private void startSearch() {
//
//        if ("搜索".equals(tvcancle.getText().toString())) {
//
//            historySet = SaveUtils.getSet(getApplicationContext(), SpValue.SEARCH_HISTORY, null);
//
//            if (historySet == null)
//                historySet = new LinkedHashSet<>();
//
//            historySet.add(etSearchInfo.getText().toString());
//            SaveUtils.putSet(getApplicationContext(), SpValue.SEARCH_HISTORY, historySet);
//
//            iSearchPres.setName(etSearchInfo.getText().toString());
//            iSearchPres.setPage(1);
//            iSearchPres.startSearch();
//
//        }else if ("取消".equals(tvcancle.getText().toString())) {
//
//
//        }
//
//    }
}
