package com.ylean.cf_hospitalapp.audio;

import android.media.MediaPlayer;
import android.util.Log;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Aaron on 16/1/12.
 */
public class UPlayer {

    public static final int STOPPLAYING = 0x32;
    private String path;
    private MediaPlayer mPlayer;

    public UPlayer(String path) {

        this.path = path;
        mPlayer = new MediaPlayer();
    }

    public void start() {

        if (mPlayer == null)
            return;

        try {
            //设置要播放的文件
            mPlayer.setDataSource(path);

//            Log.d(TAG, "播放路径:" + path);

            mPlayer.prepare();
            //播放
            mPlayer.start();

//            Log.d(TAG, "=====a=====");

            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //播放完成后停止
                    stop();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Logger.e( "prepare() failed   录音准备失败  ");
        }

    }

    public void stop() {

        if (mPlayer != null) {

            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;

            //通知 音频播放已经停止
            EventBus.getDefault().post(STOPPLAYING);
        }

    }

}
