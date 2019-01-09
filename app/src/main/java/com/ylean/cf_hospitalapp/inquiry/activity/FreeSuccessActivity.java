package com.ylean.cf_hospitalapp.inquiry.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.HomeActivity;
import com.ylean.cf_hospitalapp.my.activity.MyFreeAskAct;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 创建免费义诊成功
 * Created by linaidao on 2018/12/21.
 */

public class FreeSuccessActivity extends BaseActivity {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView goHome;
    private android.widget.TextView watch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_success);

        this.watch = (TextView) findViewById(R.id.watch);
        this.goHome = (TextView) findViewById(R.id.goHome);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(HomeActivity.class);
            }
        });
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(HomeActivity.class);
            }
        });
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //查看我的义诊
                nextActivity(MyFreeAskAct.class);

            }
        });
    }


}
