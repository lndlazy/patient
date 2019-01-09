package com.ylean.cf_hospitalapp.register;

/**
 * Created by linaidao on 2019/1/6.
 */

public interface PayStatus {


    String STATUS_WAIT_PAY = "0";//待付款
    String STATUS_WAIT_USE = "1";//待使用
    String STATUS_USED = "2";//已使用
    String STATUS_REFUND = "4";//申请退款中
    String STATUS_REFUNDED = "5";//已退款
    String STATUS_REFUND_REFUSED = "6";//退款不通过
    String STATUS_CANCLED = "7";//已取消

}
