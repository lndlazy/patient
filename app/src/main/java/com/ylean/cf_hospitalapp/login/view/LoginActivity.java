package com.ylean.cf_hospitalapp.login.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.HomeActivity;
import com.ylean.cf_hospitalapp.home.bean.LoginEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.DemoActivity;
import com.ylean.cf_hospitalapp.utils.NumFormatUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 登录
 * Created by linaidao on 2018/12/24.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_PERMISSION_LOCATION_CODE = 0x12;
    private EditText et_username;
    private EditText ed_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_login);

        initView();

//        Logger.d("存储的token::" + (String) SaveUtils.get(this, SpValue.TOKEN, ""));

        if (!TextUtils.isEmpty((String) SaveUtils.get(this, SpValue.TOKEN, "")))
            nextActivityThenKill(HomeActivity.class);

        //检查权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPer();
        }
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

    /**
     * 检查定位权限， 获取定位信息
     */
    private void checkLocationPer() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                ) {

        } else {
            //不具有定位权限，需要进行权限申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION_CODE);
        }

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

                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);//QQ登录

                break;

            case R.id.iv_weixin:

                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);//weix

                break;

            case R.id.iv_weibo:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);//weibo

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

            //   dtype  0-微信 1-扣扣 2-微博
            switch (share_media) {
                case QQ:
                    getThirdLogin(map.get("openid"), "1", map.get("name"), map.get("gender"), map.get("iconurl"));
                    break;
                case WEIXIN:
                    getThirdLogin(map.get("openid"), "0", map.get("name"), map.get("gender"), map.get("iconurl"));
                    break;
                case SINA:
                    getThirdLogin(map.get("id"), "2", map.get("name"), map.get("gender"), map.get("iconurl"));
                    break;

            }

//
///**
// *  String uid = map.get("uid");
// String openid = map.get("openid");//微博没有
// String unionid = map.get("unionid");//微博没有
// String access_token = map.get("access_token");
// String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
// String expires_in = map.get("expires_in");
// String name = map.get("name");
// String gender = map.get("gender");
// String iconurl = map.get("iconurl");
//
// */
//            String openId = map.get("uid");
//            String name = map.get("name");
//            String iconurl = map.get("iconurl");
//
//
//            //qq
//            String gender = map.get("gender");//性别  qq登录
//            String openid = map.get("openid");//qq登录  Key = openid, Value = E510C8632D72B7FD8846F3831869C975
//
//            //Key = profile_image_url, Value = http://thirdqq.qlogo.cn/qqapp/1106735139/E510C8632D72B7FD8846F3831869C975/100
//            //Key = iconurl, Value = http://thirdqq.qlogo.cn/qqapp/1106735139/E510C8632D72B7FD8846F3831869C975/100
//            String profile_image_url = map.get("profile_image_url");
////            String iconurl = map.get("iconurl");
//            //Key = accessToken, Value = 85DC4F34DD5A9C0D2FF2D4DEAAA57176
//            String accessToken = map.get("accessToken");
//            String access_token = map.get("access_token");//与accessToken同值
//            //Key = uid, Value = E510C8632D72B7FD8846F3831869C975
//            String uid = map.get("uid");
//            //Key = screen_name, Value = Aaron
//            // Key = name, Value = Aaron
//            String screen_name = map.get("screen_name");
//            // Key = expiration, Value = 1554869795077
//            String expiration = map.get("expiration");
//            // Key = expires_in, Value = 1554869795077
//            String expires_in = map.get("expires_in");
//
//
//            //微博
//            /*
//             *
//             *  Key = id, Value = 5928335579
//             *  Key = screen_name, Value = AutoMasterLee
//             *  Key = accessToken, Value = 2.00ntgMTG06PphWc9b7ceffffZ23OAE
//             *  Key = access_token, Value = 2.00ntgMTG06PphWc9b7ceffffZ23OAE
//             *  Key = name, Value = AutoMasterLee
//             *  Key = expiration, Value = 1549738799441
//             *  Key = gender, Value = 男
//             *
//             */

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
                saveUserinfo(loginEntry);

//                String data = loginEntry.getData();
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

    //保存用户数据
    private void saveUserinfo(LoginEntry loginEntry) {

        String data = loginEntry.getData();

        JSONObject objects = (JSONObject) JSONObject.parse(data);
//        String hxname = objects.getString("hxname");
//        Logger.d("hxname：：：：" + hxname);
        SaveUtils.put(getApplicationContext(), SpValue.HX_NAME, objects.getString("hxname"));
        SaveUtils.put(getApplicationContext(), SpValue.TOKEN, loginEntry.getToken());

    }

    //dtype  0-微信 1-扣扣 2-微博
    private void getThirdLogin(String openid, String type, String name, String gender, String url) {

        RetrofitHttpUtil.getInstance()
                .thirdLogin(
                        new Observer<LoginEntry>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                                showLoading("获取中...");
                            }

                            @Override
                            public void onNext(LoginEntry value) {

                                hideLoading();
                                if (value.getCode() == 0) {

                                    saveUserinfo(value);
                                    nextActivityThenKill(HomeActivity.class);

                                } else {

                                    Intent m = new Intent(LoginActivity.this, RegisterAct.class);
                                    m.putExtra("name", name);
                                    m.putExtra("gender", gender);
                                    m.putExtra("type", type);
                                    m.putExtra("url", url);
                                    m.putExtra("openid", openid);
                                    startActivity(m);

                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                hideLoading();
                                showErr(e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        }, openid, type, SpValue.CH);
//                        new BaseNoTObserver<Basebean>() {
//                            @Override
//                            public void onHandleSuccess(Basebean basebean) {
//
//                                Intent m = new Intent(LoginActivity.this, RegisterAct.class);
//                                m.putExtra("name", name);
//                                m.putExtra("gender", gender);
//                                m.putExtra("type", type);
//                                m.putExtra("url", url);
//                                m.putExtra("openid", openid);
//                                startActivity(m);
//                            }
//
//                            @Override
//                            public void onHandleError(String message) {
//                                showErr(message);
//                            }
//
//                        }, openid, type, SpValue.CH);
    }

}
