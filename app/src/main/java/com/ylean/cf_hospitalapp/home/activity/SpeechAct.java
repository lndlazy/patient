package com.ylean.cf_hospitalapp.home.activity;

import android.os.Bundle;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 精彩讲堂
 * Created by linaidao on 2019/1/1.
 */

public class SpeechAct extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_speech);

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

    }
}
