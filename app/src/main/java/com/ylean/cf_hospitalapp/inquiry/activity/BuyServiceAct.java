package com.ylean.cf_hospitalapp.inquiry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alipay.sdk.app.AuthTask;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.AlipayEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.WxPayInfoEntry;
import com.ylean.cf_hospitalapp.mall.acitity.GoodsPayActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.utils.alipay.OrderInfoUtil2_0;
import com.ylean.cf_hospitalapp.utils.alipay.PayResult;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.wxapi.PayResultActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 购买问诊服务
 * Created by linaidao on 2018/12/18.
 */

public class BuyServiceAct extends BaseActivity implements View.OnClickListener {

    private String orderNum;
    private double price;
    private android.widget.TextView tvOrderNum;
    private android.widget.TextView tvStyle;
    private android.widget.TextView tvName;
    private android.widget.TextView tvPriceTimes;
    private android.widget.ImageView ivWeixin;
    private TextView tvPayPrice;

    private android.widget.ImageView ivAlipay;
    private String doctorName;
    private String doctorId;
    private String type;
    private TextView tvSureOrder;
    private IWXAPI wxapi;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pay_service);

        orderNum = getIntent().getStringExtra("orderNum");
        doctorId = getIntent().getStringExtra("doctorId");
        doctorName = getIntent().getStringExtra("doctorName");
        type = getIntent().getStringExtra("type");//图片问诊， 视频问诊，挂号
        price = getIntent().getDoubleExtra("price", 0.00);

//        Logger.d("订单号::" + orderNum);

        initWidget();
        initData();

    }

    private void initData() {

        tvOrderNum.setText(orderNum);

        tvStyle.setText(type);
        tvName.setText(doctorName);

        tvPriceTimes.setText(CommonUtils.getNum2(price) + "元/次");
        tvPayPrice.setText(CommonUtils.getNum2(price) + "元");

    }

    private void initWidget() {
        RelativeLayout rlAlipay = (RelativeLayout) findViewById(R.id.rlAlipay);
        this.ivAlipay = (ImageView) findViewById(R.id.ivAlipay);
        this.tvPayPrice = findViewById(R.id.tvPayPrice);

        RelativeLayout rlWeixin = (RelativeLayout) findViewById(R.id.rlWeixin);
        this.ivWeixin = (ImageView) findViewById(R.id.ivWeixin);

//        this.times = (TextView) findViewById(R.id.times);
        this.tvPriceTimes = (TextView) findViewById(R.id.tvPriceTimes);
        this.tvName = (TextView) findViewById(R.id.tvName);
        tvSureOrder = findViewById(R.id.tvSureOrder);
        this.tvStyle = (TextView) findViewById(R.id.tvStyle);
        this.tvOrderNum = (TextView) findViewById(R.id.tvOrderNum);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlAlipay.setOnClickListener(this);
        rlWeixin.setOnClickListener(this);
        tvSureOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rlAlipay:

                ivAlipay.setSelected(true);
                ivWeixin.setSelected(false);
                break;

            case R.id.rlWeixin:
                ivAlipay.setSelected(false);
                ivWeixin.setSelected(true);
                break;

            case R.id.tvSureOrder:

                sureOrder();

                break;
        }


    }

    private void sureOrder() {

        if (ivAlipay.isSelected()) {
            aliPay();
        } else if (ivWeixin.isSelected()) {
            weixinPay();
        } else {
            showErr("请选择支付方式");
        }

    }

    private void weixinPay() {
        wxapi = WXAPIFactory.createWXAPI(this, "");
        // 将该app注册到微信
        wxapi.registerApp(SpValue.WX_APP_ID);
        // 判断是否安装客户端
        if (!wxapi.isWXAppInstalled()) {
            showErr("请您先安装微信客户端！");
            return;
        }

        RetrofitHttpUtil.getInstance().wxPayInfo(
                new BaseNoTObserver<WxPayInfoEntry>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);

                        showLoading("获取中...");
                    }

                    @Override
                    public void onHandleSuccess(WxPayInfoEntry wxPayInfoEntry) {

                        hideLoading();
                        wxPay(wxPayInfoEntry);

                    }

                    @Override
                    public void onHandleError(String message) {

                        hideLoading();
                        showErr(message);
                    }

                }
                , (String) SaveUtils.get(this, SpValue.TOKEN, ""), SpValue.CH, orderNum);
    }

    private void aliPay() {

//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        RetrofitHttpUtil .getInstance()  .aliPayInfo(
                        new BaseNoTObserver<AlipayEntry>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                            }

                            @Override
                            public void onHandleSuccess(AlipayEntry alipayEntry) {

                                if (alipayEntry == null || alipayEntry.getData() == null) {
                                    showErr("数据错误");
                                    return;
                                }

                                String privatekey = alipayEntry.getData().getPrivatekey();
//                                Logger.d("私钥::" + privatekey);
                                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(
                                        alipayEntry.getData().getAppid(), "问诊服务"
                                        , price, "好医无忧问诊", orderNum, alipayEntry.getData().getNotifyurl(), alipayEntry.getData().getSellerid());
                                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

//                                String privateKey = privatekey;
                                String sign = OrderInfoUtil2_0.getSign(params, privatekey, true);
                                final String orderInfo = orderParam + "&" + sign;

//                                Logger.d("params:::" + params);
//                                Logger.d("sign:::" + sign);

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        // 构造AuthTask 对象
                                        AuthTask authTask = new AuthTask(BuyServiceAct.this);
                                        // 调用授权接口，获取授权结果
                                        Map<String, String> result = authTask.authV2(orderInfo, true);

                                        if (result == null)
                                            return;

                                        PayResult payResult = new PayResult(result);

                                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                                        String resultStatus = payResult.getResultStatus();
                                        Intent m = new Intent(BuyServiceAct.this, PayResultActivity.class);
                                        if ("9000".equals(resultStatus)) {
                                            showErr("支付成功");
                                            m.putExtra("pay_success", true);
                                        }else
                                            m.putExtra("pay_success", false);
                                        startActivity(m);

                                    }
                                }).start();

                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }
                        , (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , SpValue.CH
                        , orderNum);
    }


//    private Handler mHandler = new Handler() {
//
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SDK_PAY_FLAG: {
//
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                    /**
//                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                    String resultStatus = payResult.getResultStatus();
//                    // 判断resultStatus 为9000则代表支付成功
//
//                    Logger.d("信息::" + resultInfo + resultStatus);
//                    showErr(resultInfo + resultStatus);
//
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        showErr("成功");
//
////                        showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
////                        showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
//                    }
//                    break;
//                }
//
//                default:
//                    break;
//            }
//        }
//
//        ;
//    };

    private void wxPay(WxPayInfoEntry wxPayInfoEntry) {

        Map<String, String> parse = (Map<String, String>) JSONArray.parse(wxPayInfoEntry.getData());

        PayReq req = new PayReq();
        req.appId = SpValue.WX_APP_ID;// 微信开放平台审核通过的应用APPID
        req.partnerId = parse.get("partnerid");// 微信支付分配的商户号
        req.prepayId = parse.get("prepayid");// 预支付订单号，app服务器调用“统一下单”接口获取
        req.nonceStr = parse.get("noncestr");// 随机字符串，不长于32位
        req.timeStamp = parse.get("timestamp");// 时间戳
        req.packageValue = parse.get("package");// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
        req.sign = parse.get("sign");// 签名，


        Logger.d("请求的参数::" + SpValue.WX_APP_ID
                + "\r\n:" + parse.get("partnerid")
                + "\r\n:" + parse.get("prepayid")
                + "\r\n:" + parse.get("noncestr")
                + "\r\n:" + parse.get("prepayid")
                + "\r\n:" + parse.get("timestamp")
                + "\r\n:" + parse.get("package")
                + "\r\n:" + parse.get("sign")
        );

        wxapi.sendReq(req);
    }

//    @Override
//    public void onReq(BaseReq baseReq) {
//
//    }

//    @Override
//    public void onResp(BaseResp baseResp) {
//
//
//        Logger.d("支付信息::" + baseResp.errCode + ",," + baseResp.errStr);
////        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
////            AlertDialog.Builder builder = new AlertDialog.Builder(this);
////            builder.setTitle("支付结果");
////            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
////            builder.show();
////        }
//
//
//    }
}
