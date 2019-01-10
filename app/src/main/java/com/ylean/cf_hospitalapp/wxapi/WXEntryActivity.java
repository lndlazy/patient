package com.ylean.cf_hospitalapp.wxapi;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.umeng.weixin.callback.WXCallbackActivity;

/**
 * 微信回调页面
 * Created by linaidao on 2018/12/26.
 */

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Logger.d("====微信回调页面====");


    }
}
