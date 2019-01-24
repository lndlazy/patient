package com.ylean.cf_hospitalapp.inquiry.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 问诊小结
 * Created by linaidao on 2019/1/9.
 */

public class InquiryIntroAct extends BaseActivity implements View.OnClickListener {

    private android.widget.TextView tvName;
    private android.widget.TextView tvAge;
    private android.widget.RelativeLayout rlInfo;
    private android.widget.TextView tvDepartment;
    private android.widget.EditText etDiagnosis;
    private android.widget.EditText etSuggest;
//    private android.widget.TextView sub;


    private PicAskDetailEntry.DataBean detailInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_inquiry_summary);

        detailInfo = getIntent().getParcelableExtra("detailInfo");
        initView();

        //查看问诊小结
        inquirySummaryReview();

    }

    private void inquirySummaryReview() {

        if (detailInfo == null)
            return;

        RetrofitHttpUtil
                .getInstance()
                .inquirySummaryReview(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {


                            }

                            @Override
                            public void onHandleError(String message) {

                                showErr(message);
                            }

                        }
                        , SpValue.CH
                        , (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , detailInfo.getConsultaid());
    }

    private void initView() {
//        this.sub = (TextView) findViewById(R.id.sub);
        this.etSuggest = (EditText) findViewById(R.id.etSuggest);
        this.etDiagnosis = (EditText) findViewById(R.id.etDiagnosis);
        this.tvDepartment = (TextView) findViewById(R.id.tvDepartment);
        this.rlInfo = (RelativeLayout) findViewById(R.id.rlInfo);
        this.tvAge = (TextView) findViewById(R.id.tvAge);
        this.tvName = (TextView) findViewById(R.id.tvName);

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvName.setText(detailInfo.getFlokname() + "         " + "身份证    " + detailInfo.getIdcard());
        tvAge.setText(detailInfo.getAge() + "           "
                + (SpValue.SEX_FEMALE.equals(detailInfo.getSex()) ? "女" : "男")
                + "     " + "医保     " + detailInfo.getMedicalcard());
        tvDepartment.setText("科别：" + detailInfo.getDepartname());


        rlInfo.setOnClickListener(this);
//        sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rlInfo:

                if (detailInfo == null) {
                    showErr("数据异常");
                    return;
                }


                break;

        }

    }


}