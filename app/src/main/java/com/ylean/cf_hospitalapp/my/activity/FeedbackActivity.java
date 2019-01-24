package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.NumFormatUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 帮助与反馈
 * Created by linaidao on 2019/1/6.
 */

public class FeedbackActivity extends BaseActivity {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.EditText tvFeedback;
    private android.widget.TextView tvSub;
    private EditText etTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_feedback);

        initView();

    }

    private void initView() {
        this.tvSub = (TextView) findViewById(R.id.tvSub);
        this.tvFeedback = (EditText) findViewById(R.id.tvFeedback);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);
        etTel = findViewById(R.id.etTel);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(tvFeedback.getText().toString())) {
                    showErr("请输入反馈内容");
                    return;
                }

                if (TextUtils.isEmpty(etTel.getText().toString())) {
                    showErr("请输入手机号");
                    return;
                }

                if (!NumFormatUtils.isMobileNum(etTel.getText().toString())) {
                    showErr("请输入正确的手机号");
                    return;
                }

                feedback();

            }
        });
    }

    private void feedback() {

        RetrofitHttpUtil
                .getInstance()
                .feedback(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                showErr("提交成功");
                                finish();
                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , tvFeedback.getText().toString(), "1", etTel.getText().toString());

    }
}
