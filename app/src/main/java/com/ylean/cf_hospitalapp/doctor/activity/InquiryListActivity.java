package com.ylean.cf_hospitalapp.doctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.doctor.adapter.InquiryItemAdapter;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.InquiryListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.VideoListEntry;
import com.ylean.cf_hospitalapp.doctor.presenter.IDoctorDetailPres;
import com.ylean.cf_hospitalapp.doctor.view.IDoctorDetailView;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryDetailAct;
import com.ylean.cf_hospitalapp.my.bean.DoctorDetailEntry;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多问诊记录
 * Created by linaidao on 2019/1/11.
 */

public class InquiryListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, IDoctorDetailView {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<InquiryListEntry.DataBean> inquiryList = new ArrayList<>();
    private InquiryItemAdapter inquiryItemAdapter;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;

    private IDoctorDetailPres iDoctorDetailPres = new IDoctorDetailPres(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_inquiry_list);

        init();
        iDoctorDetailPres.setDoctorId(getIntent().getStringExtra("doctorId"));
        iDoctorDetailPres.setInquiryPage(1);
        iDoctorDetailPres.setInquiryPageSize(SpValue.PAGE_SIZE);
        iDoctorDetailPres.setToken((String) SaveUtils.get(this, SpValue.TOKEN, ""));
        iDoctorDetailPres.inquiryHistory();
    }

    private void init() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        inquiryItemAdapter = new InquiryItemAdapter(this, inquiryList);
        recyclerView.setAdapter(inquiryItemAdapter);


        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                Intent m = new Intent(InquiryListActivity.this, InquiryDetailAct.class);
                m.putExtra("noedit", true);//不能回复，定时刷新
                m.putExtra("consultaid", inquiryList.get(position).getConsultaid());
                startActivity(m);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (inquiryItemAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == inquiryItemAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {

                    iDoctorDetailPres.setInquiryPage(iDoctorDetailPres.getInquiryPage() + 1);
                    iDoctorDetailPres.inquiryHistory();
                }

            }

        });

    }

    @Override
    public void onRefresh() {

        iDoctorDetailPres.setInquiryPage(1);
        iDoctorDetailPres.inquiryHistory();

    }


    @Override
    public void setInquiryData(List<InquiryListEntry.DataBean> data) {

        if (iDoctorDetailPres.getInquiryPage() == 1)
            inquiryList.clear();

        if (data.size() == 0 && iDoctorDetailPres.getInquiryPage() > 1) {
            //没有数据,页码返回
            iDoctorDetailPres.setInquiryPage(iDoctorDetailPres.getInquiryPage() - 1);
        }

        inquiryList.addAll(data);
        if (inquiryItemAdapter != null)
            inquiryItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void stopInquiryRefush() {

        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void setDoctorInfo(DoctorDetailEntry.DataBean data) {

    }

    @Override
    public void removeAttention(String doctorid) {

    }

    @Override
    public void addAttention(String doctorid) {

    }

    @Override
    public void setPicPrice(double price) {

    }

    @Override
    public void setTelPrice(double price) {

    }

    @Override
    public void setVideoPrice(double price) {

    }

    @Override
    public void setRegisterPrice(double price) {

    }


    @Override
    public void setVideoData(List<VideoListEntry.DataBean> data) {

    }

    @Override
    public void setEvaluateData(List<CommComListEntry.DataBean> data) {

    }


    @Override
    public void stopVideoSpeechRefush() {

    }
}
