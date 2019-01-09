package com.ylean.cf_hospitalapp.home.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.adapter.SearchValueAdapter;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.LinkedHashSet;
import java.util.Set;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private android.widget.TextView tvStyle;
    private android.widget.LinearLayout llSearchStyle;
    private android.widget.EditText etSearchInfo;
    private android.widget.ImageView ivClear;
    private android.widget.TextView tvStartSearch;
    private android.widget.ImageView ivDelete;
    private android.widget.RelativeLayout rlHistory;
    private android.support.v7.widget.RecyclerView recyclerView;
    private Set<String> historySet;
    private SearchValueAdapter searchValueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        initView();

    }

    private void initView() {

        this.rlHistory = (RelativeLayout) findViewById(R.id.rlHistory);
        this.ivDelete = (ImageView) findViewById(R.id.ivDelete);
        this.tvStartSearch = (TextView) findViewById(R.id.tvStartSearch);
        this.ivClear = (ImageView) findViewById(R.id.ivClear);
        this.etSearchInfo = (EditText) findViewById(R.id.etSearchInfo);
        this.llSearchStyle = (LinearLayout) findViewById(R.id.llSearchStyle);
        this.tvStyle = (TextView) findViewById(R.id.tvStyle);

        initRecyclerView();

        ivDelete.setOnClickListener(this);
        ivClear.setOnClickListener(this);
        llSearchStyle.setOnClickListener(this);
        tvStartSearch.setOnClickListener(this);

        etSearchInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ivClear.setVisibility(TextUtils.isEmpty(s.toString()) ? View.GONE : View.VISIBLE);
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
                searchValueAdapter = new SearchValueAdapter(SearchActivity.this, historySet);
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

            case R.id.tvStartSearch://开始搜索
                startSearch();
                break;

        }

    }

    private void startSearch() {

        if (TextUtils.isEmpty(etSearchInfo.getText().toString())) {
            showErr(getResources().getString(R.string.please_input_search));
            return;
        }

        historySet = SaveUtils.getSet(getApplicationContext(), SpValue.SEARCH_HISTORY, null);

        if (historySet == null)
            historySet = new LinkedHashSet<>();

        historySet.add(etSearchInfo.getText().toString());
        SaveUtils.putSet(getApplicationContext(), SpValue.SEARCH_HISTORY, historySet);

    }
}
