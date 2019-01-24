package com.ylean.cf_hospitalapp.comm.pres;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.comm.view.IGoodsinfoView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * 删除订单, 确认收货
 * Created by linaidao on 2019/1/23.
 */

public class IGoodsInfoPres {

    private IGoodsinfoView iDeleteView;

    public IGoodsInfoPres(IGoodsinfoView iDeleteView) {
        this.iDeleteView = iDeleteView;
    }

    //删除订单
    public void deleteGoodsOrder(String token, String id) {

        RetrofitHttpUtil.getInstance().deleteGoodsOrder(
                new BaseNoTObserver<Basebean>() {
                    @Override
                    public void onHandleSuccess(Basebean basebean) {

                        iDeleteView.deleteSuccess();

                    }

                    @Override
                    public void onHandleError(String message) {
                        iDeleteView.showErr(message);
                    }

                }, SpValue.CH, token, id);

    }


    //确认收货
    public void confirmReceive(String token, String id) {

        RetrofitHttpUtil.getInstance().confirmReceive(
                new BaseNoTObserver<Basebean>() {
                    @Override
                    public void onHandleSuccess(Basebean basebean) {

                        iDeleteView.confirmSuccess();

                    }

                    @Override
                    public void onHandleError(String message) {
                        iDeleteView.showErr(message);
                    }

                }, SpValue.CH, token, id);
    }

}
