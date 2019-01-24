package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.my.adapter.DoctorListAdapter;
import com.ylean.cf_hospitalapp.my.bean.MyDoctorListEntry;
import com.ylean.cf_hospitalapp.my.presenter.IDoctorListPres;
import com.ylean.cf_hospitalapp.my.view.IDoctorListView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的医生
 * Created by linaidao on 2019/1/6.
 */

public class DoctorListActivity extends BaseActivity implements View.OnClickListener, IDoctorListView, SwipeRefreshLayout.OnRefreshListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tvAdvisory;
    private android.widget.TextView tvAttent;
    private android.support.v7.widget.RecyclerView recyclerView;
    private DoctorListAdapter doctorListAdapter;
    private List<MyDoctorListEntry.DataBean> myDoctorList = new ArrayList<>();
    private IDoctorListPres iDoctorListPres = new IDoctorListPres(this);
    private SwipeRefreshLayout swipeRefreshLayout;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;
    private int currentType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_doctor_list);

        initView();

        currentType = 1;
        iDoctorListPres.getDoctorList(currentType, (String) SaveUtils.get(this, SpValue.TOKEN, ""), true);

    }

    private void initView() {

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tvAttent = (TextView) findViewById(R.id.tvAttent);
        this.tvAdvisory = (TextView) findViewById(R.id.tvAdvisory);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvAdvisory.setOnClickListener(this);
        tvAttent.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        doctorListAdapter = new DoctorListAdapter(this, myDoctorList, iDoctorListPres);
        recyclerView.setAdapter(doctorListAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (doctorListAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == doctorListAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
                    //加载更多
                    iDoctorListPres.setPage(iDoctorListPres.getPage() + 1);
                    iDoctorListPres.getDoctorList(currentType, (String) SaveUtils.get(DoctorListActivity.this, SpValue.TOKEN, ""), false);

                }

            }

        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvAdvisory://我咨询的

                currentType = 1;
                iDoctorListPres.setPage(1);
                iDoctorListPres.getDoctorList(currentType, (String) SaveUtils.get(this, SpValue.TOKEN, ""), true);

                tvAdvisory.setBackgroundResource(R.drawable.shape_left_circle_blue);
                tvAdvisory.setTextColor(getResources().getColor(R.color.white));

                tvAttent.setTextColor(getResources().getColor(R.color.tab_colorf9));
                tvAttent.setBackgroundResource(R.drawable.shape_right_white_circle);

                break;

            case R.id.tvAttent://我关注的
                currentType = 2;
                iDoctorListPres.setPage(1);
                iDoctorListPres.getDoctorList(currentType, (String) SaveUtils.get(this, SpValue.TOKEN, ""), true);

                tvAdvisory.setBackgroundResource(R.drawable.shape_left_white_circle);
                tvAdvisory.setTextColor(getResources().getColor(R.color.tab_colorf9));

                tvAttent.setTextColor(getResources().getColor(R.color.white));
                tvAttent.setBackgroundResource(R.drawable.shape_right_bule_circle);

                break;

        }

    }

    @Override
    public void onRefresh() {

        iDoctorListPres.setPage(1);
        iDoctorListPres.getDoctorList(currentType, (String) SaveUtils.get(this, SpValue.TOKEN, ""), true);

    }

    @Override
    public void setDoctorListInfo(List<MyDoctorListEntry.DataBean> data, boolean isRefush) {

        swipeRefreshLayout.setRefreshing(false);

        if (isRefush)
            myDoctorList.clear();

        myDoctorList.addAll(data);
        if (doctorListAdapter != null)
            doctorListAdapter.notifyDataSetChanged();

    }
}
