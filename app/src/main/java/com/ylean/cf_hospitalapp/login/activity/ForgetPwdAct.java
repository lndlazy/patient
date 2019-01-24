package com.ylean.cf_hospitalapp.login.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.login.bean.RegisterResultEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.NumFormatUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 忘记密码
 * Created by linaidao on 2018/12/26.
 */

public class ForgetPwdAct extends BaseActivity implements View.OnClickListener {

    private android.widget.EditText etforgetname;
    private android.widget.EditText edcode;
    private android.widget.EditText ednewpwd;
    private android.widget.TextView tvcode;
    private android.widget.EditText edpwd;
    private android.widget.CheckBox pwdselect;
    private android.widget.TextView tvsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_forget_pwd);
        initView();

    }

    private void initView() {
        this.tvsubmit = (TextView) findViewById(R.id.tv_submit);
        this.pwdselect = (CheckBox) findViewById(R.id.pwd_select);
        this.edpwd = (EditText) findViewById(R.id.ed_pwd);
        this.tvcode = (TextView) findViewById(R.id.tv_code);
        this.edcode = (EditText) findViewById(R.id.ed_code);
        this.ednewpwd = (EditText) findViewById(R.id.ednewpwd);
        this.etforgetname = (EditText) findViewById(R.id.et_forgetname);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvcode.setOnClickListener(this);
        tvsubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_code://获取验证码
                SMSCode();
                break;

            case R.id.tv_submit://提交

                subInfo();

                break;

        }

    }

    private void subInfo() {
        if (TextUtils.isEmpty(etforgetname.getText().toString())) {
            showErr("请输入手机号");
            return;
        }

        if (TextUtils.isEmpty(edcode.getText().toString())) {
            showErr("请输入验证码");
            return;
        }

        if (TextUtils.isEmpty(edpwd.getText().toString())) {
            showErr("请输入新密码");
            return;
        }

        if (!NumFormatUtils.isMobileNum(etforgetname.getText().toString())) {
            showErr("请输入正确的手机号");
            return;
        }

        if (edpwd.length() < 6 || edpwd.length() > 16) {
            showErr("密码由6-16位数组成，请重新输入");
            return;
        }

        RetrofitHttpUtil
                .getInstance()
                .findPwd(
                        new BaseNoTObserver<RegisterResultEntry>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                showLoading("修改中...");
                            }

                            @Override
                            public void onHandleSuccess(RegisterResultEntry basebean) {
                                hideLoading();
                                showErr("修改成功，请重新登录");
                                finish();

                            }

                            @Override
                            public void onHandleError(String message) {
                                hideLoading();
                                showErr(message);
                            }

                        }
                        , SpValue.CH
                        , etforgetname.getText().toString()
                        , edcode.getText().toString()
                        , edpwd.getText().toString()
                        , ednewpwd.getText().toString());
    }

    private void SMSCode() {

        if (TextUtils.isEmpty(etforgetname.getText().toString())) {
            showErr("请输入手机号");
            return;
        }

        if (!NumFormatUtils.isMobileNum(etforgetname.getText().toString())) {
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
                        , etforgetname.getText().toString()
                        , SpValue.REFIND_CODE);
        //注册(0),修改密码(1),手机号绑定(2),找回密码(3)

    }

    private final int TIME = 60;
    private Disposable countDisposable;

    private void setClickable(boolean clickable) {
        tvcode.setClickable(clickable);//设置控件不可点击
        tvcode.setEnabled(clickable);
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
                        tvcode.setText(value);
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
