package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;

/**
 * 赚取积分
 * Created by linaidao on 2019/1/3.
 */

public class EarnPointsActivity extends BaseActivity implements View.OnClickListener {

    private android.support.v7.widget.RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_earn_points);

        initView();

    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        TextView ivRight = (TextView) findViewById(R.id.ivRight);
        ImageView ivLeft = (ImageView) findViewById(R.id.ivLeft);

        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivLeft:
                finish();
                break;

            case R.id.ivRight://积分规则

                nextActivity(PointsRuleActivity.class);
                break;


        }

    }
}
