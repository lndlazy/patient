package com.ylean.cf_hospitalapp.mall.acitity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alipay.sdk.app.AuthTask;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.AlipayEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.WxPayInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.GoodsInfoEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.utils.alipay.OrderInfoUtil2_0;
import com.ylean.cf_hospitalapp.utils.alipay.PayResult;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.wxapi.PayResultActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 商品支付页面
 * Created by linaidao on 2019/1/21.
 */

public class GoodsPayActivity extends BaseActivity implements View.OnClickListener {

    private GoodsInfoEntry.DataBean goodsInfo;
    private double freightPrice;
    private String orderCode;
    private ImageView ivWeixin;
    private ImageView ivAlipay;
    private IWXAPI wxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_goods_pay);

        //商品信息
        goodsInfo = getIntent().getParcelableExtra("goodsInfo");
        //订单号
        orderCode = getIntent().getStringExtra("orderCode");
        //运费
        freightPrice = getIntent().getDoubleExtra("freightPrice", -1.00d);

        if (goodsInfo == null) {
            showErr("订单信息错误");
            finish();
        }

        init();

    }

    private void init() {

        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvordernum = findViewById(R.id.tvordernum);

        RelativeLayout rlWeixin = findViewById(R.id.rlWeixin);
        RelativeLayout rlAlipay = findViewById(R.id.rlAlipay);

        ivWeixin = findViewById(R.id.ivWeixin);
        ivAlipay = findViewById(R.id.ivAlipay);

        TextView tvPay = findViewById(R.id.tvPay);

        String priceInfo = "";
        switch (goodsInfo.getType()) {

            case "1"://1-实物商品

                if (freightPrice == 0)
                    priceInfo = goodsInfo.getPrice() + "";
                else
                    priceInfo = (goodsInfo.getPrice() + freightPrice) + "";

                break;

            case "2"://2-服务商品
                priceInfo = goodsInfo.getPrice() + "";
                break;
        }

        String content = "<font color=\"#333333\">" + "支付金额：¥" + "</font>"
                + "<font color=\"#000018\" size=\"18\">" + priceInfo + "</font>";
        tvPrice.setText(Html.fromHtml(content));

        tvordernum.setText("订单编号：" + orderCode);

        rlWeixin.setOnClickListener(this);
        rlAlipay.setOnClickListener(this);
        tvPay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rlWeixin:

                ivWeixin.setSelected(true);
                ivAlipay.setSelected(false);

                break;

            case R.id.rlAlipay:

                ivWeixin.setSelected(false);
                ivAlipay.setSelected(true);

                break;

            case R.id.tvPay:

                if (ivWeixin.isSelected()) {
                    weixinPay();
                } else if (ivAlipay.isSelected()) {
                    aliPay();
                } else {
                    showErr("请选择付款方式");
                    return;
                }

                break;

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

        RetrofitHttpUtil.getInstance().wxGoodsPayInfo(
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
                , (String) SaveUtils.get(this, SpValue.TOKEN, "")
                , SpValue.CH, orderCode);
    }

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


    private void aliPay() {

        RetrofitHttpUtil.getInstance().aliPayGoodsInfo(
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
                                alipayEntry.getData().getAppid(), "商品订单"
                                , goodsInfo.getPrice(), "好医无忧商品订单", orderCode, alipayEntry.getData().getNotifyurl(), alipayEntry.getData().getSellerid());
                        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

//                                String privateKey = privatekey;
                        String sign = OrderInfoUtil2_0.getSign(params, privatekey, true);
                        final String orderInfo = orderParam + "&" + sign;

//                        Logger.d("params:::" + params);
//                        Logger.d("sign:::" + sign);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                // 构造AuthTask 对象
                                AuthTask authTask = new AuthTask(GoodsPayActivity.this);
                                // 调用授权接口，获取授权结果
                                Map<String, String> result = authTask.authV2(orderInfo, true);

                                if (result == null)
                                    return;

                                PayResult payResult = new PayResult(result);

                                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                                String resultStatus = payResult.getResultStatus();

                                Intent m = new Intent(GoodsPayActivity.this, PayResultActivity.class);
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

                }, (String) SaveUtils.get(this, SpValue.TOKEN, ""), SpValue.CH, orderCode);
    }

}
