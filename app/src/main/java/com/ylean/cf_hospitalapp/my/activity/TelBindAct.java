package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 手机号
 * Created by linaidao on 2019/1/2.
 */

public class TelBindAct extends BaseActivity implements View.OnClickListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tvTel;
    private android.widget.RelativeLayout rlTel;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_tel_bind);
        tel = getIntent().getStringExtra("tel");
        initView();

    }

    private void initView() {

        this.rlTel = (RelativeLayout) findViewById(R.id.rlTel);
        this.tvTel = (TextView) findViewById(R.id.tvTel);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tvTel.setText(tel);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlTel.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {//更换手机号

            case R.id.rlTel:

                nextActivity(ChangeTelActivity.class);

                break;

        }

    }
}
