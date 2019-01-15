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
import com.ylean.cf_hospitalapp.doctor.adapter.VideoListAdapter;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.InquiryListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.VideoListEntry;
import com.ylean.cf_hospitalapp.doctor.presenter.IDoctorDetailPres;
import com.ylean.cf_hospitalapp.doctor.view.IDoctorDetailView;
import com.ylean.cf_hospitalapp.home.activity.VideoSpeechActivity;
import com.ylean.cf_hospitalapp.my.bean.DoctorDetailEntry;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 讲堂列表
 * Created by linaidao on 2019/1/12.
 */

public class VideoListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, IDoctorDetailView {

    private RecyclerView recyclerView;
    private List<VideoListEntry.DataBean> videoList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private VideoListAdapter videoItemAdapter;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;

    private IDoctorDetailPres iDoctorDetailPres = new IDoctorDetailPres(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_inquiry_list);

        init();
        iDoctorDetailPres.setDoctorId(getIntent().getStringExtra("doctorId"));
        iDoctorDetailPres.setToken((String) SaveUtils.get(this, SpValue.TOKEN, ""));

        iDoctorDetailPres.setVideoPage(1);
        iDoctorDetailPres.setVideoPageSize(SpValue.PAGE_SIZE);
        iDoctorDetailPres.videoHistory();
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
        tbv.setTitle("医讲堂");
        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        videoItemAdapter = new VideoListAdapter(this, videoList);
        recyclerView.setAdapter(videoItemAdapter);


        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                Intent m = new Intent(VideoListActivity.this, VideoSpeechActivity.class);
                m.putExtra("type", "3");//1直播 3视频
                m.putExtra("id", videoList.get(position).getId());
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

                if (videoItemAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == videoItemAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
//                    iDoctorDetailPres.setInquiryPage(iDoctorDetailPres.getInquiryPage() + 1);
                    iDoctorDetailPres.setVideoPage(iDoctorDetailPres.getVideoPage() + 1);
                    iDoctorDetailPres.videoHistory();
                }

            }

        });

    }

    @Override
    public void onRefresh() {
        iDoctorDetailPres.setVideoPage(1);
        iDoctorDetailPres.videoHistory();
    }


    @Override
    public void setVideoData(List<VideoListEntry.DataBean> data) {
        if (iDoctorDetailPres.getVideoPage() == 1)
            videoList.clear();

        if (data.size() == 0 && iDoctorDetailPres.getVideoPage() > 1) {
            //没有数据,页码返回
            iDoctorDetailPres.setVideoPage(iDoctorDetailPres.getVideoPage() - 1);
        }

        videoList.addAll(data);
        if (videoItemAdapter != null)
            videoItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopVideoSpeechRefush() {

        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void setInquiryData(List<InquiryListEntry.DataBean> data) {

    }

    @Override
    public void stopInquiryRefush() {

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
    public void setRegisterPrice(double price, String outdepartid) {

    }

    @Override
    public void setEvaluateData(List<CommComListEntry.DataBean> data) {

    }

}
