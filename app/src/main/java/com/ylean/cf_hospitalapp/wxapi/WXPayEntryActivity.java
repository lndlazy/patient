package com.ylean.cf_hospitalapp.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.HomeActivity;
import com.ylean.cf_hospitalapp.my.activity.MyInquiryListActivity;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 支付回调页面
 * Created by linaidao on 2018/12/24.
 */

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler, View.OnClickListener {

    private IWXAPI api;
    private TextView tvDesc;
    private TextView tvStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pay_success);

        initView();

        Logger.d("微信支付回调页面 =====>>>>>>>");

        api = WXAPIFactory.createWXAPI(this, SpValue.WX_APP_ID);
        api.handleIntent(getIntent(), this);

    }

    private void initView() {

        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goback();
            }
        });

        TextView goHome = findViewById(R.id.goHome);
        TextView checkOrder = findViewById(R.id.checkOrder);
        TextView watch = findViewById(R.id.watch);

        goHome.setOnClickListener(this);
        checkOrder.setOnClickListener(this);
        watch.setOnClickListener(this);
        tvDesc = findViewById(R.id.tvDesc);
        tvStatus = findViewById(R.id.tvStatus);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goback();
    }

    /**
     * 返回到主页面
     */
    private void goback() {
        nextActivityThenKill(HomeActivity.class);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onResp(BaseResp resp) {

        Logger.d("errCode=:" + resp.errCode + ", resp type类型:" + resp.getType() + ", transaction : " + resp.transaction

                + ",resp.errStr:::" + resp.errStr + ",:" + resp.toString());

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            switch (resp.errCode) {

                case 0:
                    tvStatus.setText("支付成功");
                    break;

                default:
                    tvStatus.setText("支付失败");
                    tvDesc.setText("订单支付失败:" + resp.errStr);
                    break;

            }

        }

    }

    @Override
    public void onReq(BaseReq baseReq) {


        Logger.d("onReq====：" + baseReq.transaction + ","+ baseReq.toString());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.goHome:
                goback();
                break;

            case R.id.checkOrder://查看订单

                nextActivity(MyInquiryListActivity.class);

                break;

            case R.id.watch://查看我的问诊

                nextActivity(MyInquiryListActivity.class);

                break;

        }

    }
}