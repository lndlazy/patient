package com.ylean.cf_hospitalapp.home.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.home.bean.VideoSpeechDetailEntry;

/**
 * Created by linaidao on 2019/1/8.
 */

public interface IVideoSpeechView extends BaseView {
    void setInfo(VideoSpeechDetailEntry.DataBean data);
}
