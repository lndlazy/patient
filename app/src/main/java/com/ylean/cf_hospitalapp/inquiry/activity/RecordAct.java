package com.ylean.cf_hospitalapp.inquiry.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.audio.MediaRecordManager;
import com.ylean.cf_hospitalapp.base.IPicPresenter;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;
import com.ylean.cf_hospitalapp.widget.TalkView;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.Date;

/**
 * 录音界面
 * Created by linaidao on 2018/12/20.
 */

public class RecordAct extends BaseActivity implements DataUploadView {

    private static final int RECORD_RESULT_CODE = 0x107;
    private TitleBackBarView tbv;
    private static final int CAMER_PERMISSION_CODE = 0x104;
    private long speakTime;//开始录音时间
    private ImageView iv_voice;
    private TextView tv_mind;
    private static final int MDEIAVOICE = 0x66;
    private String mediaPath;

    private IPicPresenter iPicPresenter = new IPicPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        EventBus.getDefault().register(this);

        initView();
        if (!checkPermisson()) {
            showErr("没有权限");
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 是否取消语音发送, (只是用来改变界面效果, 不做最终决定权)
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(Integer action) {
        switch (action) {

            case TalkView.ACTION_DOWN://手指按下 弹出dialog

                boolean refused = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) ==
                        PackageManager.PERMISSION_DENIED;

                if (refused) {// 没有权限
                    showErr("没有录音权限");
                    return;
                } else //有权限
                    pressure2speak();

                break;

            case TalkView.ACTION_MOVE_SEND:
                if (tv_mind != null) {
                    tv_mind.setText(getResources().getString(R.string.slide_up_cancle_send));
                    tv_mind.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
//                mHandler.post(voiceRunnable);
                break;

            case TalkView.ACTION_MOVE_CANCLE://取消发送
                if (tv_mind != null) {
                    tv_mind.setText(getResources().getString(R.string.release_cancle_send));
                    tv_mind.setBackgroundColor(getResources().getColor(R.color.red));
                }
                break;

            case TalkView.ACTION_UP://手指抬起,dialog消失
//                Logger.d("--------------手指松开 up--------------");
                mHandler.post(dismissRunnable);
                break;

            case TalkView.ACTION_UP_SHORT_TIME://录音时间过短
//                Logger.d("--------------手指松开 时间太短 up--------------");
                if (tv_mind != null) {
                    tv_mind.setText(getResources().getString(R.string.speak_short));
                }
                mHandler.post(dismissRunnable);
                break;

//            case TalkView.ACTION_STOP_CANCLE:
//                break;
            case TalkView.ACTION_STOP_SEND:

                //上传语音
                uploadVoiceData();

                break;
        }

    }

//    private void stopRecordAndDelete() {
//        //停止录音
//        MediaRecordManager.getInstance().stopRecordAndFile();
//        //删除录音
//        MediaRecordManager.getInstance().deleteMediaRecord();
//    }

    private void pressure2speak() {
        speakTime = System.currentTimeMillis();//记录按下的时间
        mHandler.post(voiceRunnable);//监听话筒分贝
        showDiago();
    }

    /**
     * 弹出语音框
     */
    private void showDiago() {

        if (dialog == null) {
            dialog = new Dialog(this, R.style.no_title);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.setContentView(R.layout.dialog_chat_speak);
            iv_voice = dialog.findViewById(R.id.iv_voice);
            tv_mind = dialog.findViewById(R.id.tv_mind);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    //停止录音
                    MediaRecordManager.getInstance().stopRecordAndFile();
                }
            });
        } else {
            dialog.show();
        }
    }


    private Dialog dialog;
    //实时改变音量大小的runnable
    private Runnable voiceRunnable = new Runnable() {

        @Override
        public void run() {

            Logger.d("  改变音量大小的 runnable  " + Thread.currentThread().getName() + ",时间:" + new Date().getTime());
            if (MediaRecordManager.getInstance().mMediaRecorder == null)
                return;

//            Logger.d("  mMediaRecorder 为 null 了？？？ ");

            int maxAmplitude = MediaRecordManager.getInstance().mMediaRecorder.getMaxAmplitude();
            Message msg = Message.obtain();
            msg.what = MDEIAVOICE;
            msg.obj = maxAmplitude;
            mHandler.sendMessage(msg);

        }
    };

    //让progress消失的runnable
    private Runnable dismissRunnable = new Runnable() {
        @Override
        public void run() {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
        }
    };

    //上传录音文件 TODO
    private void uploadVoiceData() {


//        Logger.d("****上传录音文件****");

        if (TextUtils.isEmpty(MediaRecordManager.getInstance().getMediaPath())) {
            showErr("获取录音文件失败");
            return;
        }


        mediaPath = MediaRecordManager.getInstance().getMediaPath();

        iPicPresenter.uploadVoice(MediaRecordManager.getInstance().getMediaPath());


//        //录音时长
//        int round = Math.round(MediaPlayer.create(this,
//                Uri.parse(MediaRecordManager.getInstance().getMediaPath())).getDuration() / 1000);
//
//        Logger.d("时长：：：" + round);
//        tvVoice.setVisibility(View.VISIBLE);
//
//        tvVoice.setText(round + "s");

//        File file = new File(MediaRecordManager.getInstance().getMediaPath());
//        RequestBody voiceBody = RequestBody.create(MediaType.parse("application/octet-stream")
//                , file);
//
//        MultipartBody.Part part = MultipartBody.Part.createFormData("Filedata", file.getName(), voiceBody);
//
//        RetrofitHttpUtil.getInstance().uploadVoice(new BaseNoTObserver<DataUploadResultEntry>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                super.onSubscribe(d);
//
//                showLoading("正在上传语音...");
//            }
//
//            @Override
//            public void onHandleSuccess(DataUploadResultEntry basebean) {
//
//                hideLoading();
//                if (basebean == null || basebean.getData() == null || basebean.getData().size() < 1)
//                    return;
//
//                Intent m = new Intent();
//                m.putExtra("voiceUrl", basebean.getData().get(0));
//                m.putExtra("mediaPath", mediaPath);
//                setResult(RECORD_RESULT_CODE, m);
//                finish();
//
//            }
//
//            @Override
//            public void onHandleError(String message) {
//                hideLoading();
//                showErr(message);
//            }
//
//        }, SpValue.CH, RelationType.OTHER_IMG, part);

    }


    private MyHandler mHandler = new MyHandler(this);

    @Override
    public void picUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {

    }

    //语音上传成功
    @Override
    public void voiceUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {
        Intent m = new Intent();
        m.putExtra("voiceUrl", dataUploadResultEntry.getData().get(0));
        m.putExtra("mediaPath", mediaPath);
        setResult(RECORD_RESULT_CODE, m);
        finish();
    }
//    private ScheduledExecutorService scheduledExecutor;

    private class MyHandler extends Handler {
        private final WeakReference<RecordAct> mActivity;

        MyHandler(RecordAct activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            RecordAct activity = mActivity.get();
            if (activity != null) {

                switch (msg.what) {

                    case MDEIAVOICE://录音音量
                        //更新麦克风录音大小状态
                        updateVoiceStatus((int) msg.obj);

                        long second = (System.currentTimeMillis() - speakTime) / 1000;

                        Logger.d(" 当前说了 : " + second + "秒");

//                        if (second >= 50) {//大于了50秒
                        if (second >= 60) {// 大于60秒 停止录音 发送录音文件, popup消失
                            stopRecord();
                            if (dialog != null && dialog.isShowing())
                                dialog.dismiss();
                        } else {
                            this.postDelayed(voiceRunnable, 300);// 间隔取样时间 300毫秒
                        }


                        break;

                }

            } else {
                //如果外部类
                mHandler = null;
            }
        }
    }

    private void stopRecord() {

        //停止录音
        MediaRecordManager.getInstance().stopRecordAndFile();

        //上传录音文件
        uploadVoiceData();
    }


    private boolean checkPermisson() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                    CAMER_PERMISSION_CODE);

            return false;
        }
        return true;
    }


    private void initView() {
        this.tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        TalkView tvTalkView = findViewById(R.id.tvTalkView);
    }


    /**
     * 更新麦克风音量变化
     * 更新话筒状态 分贝是也就是相对响度 分贝的计算公式K=20lg(Vo/Vi) Vo当前振幅值 Vi基准值为600：
     * 怎么制定基准值的呢？ 当20* Math.log10(mMediaRecorder.getMaxAmplitude() / Vi)==0的时候vi就是我所需要的基准值
     * 当我不对着麦克风说任何话的时候，测试获得的mMediaRecorder.getMaxAmplitude()值即为基准值。
     * Log.i("mic_", "麦克风的基准值：" + mMediaRecorder.getMaxAmplitude());前提时不对麦克风说任何话
     */
    private void updateVoiceStatus(int voice) {

        if (dialog == null || !dialog.isShowing())
            return;

        int ratio = voice / 50;//Vi基准值50
        int db = 0;// 分贝

        if (ratio > 1)
            db = (int) (20 * Math.log10(ratio));
//        LogUtil.e("tag", "分贝值：" + db + "     " + Math.log10(ratio));
        switch (db / 9) {

            case 0:
                iv_voice.setImageResource(R.mipmap.ic_voice_1);
                break;

            case 1:
                iv_voice.setImageResource(R.mipmap.ic_voice_2);
                break;

            case 2:
                iv_voice.setImageResource(R.mipmap.ic_voice_3);
                break;

            case 3:
                iv_voice.setImageResource(R.mipmap.ic_voice_4);
                break;

            case 4:
                iv_voice.setImageResource(R.mipmap.ic_voice_5);
                break;

            case 5:
                iv_voice.setImageResource(R.mipmap.ic_voice_6);
                break;

            case 6:
                iv_voice.setImageResource(R.mipmap.ic_voice_7);
                break;

            default:
                break;
        }

    }
}
