package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.AddFamilyNumActivity;
import com.ylean.cf_hospitalapp.my.bean.FamilyDetailEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * 家人详情
 * Created by linaidao on 2019/1/8.
 */

public class FamilyDetailActivity extends BaseActivity implements View.OnClickListener {

    private android.widget.ImageView ivLeft;
    private android.widget.TextView tvtitle;
    private android.widget.TextView ivRight;
    private android.widget.TextView etName;
    private android.widget.TextView tvSex;
    private android.widget.TextView tvBirthday;
    private android.widget.TextView tvRelationship;
    private android.widget.TextView tvCity;
    private android.widget.TextView tvTel;
    private android.widget.TextView tvIdCard;
    private android.widget.TextView tvHospitalCard;
    private android.widget.TextView tvDisHistory;
    private android.widget.TextView tvAllergyHis;
    private android.widget.TextView tvSurgeryHis;
    private String id;
    private FamilyDetailEntry.DataBean mFamilyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_family_detail);
        id = getIntent().getStringExtra("id");

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        familyDetail();
    }

    private void initView() {
        this.tvSurgeryHis = (TextView) findViewById(R.id.tvSurgeryHis);
        this.tvAllergyHis = (TextView) findViewById(R.id.tvAllergyHis);
        this.tvDisHistory = (TextView) findViewById(R.id.tvDisHistory);
        this.tvHospitalCard = (TextView) findViewById(R.id.tvHospitalCard);
        this.tvIdCard = (TextView) findViewById(R.id.tvIdCard);
        this.tvTel = (TextView) findViewById(R.id.tvTel);
        this.tvCity = (TextView) findViewById(R.id.tvCity);
        this.tvRelationship = (TextView) findViewById(R.id.tvRelationship);
        this.tvBirthday = (TextView) findViewById(R.id.tvBirthday);
        this.tvSex = (TextView) findViewById(R.id.tvSex);
        this.etName = (TextView) findViewById(R.id.etName);
        this.ivRight = (TextView) findViewById(R.id.ivRight);
        this.tvtitle = (TextView) findViewById(R.id.tv_title);
        this.ivLeft = (ImageView) findViewById(R.id.ivLeft);

        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);

    }

    //家人详情
    private void familyDetail() {

        RetrofitHttpUtil.getInstance()
                .familyDetail(new BaseNoTObserver<FamilyDetailEntry>() {
                    @Override
                    public void onHandleSuccess(FamilyDetailEntry familyDetailEntry) {

                        if (familyDetailEntry == null || familyDetailEntry.getData() == null)
                            return;

                        FamilyDetailEntry.DataBean familyInfo = familyDetailEntry.getData();
                        mFamilyInfo = familyInfo;
                        etName.setText(familyInfo.getName());
                        tvSex.setText(SpValue.SEX_FEMALE.equals(familyInfo.getSex()) ? "女" : "男");
                        tvBirthday.setText(familyInfo.getBirthdate());
                        tvRelationship.setText(CommonUtils.getRelationship(familyInfo.getRelationship()));
                        tvCity.setText(familyInfo.getProvince() + familyInfo.getCity() + familyInfo.getArea());
                        tvTel.setText(familyInfo.getPhone());
                        tvIdCard.setText(familyInfo.getIDcard());
                        tvHospitalCard.setText(familyInfo.getMedicalcard());
                        tvDisHistory.setText(familyInfo.getDiseasehistory());
                        tvAllergyHis.setText(familyInfo.getAnaphylaxis());
                        tvSurgeryHis.setText(familyInfo.getOperation());
                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), id);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ivLeft:
                finish();
                break;

            case R.id.ivRight://编辑

                Intent m = new Intent(this, AddFamilyNumActivity.class);
                m.putExtra("familyNum", mFamilyInfo);
                startActivity(m);
                break;


        }
    }
}
