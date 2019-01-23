package com.ylean.cf_hospitalapp.mall.acitity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.mall.adapter.OrderAdapter;
import com.ylean.cf_hospitalapp.mall.pres.IOrderListPres;
import com.ylean.cf_hospitalapp.mall.bean.MallOrderEntry;
import com.ylean.cf_hospitalapp.mall.view.IOrderListView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务列表订单
 * Created by linaidao on 2019/1/8.
 */

public class ServiceOrderListActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, IOrderListView {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tv1;
    private android.widget.TextView tv2;
    private android.widget.TextView tv3;
    private android.widget.TextView tv4;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;
    private android.support.v7.widget.RecyclerView recyclerView;
    private String status;
    private List<MallOrderEntry.DataBean> orderList = new ArrayList<>();


//    全部（不传）， 待付款0， 待发货 1，待收货2  ,待使用3，已完成 4)

    private static final String STATUS_ALL = "";
    private static final String STATUS_WAIT_PAY = "0";
    private static final String STATUS_WAIT_USE = "3";
    private static final String STATUS_DONE = "4";
    private SwipeRefreshLayout swipeRefreshLayout;

    private IOrderListPres iOrderListPres = new IOrderListPres(this);
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_service_list);

        initView();

        tv1.setSelected(true);
        status = STATUS_ALL;

        iOrderListPres.setPage(1);
        getData(true);

    }


    private void initView() {
        this.recyclerView = findViewById(R.id.recyclerView);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        this.tv4 = findViewById(R.id.tv4);
        this.tv3 = (TextView) findViewById(R.id.tv3);
        this.tv2 = (TextView) findViewById(R.id.tv2);
        this.tv1 = (TextView) findViewById(R.id.tv1);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
        recyclerView.addItemDecoration(divider);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);

        orderAdapter = new OrderAdapter(this, orderList);
        recyclerView.setAdapter(orderAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (orderAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == orderAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {

                    iOrderListPres.setPage(iOrderListPres.getPage() + 1);
                    getData(false);

                }

            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv1:
                setAllGray();
                tv1.setSelected(true);
                status = STATUS_ALL;
                onRefresh();
                break;

            case R.id.tv2:
                setAllGray();
                tv2.setSelected(true);
                status = STATUS_WAIT_PAY;
                onRefresh();

                break;

            case R.id.tv3:
                setAllGray();
                tv3.setSelected(true);
                status = STATUS_WAIT_USE;
                onRefresh();

                break;

            case R.id.tv4:
                setAllGray();
                tv4.setSelected(true);
                status = STATUS_DONE;
                onRefresh();

                break;


        }
    }

    //获取数据
    private void getData(boolean refush) {
        iOrderListPres.serviceOrder((String) SaveUtils.get(this, SpValue.TOKEN, "")
                , status, refush, iOrderListPres.getPage(), SpValue.ORDER_LIST_TYPE_SERVICE);
    }

    private void setAllGray() {

        tv1.setSelected(false);
        tv2.setSelected(false);
        tv3.setSelected(false);
        tv4.setSelected(false);

    }

    @Override
    public void onRefresh() {
        iOrderListPres.setPage(1);
        getData(true);
    }

    @Override
    public void stopRefush() {

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setOrderList(List<MallOrderEntry.DataBean> data, boolean refush) {

        if (refush)
            orderList.clear();

        orderList.addAll(data);

        if (orderAdapter != null)
            orderAdapter.notifyDataSetChanged();

    }
}