package com.ylean.cf_hospitalapp.utils;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.WindowManager;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

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
     * 改变背景颜色
     */
    public static void darkenBackground(Float bgcolor, Activity context) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgcolor;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);

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

    /**
     * 根据包名检测某个APP是否安装
     *
     * @param packageName 包名
     * @return true 安装 false 没有安装
     */
    /**
     * 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName){
        //获取packagemanager
         PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 将火星坐标转变成百度坐标
     *
     * @param lngLat_gd 火星坐标（高德、腾讯地图坐标等）
     * @return 百度坐标
     */

    public static LngLat bd_encrypt(LngLat lngLat_gd) {
        double x = lngLat_gd.getLongitude(), y = lngLat_gd.getLantitude();
        double z = sqrt(x * x + y * y) + 0.00002 * sin(y * x_pi);
        double theta = atan2(y, x) + 0.000003 * cos(x * x_pi);
        return new LngLat(dataDigit(6, z * cos(theta) + 0.0065), dataDigit(6, z * sin(theta) + 0.006));

    }

    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 将百度坐标转变成火星坐标
     *
     * @param lngLat_bd 百度坐标（百度地图坐标）
     * @return 火星坐标(高德、腾讯地图等)
     */
    public static LngLat bd_decrypt(LngLat lngLat_bd) {
        double x = lngLat_bd.getLongitude() - 0.0065, y = lngLat_bd.getLantitude() - 0.006;
        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * x_pi);
        double theta = atan2(y, x) - 0.000003 * cos(x * x_pi);
        return new LngLat(dataDigit(6, z * cos(theta)), dataDigit(6, z * sin(theta)));

    }

    /**
     * 对double类型数据保留小数点后多少位
     * 高德地图转码返回的就是 小数点后6位，为了统一封装一下
     *
     * @param digit 位数
     * @param in    输入
     * @return 保留小数位后的数
     */
    static double dataDigit(int digit, double in) {
        return new BigDecimal(in).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

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


//    private String getAppInfo(Context context) {
//        try {
//            String pkName = context.getPackageName();
//            String versionName = this.getPackageManager().getPackageInfo(
//                    pkName, 0).versionName;
//            int versionCode = this.getPackageManager()
//                    .getPackageInfo(pkName, 0).versionCode;
//            return pkName + "   " + versionName + "  " + versionCode;
//        } catch (Exception e) {
//        }
//        return null;
//    }



}
