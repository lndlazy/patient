package com.ylean.cf_hospitalapp.register.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.inquiry.bean.GradeLevelBean;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalTypeEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/3.
 */

public interface IHospitalView extends BaseView {

    void setHospitailList(List<HospitalListEntry.DataBean> data);

    void setGradeLevel(List<GradeLevelBean.DataBean> data);

    void setHospitalTypeInfo(List<HospitalTypeEntry.DataBean> data);
}
