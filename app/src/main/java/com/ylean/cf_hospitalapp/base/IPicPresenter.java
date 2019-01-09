package com.ylean.cf_hospitalapp.base;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.Constant;
import com.ylean.cf_hospitalapp.utils.RelationType;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.io.File;
import java.util.List;
import java.util.UUID;

import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 语音，图片上传的presenter
 * Created by linaidao on 2018/12/30.
 */

public class IPicPresenter {

    private DataUploadView dataUploadView;

    public IPicPresenter(DataUploadView dataUploadView) {
        this.dataUploadView = dataUploadView;
    }

    /**
     * 上传图片
     *
     * @param picPath 图片的本地地址集合
     */
    public void uploadPic(List<String> picPath) {

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
        for (int i = 0; i < picPath.size(); i++) {
            File file = new File(picPath.get(i));
            RequestBody imageBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            builder.addFormDataPart("Filedata", Constant.PIC_NAME + UUID.randomUUID() + ".jpg", imageBody);
        }

        RetrofitHttpUtil.getInstance().uploadImages(new BaseNoTObserver<DataUploadResultEntry>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                dataUploadView.showLoading("正在上传图片");
            }

            @Override
            public void onHandleSuccess(DataUploadResultEntry dataUploadResultEntry) {

                dataUploadView.hideLoading();

                if (dataUploadResultEntry == null || dataUploadResultEntry.getData() == null ||
                        dataUploadResultEntry.getData().size() < 1)
                    return;

                dataUploadView.picUploadSuccess(dataUploadResultEntry);

            }

            @Override
            public void onHandleError(String message) {
                dataUploadView.hideLoading();
                dataUploadView.showErr(message);

            }
        }, SpValue.CH, RelationType.OTHER_IMG, builder.build().parts());

    }


    /**
     * 上传语音
     *
     * @param fileName 语音文件名称
     */
    public void uploadVoice(String fileName) {

        File file = new File(fileName);
        RequestBody voiceBody = RequestBody.create(MediaType.parse("application/octet-stream")
                , file);

        MultipartBody.Part part = MultipartBody.Part.createFormData("Filedata", file.getName(), voiceBody);

        RetrofitHttpUtil.getInstance().uploadVoice(new BaseNoTObserver<DataUploadResultEntry>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);

                dataUploadView.showLoading("正在上传语音...");
            }

            @Override
            public void onHandleSuccess(DataUploadResultEntry basebean) {

                dataUploadView.hideLoading();
                if (basebean == null || basebean.getData() == null || basebean.getData().size() < 1)
                    return;

                dataUploadView.voiceUploadSuccess(basebean);

            }

            @Override
            public void onHandleError(String message) {
                dataUploadView.hideLoading();
                dataUploadView.showErr(message);
            }

        }, SpValue.CH, RelationType.OTHER_IMG, part);

    }


}
