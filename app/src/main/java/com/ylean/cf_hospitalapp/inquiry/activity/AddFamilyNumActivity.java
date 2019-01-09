package com.ylean.cf_hospitalapp.inquiry.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.AddressBean;
import com.ylean.cf_hospitalapp.my.activity.ModifySexActivity;
import com.ylean.cf_hospitalapp.my.activity.SelectProvinceAct;
import com.ylean.cf_hospitalapp.my.bean.FamilyDetailEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.NumFormatUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.MyDatePicker;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.disposables.Disposable;


/**
 * 添加家庭成员
 */
public class AddFamilyNumActivity extends BaseActivity implements View.OnClickListener {

    private static final int ADD_FAMILY_RESULT_CODE = 0x0116;
    private android.widget.EditText etName;
    private android.widget.TextView tvSex;
    private android.widget.TextView tvBirthday;
    private android.widget.TextView tvRelationship;
    private android.widget.TextView tvCity;
    private android.widget.EditText etTel;
    private android.widget.EditText etIdCard;
    private android.widget.EditText etHospitalCard;
    private android.widget.EditText etDisHistory;
    private android.widget.EditText etAllergyHis;
    private android.widget.EditText etSurgeryHis;

    private static final int SEX_RESULT_CODE = 0x0112;
    private static final int SEX_REQUEST_CODE = 0x0113;
    private static final int RELATIONSHIP_REQUEST_CODE = 0x0114;
    private static final int RELATIONSHIP_RESULT_CODE = 0x0115;

    private String currentSex;
    private String relationshipId;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String provinceName;
    private String cityName;
    private String areaName;
    private FamilyDetailEntry.DataBean familyNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_family_num);

        familyNum = getIntent().getParcelableExtra("familyNum");
        initView();


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        provinceCode = intent.getStringExtra("provinceCode");
        provinceName = intent.getStringExtra("provinceName");
        cityCode = intent.getStringExtra("cityCode");
        cityName = intent.getStringExtra("cityName");
        areaCode = intent.getStringExtra("areaCode");
        areaName = intent.getStringExtra("areaName");

        tvCity.setText(provinceName + cityName + areaName);

    }

    private void initView() {

        currentSex = "";
        this.etSurgeryHis = (EditText) findViewById(R.id.etSurgeryHis);
        this.etAllergyHis = (EditText) findViewById(R.id.etAllergyHis);
        this.etDisHistory = (EditText) findViewById(R.id.etDisHistory);
        this.etHospitalCard = (EditText) findViewById(R.id.etHospitalCard);
        this.etIdCard = (EditText) findViewById(R.id.etIdCard);
        this.etTel = (EditText) findViewById(R.id.etTel);
        RelativeLayout rlCity = (RelativeLayout) findViewById(R.id.rlCity);
        this.tvCity = (TextView) findViewById(R.id.tvCity);
        TextView tvSave = (TextView) findViewById(R.id.tvSave);
        RelativeLayout rlRelationship = (RelativeLayout) findViewById(R.id.rlRelationship);
        this.tvRelationship = (TextView) findViewById(R.id.tvRelationship);
        RelativeLayout rlBirthday = (RelativeLayout) findViewById(R.id.rlBirthday);
        this.tvBirthday = (TextView) findViewById(R.id.tvBirthday);
        RelativeLayout rlSex = (RelativeLayout) findViewById(R.id.rlSex);
        this.tvSex = (TextView) findViewById(R.id.tvSex);
        this.etName = (EditText) findViewById(R.id.etName);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSave.setOnClickListener(this);
        rlCity.setOnClickListener(this);
        rlSex.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        rlRelationship.setOnClickListener(this);
        rlBirthday.setOnClickListener(this);

        if (familyNum != null) {

            etName.setText(familyNum.getName());
            tvSex.setText(SpValue.SEX_FEMALE.equals(familyNum.getSex()) ? "女" : "男");
            currentSex = familyNum.getSex();

            Logger.d("性别::" + familyNum.getSex());

            tvBirthday.setText(familyNum.getBirthdate());
            tvRelationship.setText(CommonUtils.getRelationship(familyNum.getRelationship()));
            relationshipId = familyNum.getRelationship();

            tvCity.setText(familyNum.getProvince() + familyNum.getCity() + familyNum.getArea());
            etTel.setText(familyNum.getPhone());
            etIdCard.setText(familyNum.getIDcard());
            etHospitalCard.setText(familyNum.getMedicalcard());
            etDisHistory.setText(familyNum.getDiseasehistory());
            etAllergyHis.setText(familyNum.getAnaphylaxis());
            etSurgeryHis.setText(familyNum.getOperation());

            provinceCode = familyNum.getPcode();
            provinceName = familyNum.getProvince();
            cityCode = familyNum.getCcode();
            cityName = familyNum.getCity();
            areaCode = familyNum.getAcode();
            areaName = familyNum.getArea();

            tbv.setTitle("修改家人信息");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvSave://保存

                if (isDataOk()) {

                    if (familyNum == null) {
                        //添加家人
                        addMyFamily();

                    } else {
                        //修改家人信息
                        modifyFamilyNum();
                    }

                }
                break;

            case R.id.rlCity://城市选择

                nextActivity(SelectProvinceAct.class);

                break;

            case R.id.rlRelationship://关系选择
                nextActivityWithCode(RelationshipActivity.class, RELATIONSHIP_REQUEST_CODE);
                break;

            case R.id.rlBirthday://生日
                modifyBirthday();
                break;

            case R.id.rlSex://性别

                Intent m = new Intent(this, ModifySexActivity.class);
                m.putExtra("sex", currentSex);
                startActivityForResult(m, SEX_REQUEST_CODE);

                break;

        }

    }

    //添加家人
    private void addMyFamily() {

        RetrofitHttpUtil
                .getInstance()
                .addFamilyNum(
                        new BaseNoTObserver<Basebean>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);

                                showLoading("正在创建...");
                            }

                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                hideLoading();
                                showErr("创建成功");
                                setResult(ADD_FAMILY_RESULT_CODE);
                                finish();
                            }

                            @Override
                            public void onHandleError(String message) {
                                hideLoading();
                                showErr(message);
                            }

                        }, (String) SaveUtils.get(getApplicationContext(), SpValue.TOKEN, "")
                        , SpValue.CH, etName.getText().toString(), currentSex, tvBirthday.getText().toString()
                        , relationshipId, provinceCode, cityCode, areaCode, etTel.getText().toString()
                        , etIdCard.getText().toString(), etHospitalCard.getText().toString()
                        , etDisHistory.getText().toString(), etAllergyHis.getText().toString()
                        , etSurgeryHis.getText().toString(), "", provinceName
                        , cityName, areaName);

    }

    //添加家人
    private void modifyFamilyNum() {

        if (familyNum == null) {
            showErr("数据错误");
            return;
        }

        RetrofitHttpUtil.getInstance()
                .modifyFamilyNum(
                        new BaseNoTObserver<Basebean>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);

                                showLoading("正在创建...");
                            }

                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                hideLoading();
                                showErr("创建成功");
                                setResult(ADD_FAMILY_RESULT_CODE);
                                finish();
                            }

                            @Override
                            public void onHandleError(String message) {
                                hideLoading();
                                showErr(message);
                            }

                        }, (String) SaveUtils.get(getApplicationContext(), SpValue.TOKEN, "")
                        , SpValue.CH, familyNum.getId(), etName.getText().toString()
                        , currentSex, tvBirthday.getText().toString(), relationshipId
                        , provinceCode, cityCode, areaCode, etTel.getText().toString()
                        , etIdCard.getText().toString(), etHospitalCard.getText().toString()
                        , etDisHistory.getText().toString(), etAllergyHis.getText().toString()
                        , etSurgeryHis.getText().toString(), "", provinceName
                        , cityName, areaName);

    }

    /**
     * 修改生日
     */
    private void modifyBirthday() {

        Date date = new Date();
        final Calendar c = Calendar.getInstance();
        c.setTime(date);

        new MyDatePicker(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                String month = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1) + "";
                String day = dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth + "";

                tvBirthday.setText(year + "-" + month + "-" + day);

            }

        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case SEX_RESULT_CODE://性别

                if (data != null) {

                    currentSex = data.getStringExtra("modifySex");

                    Logger.d("当前选择的性别::" + currentSex);

                    if (SpValue.SEX_FEMALE.equals(currentSex)) {
                        tvSex.setText(getResources().getString(R.string.female));
                    } else {
                        tvSex.setText(getResources().getString(R.string.male));
                    }
                }

                break;

            case RELATIONSHIP_RESULT_CODE://关系

                if (data != null) {
                    relationshipId = data.getStringExtra("relationshipId");
                    tvRelationship.setText(data.getStringExtra("relationshipName"));
                }

                break;

        }

    }

    private boolean isDataOk() {

        if (TextUtils.isEmpty(etName.getText().toString())) {
            showErr("请输入姓名");
            return false;
        }

        if (TextUtils.isEmpty(tvBirthday.getText().toString())) {
            showErr("请选择出生日期");
            return false;
        }

        if (TextUtils.isEmpty(tvRelationship.getText().toString())) {
            showErr("请输入与患者的关系");
            return false;
        }

        if (TextUtils.isEmpty(tvCity.getText().toString())) {
            showErr("请填写所在城市");
            return false;
        }

        if (TextUtils.isEmpty(etTel.getText().toString())) {
            showErr("请填写手机号码");
            return false;
        }

        if (!NumFormatUtils.isMobileNum(etTel.getText().toString())) {
            showErr("请填写正确的手机号码");
            return false;
        }

        if (TextUtils.isEmpty(etIdCard.getText().toString())) {
            showErr("请填写身份证号码");
            return false;
        }

        if (!NumFormatUtils.isIdCard(etIdCard.getText().toString())) {
            showErr("请填写正确的身份证号码");
            return false;
        }

        if (TextUtils.isEmpty(etHospitalCard.getText().toString())) {
            showErr("请填写医保卡号");
            return false;
        }

        return true;
    }


}
