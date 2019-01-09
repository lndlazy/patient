package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 修改昵称
 * Created by linaidao on 2019/1/10.
 */

public class ChangeNicknameActivity extends BaseActivity {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.EditText etnickname;
    private android.widget.TextView tvsubmit;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.act_change_nickname);

        nickname = getIntent().getStringExtra("nickname");
        initView();
    }

    private void initView() {
        this.tvsubmit = (TextView) findViewById(R.id.tv_submit);
        this.etnickname = (EditText) findViewById(R.id.etnickname);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etnickname.setText(nickname);

        tvsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(etnickname.getText().toString())) {
                    showErr("请输入昵称");
                    return;
                }
                Intent d = new Intent();
                d.putExtra("nickname", etnickname.getText().toString());
                setResult(0x90, d);
                finish();

            }
        });

    }


}
