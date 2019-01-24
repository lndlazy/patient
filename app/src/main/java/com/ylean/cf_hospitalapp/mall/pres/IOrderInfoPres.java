package com.ylean.cf_hospitalapp.mall.pres;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.mall.bean.GoodsOrderInfoEntry;
import com.ylean.cf_hospitalapp.mall.view.IOrderInfoView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

import io.reactivex.disposables.Disposable;

/**
 * 商品 服务 订单详情
 * Created by linaidao on 2019/1/23.
 */

public class IOrderInfoPres {

    private IOrderInfoView iOrderInfoView;

    public IOrderInfoPres(IOrderInfoView iOrderInfoView) {
        this.iOrderInfoView = iOrderInfoView;
    }

    //获取订单详情
    public void orderInfo(String token, String orderId) {

        RetrofitHttpUtil.getInstance().goodsOrderInfo(
                new BaseNoTObserver<GoodsOrderInfoEntry>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        iOrderInfoView.showLoading("查询订单中...");
                    }

                    @Override
                    public void onHandleSuccess(GoodsOrderInfoEntry orderInfoEntry) {
                        iOrderInfoView.hideLoading();

                        if (orderInfoEntry == null || orderInfoEntry.getData() == null) {
                            iOrderInfoView.showErr("数据错误");
                            return;
                        }

                        iOrderInfoView.setOrderDetail(orderInfoEntry.getData());

                    }

                    @Override
                    public void onHandleError(String message) {
                        iOrderInfoView.showErr(message);
                        iOrderInfoView.hideLoading();
                    }

                }, SpValue.CH, token, orderId);

    }


    //取消订单
    public void cancleGoodsOrder(String token, String orderId, String status) {

        RetrofitHttpUtil.getInstance().cancleGoodsOrder(
                new BaseNoTObserver<Basebean>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        iOrderInfoView.showLoading("正在取消订单...");
                    }

                    @Override
                    public void onHandleSuccess(Basebean basebean) {
                        iOrderInfoView.hideLoading();
                        iOrderInfoView.showErr("取消订单成功");
                        iOrderInfoView.cancleOrderSuccess();
                    }

                    @Override
                    public void onHandleError(String message) {

                        iOrderInfoView.hideLoading();
                        iOrderInfoView.showErr(message);
                    }

                }, SpValue.CH, token, orderId, status, "取消订单");

    }


    //服务订单使用
    public void useServiceOrder(String token, String orderId) {

        RetrofitHttpUtil.getInstance().useServiceOrder(
                new BaseNoTObserver<Basebean>() {

                    @Override
                    public void onHandleSuccess(Basebean basebean) {
                        iOrderInfoView.showErr("订单使用成功");
                        iOrderInfoView.useServiceSuccess();
                    }

                    @Override
                    public void onHandleError(String message) {

                        iOrderInfoView.showErr(message);
                    }

                }, SpValue.CH, token, orderId);

    }

}
