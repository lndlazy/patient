package com.ylean.cf_hospitalapp.base.view;

import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;

/**
 * Created by linaidao on 2018/12/30.
 */

public interface DataUploadView extends BaseView {

    //图片上传成功
    void picUploadSuccess(DataUploadResultEntry dataUploadResultEntry);

    //语音上传成功
    void voiceUploadSuccess(DataUploadResultEntry dataUploadResultEntry);


}
