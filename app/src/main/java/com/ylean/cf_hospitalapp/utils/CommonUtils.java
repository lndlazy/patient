package com.ylean.cf_hospitalapp.utils;

import android.content.ClipboardManager;
import android.content.Context;

import java.text.DecimalFormat;

public class CommonUtils {

    //保留两位小数
    public static String getNum2(double n) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(n);
    }


    //复制文本数据到剪切板
    public static void copy2clipboard(Context context, String txt) {

        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(txt);
    }


    /**
     * 获取关系
     *
     * @param code
     * @return
     */
    public static String getRelationship(String code) {

        switch (code) {

            case "0":
                return "爷爷";
            case "1":
                return "奶奶";
            case "2":
                return "父亲";
            case "3":
                return "母亲";
            case "4":
                return "配偶";
            case "5":
                return "我";
            case "6":
                return "子女";
            case "7":
            default:
                return "其他";

        }

    }


    public static String getCommentType(String typeCode) {

        switch (typeCode) {

            case SpValue.COMMIT_TYPE_GOODS:
                return "【商品订单】";
            case SpValue.COMMIT_TYPE_SERVICE:
                return "【服务订单】";
            case SpValue.COMMIT_TYPE_DOCTOR:
                return "【医生】";
            case SpValue.COMMIT_TYPE_HOSPITAL:
                return "【医院】";
            case SpValue.COMMIT_TYPE_ARTICEL:
                return "【文章】";
            case SpValue.COMMIT_TYPE_VIDEO:
                return "【视频】";
            case SpValue.COMMIT_TYPE_TIEZI:
                return "【帖子】";
        }
        return "";

    }

}
