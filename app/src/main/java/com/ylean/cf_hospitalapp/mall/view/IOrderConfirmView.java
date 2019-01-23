package com.ylean.cf_hospitalapp.mall.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.mall.bean.GoodsInfoEntry;

/**
 * 确认订单
 * Created by linaidao on 2019/1/21.
 */

public interface IOrderConfirmView extends BaseView {

    void setGoodsInfo(GoodsInfoEntry.DataBean data);

    void setFreightInfo(boolean b, double i, double goodsPrice);

    void orderSuccess(String data);

//    void setFreightInfo(FreightPriceEntry.DataBean data);

}
