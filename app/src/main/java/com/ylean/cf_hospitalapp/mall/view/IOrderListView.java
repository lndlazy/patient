package com.ylean.cf_hospitalapp.mall.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.mall.bean.MallOrderEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/21.
 */

public interface IOrderListView extends BaseView{


    void stopRefush();

    void setOrderList(List<MallOrderEntry.DataBean> data, boolean refush);
}
