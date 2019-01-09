package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

public class ModifySexActivity extends BaseActivity implements View.OnClickListener {

    protected TextView tvright;
    private ImageView ivmale;
    private ImageView ivfemal;
    private String currentSex;

    private static final int SEX_RESULT_CODE = 0x0112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modify_sex);
        currentSex = getIntent().getStringExtra("sex");
        initWidget();
    }

    private void initWidget() {

        RelativeLayout rlfemal = (RelativeLayout) findViewById(R.id.rl_femal);
        this.ivfemal = (ImageView) findViewById(R.id.iv_femal);
        RelativeLayout rlmeal = (RelativeLayout) findViewById(R.id.rl_meal);
        this.ivmale = (ImageView) findViewById(R.id.iv_male);
        this.tvright = (TextView) findViewById(R.id.tv_right);
        ImageView ivleft = (ImageView) findViewById(R.id.iv_left);

        rlfemal.setOnClickListener(this);
        rlmeal.setOnClickListener(this);
        ivleft.setOnClickListener(this);
        tvright.setOnClickListener(this);

//        currentSex = (String) SaveUtils.get(this, SpValue.SEX, SpValue.SEX_MALE);
        if (SpValue.SEX_FEMALE.equals(currentSex)) {
            //女
            ivmale.setVisibility(View.INVISIBLE);
            ivfemal.setVisibility(View.VISIBLE);
            currentSex = SpValue.SEX_FEMALE;
        } else {
            //男
            ivmale.setVisibility(View.VISIBLE);
            ivfemal.setVisibility(View.INVISIBLE);
            currentSex = SpValue.SEX_MALE;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_femal://女
                ivmale.setVisibility(View.INVISIBLE);
                ivfemal.setVisibility(View.VISIBLE);
                currentSex = SpValue.SEX_FEMALE;
                break;

            case R.id.rl_meal://男
                ivmale.setVisibility(View.VISIBLE);
                ivfemal.setVisibility(View.INVISIBLE);
                currentSex = SpValue.SEX_MALE;
                break;

            case R.id.iv_left:
                finish();
                break;

            case R.id.tv_right://完成

                Intent data = new Intent();
                data.putExtra("modifySex", currentSex);
                setResult(SEX_RESULT_CODE, data);
                finish();

                break;

        }
    }

}
