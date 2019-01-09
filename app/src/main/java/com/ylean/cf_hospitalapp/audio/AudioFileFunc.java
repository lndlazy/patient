package com.ylean.cf_hospitalapp.audio;

import android.media.MediaRecorder;
import android.os.Environment;

import com.ylean.cf_hospitalapp.utils.Constant;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 录音帮助类
 * Created by Aaron on 15/12/23.
 */
public class AudioFileFunc {

    //音频输入-麦克风
    public final static int AUDIO_INPUT = MediaRecorder.AudioSource.MIC;

    //采用频率
    //44100是目前的标准，但是某些设备仍然支持22050，16000，11025
//    public final static int AUDIO_SAMPLE_RATE = 44100;  //44.1KHz,普遍使用的频率
    //录音输出文件
//    private final static String AUDIO_RAW_FILENAME = "RawAudio.raw";
//    private final static String AUDIO_WAV_FILENAME = "FinalAudio.wav";
//    public final static String AUDIO_AMR_FILENAME = "FinalAudio.amr";

    /**
     * 判断是否有外部存储设备sdcard
     *
     * @return true | false
     */
    public static boolean isSdcardExit() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

//    /**
//     * 获取麦克风输入的原始音频流文件路径
//     *
//     * @return
//     */
//    public static String getRawFilePath() {
//        String mAudioRawPath = "";
//        if (isSdcardExit()) {
//            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
//            mAudioRawPath = fileBasePath + "/" + AUDIO_RAW_FILENAME;
//        }
//
//        return mAudioRawPath;
//    }
//
//    /**
//     * 获取编码后的WAV格式音频文件路径
//     *
//     * @return
//     */
//    public static String getWavFilePath() {
//        String mAudioWavPath = "";
//        if (isSdcardExit()) {
//            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
//            mAudioWavPath = fileBasePath + "/" + AUDIO_WAV_FILENAME;
//        }
//        return mAudioWavPath;
//    }


    /**
     * 获取编码后的AMR格式音频文件路径
     * // 路径为 /mlxCache/时间戳.amr
     *
     * @return
     */
    public static String getAMRFilePath() {
        String currentDir = null;
        if (isSdcardExit()) {

//            String cacheDirHint = Environment.getExternalStorageDirectory().getPath() + Constant.PHOTO_PATH;
            String cacheDirHint = Environment.getExternalStorageDirectory().getPath() + Constant.PHOTO_PATH_HINT;

            File photoHint = new File(cacheDirHint);
            //创建隐藏文件夹
            if (!photoHint.exists()) {
                photoHint.mkdirs();

                //再创建 .nomedia 文件nomedia
                File noMedia = new File(cacheDirHint + ".nomedia");
                if (!noMedia.exists()) {
                    try {
                        noMedia.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else noMedia = null;

            }

            currentDir = photoHint.getAbsolutePath() + "/" + UUID.randomUUID().toString() + ".amr";

        } else {
            //TODO 没有存储设备
        }
        return currentDir;
    }


//    /**
//     * 获取文件大小
//     *
//     * @param path,文件的绝对路径
//     * @return
//     */
//    public static long getFileSize(String path) {
//        File mFile = new File(path);
//        if (!mFile.exists())
//            return -1;
//        return mFile.length();
//    }


}
