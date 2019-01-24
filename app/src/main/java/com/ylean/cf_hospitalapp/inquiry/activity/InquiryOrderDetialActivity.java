package com.ylean.cf_hospitalapp.inquiry.activity;

import android.content.DialogInterface;
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
import com.ylean.cf_hospitalapp.inquiry.presenter.IInquiryOrderPers;
import com.ylean.cf_hospitalapp.inquiry.view.IInquiryOrderView;
import com.ylean.cf_hospitalapp.mall.acitity.GoodsOrderListActivity;
import com.ylean.cf_hospitalapp.my.activity.CustomerService;
import com.ylean.cf_hospitalapp.my.bean.OrderInquiryDetailEntry;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import io.reactivex.disposables.Disposable;

/**
 * 问诊订单详情页面
 * Created by linaidao on 2019/1/8.
 */

public class InquiryOrderDetialActivity extends BaseActivity implements View.OnClickListener, IInquiryOrderView {

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
    private android.widget.TextView tv1;
    private android.widget.TextView tv2;
    private android.widget.TextView tv3;
    private android.widget.LinearLayout rlBottom;
    private String id;
    private String type;
    private OrderInquiryDetailEntry.DataBean orderInfo;


    private IInquiryOrderPers iInquiryOrderPers = new IInquiryOrderPers(this);
    private RelativeLayout rlcontact;


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

        this.rlBottom = (LinearLayout) findViewById(R.id.rlBottom);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv1 = (TextView) findViewById(R.id.tv1);
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

        rlcontact = findViewById(R.id.rlcontact);


        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        rlcontact.setOnClickListener(this);
    }

    private void orderDetail() {

        RetrofitHttpUtil.getInstance().askOrderDetail(
                new BaseNoTObserver<OrderInquiryDetailEntry>() {

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

        llCancleTime.setVisibility(View.GONE);
        tvMoney.setText(data.getCancletime());//取消时间

        switch (data.getStatus()) {

            case SpValue.ASK_STATUS_WAIT_PAY://待付款

                tvStatus.setText("待支付");
                tvpaystatus.setText("待支付");

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.VISIBLE);
                tv1.setText("取消订单");
                tv3.setText("去支付");

                break;

            case SpValue.ASK_STATUS_OVER_TIME://已过期

                tvStatus.setText("已过期");
                tvpaystatus.setText("已过期");

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv1.setText("删除订单");

                i1.setImageResource(R.mipmap.ic_order_cancled);
                break;

            case SpValue.ASK_STATUS_WAIT_SURE://待确认

                tvStatus.setText("待确认");
                tvpaystatus.setText("待确认");

                rlBottom.setVisibility(View.INVISIBLE);

                break;

            case SpValue.ASK_STATUS_WAIT_COMPLY://待完成

                tvStatus.setText("待完成");
                tvpaystatus.setText("待完成");

                rlBottom.setVisibility(View.INVISIBLE);

                break;

            case SpValue.ASK_STATUS_COMPLY://已完成

                tvStatus.setText("已完成");
                tvpaystatus.setText("已完成");

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);

                tv1.setText("删除订单");
                tv2.setText("申请售后");
                tv3.setText("1".equals(data.getIscomment()) ? "已评价" : "评价");

                i1.setImageResource(R.mipmap.ic_order_finish);

                break;

            case SpValue.ASK_STATUS_CANCLED://已取消

                tvStatus.setText("已取消");
                tvpaystatus.setText("已取消");
                llCancleTime.setVisibility(View.VISIBLE);

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);

                i1.setImageResource(R.mipmap.ic_order_cancled);

                tv1.setText("删除订单");

                break;

            case SpValue.ASK_STATUS_REFUND://申请退款中

                tvStatus.setText("申请退款中");
                tvpaystatus.setText("申请退款中");

                rlBottom.setVisibility(View.INVISIBLE);

                i1.setImageResource(R.mipmap.ic_refund);

                break;

            case SpValue.ASK_STATUS_REFUND_OK://已退款

                tvStatus.setText("已退款");
                tvpaystatus.setText("已退款");

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                i1.setImageResource(R.mipmap.ic_refund);

                tv1.setText("删除订单");

                break;

            case SpValue.ASK_STATUS_REFUND_FAIL://退款不通过

                tvStatus.setText("退款不通过");
                tvpaystatus.setText("退款不通过");

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                i1.setImageResource(R.mipmap.ic_order_cancled);

                tv1.setText("删除订单");

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

            case R.id.tv1://

                switch (orderInfo.getStatus()) {

                    case SpValue.ASK_STATUS_WAIT_PAY://未支付
                        //取消订单
                        cancleAction();
                        break;

                    case SpValue.ASK_STATUS_OVER_TIME://已过期
                    case SpValue.ASK_STATUS_COMPLY://已完成
                    case SpValue.ASK_STATUS_CANCLED://已取消
                    case SpValue.ASK_STATUS_REFUND_OK://已退款
                    case SpValue.ASK_STATUS_REFUND_FAIL://退款不通过

                        //删除订单
                        deleteAction();
                        break;

                }

                break;

            case R.id.tv2:

                switch (orderInfo.getStatus()) {

                    case SpValue.ASK_STATUS_COMPLY://已完成
                        // 申请售后
                        Intent m = new Intent(this, InquiryRefundActivity.class);
                        m.putExtra("orderInfo", orderInfo);
                        startActivityForResult(m, 0x0023);
                        break;

                }

                break;
            case R.id.tv3:

                switch (orderInfo.getStatus()) {

                    case SpValue.ASK_STATUS_WAIT_PAY://未支付
                        //去支付
                        go2pay();
                        break;

                    case SpValue.ASK_STATUS_COMPLY://已完成去评价

                        // 评价
                        Intent m = new Intent(this, InquiryEvaulateActivity.class);
                        m.putExtra("orderInfo", orderInfo);
                        startActivity(m);

                        break;

                }

                break;


            case R.id.rlcontact:
                //联系客服
                Intent s = new Intent(this, CustomerService.class);
                startActivity(s);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0x0023 && resultCode == 0x0024) {
            //申请退款成功
            finish();
        }
    }

    //删除订单
    private void deleteAction() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("您确定要删除该订单吗").setPositiveButton("删除订单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (orderInfo == null) {
                    showErr("数据错误");
                    return;
                }

                //删除订单
                iInquiryOrderPers.deleteInquiryOrder((String) SaveUtils.get(InquiryOrderDetialActivity.this, SpValue.TOKEN, "")
                        , orderInfo.getOrderid());
            }
        }).setNegativeButton("取消", null).show();
    }

    //取消订单
    private void cancleAction() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("您确定要取消该订单吗").setPositiveButton("取消订单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (orderInfo == null) {
                    showErr("数据错误");
                    return;
                }

                //取消订单
                iInquiryOrderPers.cancleInquiryOrder((String) SaveUtils.get(InquiryOrderDetialActivity.this, SpValue.TOKEN, "")
                        , orderInfo.getOrderid(), orderInfo.getStatus());
            }
        }).setNegativeButton("暂不取消", null).show();
    }

    private void go2pay() {
        Intent m = new Intent(this, BuyServiceAct.class);
        m.putExtra("orderNum", orderInfo.getCode());
        m.putExtra("doctorId", orderInfo.getDoctorid());
        m.putExtra("doctorName", orderInfo.getDoctorname());
        m.putExtra("type", "图片问诊");
        m.putExtra("price", orderInfo.getPrice());
        startActivity(m);
    }

    //取消订单成功
    @Override
    public void cancleSuccess() {
        showErr("取消订单成功");
        finish();
    }

    @Override
    public void deleteSuccess() {
        showErr("删除订单成功");
        finish();
    }
}
