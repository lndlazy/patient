package com.ylean.cf_hospitalapp.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.PicassoImageLoader;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Mapplication extends Application {

    private static final String TAG = "PATIENT_APP";

    @Override
    public void onCreate() {
        super.onCreate();

        initLogger(TAG, true);
        Fresco.initialize(this);

//        Logger.d("sha1值::" + sHA1(this));

        initPicChoose();
        /**
         * 友盟
         * */
        Config.DEBUG = true;
        UMShareAPI.get(this);//初始化sdk
        //三方登录
        PlatformConfig.setWeixin(SpValue.WX_APP_ID, SpValue.WX_APP_SECRET);
        PlatformConfig.setQQZone(SpValue.QQ_APP_ID, SpValue.QQ_APP_KEY);
        PlatformConfig.setSinaWeibo(SpValue.WB_APP_ID, SpValue.WB_APP_SECRET, "https://sns.whalecloud.com/sina2/callback");


//        RetrofitHttpUtil.getInstance().loginIn(new BaseNoTObserver<LoginEntry>() {
//            @Override
//            public void onHandleSuccess(LoginEntry loginEntry) {
//
////                SaveUtils.put(HomeActivity.this, SpValue.TOKEN, loginEntry.getToken());
////                Logger.d("登录成功缓存token：：" + loginEntry.getToken());
//
////                SharedPreferences sp = getSharedPreferences(SaveUtils.CONFIG, Context.MODE_PRIVATE);
//
//                SaveUtils.put(getApplicationContext(), SpValue.TOKEN, loginEntry.getToken());
////                sp.edit().putString("patient_taken", loginEntry.getToken()).apply();
////                Logger.d("存储的数据::" + sp.getString("patient_taken", ""));
//
//            }
//
//            @Override
//            public void onHandleError(String message) {
//
//            }
//
//        }, SpValue.CH, "18610915892", "123456");
    }

    private void initPicChoose() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    public String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
            }
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void initLogger(String tag, final boolean isShow) {
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .tag(tag) // 全局tag
//                .build();

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // 是否显示线程信息，默认 显示
                .methodCount(2)         // 方法栈打印的个数，默认是 2
                .methodOffset(5)        // 设置调用堆栈的函数偏移值，默认是 5
//                .logStrategy(customLog) // 设置log打印策略，默认是 LogCat
                .tag(tag)   // 设置全局TAG，默认是 PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
//                return BuildConfig.DEBUG;
                return isShow;
            }
        });

//        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
//        Logger.addLogAdapter(new DiskLogAdapter());
    }
}
