package com.ylean.cf_hospitalapp.login.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.login.bean.SplishEntry;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * 启动页
 * Created by linaidao on 2019/1/25.
 */

public class SplishActivity extends BaseActivity {
    private SimpleDraweeView sdvImg;

//    private android.support.v4.view.ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_splish);

        sdvImg = findViewById(R.id.sdvImg);

//        sdvImg.setImageURI(Uri.parse(SpValue.FRESCO_RES +  getPackageName() + "/" +R.mipmap.ic_splish));


//        boolean first = (boolean) SaveUtils.get(this, SpValue.IS_FIRST, true);
//        if (first) {
//            SaveUtils.put(this, SpValue.IS_FIRST, false);
//            getImg();
//
//        } else {
//            nextActivityThenKill(LoginActivity.class);
//        }


        getImg();
    }

    private void getImg() {

        RetrofitHttpUtil.getInstance().getSplishPage(
                new BaseNoTObserver<SplishEntry>() {
                    @Override
                    public void onHandleSuccess(SplishEntry basebean) {

                        if (basebean == null || basebean.getData() == null) {
                            nextActivityThenKill(LoginActivity.class);
                            return;
                        }

                        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + basebean.getData().getImg()));
                        new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        SystemClock.sleep(3000);
                                        nextActivityThenKill(LoginActivity.class);
                                    }
                                }
                        ).start();


                    }

                    @Override
                    public void onHandleError(String message) {
                        nextActivityThenKill(LoginActivity.class);
                        showErr(message);
                    }

                }, "0", SpValue.CH);
    }
}
