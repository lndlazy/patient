package com.ylean.cf_hospitalapp.my.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.my.bean.DoctorDetailEntry;
import com.ylean.cf_hospitalapp.my.view.IDoctorDetailView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

import io.reactivex.disposables.Disposable;

/**
 * Created by linaidao on 2019/1/11.
 */

public class IDoctorDetailPres {

    private IDoctorDetailView iDoctorDetailView;

    public IDoctorDetailPres(IDoctorDetailView iDoctorDetailView) {
        this.iDoctorDetailView = iDoctorDetailView;
    }

    private String doctorId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void doctorDetail(String token) {

        RetrofitHttpUtil.getInstance()
                .doctorDetail(
                        new BaseNoTObserver<DoctorDetailEntry>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                iDoctorDetailView.showLoading("获取中...");
                            }

                            @Override
                            public void onHandleSuccess(DoctorDetailEntry basebean) {
                                iDoctorDetailView.hideLoading();
                            }

                            @Override
                            public void onHandleError(String message) {
                                iDoctorDetailView.hideLoading();
                                iDoctorDetailView.showErr(message);
                            }
                        }, token, SpValue.CH, doctorId);

    }

}
