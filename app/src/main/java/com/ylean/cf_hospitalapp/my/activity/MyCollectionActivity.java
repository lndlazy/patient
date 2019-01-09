package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 我的收藏
 * Created by linaidao on 2019/1/8.
 */

public class MyCollectionActivity extends BaseActivity implements View.OnClickListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tv1;
    private android.widget.TextView tv2;
    private android.widget.TextView tv3;
    private android.widget.TextView tv4;
    private android.widget.TextView tv5;
    private android.support.v7.widget.RecyclerView recyclerView;
    private String type;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_collection);
        initView();

        tv1.setSelected(true);
        collectionList();
    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tv5 = (TextView) findViewById(R.id.tv5);
        this.tv4 = (TextView) findViewById(R.id.tv4);
        this.tv3 = (TextView) findViewById(R.id.tv3);
        this.tv2 = (TextView) findViewById(R.id.tv2);
        this.tv1 = (TextView) findViewById(R.id.tv1);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv1:
                setAllGray();
                tv1.setSelected(true);
                break;

            case R.id.tv2:
                setAllGray();
                tv2.setSelected(true);

                break;

            case R.id.tv3:
                setAllGray();
                tv3.setSelected(true);

                break;

            case R.id.tv4:
                setAllGray();
                tv4.setSelected(true);

                break;

            case R.id.tv5:
                setAllGray();
                tv5.setSelected(true);

                break;

        }
    }


    private void setAllGray() {

        tv1.setSelected(false);
        tv2.setSelected(false);
        tv3.setSelected(false);
        tv4.setSelected(false);
        tv5.setSelected(false);

    }

    private void collectionList() {

        RetrofitHttpUtil.getInstance()
                .collectList(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                            }

                            @Override
                            public void onHandleError(String message) {

                            }


                        }
                        , SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), type, page, SpValue.PAGE_SIZE);

    }


}
