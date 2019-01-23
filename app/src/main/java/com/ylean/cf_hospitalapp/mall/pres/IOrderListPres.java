package com.ylean.cf_hospitalapp.mall.pres;

import com.ylean.cf_hospitalapp.mall.bean.MallOrderEntry;
import com.ylean.cf_hospitalapp.mall.view.IOrderListView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * Created by linaidao on 2019/1/21.
 */

public class IOrderListPres {

    private IOrderListView iOrderListView;

    public IOrderListPres(IOrderListView iOrderListView) {
        this.iOrderListView = iOrderListView;
    }

    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    //    private int page_all = 1;//全部
//    private int page_wait_pay = 1;//待支付
//    private int page_wait_send = 1;//待发货
//    private int page_wait_receive = 1;//待收货
//    private int page_done = 1;//已完成
//    private int page_wait_use = 1;//待使用

    public void serviceOrder(String token, String status, final boolean refush, int page, String type) {

        RetrofitHttpUtil.getInstance().serviceList(
                new BaseNoTObserver<MallOrderEntry>() {
                    @Override
                    public void onHandleSuccess(MallOrderEntry basebean) {
                        iOrderListView.stopRefush();

                        if (basebean == null || basebean.getData() == null)
                            return;

                        iOrderListView.setOrderList(basebean.getData(), refush);

                    }

                    @Override
                    public void onHandleError(String message) {
                        iOrderListView.stopRefush();
                        iOrderListView.showErr(message);
                    }

                }, SpValue.CH, token, status, type, page, SpValue.PAGE_SIZE);

    }

}
