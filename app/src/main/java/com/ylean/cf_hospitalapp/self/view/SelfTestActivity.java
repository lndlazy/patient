package com.ylean.cf_hospitalapp.self.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 症状自测
 * Created by linaidao on 2018/12/21.
 */

public class SelfTestActivity extends BaseActivity {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.support.v7.widget.RecyclerView readRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_self_test);


        this.readRecyclerView = (RecyclerView) findViewById(R.id.readRecyclerView);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);


        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
