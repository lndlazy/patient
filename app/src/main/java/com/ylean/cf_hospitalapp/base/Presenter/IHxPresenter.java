package com.ylean.cf_hospitalapp.base.Presenter;

import android.content.Intent;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.base.view.IHxView;

/**
 * 环信登录p
 * Created by linaidao on 2019/1/15.
 */

public class IHxPresenter {

    private IHxView iHxView;

    public IHxPresenter(IHxView iHxView) {
        this.iHxView = iHxView;
    }

    public void loginHx(String username) {
        
        if(EMClient.getInstance().isLoggedInBefore()){
            return;
        }

        //login
        EMClient.getInstance().login(username, "123456", new EMCallBack() {

            @Override
            public void onSuccess() {

                Logger.d("==环信登录成功==");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String error) {
                iHxView.showErr(error);
            }
        });


    }
}
