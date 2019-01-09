package com.ylean.cf_hospitalapp.my.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.my.bean.MyDoctorListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/6.
 */

public interface IDoctorListView extends BaseView{


    void setDoctorListInfo(List<MyDoctorListEntry.DataBean> data, boolean isRefush);
}
