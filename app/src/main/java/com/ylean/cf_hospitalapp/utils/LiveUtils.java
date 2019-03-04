package com.ylean.cf_hospitalapp.utils;

import com.orhanobut.logger.Logger;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LiveUtils {


    //获取拉流地址
    public static String get_url(String streamName) {

        String appname = "Changfeng";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String currtime = simpleDateFormat.format(date);
        long l = Utils.timeDifference("1970-1-1 00:00:00", currtime) / 1000;
        String timestamp = l + "";
        Logger.d(Utils.timeDifference("1970-1-1 00:00:00", currtime) + "" + "======" + timestamp);
        String main_key = "CGiRn89XWI";
        String str = "/" + appname + "/" + streamName + ".flv-" + timestamp + "-0-0-" + main_key;
        Logger.d(str);
        String sign = md5(str);
        String str_key = timestamp + "-0-0-" + sign;
        String msURI = "http://mlive.goodywy.com/" + appname + "/" + streamName + ".flv?auth_key=" + str_key;
        Logger.d("拉流地址:::" + msURI);
        return msURI;
    }

    /**
     * 获取时间差
     *
     * @param nowtime
     * @param endtime
     * @return
     */
    private static long timeDifference(String nowtime, String endtime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long diff = 0;
        try {
            //系统时间转化为Date形式
            Date dstart = format.parse(nowtime);
            //活动结束时间转化为Date形式
            Date dend = format.parse(endtime);
            //算出时间差，用ms表示
            diff = dend.getTime() - dstart.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //返回时间差
        return diff;
    }


    /**
     * 加密
     *
     * @param plaintext 明文
     * @return ciphertext 密文
     */
    private static String md5(String plaintext) {


        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
