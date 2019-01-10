package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.my.presenter.IDoctorDetailPres;
import com.ylean.cf_hospitalapp.my.view.IDoctorDetailView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 医生详情页面
 * Created by linaidao on 2019/1/10.
 */

public class DoctorDetailActivity extends BaseActivity implements IDoctorDetailView {

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

    private IDoctorDetailPres iDoctorDetailPres = new IDoctorDetailPres(this);
    private String doctorId;
    private TextView tvName;
    private TextView tvDepartment;
    private TextView tvHospitalName;
    private TextView tvIntroduce;
    private TextView tvAttention;
    private SimpleDraweeView sdvImg;
    private TextView tvhospitaldesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.act_doctor_detail);

        doctorId = getIntent().getStringExtra("doctorId");
        initView();

        iDoctorDetailPres.setDoctorId(doctorId);
        iDoctorDetailPres.doctorDetail((String) SaveUtils.get(this, SpValue.TOKEN, ""));
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

        sdvImg = findViewById(R.id.sdvImg);

        tvName = findViewById(R.id.tvName);
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
    }
}
