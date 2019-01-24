package com.ylean.cf_hospitalapp.mall.acitity;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.ylean.cf_hospitalapp.comm.pres.IGoodsInfoPres;
import com.ylean.cf_hospitalapp.comm.view.IGoodsinfoView;
import com.ylean.cf_hospitalapp.mall.adapter.OrderAdapter;
import com.ylean.cf_hospitalapp.mall.bean.GoodsOrderInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.MallOrderEntry;
import com.ylean.cf_hospitalapp.mall.pres.IOrderInfoPres;
import com.ylean.cf_hospitalapp.mall.pres.IOrderListPres;
import com.ylean.cf_hospitalapp.mall.view.IOrderInfoView;
import com.ylean.cf_hospitalapp.mall.view.IOrderListView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品订单列表
 * Created by linaidao on 2019/1/21.
 */

public class GoodsOrderListActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, IOrderListView, IOrderInfoView, IGoodsinfoView {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tv1;
    private android.widget.TextView tv2;
    private android.widget.TextView tv3;
    private android.widget.TextView tv4;
    private android.widget.TextView tv5;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;
    private android.support.v7.widget.RecyclerView recyclerView;
    private String status;

    //    全部（不传）， 待付款0， 待发货 1，待收货2  ,待使用3，已完成 4)
    private static final String STATUS_ALL = "";
    private static final String STATUS_WAIT_PAY = "0";
    private static final String STATUS_WAIT_SEND = "1";
    private static final String STATUS_WAIT_RECEIVER = "2";
    private static final String STATUS_DONE = "4";

    private List<MallOrderEntry.DataBean> orderList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;

    private IOrderListPres iOrderListPres = new IOrderListPres(this);
    private IGoodsInfoPres iDeletePres = new IGoodsInfoPres(this);
    private IOrderInfoPres iOrderInfoPres = new IOrderInfoPres(this);

    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_goods_order);

        initView();


        tv1.setSelected(true);
        status = STATUS_ALL;

    }


    @Override
    protected void onResume() {
        super.onResume();

        iOrderListPres.setPage(1);
        getData(true);

    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        this.tv5 = (TextView) findViewById(R.id.tv5);
        this.tv4 = (TextView) findViewById(R.id.tv4);
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
        tv5.setOnClickListener(this);

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

    //获取数据
    private void getData(boolean refush) {
        iOrderListPres.serviceOrder((String) SaveUtils.get(this, SpValue.TOKEN, "")
                , status, refush, iOrderListPres.getPage(), SpValue.ORDER_LIST_TYPE_GOODS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv1://全部
                setAllGray();
                tv1.setSelected(true);
                status = STATUS_ALL;
                onRefresh();
                break;

            case R.id.tv2://待支付
                setAllGray();
                tv2.setSelected(true);
                status = STATUS_WAIT_PAY;
                onRefresh();

                break;

            case R.id.tv3://待发货
                setAllGray();
                tv3.setSelected(true);
                status = STATUS_WAIT_SEND;
                onRefresh();

                break;

            case R.id.tv4://待收货
                setAllGray();
                tv4.setSelected(true);
                status = STATUS_WAIT_RECEIVER;
                onRefresh();

                break;

            case R.id.tv5://已完成
                setAllGray();
                tv5.setSelected(true);
                status = STATUS_DONE;
                onRefresh();

                break;

        }
    }

    private void setAllGray() {

        tv1.setSelected(false);
        tv2.setSelected(false);
        tv3.setSelected(false);
        tv4.setSelected(false);
        tv5.setSelected(false);

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


    //取消订单
    public void cancleAction(String orderId, String status) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("您确定要取消订单吗").setPositiveButton("取消订单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                iOrderInfoPres.cancleGoodsOrder((String) SaveUtils.get(GoodsOrderListActivity.this, SpValue.TOKEN, ""), orderId
                        , status);
            }
        }).setNegativeButton("保留", null).show();
    }

    //确认收货
    public void confirmGetGoods(String orderId) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("确认收货").setPositiveButton("已收货", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //确认收货
                iDeletePres.confirmReceive((String) SaveUtils.get(GoodsOrderListActivity.this, SpValue.TOKEN, ""), orderId);

            }
        }).setNegativeButton("取消", null).show();
    }

    //评价商品
    public void command(GoodsOrderInfoEntry.DataBean goodsInfo) {
        Intent m = new Intent(this, GoodsCommandActivity.class);
        m.putExtra("goodsInfo", goodsInfo);
        startActivityForResult(m, 0x0041);
    }

    //使用订单
    public void go2use(String orderId) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("确认使用该订单吗").setPositiveButton("确认使用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //使用服务订单
                iOrderInfoPres.useServiceOrder((String) SaveUtils.get(GoodsOrderListActivity.this, SpValue.TOKEN, ""), orderId);

            }
        }).setNegativeButton("取消", null).show();

    }


    //删除订单
    public void deleteOrder(String orderId) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("您确定要删除该订单吗").setPositiveButton("删除订单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //删除订单
                iDeletePres.deleteGoodsOrder((String) SaveUtils.get(GoodsOrderListActivity.this, SpValue.TOKEN, ""), orderId);
            }
        }).setNegativeButton("取消", null).show();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 0x0041:

                if (resultCode == 0x003)
                    //评价成功
                    refush();

                break;

        }
    }


    @Override
    public void setOrderDetail(GoodsOrderInfoEntry.DataBean data) {

    }

    //取消订单成功
    @Override
    public void cancleOrderSuccess() {
        refush();

    }

    //使用服务成功
    @Override
    public void useServiceSuccess() {
        refush();

    }

    @Override
    public void deleteSuccess() {
        refush();

    }

    @Override
    public void confirmSuccess() {
        refush();
    }


    private void refush() {
        iOrderListPres.setPage(1);
        getData(true);
    }
}
