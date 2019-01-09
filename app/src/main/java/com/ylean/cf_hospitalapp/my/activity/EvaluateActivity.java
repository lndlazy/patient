package com.ylean.cf_hospitalapp.my.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.adapter.PicGridAdapter;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.my.presenter.IEvaluatePres;
import com.ylean.cf_hospitalapp.my.view.IEvaluateView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.widget.MyRatingBar;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 评价页面
 */
public class EvaluateActivity extends BaseActivity implements IEvaluateView {

    IEvaluatePres iEvaluatePres = new IEvaluatePres(this);

    private PicAskDetailEntry.DataBean inquiryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_argement);


        String consultaid = getIntent().getStringExtra("consultaid");
        inquiryInfo = getIntent().getParcelableExtra("inquiryInfo");
        initWidget();


    }


    private void initWidget() {
        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView picRecyclerView = findViewById(R.id.picRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        picRecyclerView.setLayoutManager(layoutManager);
        picRecyclerView.setItemAnimator(new DefaultItemAnimator());
        PicGridAdapter picGridAdapter = new PicGridAdapter(this);
        picRecyclerView.setAdapter(picGridAdapter);

        SimpleDraweeView sdvImg = findViewById(R.id.sdvImg);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvJob = findViewById(R.id.tvJob);
        TextView tvCompany = findViewById(R.id.tvCompany);
        TextView tvInfo = findViewById(R.id.tvInfo);
        TextView tvType = findViewById(R.id.tvType);
        TextView tvPirce = findViewById(R.id.tvPirce);
        MyRatingBar rb1 = findViewById(R.id.rb1);
        MyRatingBar rb2 = findViewById(R.id.rb2);
        MyRatingBar rb3 = findViewById(R.id.rb3);
        EditText etConent = findViewById(R.id.etConent);
        TextView tvCommit = findViewById(R.id.tvCommit);


        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + inquiryInfo.getDocimg()));
        tvName.setText(inquiryInfo.getDoctorname());
        tvJob.setText(inquiryInfo.getDepartname() + "   " + inquiryInfo.getDoctitlename());
//        tvCompany.setText(inquiryInfo.get);


    }
}
