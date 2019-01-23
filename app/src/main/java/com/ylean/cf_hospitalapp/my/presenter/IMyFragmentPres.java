package com.ylean.cf_hospitalapp.my.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.my.bean.MyInfoEntry;
import com.ylean.cf_hospitalapp.my.view.IMyFragmentView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * Created by linaidao on 2019/1/2.
 */

public class IMyFragmentPres {

    private IMyFragmentView iMyFragmentView;

    public IMyFragmentPres(IMyFragmentView iMyFragmentView) {
        this.iMyFragmentView = iMyFragmentView;
    }

    public void myInfo(String token) {

        RetrofitHttpUtil.getInstance().patientInfo(
                new BaseNoTObserver<MyInfoEntry>() {
                    @Override
                    public void onHandleSuccess(MyInfoEntry myInfoEntry) {

                        if (myInfoEntry != null)
                            iMyFragmentView.setInfo(myInfoEntry);

                    }

                    @Override
                    public void onHandleError(String message) {
                        iMyFragmentView.showErr(message);
                    }

                }, SpValue.CH, token);

    }

}
