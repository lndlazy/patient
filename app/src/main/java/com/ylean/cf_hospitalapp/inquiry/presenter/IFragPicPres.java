package com.ylean.cf_hospitalapp.inquiry.presenter;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.inquiry.view.IFreagThreeView;
import com.ylean.cf_hospitalapp.my.bean.MyAskReusltList;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * Created by linaidao on 2018/12/28.
 */

public class IFragPicPres {

    private IFreagThreeView iFreagThreeView;

    public IFragPicPres(IFreagThreeView iFreagThreeView) {
        this.iFreagThreeView = iFreagThreeView;
    }

    private int pageOne = 1;

    public void getPicAskInfo(String askTypePic, String token) {
        RetrofitHttpUtil
                .getInstance()
                .askList(
                        new BaseNoTObserver<MyAskReusltList>() {

                            @Override
                            public void onHandleSuccess(MyAskReusltList reusltList) {

                                if (reusltList == null || reusltList.getData() == null)
                                    return;

                                iFreagThreeView.setPicAskInfo(reusltList.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iFreagThreeView.showErr(message);
                            }

                        }
                        , token
                        , SpValue.CH
                        , askTypePic
                        , pageOne
                        , SpValue.PAGE_SIZE);
    }


    //刷新
    public void refush(String askTypePic, String token) {

        Logger.d("===刷新=== refush ");
        iFreagThreeView.clearPicInfo();
        pageOne = 1;
        getPicAskInfo(askTypePic, token);

    }

    //加载下一页数据
    public void nextPage(String askTypePic, String token) {

        Logger.d("==nextPage==");
        pageOne++;
        getPicAskInfo(askTypePic, token);
    }
}
