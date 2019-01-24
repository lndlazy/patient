package com.ylean.cf_hospitalapp.my.activity;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.HomeActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryRefundActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.OrderEntry;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.presenter.IInquiryOrderPers;
import com.ylean.cf_hospitalapp.inquiry.view.IInquiryOrderView;
import com.ylean.cf_hospitalapp.my.bean.OrderInquiryDetailEntry;
import com.ylean.cf_hospitalapp.my.presenter.IMyRegisterP;
import com.ylean.cf_hospitalapp.my.adapter.OrderListAdapter;
import com.ylean.cf_hospitalapp.my.view.IMyRegisteredView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的问诊
 * Created by linaidao on 2018/12/23.
 */

public class MyInquiryListActivity extends BaseActivity implements IMyRegisteredView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, IInquiryOrderView {

    private android.widget.TextView tvPic;
    private android.widget.TextView tvTel;
    private android.widget.TextView tvVideo;
    private android.widget.TextView tvAll;
    private android.widget.TextView tvWaitPay;
    private android.widget.TextView tvWaitDone;
    private android.widget.TextView tvDone;
    private android.widget.TextView tvCancled;
    private android.support.v7.widget.RecyclerView recyclerView;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;
    private IMyRegisterP iMyRegisterP = new IMyRegisterP(this);
    private TextView tvWaitSure;
    private ImageView ivWaitSure;

    private List<OrderEntry.DataBean> orderEntryList = new ArrayList<>();
    private OrderListAdapter orderListAdapter;
    private SwipeRefreshLayout swipView;

    private String currentType = "";//当前问诊类型， 图文，视频，电话

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_inquiry);

        currentType = SpValue.ASK_TYPE_PIC;

        initView();
        iMyRegisterP.myInquiry(currentType, true);
    }

    private void initView() {

        this.recyclerView = findViewById(R.id.recyclerView);
        this.tvCancled = findViewById(R.id.tvCancled);
        this.tvDone = findViewById(R.id.tvDone);
        this.tvWaitDone = (TextView) findViewById(R.id.tvWaitDone);
        this.tvWaitPay = (TextView) findViewById(R.id.tvWaitPay);
        this.tvAll = (TextView) findViewById(R.id.tvAll);
        this.tvVideo = (TextView) findViewById(R.id.tvVideo);
        this.tvTel = (TextView) findViewById(R.id.tvTel);
        this.tvPic = (TextView) findViewById(R.id.tvPic);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        ivWaitSure = findViewById(R.id.ivWaitSure);
        tvWaitSure = findViewById(R.id.tvWaitSure);

        swipView = findViewById(R.id.swipView);
        swipView.setOnRefreshListener(this);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(HomeActivity.class);
            }
        });

        tvPic.setOnClickListener(this);
        tvTel.setOnClickListener(this);
        tvVideo.setOnClickListener(this);
        tvAll.setOnClickListener(this);
        tvWaitPay.setOnClickListener(this);
        tvWaitDone.setOnClickListener(this);
        tvDone.setOnClickListener(this);
        tvCancled.setOnClickListener(this);
        tvWaitSure.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
        recyclerView.addItemDecoration(divider);

        orderListAdapter = new OrderListAdapter(this, orderEntryList, SpValue.ASK_TYPE_PIC);
        recyclerView.setAdapter(orderListAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (orderListAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == orderListAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
                    iMyRegisterP.pagePlus();
                    iMyRegisterP.myInquiry(currentType, false);
                }

            }

        });

//        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
//            @Override
//            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
//
//                if (orderEntryList.get(position) == null || TextUtils.isEmpty(orderEntryList.get(position).getType()))
//                    return;
//
//                switch (orderEntryList.get(position).getType()) {
//
//                    case SpValue.ASK_TYPE_PIC://图文详情
//
//                        Intent m = new Intent(MyInquiryListActivity.this, InquiryOrderDetialActivity.class);
//                        m.putExtra("type", orderEntryList.get(position).getType());
//                        m.putExtra("id", orderEntryList.get(position).getOrderid());
//                        startActivity(m);
//
//                        break;
//
//                    case SpValue.ASK_TYPE_TEL:
//                        break;
//
//                    case SpValue.ASK_TYPE_VIDEO:
//                        break;
//
//                }
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvPic://图文

                currentType = SpValue.ASK_TYPE_PIC;
                setType(SpValue.ASK_TYPE_PIC);

                break;

            case R.id.tvTel://电话
                currentType = SpValue.ASK_TYPE_TEL;

                setType(SpValue.ASK_TYPE_TEL);

                break;

            case R.id.tvVideo://视频
                currentType = SpValue.ASK_TYPE_VIDEO;

                setType(SpValue.ASK_TYPE_VIDEO);

                break;

            case R.id.tvAll://全部订单

                setOrderStatus(SpValue.ORDER_ALL);

                break;

            case R.id.tvWaitPay://等待支付

                setOrderStatus(SpValue.ORDER_WAIT_PAY);

                break;

            case R.id.tvWaitDone://等待完成

                setOrderStatus(SpValue.ORDER_WAIT_DONE);

                break;

            case R.id.tvDone://已完成

                setOrderStatus(SpValue.ORDER_DONE);

                break;

            case R.id.tvCancled://已取消

                setOrderStatus(SpValue.ORDER_CANCLE);

                break;

            case R.id.tvWaitSure://待确认

                setOrderStatus(SpValue.ORDER_WAIT_SURE);

                break;

        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setOrderInfo(List<OrderEntry.DataBean> orderEntryList, String currentType, boolean refush) {

        swipView.setRefreshing(false);

        if (refush)
            this.orderEntryList.clear();

        this.orderEntryList.addAll(orderEntryList);

        if (orderListAdapter != null)
            orderListAdapter.notifyDataSetChanged();

    }

    //订单状态
    public void setOrderStatus(String status) {

        tvAll.setTextColor(getResources().getColor(R.color.txt_color_ma3));
        tvWaitPay.setTextColor(getResources().getColor(R.color.txt_color_ma3));
        tvWaitDone.setTextColor(getResources().getColor(R.color.txt_color_ma3));
        tvDone.setTextColor(getResources().getColor(R.color.txt_color_ma3));
        tvWaitSure.setTextColor(getResources().getColor(R.color.txt_color_ma3));
        tvCancled.setTextColor(getResources().getColor(R.color.txt_color_ma3));

        switch (status) {

            case SpValue.ORDER_ALL://全部
                tvAll.setTextColor(getResources().getColor(R.color.tab_colorf9));
                iMyRegisterP.setStatus(SpValue.ORDER_ALL);
                break;

            case SpValue.ORDER_WAIT_PAY://待付款
                tvWaitPay.setTextColor(getResources().getColor(R.color.tab_colorf9));
                iMyRegisterP.setStatus(SpValue.ORDER_WAIT_PAY);
                break;

            case SpValue.ORDER_WAIT_SURE://待确认
                tvWaitSure.setTextColor(getResources().getColor(R.color.tab_colorf9));
                iMyRegisterP.setStatus(SpValue.ORDER_WAIT_SURE);
                break;

            case SpValue.ORDER_WAIT_DONE://待完成
                tvWaitDone.setTextColor(getResources().getColor(R.color.tab_colorf9));
                iMyRegisterP.setStatus(SpValue.ORDER_WAIT_DONE);
                break;

            case SpValue.ORDER_DONE://已完成
                tvDone.setTextColor(getResources().getColor(R.color.tab_colorf9));
                iMyRegisterP.setStatus(SpValue.ORDER_DONE);
                break;

            case SpValue.ORDER_CANCLE://已取消
                tvCancled.setTextColor(getResources().getColor(R.color.tab_colorf9));
                iMyRegisterP.setStatus(SpValue.ORDER_CANCLE);
                break;

            default://全部
                tvAll.setTextColor(getResources().getColor(R.color.tab_colorf9));
                iMyRegisterP.setStatus(SpValue.ORDER_ALL);
                break;
        }

        iMyRegisterP.myInquiry(currentType, true);

    }

    public void setType(String type) {

        switch (type) {

            case SpValue.ASK_TYPE_PIC://图文

                tvPic.setBackgroundResource(R.drawable.shape_left_circle_blue);
                tvPic.setTextColor(getResources().getColor(R.color.white));

                tvTel.setTextColor(getResources().getColor(R.color.tab_colorf9));
                tvTel.setBackgroundResource(R.drawable.shape_middle_white);

                tvVideo.setTextColor(getResources().getColor(R.color.tab_colorf9));
                tvVideo.setBackgroundResource(R.drawable.shape_right_white_circle);

                ivWaitSure.setVisibility(View.GONE);
                tvWaitSure.setVisibility(View.GONE);

                iMyRegisterP.setType(SpValue.ASK_TYPE_PIC);

                break;

            case SpValue.ASK_TYPE_TEL://电话

                tvPic.setBackgroundResource(R.drawable.shape_left_white_circle);
                tvPic.setTextColor(getResources().getColor(R.color.tab_colorf9));

                tvTel.setTextColor(getResources().getColor(R.color.white));
                tvTel.setBackgroundResource(R.color.tab_colorf9);

                tvVideo.setTextColor(getResources().getColor(R.color.tab_colorf9));
                tvVideo.setBackgroundResource(R.drawable.shape_right_white_circle);

                ivWaitSure.setVisibility(View.VISIBLE);
                tvWaitSure.setVisibility(View.VISIBLE);

                iMyRegisterP.setType(SpValue.ASK_TYPE_TEL);

                break;

            case SpValue.ASK_TYPE_VIDEO://视频

                tvPic.setBackgroundResource(R.drawable.shape_left_white_circle);
                tvPic.setTextColor(getResources().getColor(R.color.tab_colorf9));

                tvTel.setTextColor(getResources().getColor(R.color.tab_colorf9));
                tvTel.setBackgroundResource(R.drawable.shape_middle_white);

                tvVideo.setTextColor(getResources().getColor(R.color.white));
                tvVideo.setBackgroundResource(R.drawable.shape_right_bule_circle);

                ivWaitSure.setVisibility(View.VISIBLE);
                tvWaitSure.setVisibility(View.VISIBLE);

                iMyRegisterP.setType(SpValue.ASK_TYPE_VIDEO);

                break;

        }


        if (orderListAdapter != null)
            orderListAdapter.setType(type);

        iMyRegisterP.setPage(1);
        iMyRegisterP.setStatus(SpValue.ORDER_ALL);
        iMyRegisterP.myInquiry(currentType, true);

    }

    @Override
    public void onRefresh() {

        refushCurrent();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        nextActivity(HomeActivity.class);
    }


    private IInquiryOrderPers iInquiryOrderPers = new IInquiryOrderPers(this);


    //删除订单
    public void deleteAction(OrderEntry.DataBean dataBean) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("您确定要删除该订单吗").setPositiveButton("删除订单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (dataBean == null) {
                    showErr("数据错误");
                    return;
                }

                //删除订单
                iInquiryOrderPers.deleteInquiryOrder((String) SaveUtils.get(MyInquiryListActivity.this, SpValue.TOKEN, "")
                        , dataBean.getOrderid());
            }
        }).setNegativeButton("取消", null).show();
    }


    //取消订单
    public void cancleAction(OrderEntry.DataBean dataBean) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("您确定要取消该订单吗").setPositiveButton("取消订单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (dataBean == null) {
                    showErr("数据错误");
                    return;
                }

                //取消订单
                iInquiryOrderPers.cancleInquiryOrder((String) SaveUtils.get(MyInquiryListActivity.this, SpValue.TOKEN, "")
                        , dataBean.getOrderid(), dataBean.getStatus());
            }
        }).setNegativeButton("暂不取消", null).show();
    }



    public void refund(OrderEntry.DataBean dataBean) {
        if (dataBean==null)
            return;

        Intent m = new Intent(this, InquiryRefundActivity.class);

        OrderInquiryDetailEntry.DataBean orderInfo = new OrderInquiryDetailEntry.DataBean();
        orderInfo.setDoctorimgurl(dataBean.getDoctorimgurl());
        orderInfo.setDepartname(dataBean.getDoctorname());
        orderInfo.setDepartname(dataBean.getDepartname());
        orderInfo.setTitlename(dataBean.getTitlename());
        orderInfo.setHospitalname(dataBean.getHospitalname());
        orderInfo.setAdeptdesc(dataBean.getAdeptdesc());
        orderInfo.setPrice(dataBean.getPrice());
        orderInfo.setOrderid(dataBean.getOrderid());
        orderInfo.setStatus(dataBean.getStatus());

        m.putExtra("orderInfo", orderInfo);
        startActivityForResult(m, 0x0023);
    }

    @Override
    public void cancleSuccess() {
        refushCurrent();
    }

    @Override
    public void deleteSuccess() {
        refushCurrent();

    }

    //刷新当前状态
    private void refushCurrent() {
        iMyRegisterP.setPage(1);
        iMyRegisterP.myInquiry(currentType, true);
    }
}
