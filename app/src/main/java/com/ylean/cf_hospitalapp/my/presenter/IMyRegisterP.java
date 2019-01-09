package com.ylean.cf_hospitalapp.my.presenter;

import com.ylean.cf_hospitalapp.inquiry.bean.OrderEntry;
import com.ylean.cf_hospitalapp.my.view.IMyRegisteredView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 我的问诊p
 * Created by linaidao on 2018/12/24.
 */

public class IMyRegisterP {

    IMyRegisteredView iMyRegisteredView;

    //1-图文问诊 2-电话问诊 3-视频问诊
    String type = "";

    //订单状态
    String status = "";

    int page = 1;

    public IMyRegisterP(IMyRegisteredView iMyRegisteredView) {
        this.iMyRegisteredView = iMyRegisteredView;

        type = SpValue.ASK_TYPE_PIC;//默认图文
        status = SpValue.ORDER_ALL;//默认全部订单
        page = 1;

    }

    //我的问诊 currentType 当前问诊类型  图片，视频，电话
    public void myInquiry(final String currentType) {

        RetrofitHttpUtil
                .getInstance()
                .myInquiry(
                        new BaseNoTObserver<OrderEntry>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);

                                iMyRegisteredView.showLoading("获取中...");

                            }

                            @Override
                            public void onHandleSuccess(OrderEntry orderEntry) {

                                iMyRegisteredView.hideLoading();

                                if (orderEntry != null && orderEntry.getData()!=null) {
                                    List<OrderEntry.DataBean> orderEntryList = orderEntry.getData();

                                    for (int i = 0; i < orderEntryList.size(); i++) {
                                        orderEntryList.get(i).setType(currentType);
                                    }

                                    iMyRegisteredView.setOrderInfo(orderEntryList, currentType);
                                }

                            }

                            @Override
                            public void onHandleError(String message) {
                                iMyRegisteredView.hideLoading();
                                iMyRegisteredView.showErr(message);
                            }
                        }
                        , (String) SaveUtils.get(iMyRegisteredView.getContext(), SpValue.TOKEN, "")
                        , SpValue.CH
                        , type
                        , status
                        , page
                        , SpValue.PAGE_SIZE);

    }

    //设置订单状态
    public void setStatus(String status) {
        this.status = status;
    }

    //设置问诊类型
    public void setType(String type) {
        this.type = type;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public void pagePlus() {
        page++;
    }

}
