package com.ylean.cf_hospitalapp.home.presenter;

import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.home.bean.VideoSpeechDetailEntry;
import com.ylean.cf_hospitalapp.home.view.IVideoSpeechView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * Created by linaidao on 2019/1/8.
 */

public class IVideoSpeechPres {

    private IVideoSpeechView iVideoSpeechView;

    public IVideoSpeechPres(IVideoSpeechView iVideoSpeechView) {
        this.iVideoSpeechView = iVideoSpeechView;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void videoSpeechDetail(String token) {

        RetrofitHttpUtil.getInstance()
                .videoSpeechDetail(
                        new BaseNoTObserver<VideoSpeechDetailEntry>() {
                            @Override
                            public void onHandleSuccess(VideoSpeechDetailEntry videoSpeechDetail) {

                                if (videoSpeechDetail == null || videoSpeechDetail.getData() == null)
                                    return;

                                iVideoSpeechView.setInfo(videoSpeechDetail.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iVideoSpeechView.showErr(message);
                            }

                        }, token, SpValue.CH, id);


    }


    public void videoCommentList(String token) {

        RetrofitHttpUtil.getInstance()
                .videoCommentList(
                        new BaseNoTObserver<CommComListEntry>() {
                            @Override
                            public void onHandleSuccess(CommComListEntry basebean) {

                                if (basebean == null || basebean.getData() == null)
                                    return;

                                iVideoSpeechView.setCommentData(basebean.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iVideoSpeechView.showErr(message);
                            }
                        }, SpValue.CH, token, id, 1, Integer.MAX_VALUE + "");

    }
}
