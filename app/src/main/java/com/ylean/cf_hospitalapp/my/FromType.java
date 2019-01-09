package com.ylean.cf_hospitalapp.my;

/**
 * Created by linaidao on 2019/1/3.
 */

public class FromType {


    public static String getType(String code) {

        switch (code) {

            case "0":
                return "支付订单";

            case "1":
                return "签到";
            case "2":
                return "邀请好友";

            case "3":
                return "新用户注册";

            case "4":
                return "手机绑定";

            case "5":
                return "首次使用付费服务";

            case "6":
                return "设置头像";

            case "7":
                return "发帖一次";

            case "8":
                return "评论一次";

            case "9":
                return "分享文章";

            case "10":
                return "反馈意见并被采纳";

        }

        return "其他";

    }


}
