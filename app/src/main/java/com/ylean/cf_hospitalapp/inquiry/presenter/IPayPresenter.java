package com.ylean.cf_hospitalapp.inquiry.presenter;

import android.content.Context;

import com.ylean.cf_hospitalapp.inquiry.bean.DepartmentListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorLevelListBean;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.GradeLevelBean;
import com.ylean.cf_hospitalapp.inquiry.view.IPayView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import io.reactivex.disposables.Disposable;

public class IPayPresenter {

    private IPayView iPayView;

    public IPayPresenter(IPayView iPayView) {
        this.iPayView = iPayView;
    }

    public void doctorList(Context context, String searchname, String departid, String doctitleid
            , String hosgradid, String asktype, String sorttype, int page, String size) {

        RetrofitHttpUtil.getInstance().getDoctorList(
                new BaseNoTObserver<DoctorListEntry>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
//                        iPayView.hideLoading();
                        iPayView.showLoading("获取中...");
                    }

                    @Override
                    public void onHandleSuccess(DoctorListEntry doctorListEntry) {
                        iPayView.hideLoading();
                        iPayView.defalutInfo(doctorListEntry);
                    }

                    @Override
                    public void onHandleError(String message) {
                        iPayView.hideLoading();
                        iPayView.showInfo(message);
                    }

                }, SpValue.CH
                , (String) SaveUtils.get(context.getApplicationContext(), SpValue.HOSPITAL_ID, "")
                , searchname, departid, doctitleid, hosgradid
                , asktype, sorttype, page, size);

    }

    public void freeDoctorList(Context context, String docname, String departid, String doctitleid
            , String hosgradid, int page, String size) {

        RetrofitHttpUtil.getInstance().getFreeDoctorList(
                new BaseNoTObserver<DoctorListEntry>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
//                        iPayView.hideLoading();
                        iPayView.showLoading("获取中...");
                    }

                    @Override
                    public void onHandleSuccess(DoctorListEntry doctorListEntry) {
                        iPayView.hideLoading();
                        iPayView.defalutInfo(doctorListEntry);
                    }

                    @Override
                    public void onHandleError(String message) {
                        iPayView.hideLoading();
                        iPayView.showInfo(message);
                    }

                }, SpValue.CH
                , (String) SaveUtils.get(context.getApplicationContext(), SpValue.HOSPITAL_ID, "")
                , departid, doctitleid, hosgradid, docname, page, size);

    }

    //获取医生等级列表
    public void doctorLevelList() {

        RetrofitHttpUtil.getInstance()
                .getDoctorLevelList(new BaseNoTObserver<DoctorLevelListBean>() {

                    @Override
                    public void onHandleSuccess(DoctorLevelListBean doctorListEntry) {
                        iPayView.setDoctorLevelInfo(doctorListEntry.getData());
                    }

                    @Override
                    public void onHandleError(String message) {
                        iPayView.showInfo(message);
                    }

                }, SpValue.CH);

    }

    //获取医院等级列表
    public void hosGradeLevelList() {

        RetrofitHttpUtil.getInstance()
                .getHosGradeLevelList(new BaseNoTObserver<GradeLevelBean>() {

                    @Override
                    public void onHandleSuccess(GradeLevelBean gradeLevelBean) {
                        iPayView.setGradeLevelInfo(gradeLevelBean.getData());
                    }

                    @Override
                    public void onHandleError(String message) {
                        iPayView.showInfo(message);
                    }

                }, SpValue.CH);

    }

    /**
     * 获取所有的科室
     */
    public void allRoom() {
        RetrofitHttpUtil.getInstance().getAllDepartment(new BaseNoTObserver<DepartmentListEntry>() {
            @Override
            public void onHandleSuccess(DepartmentListEntry departmentListEntry) {

//                List<DepartmentListEntry.DataBean> departmentListEntryData = departmentListEntry.getData();
                iPayView.setAllRoomInfo(departmentListEntry.getData());
            }

            @Override
            public void onHandleError(String message) {

            }

        }, SpValue.CH);

    }
}
