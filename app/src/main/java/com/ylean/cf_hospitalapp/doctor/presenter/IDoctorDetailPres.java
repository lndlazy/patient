package com.ylean.cf_hospitalapp.doctor.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.doctor.bean.CommitListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.InquiryListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.VideoListEntry;
import com.ylean.cf_hospitalapp.hospital.bean.ServiceInfoEntry;
import com.ylean.cf_hospitalapp.my.bean.DoctorDetailEntry;
import com.ylean.cf_hospitalapp.doctor.view.IDoctorDetailView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 医生详情p
 * Created by linaidao on 2019/1/11.
 */

public class IDoctorDetailPres {

    private IDoctorDetailView iDoctorDetailView;
    private int page = 1;

    public IDoctorDetailPres(IDoctorDetailView iDoctorDetailView) {
        this.iDoctorDetailView = iDoctorDetailView;
    }

    private String doctorId;
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    private DoctorDetailEntry.DataBean doctorInfo;

    public DoctorDetailEntry.DataBean getDoctorInfo() {
        return doctorInfo;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void doctorDetail() {

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

                                if (basebean == null || basebean.getData() == null)
                                    return;

                                doctorInfo = basebean.getData();
                                iDoctorDetailView.setDoctorInfo(basebean.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iDoctorDetailView.hideLoading();
                                iDoctorDetailView.showErr(message);
                            }
                        }, token, SpValue.CH, doctorId);

    }


    //点击关注
    public void clickAttention() {

        if (doctorInfo == null) {
            iDoctorDetailView.showErr("数据错误");
            return;
        }

        if ("1".equals(doctorInfo.getIscollect())) {
            //已关注， 取消关注
            iDoctorDetailView.removeAttention(doctorInfo.getDoctorid());
        } else {
            //未关注， 点击关注
            iDoctorDetailView.addAttention(doctorInfo.getDoctorid());
        }

    }

    //医生评价列表
    public void doctorDetailCommentList() {
        RetrofitHttpUtil.getInstance()
                .doctorDetailCommentList(new BaseNoTObserver<CommitListEntry>() {
                    @Override
                    public void onHandleSuccess(CommitListEntry basebean) {

                        if (basebean==null || basebean.getData()==null)
                            return;

                        List<CommitListEntry.DataBean> data = basebean.getData();


                    }

                    @Override
                    public void onHandleError(String message) {
                        iDoctorDetailView.showErr(message);
                    }

                }, token, SpValue.CH, doctorId, page, Integer.MAX_VALUE + "");
    }

    //服务项目
    public void serviceInfo() {

        RetrofitHttpUtil.getInstance()
                .serviceInfo(new BaseNoTObserver<ServiceInfoEntry>() {
                    @Override
                    public void onHandleSuccess(ServiceInfoEntry basebean) {

                        if (basebean == null || basebean.getData() == null)
                            return;

                        setServiceInfo(basebean.getData());

                    }

                    @Override
                    public void onHandleError(String message) {
                        iDoctorDetailView.showErr(message);
                    }

                }, SpValue.CH, doctorId);
    }


    //设置服务项目 价格
    private void setServiceInfo(List<ServiceInfoEntry.DataBean> data) {

        try {

            for (int i = 0; i < data.size(); i++) {

                if ("1".equals(data.get(i).getAsktype())) {
                    iDoctorDetailView.setPicPrice(data.get(i).getPrice());

                } else if ("2".equals(data.get(i).getAsktype())) {
                    iDoctorDetailView.setTelPrice(data.get(i).getPrice());

                } else if ("3".equals(data.get(i).getAsktype())) {
                    iDoctorDetailView.setVideoPrice(data.get(i).getPrice());

                } else if ("4".equals(data.get(i).getAsktype())) {
                    iDoctorDetailView.setRegisterPrice(data.get(i).getPrice());

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //问诊记录
    public void inquiryHistory() {

        RetrofitHttpUtil.getInstance()
                .inquiryHistory(new BaseNoTObserver<InquiryListEntry>() {
                    @Override
                    public void onHandleSuccess(InquiryListEntry basebean) {

                        if (basebean == null || basebean.getData() == null)
                            return;

                        iDoctorDetailView.setInquiryData(basebean.getData());
                    }

                    @Override
                    public void onHandleError(String message) {
                        iDoctorDetailView.showErr(message);
                    }

                }, SpValue.CH, doctorId, 1, "2");
    }

    //医讲堂 记录
    public void videoHistory() {

        RetrofitHttpUtil.getInstance()
                .videoHistory(new BaseNoTObserver<VideoListEntry>() {
                    @Override
                    public void onHandleSuccess(VideoListEntry basebean) {

                        if (basebean == null || basebean.getData() == null)
                            return;

                        iDoctorDetailView.setVideoData(basebean.getData());
                    }

                    @Override
                    public void onHandleError(String message) {
                        iDoctorDetailView.showErr(message);
                    }

                }, SpValue.CH, doctorId, 1, "2");
    }

}
