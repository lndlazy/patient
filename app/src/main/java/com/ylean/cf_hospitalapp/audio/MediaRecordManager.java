package com.ylean.cf_hospitalapp.audio;

import android.content.Context;
import android.media.MediaRecorder;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.utils.Constant;

import java.io.File;
import java.io.IOException;

/**
 * 录音管理
 * Created by Aaron on 15/12/23.
 */
public class MediaRecordManager {

    private boolean isRecord = false;

    public MediaRecorder mMediaRecorder;
    private File file;

    private MediaRecordManager() {
    }

    private static MediaRecordManager mInstance;

    public synchronized static MediaRecordManager getInstance() {
        if (mInstance == null)
            mInstance = new MediaRecordManager();
        return mInstance;
    }

    /**
     * 开始录音
     *
     * @param ctx
     * @return
     */
    public int startRecordAndFile(Context ctx) throws Exception {
        //判断是否有外部存储设备sdcard
        if (AudioFileFunc.isSdcardExit()) {
            if (isRecord) {
                return Constant.E_STATE_RECODING;
            } else {
                if (mMediaRecorder == null)
                    createMediaRecord(ctx);

                try {
                    mMediaRecorder.prepare();
                    mMediaRecorder.start();

                    // 让录制状态为true
                    isRecord = true;
                    return Constant.SUCCESS;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return Constant.E_UNKOWN;
                }
            }

        } else {
            return Constant.E_NOSDCARD;
        }
    }


    public void stopRecordAndFile() {

        Logger.d("===停止录音===");
        try {
            if (mMediaRecorder != null) {
//            System.out.println("stopRecord");
                isRecord = false;
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createMediaRecord(Context ctx) throws Exception {
         /* ①Initial：实例化MediaRecorder对象 */
        mMediaRecorder = new MediaRecorder();

        try {
              /* setAudioSource/setVedioSource*/
            mMediaRecorder.setAudioSource(AudioFileFunc.AUDIO_INPUT);//设置麦克风
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ctx, "请允许录音权限", Toast.LENGTH_SHORT).show();
            return;
        }

        /* 设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default
         * THREE_GPP(3gp格式，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
         */
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);

         /* 设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default */
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

         /* 设置输出文件的路径 */
        String amrFilePath = AudioFileFunc.getAMRFilePath();

        if (amrFilePath == null)
            throw new Exception(" 没有外部存储设备 ");

        file = new File(amrFilePath);

//        if (!file.exists()) {
//            file.mkdir();
//        }

        mMediaRecorder.setOutputFile(amrFilePath);
    }

    /**
     * 获得录音文件的路径
     *
     * @return
     */
    public String getMediaPath() {

        if (file != null && file.exists()) {

            try {
                return file.getPath();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Logger.d("文件不存在");
            return null;
        }
    }

    /**
     * 删除录音文件
     */
    public void deleteMediaRecord() {

        if (file != null && file.exists())
            file.delete();

    }

}
