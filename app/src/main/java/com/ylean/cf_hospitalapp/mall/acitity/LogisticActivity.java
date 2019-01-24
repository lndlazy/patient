package com.ylean.cf_hospitalapp.mall.acitity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.mall.adapter.LogisticsAdapter;
import com.ylean.cf_hospitalapp.mall.bean.LogisticEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 物流
 * Created by linaidao on 2019/1/24.
 */

public class LogisticActivity extends BaseActivity {

    private String orderId;
    private List<LogisticEntry.DataBean.TracesBean> lofisitcTraces = new ArrayList<>();
    private LogisticsAdapter logisticsAdapter;
    private TextView tvcode;
    private TextView tkd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_logistic);

        orderId = getIntent().getStringExtra("orderId");
        initview();

        getLogistic();
    }

    private void getLogistic() {

        RetrofitHttpUtil.getInstance().getLogistic(
                new BaseNoTObserver<LogisticEntry>() {
                    @Override
                    public void onHandleSuccess(LogisticEntry basebean) {

                        if (basebean == null || basebean.getData() == null || basebean.getData().getTraces() == null)
                            return;

                        tkd.setText(basebean.getData().getName());
                        tvcode.setText(basebean.getData().getLogisticcode());

                        lofisitcTraces.addAll(basebean.getData().getTraces());

                        if (logisticsAdapter != null)
                            logisticsAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), orderId);

    }

    private void initview() {
        tvcode = findViewById(R.id.tvcode);
        tkd = findViewById(R.id.tkd);
        TitleBackBarView tvb = findViewById(R.id.tbv);
        tvb.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        logisticsAdapter = new LogisticsAdapter(this, lofisitcTraces);
        recyclerView.setAdapter(logisticsAdapter);
    }
}
