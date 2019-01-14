package com.ylean.cf_hospitalapp.home.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.home.bean.VideoSpeechDetailEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/8.
 */

public interface IVideoSpeechView extends BaseView {

    void setInfo(VideoSpeechDetailEntry.DataBean data);

    void setCommentData(List<CommComListEntry.DataBean> data);

}
