package com.ylean.cf_hospitalapp.register.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.register.bean.DoctorTypeEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/4.
 */

public interface IChooseNumView extends BaseView {

    void doctorTypeInfo(List<DoctorTypeEntry.DataBean> data);

    void fillDate();

    void stopRefush();

}
