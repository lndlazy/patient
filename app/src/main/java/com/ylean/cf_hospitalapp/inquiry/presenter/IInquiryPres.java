package com.ylean.cf_hospitalapp.inquiry.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.ylean.cf_hospitalapp.inquiry.bean.ChatEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.inquiry.view.IInquiryView;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.Constant;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.io.File;

import io.reactivex.disposables.Disposable;

/**
 * Created by linaidao on 2018/12/28.
 */

public class IInquiryPres {

    private IInquiryView iInquiryView;
    private String consultaid;

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

    public IInquiryPres(IInquiryView iInquiryView) {
        this.iInquiryView = iInquiryView;
    }

    public String getConsultaid() {
        return consultaid;
    }

    public void setConsultaid(String consultaid) {
        this.consultaid = consultaid;
    }

    public void detailInfo(String token) {

        RetrofitHttpUtil
                .getInstance()
                .getFreePicAskDetail(
                        new BaseNoTObserver<PicAskDetailEntry>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                iInquiryView.showLoading("获取中...");
                            }

                            @Override
                            public void onHandleSuccess(PicAskDetailEntry detailEntry) {
                                iInquiryView.hideLoading();

                                if (detailEntry == null || detailEntry.getData() == null)
                                    return;

                                iInquiryView.setDetailInfo(detailEntry.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iInquiryView.hideLoading();
                                iInquiryView.showErr(message);
                            }

                        }
                        , SpValue.CH
                        , token
                        , consultaid);

    }

    public void chatList(String token, final boolean isLoop) {

        RetrofitHttpUtil
                .getInstance()
                .getChatInfo(
                        new BaseNoTObserver<ChatEntry>() {
                            @Override
                            public void onHandleSuccess(ChatEntry chatEntry) {

                                if (chatEntry == null || chatEntry.getData() == null)
                                    return;

                                iInquiryView.setChatInfo(chatEntry.getData(), isLoop);

                            }

                            @Override
                            public void onHandleError(String message) {
                                iInquiryView.showErr(message);
                            }

                        }
                        , SpValue.CH
                        , token
                        , consultaid
                );

    }


    //聊天回复
    public void chatReply(String content, String token, String type, String userType) {

        RetrofitHttpUtil
                .getInstance()
                .chatReply(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                iInquiryView.replySuccess();
                            }

                            @Override
                            public void onHandleError(String message) {
                                iInquiryView.showErr(message);
                            }

                        }
                        , SpValue.CH
                        , token
                        , consultaid
                        , content
                        , type
                        , userType);

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
        iInquiryView.nextActivityForPhoto(mIntent, requestCode);

    }

    public void getFromPhotoAlbum(int requestCode, Context context) {
        mIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        iInquiryView.nextActivityForPhoto(mIntent, requestCode);

    }

    //结束问诊
    public void endInquiry(String token) {

        RetrofitHttpUtil
                .getInstance()
                .endInquiry(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                                iInquiryView.showErr("结束问诊成功");
                                iInquiryView.endInquirySuccess();

                            }

                            @Override
                            public void onHandleError(String message) {
                                iInquiryView.showErr(message);
                            }

                        }, SpValue.CH
                        , token
                        , consultaid);


    }
}
