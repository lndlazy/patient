package com.ylean.cf_hospitalapp.utils;

public interface SpValue {

    String CH = "1";//登录通道(0 电脑，1安卓，2苹果)
    String PAGE_SIZE = "15";

    String TOKEN = "paient_token";//token
    String HX_NAME = "HX_NAME";//token
    String USER_PIC = "USER_PIC";//头像
    String USER_GENDER = "USER_GENDER";//性别
    String USER_NICKNAME = "USER_NICKNAME";//昵称
    String USER_ID = "USER_ID";//userid
    String USER_PATIENT_ID = "USER_PATIENT_ID";//patientid
    String USER_PHONE = "USER_PHONE";//手机号
    String ID = "ID";//id

    String USER_IMG = "user_img";//用户头像

    String SEARCH_HISTORY = "search_history";//首页医生、科室、医院、资讯 搜索历史
    String HOSPITAL_ID = "hospital_id";

    String LAT = "lat";
    String LON = "lon";
    String CITY = "city";
    String PROVINCE = "province";
    String CITY_CODE = "city_code";


    //我的家人
    String SEX = "sex";//sex：0 女 1 男
    String SEX_MALE = "1";//  男
    String SEX_FEMALE = "0";// 女


    //我的性别 1-男  2-女
    String MY_SEX_MALE = "1";//  男
    String MY_SEX_FEMALE = "2";// 女


    String FRESCO_LOCAL = "file://";//本地文件
    String FRESCO_ASSET = "asset://";//asset目录下的资源
    String FRESCO_RES = "res://";//res目录下的资源


    String ASK_TYPE_PIC = "1";
    String ASK_TYPE_TEL = "2";
    String ASK_TYPE_VIDEO = "3";
    String ASK_TYPE_FREE = "4";
//    1-图文   2-电话   3-视频


    String ASK_CHARGE = "ASK_CHARGE";//付费问诊
    String ASK_FREE = "ASK_FREE";//免费问诊


    String ASK_STATUS_WAIT_PAY = "0";//待付款
    String ASK_STATUS_OVER_TIME = "1";//已过期
    String ASK_STATUS_WAIT_SURE = "2";//待确认
    String ASK_STATUS_WAIT_COMPLY = "3";//待完成
    String ASK_STATUS_COMPLY = "4";//已完成
    String ASK_STATUS_CANCLED = "5";//已取消
    String ASK_STATUS_REFUND = "6";//申请退款中
    String ASK_STATUS_REFUND_OK = "7";//已退款
    String ASK_STATUS_REFUND_FAIL = "8";//退款不通过

    //0-待付款 1-待确认 2-待完成 3-已完成 4-已取消
//    查全部 不传

    String ORDER_ALL = "";//全部
    String ORDER_WAIT_PAY = "0";//0-待付款
    String ORDER_WAIT_SURE = "1";//1-待确认
    String ORDER_WAIT_DONE = "2";//2-待完成
    String ORDER_DONE = "3";//3-已完成
    String ORDER_CANCLE = "4";//4-已取消


    String REGISTER_CODE = "0";//注册
    String MODIFY_CODE = "1";//修改密码
    String BIND_CODE = "2";//手机绑定
    String REFIND_CODE = "3";//找回密码


    //    1-专家直播 2-专家讲堂
    String EXPERT_VIDEO = "1";//1-专家直播
    String EXPERT_SPEECH = "2";//2-专家讲堂


    /*
    评论类型（1-商品订单 2-服务订单 3-医生 4-医院 5-文章 6-视频 7-帖子）[评论 挂号订单和问诊订单时，type传 3]
    */
    String COMMIT_TYPE_GOODS = "1";
    String COMMIT_TYPE_SERVICE = "2";
    String COMMIT_TYPE_DOCTOR = "3";
    String COMMIT_TYPE_HOSPITAL = "4";
    String COMMIT_TYPE_ARTICEL = "5";
    String COMMIT_TYPE_VIDEO = "6";
    String COMMIT_TYPE_TIEZI = "7";


    String ORDER_LIST_TYPE_GOODS = "1";
    String ORDER_LIST_TYPE_SERVICE = "2";


    String QQ_APP_ID = "1106735139";
    String QQ_APP_KEY = "quxxPP5R47mwxCAX";
    // WX_APP_ID替换为你的应用从官方网站申请到的合法appId
//    String WX_APP_ID = "wxbf509e0d834bc8fa";//  微信id
    String WX_APP_ID = "wx938a108ab5898c2e";//  微信id
    // 自己微信应用的 appSecret
//    String WX_APP_SECRET = "59d80d4f92d0b09dc231df25f5ad22cd";
    String WX_APP_SECRET = "58e66b8d1ff6c2f4bdb440cae4d6a5dc";

    String WB_APP_ID = "483287855";
    String WB_APP_SECRET = "602e5b9e35ab5e06be29cbdd9ad1a254";

    String IS_FIRST = "is_first";//是否是第一次登陆
    String IS_NOTICE = "is_notice";//是否通知
//    String SPLISH_PIC_URL = "splish_pic_url";//d登录图片地址
}
