package com.ylean.cf_hospitalapp.my.view;

import android.content.Context;

import com.ylean.cf_hospitalapp.inquiry.bean.OrderEntry;
import com.ylean.cf_hospitalapp.base.view.BaseView;

import java.util.List;

/**
 * Created by linaidao on 2018/12/24.
 */

public interface IMyRegisteredView extends BaseView {

    Context getContext();

    void setOrderInfo(List<OrderEntry.DataBean> orderEntryList, String currentType, boolean refush);
}
