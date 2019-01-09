package com.ylean.cf_hospitalapp.home.activity;

import android.os.Bundle;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 精彩问诊
 * Created by linaidao on 2019/1/1.
 */

public class AnswerDetailAct extends BaseActivity {

    //问诊id
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_answer_detatil);
        //问诊id
        id = getIntent().getStringExtra("id");
        
        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
