package com.ylean.cf_hospitalapp.inquiry.view;

import com.ylean.cf_hospitalapp.inquiry.bean.DepartmentListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorLevelListBean;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.GradeLevelBean;

import java.util.List;

public interface IPayView {

    void showInfo(String msg);

    void setDoctorLevelInfo(List<DoctorLevelListBean.DataBean> data);

    void setGradeLevelInfo(List<GradeLevelBean.DataBean> gradeLevelBeanData);

    void showLoading(String message);

    void hideLoading();

    void defalutInfo(DoctorListEntry doctorListEntry);

    void setAllRoomInfo(List<DepartmentListEntry.DataBean> data);
}
