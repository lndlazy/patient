package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.HomeActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
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
 * 修改密码
 * Created by linaidao on 2019/1/10.
 */

public class ChangePwdActivity extends BaseActivity implements View.OnClickListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;

    private android.widget.TextView tvtel;
    private android.widget.EditText edcode;
    private android.widget.TextView tvcode;
    private android.widget.EditText etpwd;
    private android.widget.EditText etpwdrepeat;
    private android.widget.TextView tvsubmit;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_change_pwd);

        tel = getIntent().getStringExtra("tel");

        initView();

        tvtel.setText(tel);
    }

    private void initView() {
        this.tvsubmit = (TextView) findViewById(R.id.tv_submit);
        this.etpwdrepeat = (EditText) findViewById(R.id.etpwdrepeat);
        this.etpwd = (EditText) findViewById(R.id.etpwd);
        this.tvcode = (TextView) findViewById(R.id.tvcode);
        this.edcode = (EditText) findViewById(R.id.edcode);
        this.tvtel = (TextView) findViewById(R.id.tvtel);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

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

            case R.id.tvcode://获取验证码
                SMSCode();
                break;

            case R.id.tv_submit:

                if (TextUtils.isEmpty(tel)) {
                    showErr("数据异常");
                    return;
                }

                if (TextUtils.isEmpty(edcode.getText().toString())) {
                    showErr("请输入验证码");
                    return;
                }

                if (TextUtils.isEmpty(etpwd.getText().toString())) {
                    showErr("请输入密码");
                    return;
                }

                if (TextUtils.isEmpty(etpwdrepeat.getText().toString())) {
                    showErr("请输入确认密码");
                    return;
                }

                changePwd();

                break;

        }
    }


    private void changePwd() {

        RetrofitHttpUtil.getInstance()
                .changePwd(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                showErr("修改成功");
                                nextActivity(HomeActivity.class);
                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), edcode.getText().toString()
                        , etpwd.getText().toString(), etpwdrepeat.getText().toString());

    }

    private void SMSCode() {

        if (TextUtils.isEmpty(tel)) {
            showErr("数据异常");
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

                            }

                            @Override
                            public void onHandleError(String message) {
                                hideLoading();
                                showErr(message);
                            }

                        }, SpValue.CH, tel, SpValue.MODIFY_CODE);
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
