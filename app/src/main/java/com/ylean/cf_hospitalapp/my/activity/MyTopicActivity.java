package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 我的话题
 * Created by linaidao on 2019/1/8.
 */

public class MyTopicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_topic);


        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
