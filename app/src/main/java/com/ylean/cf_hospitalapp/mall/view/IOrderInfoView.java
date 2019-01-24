package com.ylean.cf_hospitalapp.mall.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.mall.bean.GoodsOrderInfoEntry;

/**
 * Created by linaidao on 2019/1/23.
 */

public interface IOrderInfoView extends BaseView{

    void setOrderDetail(GoodsOrderInfoEntry.DataBean data);

    void cancleOrderSuccess();

    void useServiceSuccess();


}
