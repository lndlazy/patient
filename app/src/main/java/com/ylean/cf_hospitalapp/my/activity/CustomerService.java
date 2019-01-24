package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 联系客服
 * Created by linaidao on 2019/1/9.
 */

public class CustomerService extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_constom_service);

        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
