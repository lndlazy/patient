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

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.HomeActivity;
import com.ylean.cf_hospitalapp.home.bean.LoginEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.NumFormatUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 登录
 * Created by linaidao on 2018/12/24.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText ed_pwd;
    private UMShareAPI umShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_login);

        umShareAPI = UMShareAPI.get(this);

        initView();

        if (!TextUtils.isEmpty((String) SaveUtils.get(this, SpValue.TOKEN, "")))
            nextActivityThenKill(HomeActivity.class);

    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        TextView tv_toregist = findViewById(R.id.tv_toregist);
        et_username = findViewById(R.id.et_username);
        ed_pwd = findViewById(R.id.ed_pwd);
        CheckBox pwd_select = findViewById(R.id.pwd_select);
        TextView tv_forgetpwd = findViewById(R.id.tv_forgetpwd);

        ImageView iv_qq = findViewById(R.id.iv_qq);
        ImageView iv_weixin = findViewById(R.id.iv_weixin);
        ImageView iv_weibo = findViewById(R.id.iv_weibo);

        TextView tv_vip_login = findViewById(R.id.tv_vip_login);

        iv_back.setOnClickListener(this);
        tv_vip_login.setOnClickListener(this);
        tv_toregist.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
        iv_weixin.setOnClickListener(this);
        iv_weibo.setOnClickListener(this);
        tv_forgetpwd.setOnClickListener(this);

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

            case R.id.iv_back://返回首页

                nextActivityThenKill(HomeActivity.class);

                break;

            case R.id.tv_vip_login://登录

                checkInput();

                break;

            case R.id.tv_toregist://注册

                nextActivity(RegisterAct.class);

                break;

            case R.id.iv_qq:

                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);//QQ登录

                break;

            case R.id.iv_weixin:

                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);//weix

                break;

            case R.id.iv_weibo:
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);//weix

                break;

            case R.id.tv_forgetpwd://忘记密码

                nextActivity(ForgetPwdAct.class);

                break;

        }

    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            String openId = map.get("uid");
            String name = map.get("name");
            String iconurl = map.get("iconurl");
            String gender = map.get("gender");


        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    //检测输入内容
    private void checkInput() {

        if (TextUtils.isEmpty(et_username.getText().toString())) {
            showErr("请输入手机号");
            return;
        }

        if (!NumFormatUtils.isMobileNum(et_username.getText().toString())) {
            showErr("请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(ed_pwd.getText().toString())) {
            showErr("请输入密码");
            return;
        }

        RetrofitHttpUtil.getInstance().loginIn(new BaseNoTObserver<LoginEntry>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                showLoading("登录中...");
            }

            @Override
            public void onHandleSuccess(LoginEntry loginEntry) {

                hideLoading();
//                SaveUtils.put(HomeActivity.this, SpValue.TOKEN, loginEntry.getToken());
//                Logger.d("登录成功缓存token：：" + loginEntry.getToken());

//                SharedPreferences sp = getSharedPreferences(SaveUtils.CONFIG, Context.MODE_PRIVATE);

                SaveUtils.put(getApplicationContext(), SpValue.TOKEN, loginEntry.getToken());
//                sp.edit().putString("patient_taken", loginEntry.getToken()).apply();
//                Logger.d("存储的数据::" + sp.getString("patient_taken", ""));
                nextActivityThenKill(HomeActivity.class);

            }

            @Override
            public void onHandleError(String message) {
                hideLoading();
                showErr(message);
            }

        }, SpValue.CH, et_username.getText().toString(), ed_pwd.getText().toString());

    }
}
