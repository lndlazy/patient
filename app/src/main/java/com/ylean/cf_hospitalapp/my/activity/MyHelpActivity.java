package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.hx.HelpChatActivity;
import com.ylean.cf_hospitalapp.my.adapter.HelpAdapter;
import com.ylean.cf_hospitalapp.my.bean.HelpEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.Constant;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的帮帮团
 * Created by linaidao on 2019/1/9.
 */

public class MyHelpActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private android.support.v7.widget.RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HelpAdapter helpAdapter;
    private List<HelpEntry.DataBean> helpList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_help);
        init();

        helpList();
    }

    private void helpList() {

        RetrofitHttpUtil.getInstance()
                .helpList(new BaseNoTObserver<HelpEntry>() {
                    @Override
                    public void onHandleSuccess(HelpEntry basebean) {
                        swipeRefreshLayout.setRefreshing(false);

                        if (basebean == null || basebean.getData() == null)
                            return;

                        helpList.clear();

                        helpList.addAll(basebean.getData());
                        if (helpAdapter != null)
                            helpAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""));

    }

    private void init() {

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        helpAdapter = new HelpAdapter(this, helpList);
        recyclerView.setAdapter(helpAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent mIntent = new Intent(MyHelpActivity.this, HelpChatActivity.class);
                mIntent.putExtra("chatType", Constant.CHATTYPE_GROUP);
                mIntent.putExtra("userId", helpList.get(position).getRoomid());
                startActivity(mIntent);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void onRefresh() {
        helpList();
    }

}
