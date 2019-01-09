//package com.ylean.cf_hospitalapp.register.activity;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.Html;
//import android.view.View;
//import android.widget.TextView;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.ylean.cf_hospitalapp.R;
//import com.ylean.cf_hospitalapp.base.bean.Basebean;
//import com.ylean.cf_hospitalapp.base.view.BaseActivity;
//import com.ylean.cf_hospitalapp.net.ApiService;
//import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
//import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
//import com.ylean.cf_hospitalapp.register.bean.HospitalInfoEntry;
//import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
//import com.ylean.cf_hospitalapp.utils.SpValue;
//import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
//
///**
// * 医院详情
// * Created by linaidao on 2019/1/4.
// */
//
//public class HospitalDetailActivity extends BaseActivity {
//
//    private android.widget.TextView tvContent;
//    private HospitalListEntry.DataBean hospitalInfo;
//    private TextView tel;
//    private TextView tvAddress;
//    private TextView tvHospitalName;
//    private SimpleDraweeView sdvPic;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        hospitalInfo = getIntent().getParcelableExtra("hospitalInfo");
//        setContentView(R.layout.act_hospital_detail);
//
//        initView();
//        hospitalDetail();
//
//    }
//
//    private void hospitalDetail() {
//
//        if (hospitalInfo == null) {
//            showErr("数据错误");
//            return;
//        }
//
//        RetrofitHttpUtil
//                .getInstance()
//                .hospitalInfo(
//
//                        new BaseNoTObserver<HospitalInfoEntry>() {
//                            @Override
//                            public void onHandleSuccess(HospitalInfoEntry hospitalInfoEntry) {
//
//                                if (hospitalInfoEntry != null && hospitalInfoEntry.getData() != null) {
//
//                                    setHospitalInfo(hospitalInfoEntry);
//                                }
//
//                            }
//
//                            @Override
//                            public void onHandleError(String message) {
//                                showErr(message);
//                            }
//
//                        }, SpValue.CH,
//                        hospitalInfo.getHospitalid());
//
//    }
//
//    private void setHospitalInfo(HospitalInfoEntry hospitalInfoEntry) {
//
//        HospitalInfoEntry.DataBean data = hospitalInfoEntry.getData();
//
//        tvHospitalName.setText(data.getHospitalname());
//        tvAddress.setText(data.getAddress());
//
//        sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getImgurl()));
//        tel.setText(data.getSupportel());
//        tvContent.setText(Html.fromHtml(data.getDescription()));
//    }
//
//    private void initView() {
//        this.tvContent = (TextView) findViewById(R.id.tvContent);
//        tel = (TextView) findViewById(R.id.tel);
//        tvAddress = (TextView) findViewById(R.id.tvAddress);
//        tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
//        sdvPic = (SimpleDraweeView) findViewById(R.id.sdvPic);
//        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
//
//        tbv.setOnLeftClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        if (hospitalInfo == null)
//            return;
//
//
//        sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + hospitalInfo.getImgurl()));
//        tvHospitalName.setText(hospitalInfo.getHospitalname());
//        tvAddress.setText(hospitalInfo.getAddress());
//        tel.setText(hospitalInfo.getSupportel());
//
//    }
//}
