package com.ylean.cf_hospitalapp.my.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.my.DelCollectType;
import com.ylean.cf_hospitalapp.my.adapter.DoctorListAdapter;
import com.ylean.cf_hospitalapp.my.view.IDoctorAttentionView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * Created by linaidao on 2019/1/11.
 */

public class IDoctorAttentionPres {

    private IDoctorAttentionView iDoctorAttentionView;

    public IDoctorAttentionPres(IDoctorAttentionView iDoctorAttentionView) {
        this.iDoctorAttentionView = iDoctorAttentionView;
    }

    //关注医生
    public void addAttentionDoctor(String doctorid, String token) {

        RetrofitHttpUtil
                .getInstance()
                .attentionDoctor(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                                iDoctorAttentionView.showErr("关注成功");
                                iDoctorAttentionView.attentionSuccess();
                            }

                            @Override
                            public void onHandleError(String message) {
                                iDoctorAttentionView.showErr(message);
                                iDoctorAttentionView.attentionFaile();

                            }

                        }, SpValue.CH, token, doctorid);

    }

    //取消关注医生
    public void removeAttentionDoctor(String doctorid, String token) {
        RetrofitHttpUtil
                .getInstance()
                .noAttentionDoctor(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                                iDoctorAttentionView.showErr("取消成功");
                                iDoctorAttentionView.removeAttentionSuccess();

                            }

                            @Override
                            public void onHandleError(String message) {
                                iDoctorAttentionView.showErr(message);
                                iDoctorAttentionView.removeAttentionFail();
                            }

                        }, SpValue.CH, token, doctorid, DelCollectType.TYPE_DOCTOR);
    }

}
