package com.ylean.cf_hospitalapp.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;

/**
 * Created by linaidao on 2019/1/4.
 */

public class DemoActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.act_aaaa);

        TextView tv = findViewById(R.id.tv);

        tv.setText("12345" + "\r\n" + "上山打老虎");

    }
}
