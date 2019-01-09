package com.ylean.cf_hospitalapp.inquiry.utils;

/**
 * Created by linaidao on 2018/12/28.
 */

public interface ChatType {

    String CHAT_CONTENT_TYPE_TXT = "1";//文本聊天内容
    String CHAT_CONTENT_TYPE_VIDEO = "2";//语音聊天内容
    String CHAT_CONTENT_TYPE_PIC = "3";//图片聊天内容
    String CHAT_CONTENT_TYPE_DETAIL = "4";//问诊小结

    String CHAT_USER_TYPE_PATIENT = "2";//类型 患者
    String CHAT_USER_TYPE_DOCTOR = "1";//类型 医生

}
