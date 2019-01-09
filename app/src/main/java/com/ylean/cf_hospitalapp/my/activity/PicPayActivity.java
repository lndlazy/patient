package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.BuyServiceAct;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.my.bean.OrderInquiryDetailEntry;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import io.reactivex.disposables.Disposable;

/**
 * 图文支付页面
 * Created by linaidao on 2019/1/8.
 */

public class PicPayActivity extends BaseActivity implements View.OnClickListener {

    private android.widget.ImageView i1;
    private android.widget.TextView tvStatus;
    private com.facebook.drawee.view.SimpleDraweeView sdvImg;
    private android.widget.TextView tvName;
    private android.widget.TextView tvDepartment;
    private android.widget.TextView tvHospitalName;
    private android.widget.TextView tvIntroduce;
    //    private android.widget.RelativeLayout rlPatient;
    private android.widget.TextView tvType;
    private android.widget.TextView tvCopy;
    private android.widget.RelativeLayout rlNum;
    private android.widget.TextView tvTime;
    private android.widget.RelativeLayout rlTime;
    private android.widget.TextView tvcode;
    private android.widget.TextView tvpaystatus;
    private android.widget.TextView tvprice;
    private android.widget.TextView tvMoney;
    private android.widget.LinearLayout llCancleTime;
    private android.widget.TextView tvcancle;
    private android.widget.TextView tvNext;
    private android.widget.LinearLayout rlBottom;
    private String id;
    private String type;
    private OrderInquiryDetailEntry.DataBean orderInfo;
    private LinearLayout llneedpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_pic_pay);

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        initView();
        orderDetail();
        if (SpValue.ASK_TYPE_PIC.equals(type)) {
            //图文问诊
            rlTime.setVisibility(View.GONE);
        }

    }

    private void initView() {
        llneedpay = findViewById(R.id.llneedpay);
        this.rlBottom = (LinearLayout) findViewById(R.id.rlBottom);
        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.tvcancle = (TextView) findViewById(R.id.tvcancle);
        this.llCancleTime = (LinearLayout) findViewById(R.id.llCancleTime);
        this.tvMoney = (TextView) findViewById(R.id.tvMoney);
        this.tvprice = (TextView) findViewById(R.id.tvprice);
        this.tvpaystatus = (TextView) findViewById(R.id.tvpaystatus);
        this.tvcode = (TextView) findViewById(R.id.tvcode);
        this.rlTime = (RelativeLayout) findViewById(R.id.rlTime);
        this.tvTime = (TextView) findViewById(R.id.tvTime);
        this.rlNum = (RelativeLayout) findViewById(R.id.rlNum);
        this.tvCopy = (TextView) findViewById(R.id.tvCopy);
        this.tvType = (TextView) findViewById(R.id.tvType);
//        this.rlPatient = (RelativeLayout) findViewById(R.id.rlPatient);
        this.tvIntroduce = (TextView) findViewById(R.id.tvIntroduce);
        this.tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
        this.tvDepartment = (TextView) findViewById(R.id.tvDepartment);
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.sdvImg = (SimpleDraweeView) findViewById(R.id.sdvImg);
        this.tvStatus = (TextView) findViewById(R.id.tvStatus);
        this.i1 = (ImageView) findViewById(R.id.i1);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvNext.setOnClickListener(this);
        tvcancle.setOnClickListener(this);
    }

    private void orderDetail() {

        RetrofitHttpUtil.getInstance()
                .askOrderDetail(new BaseNoTObserver<OrderInquiryDetailEntry>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);

                        showLoading("加载中...");
                    }

                    @Override
                    public void onHandleSuccess(OrderInquiryDetailEntry picAskDetailEntry) {

                        hideLoading();
                        if (picAskDetailEntry == null || picAskDetailEntry.getData() == null)
                            return;

                        setinfo(picAskDetailEntry.getData());

                    }

                    @Override
                    public void onHandleError(String message) {
                        hideLoading();
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), id);

    }

    private void setinfo(OrderInquiryDetailEntry.DataBean data) {

        orderInfo = data;
        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getDoctorimgurl()));
        tvName.setText(data.getDoctorname());
        tvDepartment.setText(data.getDepartname() + "   " + data.getTitlename());
        tvHospitalName.setText(data.getHospitalname());
        tvIntroduce.setText(data.getAdeptdesc());
        tvTime.setText(data.getAsktime());//预约时间
        tvprice.setText("¥" + data.getPrice());
        tvCopy.setText("¥" + data.getPrice());
        tvcode.setText(data.getCode());
        llneedpay.setVisibility(View.GONE);
        llCancleTime.setVisibility(View.GONE);
        tvMoney.setText(data.getCancletime());//取消时间

        switch (data.getStatus()) {

            case SpValue.ASK_STATUS_WAIT_PAY://待付款

                tvcancle.setText("取消订单");
                tvNext.setText("去支付");
                rlBottom.setVisibility(View.VISIBLE);
                tvStatus.setText("待支付");
                tvpaystatus.setText("待支付");
                llneedpay.setVisibility(View.VISIBLE);

                break;

            case SpValue.ASK_STATUS_OVER_TIME://已过期

                rlBottom.setVisibility(View.INVISIBLE);
                tvStatus.setText("已过期");
                tvpaystatus.setText("已过期");

                break;

            case SpValue.ASK_STATUS_WAIT_SURE://待确认
                rlBottom.setVisibility(View.INVISIBLE);
                tvStatus.setText("待确认");
                tvpaystatus.setText("待确认");

                break;

            case SpValue.ASK_STATUS_WAIT_COMPLY://待完成
                rlBottom.setVisibility(View.INVISIBLE);
                tvStatus.setText("待完成");
                tvpaystatus.setText("待完成");

                break;

            case SpValue.ASK_STATUS_COMPLY://已完成

                tvcancle.setVisibility(View.INVISIBLE);
                tvNext.setText("去评价");
                rlBottom.setVisibility(View.VISIBLE);
                tvStatus.setText("已完成");
                tvpaystatus.setText("已完成");

                break;

            case SpValue.ASK_STATUS_CANCLED://已取消
                rlBottom.setVisibility(View.INVISIBLE);
                tvStatus.setText("已取消");
                tvpaystatus.setText("已取消");
                llCancleTime.setVisibility(View.VISIBLE);

                break;

            case SpValue.ASK_STATUS_REFUND://申请退款中
                rlBottom.setVisibility(View.INVISIBLE);
                tvStatus.setText("申请退款中");
                tvpaystatus.setText("申请退款中");

                break;

            case SpValue.ASK_STATUS_REFUND_OK://已退款
                rlBottom.setVisibility(View.INVISIBLE);
                tvStatus.setText("已退款");

                break;

            case SpValue.ASK_STATUS_REFUND_FAIL://退款不通过
                rlBottom.setVisibility(View.INVISIBLE);
                tvStatus.setText("退款不通过");
                tvpaystatus.setText("退款不通过");

                break;

        }

//        问诊方式 1-图文 2-电话 3-视频
        switch (data.getAsktype()) {

            case SpValue.ASK_TYPE_PIC:
                rlTime.setVisibility(View.GONE);
                tvType.setText("图文问诊");
                break;

            case SpValue.ASK_TYPE_TEL:
                rlTime.setVisibility(View.VISIBLE);
                tvType.setText("电话问诊");

                break;

            case SpValue.ASK_TYPE_VIDEO:
                rlTime.setVisibility(View.VISIBLE);
                tvType.setText("视频问诊");

                break;

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvNext://

                switch (orderInfo.getStatus()) {

                    case SpValue.ASK_STATUS_WAIT_PAY://未支付 去支付

                        Intent m = new Intent(this, BuyServiceAct.class);
                        m.putExtra("orderNum", orderInfo.getCode());
                        m.putExtra("doctorId", orderInfo.getDoctorid());
                        m.putExtra("doctorName", orderInfo.getDoctorname());
                        m.putExtra("type", "图片问诊");
                        m.putExtra("price", orderInfo.getPrice());

                        startActivity(m);

                        break;

                    case SpValue.ASK_STATUS_COMPLY://已完成去评价


                        break;

                }

                break;

            case R.id.tvcancle:
                //取消订单


                break;

        }
    }
}
