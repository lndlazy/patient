package com.ylean.cf_hospitalapp.inquiry.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.my.bean.MyAskReusltList;

import java.util.List;

/**
 * Created by linaidao on 2018/12/28.
 */

public interface IFreagThreeView extends BaseView {


    void setPicAskInfo(List<MyAskReusltList.DataBean> data);

    void clearPicInfo();
//    void refushPage();
}
