package com.ylean.cf_hospitalapp.doctor.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.Presenter.ICollectionPres;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.ICollectionView;
import com.ylean.cf_hospitalapp.doctor.adapter.EvaluateAdapter;
import com.ylean.cf_hospitalapp.doctor.adapter.InquiryItemAdapter;
import com.ylean.cf_hospitalapp.doctor.adapter.VideoListAdapter;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.InquiryListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.VideoListEntry;
import com.ylean.cf_hospitalapp.home.activity.VideoSpeechActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryDetailAct;
import com.ylean.cf_hospitalapp.inquiry.activity.PayTWActivity;
import com.ylean.cf_hospitalapp.my.bean.DoctorDetailEntry;
import com.ylean.cf_hospitalapp.doctor.presenter.IDoctorDetailPres;
import com.ylean.cf_hospitalapp.doctor.view.IDoctorDetailView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.List;

/**
 * 医生详情页面
 * Created by linaidao on 2019/1/10.
 */

public class DoctorDetailActivity extends BaseActivity implements IDoctorDetailView, View.OnClickListener, ICollectionView {

    private android.widget.TextView tvPicPrice;
    private android.widget.TextView tvpicinquiry;
    private android.widget.TextView tvTelPrice;
    private android.widget.TextView tvtelinquiry;
    private android.widget.TextView tvvideoprice;
    private android.widget.TextView tvvideoinquiry;
    private android.widget.TextView tvregisterprice;
    private android.widget.TextView tvregister;
    private android.widget.RelativeLayout rlmoreregister;
    private android.support.v7.widget.RecyclerView inquiryRecyclerview;
    private android.widget.RelativeLayout rlmorevideo;
    private android.support.v7.widget.RecyclerView videoRecyclerview;
    private android.support.v7.widget.RecyclerView commentRecyclerview;

    private TextView tvName;
    private TextView tvDepartment;
    private TextView tvHospitalName;
    private TextView tvIntroduce;
    private TextView tvAttention;
    private SimpleDraweeView sdvImg;
    private TextView tvhospitaldesc;
    private TextView tvdoctordesc;
    private LinearLayout llpic;
    private LinearLayout lltel;
    private LinearLayout llvideo;
    private LinearLayout llregister;

    //医生详情
    private DoctorDetailEntry.DataBean doctorInfo;
    //医生详情presenter
    private IDoctorDetailPres iDoctorDetailPres = new IDoctorDetailPres(this);
    //关注，取消关注 病友
    private ICollectionPres iCollectionPres = new ICollectionPres(this);

    private EvaluateAdapter evaluateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_doctor_detail);

        initView();

        iDoctorDetailPres.setDoctorId(getIntent().getStringExtra("doctorId"));
        iDoctorDetailPres.setToken((String) SaveUtils.get(this, SpValue.TOKEN, ""));

        //医生详情
        iDoctorDetailPres.doctorDetail();

        //服务项目
        iDoctorDetailPres.serviceInfo();

        //问诊记录
        iDoctorDetailPres.setInquiryPage(1);
        iDoctorDetailPres.setInquiryPageSize("2");
        iDoctorDetailPres.inquiryHistory();

        //医讲堂记录
        iDoctorDetailPres.setVideoPage(1);
        iDoctorDetailPres.setVideoPageSize("2");
        iDoctorDetailPres.videoHistory();

        //医生评价列表
        iDoctorDetailPres.doctorDetailCommentList();

    }

    private void initView() {
        this.commentRecyclerview = (RecyclerView) findViewById(R.id.commentRecyclerview);
        this.videoRecyclerview = (RecyclerView) findViewById(R.id.videoRecyclerview);
        this.rlmorevideo = (RelativeLayout) findViewById(R.id.rlmorevideo);
        this.inquiryRecyclerview = (RecyclerView) findViewById(R.id.inquiryRecyclerview);
        this.rlmoreregister = (RelativeLayout) findViewById(R.id.rlmoreregister);
        this.tvregister = (TextView) findViewById(R.id.tvregister);
        this.tvregisterprice = (TextView) findViewById(R.id.tvregisterprice);
        this.tvvideoinquiry = (TextView) findViewById(R.id.tvvideoinquiry);
        this.tvvideoprice = (TextView) findViewById(R.id.tvvideoprice);
        this.tvtelinquiry = (TextView) findViewById(R.id.tvtelinquiry);
        this.tvTelPrice = (TextView) findViewById(R.id.tvTelPrice);
        this.tvpicinquiry = (TextView) findViewById(R.id.tvpicinquiry);
        this.tvPicPrice = (TextView) findViewById(R.id.tvPicPrice);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        llpic = findViewById(R.id.llpic);
        lltel = findViewById(R.id.lltel);
        llvideo = findViewById(R.id.llvideo);
        llregister = findViewById(R.id.llregister);

        llpic.setVisibility(View.GONE);
        lltel.setVisibility(View.GONE);
        llvideo.setVisibility(View.GONE);
        llregister.setVisibility(View.GONE);

        sdvImg = findViewById(R.id.sdvImg);

        tvName = findViewById(R.id.tvName);
        tvdoctordesc = findViewById(R.id.tvdoctordesc);
        tvDepartment = findViewById(R.id.tvDepartment);
        tvHospitalName = findViewById(R.id.tvHospitalName);
        tvIntroduce = findViewById(R.id.tvIntroduce);
        tvAttention = findViewById(R.id.tvAttention);
        tvhospitaldesc = findViewById(R.id.tvhospitaldesc);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvpicinquiry.setOnClickListener(this);
        tvtelinquiry.setOnClickListener(this);
        tvvideoinquiry.setOnClickListener(this);
        tvregister.setOnClickListener(this);
        rlmoreregister.setOnClickListener(this);
        tvAttention.setOnClickListener(this);
        rlmorevideo.setOnClickListener(this);

        inuqiryRecyclerView();
        inintVideoRecyclerview();
        initEvaluateRecyclerview();

    }

    private void initEvaluateRecyclerview() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commentRecyclerview.setLayoutManager(linearLayoutManager);
        commentRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        commentRecyclerview.addItemDecoration(divider);

        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        commentRecyclerview.setHasFixedSize(true);
        commentRecyclerview.setNestedScrollingEnabled(false);

    }

    private void inintVideoRecyclerview() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        videoRecyclerview.setLayoutManager(linearLayoutManager);
        videoRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        videoRecyclerview.addItemDecoration(divider);

        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        videoRecyclerview.setHasFixedSize(true);
        videoRecyclerview.setNestedScrollingEnabled(false);

    }

    private void inuqiryRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        inquiryRecyclerview.setLayoutManager(linearLayoutManager);
        inquiryRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        inquiryRecyclerview.addItemDecoration(divider);

        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        inquiryRecyclerview.setHasFixedSize(true);
        inquiryRecyclerview.setNestedScrollingEnabled(false);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvpicinquiry://图文问诊

                if (doctorInfo == null) {
                    showErr("数据异常");
                    return;
                }

                Intent m = new Intent(this, PayTWActivity.class);
                m.putExtra("doctorId", doctorInfo.getDoctorid());
                m.putExtra("doctorName", doctorInfo.getDoctorname());
                m.putExtra("type", SpValue.ASK_TYPE_PIC);
                m.putExtra("price", picPrice);
                m.putExtra("askType", SpValue.ASK_CHARGE);
                startActivity(m);

                break;

            case R.id.tvtelinquiry://电话问诊
                Intent n = new Intent(this, PayTWActivity.class);
                n.putExtra("doctorId", doctorInfo.getDoctorid());
                n.putExtra("doctorName", doctorInfo.getDoctorname());
                n.putExtra("type", SpValue.ASK_TYPE_TEL);
                n.putExtra("price", telPrice);
                n.putExtra("askType", SpValue.ASK_CHARGE);
                startActivity(n);

                break;

            case R.id.tvvideoinquiry://视频问诊
                Intent intent = new Intent(this, PayTWActivity.class);
                intent.putExtra("doctorId", doctorInfo.getDoctorid());
                intent.putExtra("doctorName", doctorInfo.getDoctorname());
                intent.putExtra("type", SpValue.ASK_TYPE_VIDEO);
                intent.putExtra("price", videoPrice);
                intent.putExtra("askType", SpValue.ASK_CHARGE);
                startActivity(intent);

                break;

            case R.id.tvregister://挂号 TODO

//                Intent rIntent = new Intent(this, ChooseNumActivity.class);
//                rIntent.putExtra("departid", doctorInfo.getDepartid());//科室id
//                rIntent.putExtra("registerId", "");//门诊id
//                rIntent.putExtra("registerName", "");//门诊名称
//                rIntent.putExtra("departname", doctorInfo.getDepartname());//
//
//                HospitalListEntry.DataBean hospitalInfo = new HospitalListEntry.DataBean();
//
//                rIntent.putExtra("hospitalInfo", "");
//                startActivity(rIntent);

//                departid = getIntent().getStringExtra("departid");//部门id
//                registerId = getIntent().getStringExtra("registerId");//门诊id
//                registerName = getIntent().getStringExtra("registerName");//门诊名称
//                departname = getIntent().getStringExtra("departname");//部门名称
//                hospitalInfo = getIntent().getParcelableExtra("hospitalInfo");//医院id跟医院昵称

                break;

            case R.id.rlmoreregister://更多问诊记录

                if (doctorInfo == null) {
                    showErr("数据错误");
                    return;
                }

                Intent inquiryIntent = new Intent(this, InquiryListActivity.class);
                inquiryIntent.putExtra("doctorId", doctorInfo.getDoctorid());
                startActivity(inquiryIntent);

                break;

            case R.id.rlmorevideo://更多医讲堂

                if (doctorInfo == null) {
                    showErr("数据错误");
                    return;
                }

                Intent videoIntent = new Intent(this, VideoListActivity.class);
                videoIntent.putExtra("doctorId", doctorInfo.getDoctorid());
                startActivity(videoIntent);

                break;

            case R.id.tvAttention://关注
                iDoctorDetailPres.clickAttention();
                break;
        }
    }

    //设置医生数据
    @Override
    public void setDoctorInfo(DoctorDetailEntry.DataBean data) {

        doctorInfo = data;
        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getImgurl()));
        tvName.setText(data.getDoctorname());
        tvDepartment.setText(data.getDepartname() + "   " + data.getDoctortitle());
        tvHospitalName.setText(data.getHospiatlname());
        tvIntroduce.setText(data.getAcademicdesc());
        tvdoctordesc.setText(data.getAdeptdesc());
        tvhospitaldesc.setText(Html.fromHtml(data.getHosdesc()));

        //是否关注
        tvAttention.setText("1".equals(data.getIscollect()) ? "已关注" : "未关注");

    }

    //设置问诊数据
    @Override
    public void setInquiryData(List<InquiryListEntry.DataBean> data) {

        InquiryItemAdapter inquiryItemAdapter = new InquiryItemAdapter(this, data);
        inquiryRecyclerview.setAdapter(inquiryItemAdapter);
        inquiryRecyclerview.addOnItemTouchListener(new OnItemClickListener(inquiryRecyclerview) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                Intent m = new Intent(DoctorDetailActivity.this, InquiryDetailAct.class);
                m.putExtra("noedit", true);//不能回复，定时刷新
                m.putExtra("consultaid", data.get(position).getConsultaid());
                startActivity(m);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    //设置医讲堂数据
    @Override
    public void setVideoData(List<VideoListEntry.DataBean> data) {

        VideoListAdapter videoListAdapter = new VideoListAdapter(this, data);
        videoRecyclerview.setAdapter(videoListAdapter);

        videoRecyclerview.addOnItemTouchListener(new OnItemClickListener(videoRecyclerview) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                Intent m = new Intent(DoctorDetailActivity.this, VideoSpeechActivity.class);
                m.putExtra("type", "3");//1直播 3视频
                m.putExtra("id", data.get(position).getId());
                startActivity(m);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    //设置评价列表数据
    @Override
    public void setEvaluateData(List<CommComListEntry.DataBean> data) {

        evaluateAdapter = new EvaluateAdapter(this, data);
        commentRecyclerview.setAdapter(evaluateAdapter);

    }

    @Override
    public void stopInquiryRefush() {

    }

    @Override
    public void stopVideoSpeechRefush() {

    }

    //取消关注医生
    @Override
    public void removeAttention(String doctorid) {
        iCollectionPres.setId(doctorid);

        iCollectionPres.removeCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "5");
    }

    //关注医生
    @Override
    public void addAttention(String doctorid) {
        iCollectionPres.setId(doctorid);

        iCollectionPres.addCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "5");
    }

    private double picPrice;
    private double telPrice;
    private double videoPrice;
    private double registerPrice;

    @Override
    public void setPicPrice(double price) {
        llpic.setVisibility(View.VISIBLE);
        picPrice = price;
        tvPicPrice.setText(String.valueOf(price) + "元/次");
    }

    @Override
    public void setTelPrice(double price) {
        lltel.setVisibility(View.VISIBLE);
        telPrice = price;
        tvTelPrice.setText(String.valueOf(price) + "元/次");
    }

    @Override
    public void setVideoPrice(double price) {
        videoPrice = price;
        llvideo.setVisibility(View.VISIBLE);
        tvvideoprice.setText(String.valueOf(price) + "元/次");
    }

    @Override
    public void setRegisterPrice(double price) {
        registerPrice = price;
        llregister.setVisibility(View.VISIBLE);
        tvregisterprice.setText(String.valueOf(price) + "元/次");
    }

    private CommComListEntry.DataBean currentPersion;

    //点击关注、取消关注 评论人
    public void personAttention(CommComListEntry.DataBean dataBean) {
        //收藏(关注)类型  1-直播 2-资讯 3-讲堂 4-帖子 5-医生6-病友 7-文章
        currentPersion = dataBean;

        iCollectionPres.setId(dataBean.getEvaluateid());

        if ("1".equals(dataBean.getIsfollow()))
            //已经关注， 取消关注
            iCollectionPres.removeCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "6");
        else
            //未关注，点击关注
            iCollectionPres.addCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "6");

    }

    //关注 成功
    @Override
    public void collectionSuccess(String type) {

        if ("5".equals(type)) {
            //医生
            iDoctorDetailPres.getDoctorInfo().setIscollect("1");
            tvAttention.setText("已关注");
        } else if ("6".equals(type)) {
            //病友
            currentPersion.setIsfollow("1");
            showErr("关注成功");
            if (evaluateAdapter != null)
                evaluateAdapter.notifyDataSetChanged();

        }

    }

    //取消关注 成功
    @Override
    public void removeCollectionSuccess(String type) {

        if ("5".equals(type)) {
            //医生
            iDoctorDetailPres.getDoctorInfo().setIscollect("0");
            tvAttention.setText("未关注");
        } else if ("6".equals(type)) {
            //病友
            currentPersion.setIsfollow("0");
            showErr("取消关注成功");
            if (evaluateAdapter != null)
                evaluateAdapter.notifyDataSetChanged();
        }

    }
}
