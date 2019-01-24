package com.ylean.cf_hospitalapp.inquiry.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.inquiry.view.IInquiryOrderView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * Created by linaidao on 2019/1/24.
 */

public class IInquiryOrderPers {

    private IInquiryOrderView iInquiryOrderView;

    public IInquiryOrderPers(IInquiryOrderView iInquiryOrderView) {
        this.iInquiryOrderView = iInquiryOrderView;
    }


    //取消问诊订单
    public void cancleInquiryOrder(String token, String orderId, String status) {

        RetrofitHttpUtil.getInstance().cancleInquiryOrder(
                new BaseNoTObserver<Basebean>() {
                    @Override
                    public void onHandleSuccess(Basebean basebean) {

                        iInquiryOrderView.cancleSuccess();
                    }

                    @Override
                    public void onHandleError(String message) {
                        iInquiryOrderView.showErr(message);
                    }

                }, token, SpValue.CH, orderId, status, "申请退款");

    }

    //取消问诊订单
    public void deleteInquiryOrder(String token, String orderId) {

        RetrofitHttpUtil.getInstance().deleteInquiryOrder(
                new BaseNoTObserver<Basebean>() {
                    @Override
                    public void onHandleSuccess(Basebean basebean) {

                        iInquiryOrderView.deleteSuccess();
                    }

                    @Override
                    public void onHandleError(String message) {
                        iInquiryOrderView.showErr(message);
                    }

                }, token, SpValue.CH, orderId);

    }
}
