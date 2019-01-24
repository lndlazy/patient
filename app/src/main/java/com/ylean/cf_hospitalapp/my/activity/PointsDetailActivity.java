package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.my.adapter.PointsDetailAdapter;
import com.ylean.cf_hospitalapp.my.bean.PointsDetailEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分明细
 * Created by linaidao on 2019/1/3.
 */

public class PointsDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private android.support.v7.widget.RecyclerView recyclerView;
    private int page = 1;
    private PointsDetailAdapter pointsDetailAdapter;
    private List<PointsDetailEntry.DataBean> pointsList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_points_detail);
        initView();

    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);
//        recyclerView.setAdapter();

        pointsDetailAdapter = new PointsDetailAdapter(this, pointsList);
        recyclerView.setAdapter(pointsDetailAdapter);
        pointsDetail(true);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (pointsDetailAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == pointsDetailAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
                    page++;
                    pointsDetail(false);
                }

            }

        });
    }

    private void pointsDetail(final boolean refush) {

        RetrofitHttpUtil
                .getInstance()
                .pointsHistory(
                        new BaseNoTObserver<PointsDetailEntry>() {
                            @Override
                            public void onHandleSuccess(PointsDetailEntry pointsDetailEntry) {

                                swipeRefreshLayout.setRefreshing(false);

                                if (refush)
                                    pointsList.clear();

                                pointsList.addAll(pointsDetailEntry.getData());
                                if (pointsDetailAdapter != null)
                                    pointsDetailAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                                swipeRefreshLayout.setRefreshing(false);

                            }

                        }, SpValue.CH
                        , (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , ""
                        , page
                        , SpValue.PAGE_SIZE);

    }

    @Override
    public void onRefresh() {
        page = 1;
        pointsDetail(true);
    }
}
