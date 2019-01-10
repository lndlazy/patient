package com.ylean.cf_hospitalapp.my.presenter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.my.bean.BindEntry;
import com.ylean.cf_hospitalapp.my.view.ISettingView;
import com.ylean.cf_hospitalapp.my.view.TakePhotoView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.Constant;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.io.File;

import io.reactivex.disposables.Disposable;

/**
 * Created by linaidao on 2019/1/2.
 */

public class ISettingPres {

    private ISettingView iSettingView;

    public ISettingPres(ISettingView iSettingView) {
        this.iSettingView = iSettingView;
    }

    /*相册选择的code*/
    public static final int PHOTO_ALBUM_CODE = 1041;
    /*拍照的code*/
    public static final int TAKE_PHOTO_CODE = 1042;
    /* 裁剪*/
    public static final int PHOTO_RESOULT = 1043;

    private File currentFile;
    private Intent mIntent;

    public File getCurrentFile() {
        return currentFile;
    }


    //查询绑定信息
    public void bindInfo(String token) {

        RetrofitHttpUtil
                .getInstance()
                .bindInfo(
                        new BaseNoTObserver<BindEntry>() {
                            @Override
                            public void onHandleSuccess(BindEntry bindEntry) {

                                if (bindEntry != null && bindEntry.getData() != null) {
                                    iSettingView.bindInfo(bindEntry.getData());
                                }

                            }

                            @Override
                            public void onHandleError(String message) {
                                iSettingView.showErr(message);
                            }

                        }, token);

    }

    //修改个人信息 1-男 2-女
    public void updateInfo(String token, final String imgUrl, final String name, final String birthday, final String sex
            , final String location, final String address) {

        RetrofitHttpUtil
                .getInstance()
                .updateInfo(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                                if (!TextUtils.isEmpty(imgUrl))
                                    iSettingView.setImg(imgUrl);

                                if (!TextUtils.isEmpty(name))
                                    iSettingView.setName(name);

                                if (!TextUtils.isEmpty(birthday))
                                    iSettingView.setBirthday(birthday);

                                if (!TextUtils.isEmpty(sex))
                                    iSettingView.setSex(sex);

                                if (!TextUtils.isEmpty(location))
                                    iSettingView.setLocation(location);

                                if (!TextUtils.isEmpty(address))
                                    iSettingView.setAddress(address);

                            }

                            @Override
                            public void onHandleError(String message) {
                                iSettingView.showErr(message);
                            }

                        }, SpValue.CH, token, imgUrl, name, birthday, sex, location, address);
    }


    /**
     * 拍照
     *
     * @param requestCode
     * @param context
     */
    public void getTakePhoto(int requestCode, Context context) {

        String cacheDir = Environment.getExternalStorageDirectory().getPath() + Constant.PHOTO_PATH;
        File photoFile = new File(cacheDir);
        //文件夹不存在就创建文件夹
        if (!photoFile.exists()) {
            photoFile.mkdirs();
        }
        //创建存放图片的jpg文件
        currentFile = new File(photoFile, System.currentTimeMillis() + ".jpg");
//        return Uri.fromFile(currentFile);

        Uri uri = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(context,
                    Constant.FILE_URL, currentFile);
        } else {
            //创建存放图片的jpg文件
            uri = Uri.fromFile(currentFile);
        }

        if (uri == null)
            return;

        mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        iSettingView.nextActivityForPhoto(mIntent, requestCode);

    }

    public void getFromPhotoAlbum(int requestCode, Context context) {
        mIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        iSettingView.nextActivityForPhoto(mIntent, requestCode);

    }


    /**
     * 裁剪图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri, File outFile) {

        if (uri == null) {
            Logger.d("The uri is not exist.");
            return;
        }

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()); // 输出的图片格式
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(outFile.getPath()));
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
//        intent.putExtra("return-data", true);
        iSettingView.nextActivityForPhoto(intent, PHOTO_RESOULT);
    }

    /**
     * 相册选择的图片
     *
     * @param data
     */
    public Uri checkSelectPhoto(Context context, Intent data, TakePhotoView view) {

        if (data == null) {
            view.showErr("图片读取失败");
            return null;
        }

        Uri selectedImage = data.getData();
        if (selectedImage == null) {
            view.showErr("图片读取失败");
            return null;
        }

        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = view.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        if (cursor == null) {
            view.showErr("图片读取失败");
            return null;
        }
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            return FileProvider.getUriForFile(context, Constant.FILE_URL, new File(picturePath));
        else
            return Uri.fromFile(new File(picturePath));

    }

    //退出app
    public void exitApp(String token) {

        RetrofitHttpUtil.getInstance()
                .exitApp(
                        new BaseNoTObserver<Basebean>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                iSettingView.showLoading("正在退出...");
                            }

                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                iSettingView.hideLoading();
                                iSettingView.exitSuccess();
                            }

                            @Override
                            public void onHandleError(String message) {
                                iSettingView.hideLoading();
                                iSettingView.showErr(message);
                            }

                        }, token, SpValue.CH);
    }


    //绑定第三方登录
    public void bindThirdLogin(String token, String openId, String type, String name, String gender, String iconurl) {

        RetrofitHttpUtil.getInstance()
                .bindThirdLogin(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                                iSettingView.showErr("绑定成功");
                                iSettingView.bindSuccess(name, gender, iconurl);

                            }

                            @Override
                            public void onHandleError(String message) {
                                iSettingView.showErr(message);
                            }
                        }, openId, type, token);

    }

}
