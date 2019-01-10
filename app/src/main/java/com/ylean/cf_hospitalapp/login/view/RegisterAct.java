package com.ylean.cf_hospitalapp.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.login.bean.RegisterResultEntry;
import com.ylean.cf_hospitalapp.my.activity.WebviewActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.NumFormatUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 注册
 * Created by linaidao on 2018/12/25.
 */

public class RegisterAct extends BaseActivity implements View.OnClickListener {

    private android.widget.ImageView back;
    private android.widget.TextView tv_login;
    private android.widget.EditText et_registname;
    private android.widget.EditText ed_pwd;
    private android.widget.CheckBox pwd_select;
    private android.widget.EditText ed_code;
    private android.widget.TextView tv_code;
    private android.widget.CheckBox checkbox_select;
    private android.widget.TextView tv_info;
    private android.widget.TextView tv_vip_regist;
    private String name;
    private String gender;
    private String type;
    private String url;
    private String openid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_register);
        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");
        type = getIntent().getStringExtra("type");
        url = getIntent().getStringExtra("url");
        openid = getIntent().getStringExtra("openid");

        Logger.d("name:" + name + ",gender:" + gender + ",type:" + type + ",url:" + url + ",openid:" + openid);
        initView();

    }

    private void initView() {

        this.tv_vip_regist = (TextView) findViewById(R.id.tv_vip_regist);
        this.tv_info = (TextView) findViewById(R.id.tv_info);
        this.checkbox_select = (CheckBox) findViewById(R.id.checkbox_select);
        this.tv_code = (TextView) findViewById(R.id.tv_code);
        this.ed_code = (EditText) findViewById(R.id.ed_code);
        this.pwd_select = (CheckBox) findViewById(R.id.pwd_select);
        this.ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        this.et_registname = (EditText) findViewById(R.id.et_registname);
        this.tv_login = (TextView) findViewById(R.id.tv_login);
        this.back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(this);
        tv_code.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        pwd_select.setOnClickListener(this);
        tv_vip_regist.setOnClickListener(this);
        tv_info.setOnClickListener(this);

        pwd_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ed_pwd.setInputType(isChecked ? (InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                        : (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));

                if (!TextUtils.isEmpty(ed_pwd.getText().toString()))
                    ed_pwd.setSelection(ed_pwd.getText().toString().length());
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.back:
            case R.id.tv_login:

                finish();

                break;

            case R.id.tv_code://发送验证码

                SMSCode();

                break;

            case R.id.tv_vip_regist://注册

                checkInfo();

                break;

            case R.id.tv_info://服务协议

                Intent m = new Intent(this, WebviewActivity.class);
                m.putExtra("title", "服务协议");
                m.putExtra("url", ApiService.WEB_ROOT + ApiService.SERVICE_XIEYI);
                startActivity(m);

                break;
        }

    }

    private void checkInfo() {
        if (TextUtils.isEmpty(et_registname.getText().toString())) {
            showErr("请输入手机号");
            return;
        }

        if (!NumFormatUtils.isMobileNum(et_registname.getText().toString())) {
            showErr("请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(ed_pwd.getText().toString())) {
            showErr("请输入密码");
            return;
        }

        if (TextUtils.isEmpty(ed_code.getText().toString())) {
            showErr("请输入验证码");
            return;
        }

        if (!checkbox_select.isChecked()) {
            showErr("请同意《服务协议》");
            return;
        }

        if (ed_pwd.getText().toString().length() < 6 || ed_pwd.getText().toString().length() > 16) {
            showErr("密码应在6~16位之间");
            return;
        }

        registerInfo();

    }

    private void registerInfo() {

        RetrofitHttpUtil
                .getInstance()
                .registerInfo(
                        new BaseNoTObserver<RegisterResultEntry>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                showLoading("注册中...");
                            }

                            @Override
                            public void onHandleSuccess(RegisterResultEntry basebean) {
                                hideLoading();

//                                String data = basebean.getData();
//                                JSONObject.parse(basebean.getData());
//                                JSONObject j = new JSONObject();

                                showErr("注册成功");
                                finish();

                            }

                            @Override
                            public void onHandleError(String message) {
                                hideLoading();
                                showErr(message);
                            }

                        }, SpValue.CH, et_registname.getText().toString(), ed_code.getText().toString()
                        , ed_pwd.getText().toString(), name, openid, type, url, "");

    }

    private void SMSCode() {
        if (TextUtils.isEmpty(et_registname.getText().toString())) {
            showErr("请输入手机号");
            return;
        }

        if (!NumFormatUtils.isMobileNum(et_registname.getText().toString())) {
            showErr("请输入正确的手机号");
            return;
        }

        countDown();
        sendSMSCode();
    }

    private void sendSMSCode() {

        RetrofitHttpUtil
                .getInstance()
                .smsCode(
                        new BaseNoTObserver<Basebean>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                showLoading("获取中...");
                            }

                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                hideLoading();

//                                if (basebean.getCode() ==0) {
//                                    //验证码获取成功
//
//                                }

                            }

                            @Override
                            public void onHandleError(String message) {
                                hideLoading();
                                showErr(message);
                            }

                        }
                        , SpValue.CH
                        , et_registname.getText().toString()
                        , SpValue.REGISTER_CODE);
//册(0),修改密码(1),手机号绑定(2),找回密码(3)

    }

    private final int TIME = 60;
    private Disposable countDisposable;

    private void setClickable(boolean clickable) {
        tv_code.setClickable(clickable);//设置控件不可点击
        tv_code.setEnabled(clickable);
    }

    /**
     * 倒计时
     */
    private void countDown() {

        setClickable(false);

        Observable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {

//                LogUtil.d(TAG, "发送事件 " + aLong);

                if (aLong > TIME)
                    countDisposable.dispose();

                if (aLong == TIME) {
                    setClickable(true);
                    //设置控件可以点击
                    return "获取验证码";
                }

                return (TIME - aLong) + " 秒";
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        countDisposable = d;
                    }

                    @Override
                    public void onNext(String value) {
//                        LogUtil.d(TAG, "接受事件:" + value);
//                        iRegisterView.setCountTime(value);
                        tv_code.setText(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
