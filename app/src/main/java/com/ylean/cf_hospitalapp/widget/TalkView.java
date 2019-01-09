package com.ylean.cf_hospitalapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.audio.MediaRecordManager;

import org.greenrobot.eventbus.EventBus;

/**
 * 录音按钮
 * Created by linaidao on 2018/12/20.
 */

public class TalkView extends Button {

    private float pressY;//按下时的y点坐标
    //    private float pressY;
    private boolean isCancle;//是否取消发送

    //    private long currentTime;//当前的时间
    private float chageY;//
    //    private float moveY;
    private final long CHANGE_Y = 50;

    public static final int ACTION_NORMAL = 0x1;
    public static final int ACTION_DOWN = 0x72;//手指按下,开始录音
    public static final int ACTION_MOVE_SEND = 0x73;//手指移动,没有移动到一定距离,可以发送
    public static final int ACTION_MOVE_CANCLE = 0x74;//手指移动距离大,取消发送
    public static final int ACTION_UP = 0x75;//手指抬起
    public static final int ACTION_UP_SHORT_TIME = 0x76;//说话时间过短,抬起
    public static final int ACTION_STOP_SEND = 0x77;//说话时间过短,抬起
    public static final int ACTION_STOP_CANCLE = 0x78;//说话时间过短,抬起
    private long speakTime;
    private Context ctx;

    public TalkView(Context context) {
        this(context, null);
    }

    public TalkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TalkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.ctx = context;
        isCancle = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN://按下
//                Logger.d("----按下----");

                pressY = event.getY();

                speakTime = System.currentTimeMillis();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //开始录音
                        try {
                            MediaRecordManager.getInstance().startRecordAndFile(getContext());
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }).start();
                EventBus.getDefault().post(ACTION_DOWN);
                break;

            case MotionEvent.ACTION_MOVE://手指移动

                chageY = Math.abs(event.getY() - pressY);
//                Logger.d("----移动----" + chageY);
                if (chageY > CHANGE_Y) {
                    //滑动的Y轴方向大于50像素
                    isCancle = true;
                    EventBus.getDefault().post(ACTION_MOVE_CANCLE);//通知 页面取消发送语音

                } else {
                    isCancle = false;
                    EventBus.getDefault().post(ACTION_MOVE_SEND);//通知 页面改变语音 音量
                }

                break;

            case MotionEvent.ACTION_UP://手指抬起
            case MotionEvent.ACTION_CANCEL:
//                Logger.d("----抬起----");

                if ((System.currentTimeMillis() - speakTime) < 1000) {//说话时间太短
                    EventBus.getDefault().post(ACTION_UP_SHORT_TIME);
                    isCancle = true;
                } else
                    EventBus.getDefault().post(ACTION_UP);

                //停止录音
                MediaRecordManager.getInstance().stopRecordAndFile();

                if (isCancle) {
                    //取消发送
//                    EventBus.getDefault().post(ACTION_STOP_CANCLE);
                    //删除该条录音
                    MediaRecordManager.getInstance().deleteMediaRecord();

                } else
                    //发送
                    EventBus.getDefault().post(ACTION_STOP_SEND);

                break;
//            case MotionEvent.ACTION_CANCEL:
//                //按钮弹起逻辑
//
//                Logger.d("----  ACTION_CANCELACTION_CANCEL----");
//                break;
        }

        return true;
    }
}
