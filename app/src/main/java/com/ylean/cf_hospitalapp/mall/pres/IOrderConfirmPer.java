package com.ylean.cf_hospitalapp.mall.pres;

import com.ylean.cf_hospitalapp.inquiry.bean.PicAskResutEntry;
import com.ylean.cf_hospitalapp.mall.bean.FreightPriceEntry;
import com.ylean.cf_hospitalapp.mall.bean.GoodsInfoEntry;
import com.ylean.cf_hospitalapp.mall.view.IOrderConfirmView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

import io.reactivex.disposables.Disposable;

/**
 * Created by linaidao on 2019/1/21.
 */

public class IOrderConfirmPer {

    private IOrderConfirmView iOrderConfirmView;

    public IOrderConfirmPer(IOrderConfirmView iOrderConfirmView) {
        this.iOrderConfirmView = iOrderConfirmView;
    }

    private double basefreight = -1;//基本运费
    private double byfreight = -1;//包邮价
    private double goodsPrice = -1;//商品价格


    //获取运费
    public void freightInfo(String token) {

        RetrofitHttpUtil.getInstance()
                .freightInfo(new BaseNoTObserver<FreightPriceEntry>() {
                    @Override
                    public void onHandleSuccess(FreightPriceEntry basebean) {

                        if (basebean == null || basebean.getData() == null)
                            return;

                        basefreight = basebean.getData().getBasefreight();
                        byfreight = basebean.getData().getByfreight();
//                        iOrderConfirmView.setFreightInfo(basebean.getData());

                        if (goodsPrice != -1) {

                            if (goodsPrice >= byfreight) {
                                //商品价格大于等于包邮价  免运费
                                iOrderConfirmView.setFreightInfo(false, 0.00, goodsPrice);

                            } else {
                                //   要运费
                                iOrderConfirmView.setFreightInfo(true, basefreight, goodsPrice);
                            }

                        }

                    }

                    @Override
                    public void onHandleError(String message) {
                        iOrderConfirmView.showErr(message);
                    }

                }, token, SpValue.CH);

    }

    //获取商品详情
    public void goodsInfo(String token, String id) {

        RetrofitHttpUtil.getInstance()
                .goodsInfo(new BaseNoTObserver<GoodsInfoEntry>() {
                    @Override
                    public void onHandleSuccess(GoodsInfoEntry basebean) {

                        if (basebean == null || basebean.getData() == null)
                            return;

                        goodsPrice = basebean.getData().getPrice();
                        iOrderConfirmView.setGoodsInfo(basebean.getData());

                        if (basefreight != -1 && byfreight != -1) {

                            if (goodsPrice >= byfreight) {
                                //商品价格大于等于包邮价  免运费

                                iOrderConfirmView.setFreightInfo(false, 0.00, goodsPrice);

                            } else {
                                //   要运费
                                iOrderConfirmView.setFreightInfo(true, basefreight, goodsPrice);
                            }

                        }

                    }

                    @Override
                    public void onHandleError(String message) {
                        iOrderConfirmView.showErr(message);
                    }

                }, token, SpValue.CH, id);

    }


    /**
     * 生成订单
     *
     * @param token
     * @param addrid       收货地址ID
     * @param totalmoney   订单金额（不包括运费）
     * @param freightmoney 运费 服务订单没有运费 传0
     * @param usepoints    订单积分
     * @param skuid        商品id
     * @param skuprice     商品金额
     * @param points       商品积分
     * @param skutype      商品类型 1-实物 2-服务
     * @param skucount     商品数量
     * @param remark       备注 没有传 无
     */
    public void goodsOrder(String token, String addrid, String totalmoney, String freightmoney
            , String usepoints, String skuid, String skuprice, String points, String skutype
            , String skucount, String remark) {

        RetrofitHttpUtil.getInstance()
                .goodsOrder(new BaseNoTObserver<PicAskResutEntry>() {

                                @Override
                                public void onSubscribe(Disposable d) {
                                    super.onSubscribe(d);

                                    iOrderConfirmView.showLoading("正在生成订单...");
                                }

                                @Override
                                public void onHandleSuccess(PicAskResutEntry resutEntry) {

                                    iOrderConfirmView.hideLoading();

                                    if (resutEntry == null || resutEntry.getData() == null) {
                                        iOrderConfirmView.showErr("数据错误");
                                        return;
                                    }

                                    iOrderConfirmView.orderSuccess(resutEntry.getData());

                                }

                                @Override
                                public void onHandleError(String message) {
                                    iOrderConfirmView.hideLoading();
                                    iOrderConfirmView.showErr(message);
                                }

                            }, SpValue.CH, token, addrid, totalmoney, freightmoney, usepoints
                        , skuid, skuprice, points, skutype, skucount, remark);

    }

}
