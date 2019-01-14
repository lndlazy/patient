package com.ylean.cf_hospitalapp.hospital.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.hospital.bean.HospDepartListEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalInfoEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/12.
 */

public interface IHospitalDetailView extends BaseView {


    void setHospitalInfo(HospitalInfoEntry hospitalInfoEntry);

    void setDepartmentInfo(List<HospDepartListEntry.DataBean> data);

    void setCommentInfo(List<CommComListEntry.DataBean> data);

}
