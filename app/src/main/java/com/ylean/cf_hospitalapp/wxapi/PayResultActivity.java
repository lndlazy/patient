package com.ylean.cf_hospitalapp.wxapi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.activity.HomeActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * Created by linaidao on 2019/3/1.
 */

public class PayResultActivity extends BaseActivity {

    private boolean pay_success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.act_pay_resutl);


        pay_success = getIntent().getBooleanExtra("pay_success", false);

        initView();

    }

    private void initView() {

        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tvStatus = findViewById(R.id.tvStatus);
        TextView tvDesc = findViewById(R.id.tvDesc);
        ImageView ivpay = findViewById(R.id.ivpay);


        TextView goHome = findViewById(R.id.goHome);


        tvStatus.setText(pay_success ? "支付成功" : "支付失败");
        tvDesc.setText(pay_success ? "订单已经支付成功" : "订单支付失败");
        ivpay.setImageResource(pay_success ? R.mipmap.ic_sun_success : R.mipmap.ic_pay_fail);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(HomeActivity.class);
            }
        });

    }
}
