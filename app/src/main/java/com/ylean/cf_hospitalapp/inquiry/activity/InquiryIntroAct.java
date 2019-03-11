package com.ylean.cf_hospitalapp.inquiry.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.inquiry.bean.InquiryIntrBean;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 问诊小结
 * Created by linaidao on 2019/1/9.
 */

public class InquiryIntroAct extends BaseActivity {

    //    private PicAskDetailEntry.DataBean detailInfo;
    private TitleBackBarView tbv;
    private com.facebook.drawee.view.SimpleDraweeView sdvPic;
    private TextView hpn;
    private TextView desc;
    private TextView tv1;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvDepartment;
    private TextView diagnosis;
    private TextView suggest;
    private com.facebook.drawee.view.SimpleDraweeView dpic;
    private TextView dn;
    private TextView adesc;
    private TextView date;

    private String consultaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_inquiry_summary);

//        detailInfo = getIntent().getParcelableExtra("detailInfo");
        consultaid = getIntent().getStringExtra("consultaid");
        initView();

        //查看问诊小结
        inquirySummaryReview();

    }

    private void inquirySummaryReview() {

        RetrofitHttpUtil.getInstance().inquirySummaryReview(
                new BaseNoTObserver<InquiryIntrBean>() {
                    @Override
                    public void onHandleSuccess(InquiryIntrBean basebean) {

                        if (basebean == null || basebean.getData() == null) {
                            showErr("数据错误");
                            return;
                        }

                        setInfo(basebean.getData());

                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                , consultaid);
    }


    private void setInfo(InquiryIntrBean.DataBean data) {

        sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getHosimg()));
        hpn.setText(data.getHospitalname());
        tvName.setText(data.getFlokname() + "       身份证号        " + data.getIdcard());
        tvAge.setText(data.getAge() + "岁        " + ("1".equals(data.getSex()) ? "男       医保" : "女      医保") + data.getMedicalcard());
        tvDepartment.setText("科别：" + data.getDepartname());
        diagnosis.setText(data.getProblem());//初步诊断
        suggest.setText(data.getSuggest());//问诊建议
        dpic.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getDocimg()));//医生头像
        dn.setText(data.getDoctorname());//医生姓名
        adesc.setText(data.getDepartname() + " " + data.getDoctitlename());//医生介绍
        date.setText(data.getSummarydate());//日期
    }

    private void initView() {

        this.date = (TextView) findViewById(R.id.date);
        this.adesc = (TextView) findViewById(R.id.adesc);
        this.dn = (TextView) findViewById(R.id.dn);
        this.dpic = (SimpleDraweeView) findViewById(R.id.dpic);
        this.suggest = (TextView) findViewById(R.id.suggest);
        this.diagnosis = (TextView) findViewById(R.id.diagnosis);
        this.tvDepartment = (TextView) findViewById(R.id.tvDepartment);
        this.tvAge = (TextView) findViewById(R.id.tvAge);
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.tv1 = (TextView) findViewById(R.id.tv1);
        this.desc = (TextView) findViewById(R.id.desc);
        this.hpn = (TextView) findViewById(R.id.hpn);
        this.sdvPic = (SimpleDraweeView) findViewById(R.id.sdvPic);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);


        suggest = findViewById(R.id.suggest);
        diagnosis = findViewById(R.id.diagnosis);
        tvDepartment = (TextView) findViewById(R.id.tvDepartment);

        this.tvAge = (TextView) findViewById(R.id.tvAge);
        this.tvName = (TextView) findViewById(R.id.tvName);

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        tvName.setText(detailInfo.getFlokname() + "         " + "身份证    " + detailInfo.getIdcard());
//        tvAge.setText(detailInfo.getAge() + "           "
//                + (SpValue.SEX_FEMALE.equals(detailInfo.getSex()) ? "女" : "男")
//                + "     " + "医保     " + detailInfo.getMedicalcard());
//        tvDepartment.setText("科别：" + detailInfo.getDepartname());


    }


}