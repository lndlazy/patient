package com.ylean.cf_hospitalapp.my.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.my.view.IEvaluateView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * 评价presenter
 * Created by linaidao on 2019/1/2.
 */

public class IEvaluatePres {

    private IEvaluateView iEvaluateView;

    public IEvaluatePres(IEvaluateView iEvaluateView) {
        this.iEvaluateView = iEvaluateView;
    }

    /**
     * @param token
     * @param relateid        问诊订单和挂号订单评论的是 医生，所以传医生id  商品订单和服务订单 relateid 传 商品id
     * @param ordercode       评论 挂号订单，问诊订单，商品订单，服务订单时需要传订单编号
     * @param content         评论内容
     * @param stardepict      商品描述， 治疗效果
     * @param starservice     服务态度， 物流
     * @param starperformance 性价比
     * @param type            评论类型（1-商品订单 2-服务订单 3-医生 4-医院 5-文章 6-视频 7-帖子） 论 挂号订单和问诊订单时，type传 3
     * @param imgs            评论图片
     * @param ordertype       订单类型1图文问诊2电话问诊3视频问诊4挂号订单[评论挂号订单和问诊订单是需要传 ordertype]
     */
    public void addEvaluate(String token, String relateid, String ordercode
            , String content, String stardepict, String starservice, String starperformance
            , String type, String imgs, String ordertype) {

        RetrofitHttpUtil
                .getInstance()
                .addEvaluation(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                iEvaluateView.evaluateSuccess();
                            }

                            @Override
                            public void onHandleError(String message) {
                                iEvaluateView.showErr(message);
                            }

                        }, SpValue.CH, token, relateid, ordercode
                        , content, stardepict, starservice, starperformance, type, imgs, ordertype);

    }

}
