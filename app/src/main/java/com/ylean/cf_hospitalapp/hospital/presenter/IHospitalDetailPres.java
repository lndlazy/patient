package com.ylean.cf_hospitalapp.hospital.presenter;


import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.hospital.bean.HospDepartListEntry;
import com.ylean.cf_hospitalapp.hospital.view.IHospitalDetailView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.register.bean.HospitalInfoEntry;
import com.ylean.cf_hospitalapp.utils.SpValue;

import io.reactivex.disposables.Disposable;

/**
 * 医院详情
 * Created by linaidao on 2019/1/12.
 */

public class IHospitalDetailPres {

    private IHospitalDetailView iHospitalView;

    public IHospitalDetailPres(IHospitalDetailView iHospitalView) {
        this.iHospitalView = iHospitalView;
    }

    private String hospitalid;

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid;
    }

    //医院详情
    public void hospitalDetail() {

        RetrofitHttpUtil.getInstance()
                .hospitalInfo(
                        new BaseNoTObserver<HospitalInfoEntry>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                iHospitalView.showLoading("获取中...");
                            }

                            @Override
                            public void onHandleSuccess(HospitalInfoEntry hospitalInfoEntry) {

                                iHospitalView.hideLoading();

                                if (hospitalInfoEntry != null && hospitalInfoEntry.getData() != null) {

                                    iHospitalView.setHospitalInfo(hospitalInfoEntry);
                                }

                            }

                            @Override
                            public void onHandleError(String message) {
                                iHospitalView.showErr(message);
                                iHospitalView.hideLoading();

                            }

                        }, SpValue.CH, hospitalid);

    }


    //医院 科室 列表
    public void hospitalDeartmentList() {

        RetrofitHttpUtil.getInstance()
                .hospitalDeartmentList(
                        new BaseNoTObserver<HospDepartListEntry>() {
                            @Override
                            public void onHandleSuccess(HospDepartListEntry basebean) {

                                if (basebean == null || basebean.getData() == null)
                                    return;

                                iHospitalView.setDepartmentInfo(basebean.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iHospitalView.showErr(message);
                            }

                        }, SpValue.CH, hospitalid);

    }

    //医院评论列表
    public void hospitalCommentList(String token) {

        RetrofitHttpUtil.getInstance()
                .hospitalCommentList(
                        new BaseNoTObserver<CommComListEntry>() {
                            @Override
                            public void onHandleSuccess(CommComListEntry commComListEntry) {

                                if (commComListEntry == null || commComListEntry.getData() == null)
                                    return;

                                iHospitalView.setCommentInfo(commComListEntry.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iHospitalView.showErr(message);
                            }

                        }, SpValue.CH, token, hospitalid, 1, Integer.MAX_VALUE + "");

    }

}
