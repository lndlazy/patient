package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
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
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.my.adapter.PointEarnAdatper;
import com.ylean.cf_hospitalapp.my.bean.EarnPointsBean;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 赚取积分
 * Created by linaidao on 2019/1/3.
 */

public class EarnPointsActivity extends BaseActivity implements View.OnClickListener {

    private android.support.v7.widget.RecyclerView recyclerView;

    private List<EarnPointsBean.DataBean> pointsList = new ArrayList<>();
    private PointEarnAdatper pointEarnAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_earn_points);

        initView();

        getInfo();

    }

    private void getInfo() {

        RetrofitHttpUtil.getInstance().howGetPoints(
                new BaseNoTObserver<EarnPointsBean>() {
                    @Override
                    public void onHandleSuccess(EarnPointsBean earnPointsBean) {

                        if (earnPointsBean == null || earnPointsBean.getData() == null)
                            return;

                        pointsList.clear();
                        pointsList.addAll(earnPointsBean.getData());

                        if (pointEarnAdatper != null)
                            pointEarnAdatper.notifyDataSetChanged();

                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""));

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
        pointEarnAdatper = new PointEarnAdatper(this, pointsList);
        recyclerView.setAdapter(pointEarnAdatper);

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                if ("签到".equals(pointsList.get(position).getName())) {
                    startCheckIn();
                }

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }


    //签到
    private void startCheckIn() {

        RetrofitHttpUtil.getInstance().checkIn(
                new BaseNoTObserver<Basebean>() {
                    @Override
                    public void onHandleSuccess(Basebean basebean) {
                        getInfo();
                    }

                    @Override
                    public void onHandleError(String message) {

                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivLeft:
                finish();
                break;

            case R.id.ivRight://积分规则

                Intent m = new Intent(this, WebviewActivity.class);
                m.putExtra("title", "积分规则");
                m.putExtra("url", ApiService.WEB_ROOT + ApiService.H5_BASE_WEB + "?ch=" + SpValue.CH
                        + "&ctype=8888");
                startActivity(m);

                break;


        }

    }
}
