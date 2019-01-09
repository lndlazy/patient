package com.ylean.cf_hospitalapp.inquiry.view;

import com.ylean.cf_hospitalapp.inquiry.bean.ChatEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;

import java.util.List;

/**
 * Created by linaidao on 2018/12/28.
 */

public interface IInquiryView extends DataUploadView {

    void setDetailInfo(PicAskDetailEntry.DataBean data);

    void replySuccess();

    void setChatInfo(List<ChatEntry.DataBean> data, boolean isLoop);

    void endInquirySuccess();

}
