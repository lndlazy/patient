package com.ylean.cf_hospitalapp.register.activity;

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
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.register.adapter.RegisterListAdapter;
import com.ylean.cf_hospitalapp.register.bean.RegisterOrderEntry;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的挂号订单列表
 * Created by linaidao on 2019/1/4.
 */

public class MyRegisterListActivity extends BaseActivity {

    private android.support.v7.widget.RecyclerView recyclerView;
    private List<RegisterOrderEntry.DataBean> registerList = new ArrayList<>();
    private RegisterListAdapter registerOrderAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Intent m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_register_order);

        initView();

        reigsterOrderList();

    }

    @Override
    protected void onResume() {
        super.onResume();

        reigsterOrderList();
    }

    private void reigsterOrderList() {

        RetrofitHttpUtil
                .getInstance()
                .registerOrderList(
                        new BaseNoTObserver<RegisterOrderEntry>() {
                            @Override
                            public void onHandleSuccess(RegisterOrderEntry orderEntry) {

                                if (orderEntry != null && orderEntry.getData() != null) {

//                                    if (isrefush)
                                    swipeRefreshLayout.setRefreshing(false);

                                    registerList.clear();

                                    registerList.addAll(orderEntry.getData());
                                    if (registerOrderAdapter != null)
                                        registerOrderAdapter.notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onHandleError(String message) {
                                swipeRefreshLayout.setRefreshing(false);
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""));

    }

    private void initView() {

        this.recyclerView = findViewById(R.id.recyclerView);

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
        recyclerView.addItemDecoration(divider);

        registerOrderAdapter = new RegisterListAdapter(this, registerList);
        recyclerView.setAdapter(registerOrderAdapter);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reigsterOrderList();

            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                m = new Intent(MyRegisterListActivity.this, RegisterPayActivity.class);
                m.putExtra("id", registerList.get(position).getId());
                startActivity(m);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }
}
