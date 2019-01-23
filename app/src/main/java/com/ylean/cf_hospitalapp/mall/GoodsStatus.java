package com.ylean.cf_hospitalapp.mall;

/**
 * Created by linaidao on 2019/1/21.
 */

public interface GoodsStatus {

    String WAIT_PAY = "0";//待付款
    String WAIT_SEND = "1";//待发货(
    String WAIT_RECEIVE = "2";//待收货
    String RECEIVE = "3";//已确认收货(
    String CANCLE = "4";//已取消
    String REBACKING = "5";//退货中
    String BACK_SUCCESS = "6";//已退款
    String REBACK_FAIL = "7";//退款不通过
    String DONE = "8";//已完成
    String WAIT_USE = "10";//待使用

    String EXCHANGEING = "11";//换货中
    String ALREADY_EXCHANGING = "12";//已换货
    String EXCHANGE_RESUFED = "13";//换货不通过

}
