package com.ylean.cf_hospitalapp.register.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.register.PayStatus;
import com.ylean.cf_hospitalapp.register.bean.OrderInfoEntry;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.IDateUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import io.reactivex.disposables.Disposable;

/**
 * 预约挂号详情页面
 * Created by linaidao on 2019/1/5.
 */

public class RegisterPayActivity extends BaseActivity implements View.OnClickListener {

    private android.widget.TextView tvName;
    private android.widget.TextView tvIdCard;
    private android.widget.TextView tvAge;
    private android.widget.RelativeLayout rlPatient;
    private android.widget.TextView tvHospitalName;
    private android.widget.TextView tvDepartmentName;
    private android.widget.TextView tvDoctorName;
    private android.widget.TextView tvMoney;
    private android.widget.TextView tvDate;
    private android.widget.TextView tvOrderNum;
    private android.widget.TextView tvOrderStatus;
    private TextView tvNum;
    private TextView tvStatus;
    private String id;
    private RelativeLayout rlNum;
    private TextView tvNext;
    private OrderInfoEntry.DataBean mOrderInfo;
    private RelativeLayout rlBottom;
    private TextView tvcancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_register_pay);

        id = getIntent().getStringExtra("id");
        initView();

        orderInfo();
    }

    private void orderInfo() {

        RetrofitHttpUtil
                .getInstance()
                .orderInfo(
                        new BaseNoTObserver<OrderInfoEntry>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                showLoading("获取订单详情中...");
                            }

                            @Override
                            public void onHandleSuccess(OrderInfoEntry orderInfoEntry) {

                                hideLoading();

                                if (orderInfoEntry == null || orderInfoEntry.getData() == null) {
                                    showErr("数据错误");
                                    return;
                                }

                                OrderInfoEntry.DataBean orderInfo = orderInfoEntry.getData();
                                mOrderInfo = orderInfo;
                                setBaseInfo(orderInfo);

                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                                hideLoading();
                            }

                        }, SpValue.CH
                        , (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , id);

    }

    private void setBaseInfo(OrderInfoEntry.DataBean orderInfo) {
        tvName.setText(orderInfo.getFolkname());
        tvIdCard.setText("身份证   " + orderInfo.getIdcard());

        try {
            tvAge.setText(IDateUtils.getAge(IDateUtils.parse(orderInfo.getBirthdate()))
                    + "岁    " + (SpValue.SEX_FEMALE.equals(orderInfo.getSex()) ? "女 " : "男  ")
                    + "医保  " + orderInfo.getMedicalcard());
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvNum.setText(orderInfo.getCode());//挂号号码
        tvHospitalName.setText(orderInfo.getHospitalname());
        tvDepartmentName.setText(orderInfo.getDepartname());

        switch (mOrderInfo.getAppointtype()) {

            case "0"://普通医生门诊
                tvDoctorName.setText(orderInfo.getDoctorname() + "  " + orderInfo.getDoctitle() + " " + orderInfo.getDocdocteachname());
                break;

            case "1":
                tvDoctorName.setText("普通门诊 " + orderInfo.getDoctorname() + "  " + orderInfo.getDoctitle() + " " + orderInfo.getDocdocteachname());
                break;

            case "2":
                tvDoctorName.setText("专家门诊 " + orderInfo.getDoctorname() + "  " + orderInfo.getDoctitle() + " " + orderInfo.getDocdocteachname());
                break;
        }

        tvMoney.setText(orderInfo.getPrice() + "元");
        tvDate.setText(orderInfo.getAppointtime());

        tvOrderNum.setText(orderInfo.getCode());//订单号

        switch (orderInfo.getStatus()) {

            case PayStatus.STATUS_WAIT_PAY://待支付

                tvStatus.setText("待支付");
                tvOrderStatus.setText("待支付");
                rlNum.setVisibility(View.GONE);
                tvNext.setVisibility(View.VISIBLE);
                tvNext.setText("去支付");
                tvcancle.setVisibility(View.VISIBLE);
                rlBottom.setVisibility(View.VISIBLE);

                break;

            case PayStatus.STATUS_WAIT_USE://待使用

                tvStatus.setText("待使用");
                tvOrderStatus.setText("待使用");
                rlNum.setVisibility(View.VISIBLE);
                tvNext.setVisibility(View.VISIBLE);
                tvNext.setText("申请退款");
                tvcancle.setVisibility(View.INVISIBLE);
                rlBottom.setVisibility(View.VISIBLE);
                break;

            case PayStatus.STATUS_USED://已使用

                tvStatus.setText("已使用");
                tvOrderStatus.setText("已使用");
                rlNum.setVisibility(View.VISIBLE);
                tvNext.setVisibility(View.INVISIBLE);

                break;

            case PayStatus.STATUS_REFUND://申请退款中

                tvStatus.setText("申请退款中");
                tvOrderStatus.setText("申请退款中");
                rlNum.setVisibility(View.VISIBLE);
                rlBottom.setVisibility(View.INVISIBLE);

                break;

            case PayStatus.STATUS_REFUNDED://已退款

                tvStatus.setText("已退款");
                tvOrderStatus.setText("已退款");
                rlNum.setVisibility(View.VISIBLE);
                rlBottom.setVisibility(View.INVISIBLE);

                break;

            case PayStatus.STATUS_REFUND_REFUSED://退款不通过

                tvStatus.setText("退款不通过");
                tvOrderStatus.setText("退款不通过");
                rlNum.setVisibility(View.VISIBLE);
                rlBottom.setVisibility(View.INVISIBLE);

                break;

            case PayStatus.STATUS_CANCLED://已取消
                tvStatus.setText("已取消");
                tvOrderStatus.setText("已取消");
                rlNum.setVisibility(View.GONE);
                rlBottom.setVisibility(View.INVISIBLE);

                break;
        }


    }


    private void initView() {

        rlNum = findViewById(R.id.rlNum);
        tvNum = findViewById(R.id.tvNum);
        TextView tvCopy = findViewById(R.id.tvCopy);

        rlBottom = findViewById(R.id.rlBottom);
        tvcancle = findViewById(R.id.tvcancle);
        this.tvOrderStatus = (TextView) findViewById(R.id.tvOrderStatus);
        this.tvOrderNum = (TextView) findViewById(R.id.tvOrderNum);
        this.tvDate = (TextView) findViewById(R.id.tvDate);
        this.tvMoney = (TextView) findViewById(R.id.tvMoney);
        this.tvDoctorName = (TextView) findViewById(R.id.tvDoctorName);
        this.tvDepartmentName = (TextView) findViewById(R.id.tvDepartmentName);
        this.tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
        this.rlPatient = (RelativeLayout) findViewById(R.id.rlPatient);
        this.tvAge = (TextView) findViewById(R.id.tvAge);
        this.tvIdCard = (TextView) findViewById(R.id.tvIdCard);
        this.tvName = (TextView) findViewById(R.id.tvName);
        tvStatus = findViewById(R.id.tvStatus);
        tvNext = findViewById(R.id.tvNext);

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCopy.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        tvcancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvCopy://复制

                if (!TextUtils.isEmpty(tvNum.getText().toString()))
                    CommonUtils.copy2clipboard(this, tvNum.getText().toString());

                break;

            case R.id.tvNext://下一步

                if (mOrderInfo == null) {
                    showErr("数据错误");
                    return;
                }

                switch (mOrderInfo.getStatus()) {

                    case PayStatus.STATUS_WAIT_PAY://去支付

                        Intent m = new Intent(this, PayRegiserActivity.class);
                        m.putExtra("orderNum", mOrderInfo.getCode());

//                        mOrderInfo.get

                        m.putExtra("doctorId", mOrderInfo.getDoctorid());
                        m.putExtra("doctorName", mOrderInfo.getDoctorname());
                        m.putExtra("type", "问诊订单");
                        m.putExtra("price", mOrderInfo.getPrice());
                        startActivity(m);

                        break;

                    case PayStatus.STATUS_WAIT_USE://申请退款

                        Intent n = new Intent(this, PayBackActivity.class);
                        n.putExtra("type", "挂号订单");
                        n.putExtra("mOrderInfo", mOrderInfo);
                        startActivity(n);

                        break;

                }

                break;

            case R.id.tvcancle://取消订单

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

                builder.setTitle("提示").setMessage("您确定要取消订单吗").setPositiveButton("取消订单", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cancleOrder();
                    }
                }).show();

                break;
        }
    }

    //取消订单
    private void cancleOrder() {

        RetrofitHttpUtil
                .getInstance()
                .cancleOrder(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                showErr("取消成功");
                                finish();
                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , id, PayStatus.STATUS_WAIT_PAY, "取消订单");

    }

}
