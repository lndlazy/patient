package com.ylean.cf_hospitalapp.mall.acitity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.mall.bean.AddressListEntry;
import com.ylean.cf_hospitalapp.my.activity.SelectProvinceAct;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.NumFormatUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 收货地址设置
 * Created by linaidao on 2019/1/7.
 */

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.EditText etName;
    private android.widget.EditText etTel;
    private android.widget.TextView tvLocation;
    private android.widget.EditText etAddDetail;
    private android.widget.ImageView ivDefault;
    private android.widget.LinearLayout llDefault;
    private android.widget.TextView tvNext;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String areaCode;
    private String areaName;
    private AddressListEntry.DataBean addressInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_add_address);

        addressInfo = getIntent().getParcelableExtra("addressInfo");
        initView();

        setInfo();

    }

    private void setInfo() {
        if (addressInfo != null) {

            etName.setText(addressInfo.getName());
            etTel.setText(addressInfo.getMobile());
            tvLocation.setText(addressInfo.getProvincename()
                    + addressInfo.getCityname()
                    + addressInfo.getAreaname());
            etAddDetail.setText(addressInfo.getAddress());
            ivDefault.setSelected("1".equals(addressInfo.getIsdefault()));

            provinceCode = addressInfo.getProvincecode();
            provinceName = addressInfo.getProvincename();
            cityCode = addressInfo.getCitycode();
            cityName = addressInfo.getCityname();
            areaCode = addressInfo.getAreacode();
            areaName = addressInfo.getAreaname();

            tvNext.setText("确认修改");
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Logger.d("====onNewIntent====");
        provinceCode = intent.getStringExtra("provinceCode");
        provinceName = intent.getStringExtra("provinceName");
        cityCode = intent.getStringExtra("cityCode");
        cityName = intent.getStringExtra("cityName");
        areaCode = intent.getStringExtra("areaCode");
        areaName = intent.getStringExtra("areaName");

        tvLocation.setText(provinceName + cityName + areaName);

    }


    private void initView() {
        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.llDefault = (LinearLayout) findViewById(R.id.llDefault);
        this.ivDefault = (ImageView) findViewById(R.id.ivDefault);
        this.etAddDetail = (EditText) findViewById(R.id.etAddDetail);
        this.tvLocation = (TextView) findViewById(R.id.tvLocation);
        this.etTel = (EditText) findViewById(R.id.etTel);
        this.etName = (EditText) findViewById(R.id.etName);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvNext.setOnClickListener(this);
        tvLocation.setOnClickListener(this);
        llDefault.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvNext://下一步

                nextStep();

                break;

            case R.id.llDefault://默认地址
                ivDefault.setSelected(!ivDefault.isSelected());
                break;

            case R.id.tvLocation://选择区域


                Intent m = new Intent(this, SelectProvinceAct.class);
                m.putExtra("type", "address");
                startActivity(m);

//                nextActivity(SelectProvinceAct.class);
                break;

        }
    }

    private void nextStep() {

        if (TextUtils.isEmpty(etName.getText().toString())) {
            showErr("请输入收货人姓名");
            return;
        }

        if (TextUtils.isEmpty(etTel.getText().toString())) {
            showErr("请输入手机号");
            return;
        }

        if (!NumFormatUtils.isMobileNum(etTel.getText().toString())) {
            showErr("请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(tvLocation.getText().toString())) {
            showErr("请选择地址");
            return;
        }

        if (TextUtils.isEmpty(etAddDetail.getText().toString())) {
            showErr("请输入详细地址");
            return;
        }

        if (addressInfo == null)
            addAddress();
        else
            modifyAddress();
    }

    private void modifyAddress() {

        RetrofitHttpUtil.getInstance()
                .modifyAddress(new BaseNoTObserver<Basebean>() {
                                   @Override
                                   public void onHandleSuccess(Basebean basebean) {
                                       showErr("地址修改成功");
                                       finish();
                                   }

                                   @Override
                                   public void onHandleError(String message) {
                                       showErr(message);
                                   }

                               }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , addressInfo.getId(), etName.getText().toString(), etTel.getText().toString(), provinceCode
                        , cityCode, areaCode, provinceName, cityName, areaName, etAddDetail.getText().toString(), ivDefault.isSelected() ? "1" : "0");


    }

    //添加地址
    private void addAddress() {

        RetrofitHttpUtil.getInstance()
                .addAddress(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                showErr("添加地址成功");
                                finish();
                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , etName.getText().toString(), etTel.getText().toString(), provinceCode
                        , cityCode, areaCode, provinceName, cityName, areaName, etAddDetail.getText().toString(), ivDefault.isSelected() ? "1" : "0");//是否默认(0不默认 1默认)

    }
}
