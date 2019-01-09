package com.ylean.cf_hospitalapp.inquiry.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.ChatEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.presenter.IInquiryPres;
import com.ylean.cf_hospitalapp.inquiry.adapter.ChatAdapter;
import com.ylean.cf_hospitalapp.inquiry.utils.ChatType;
import com.ylean.cf_hospitalapp.audio.MediaRecordManager;
import com.ylean.cf_hospitalapp.base.IPicPresenter;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.view.IInquiryView;
import com.ylean.cf_hospitalapp.my.activity.EvaluateActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;
import com.ylean.cf_hospitalapp.widget.TalkView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 图片问诊详情页面
 * Created by linaidao on 2018/12/21.
 */

public class InquiryDetailAct extends BaseActivity implements View.OnClickListener, IInquiryView {

    private ImageView ivleft;
    private TextView tvDesc;
    private TextView tvStartTime;
    private RecyclerView recyclerView;
    private TextView tvEnd;
    private SimpleDraweeView sdvImg;
    private TextView tvName;
    private TextView tvJob;
    private TextView tvInfo;
    private TextView tvAttent;
    private ChatAdapter chatAdapter;
    private static final int CAMER_PERMISSION_CODE = 0x108;
    private long speakTime;//开始录音时间
    private static final int MDEIAVOICE = 0x67;
    private ImageView iv_voice;
    private TextView tv_mind;
    private List<ChatEntry.DataBean> chatInfoList = new ArrayList<>();

    private IInquiryPres iInquiryPres = new IInquiryPres(this);
    private EditText etInput;
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 0x20;
    private static final int REQUEST_PERMISSION_WRITE_CODE = 0x21;

    private IPicPresenter iPicPresenter = new IPicPresenter(this);
    private String consultaid;
    private boolean noedit;//不能编辑，回复
    private LinearLayout llInput;
    private TextView endInquiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_inquiry_detail);

        isLoad = true;
        noedit = getIntent().getBooleanExtra("noedit", false);
        consultaid = getIntent().getStringExtra("consultaid");
        initView();

        if (noedit) {
            llInput.setVisibility(View.GONE);
        }
        iInquiryPres.setConsultaid(consultaid);
        iInquiryPres.detailInfo((String) SaveUtils.get(this, SpValue.TOKEN, ""));
        iInquiryPres.chatList((String) SaveUtils.get(this, SpValue.TOKEN, ""), true);

        EventBus.getDefault().register(this);

        checkPermisson();

    }

    private boolean isLoad = false;

    private Runnable chatListRunable = new Runnable() {
        @Override
        public void run() {

            if (isLoad && !noedit) {

                iInquiryPres.chatList((String) SaveUtils.get(InquiryDetailAct.this, SpValue.TOKEN, ""), true);
//                mHandler.postDelayed(this, 3000);
            }

        }
    };

    private boolean checkPermisson() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CAMER_PERMISSION_CODE);

            return false;
        }
        return true;
    }

    private void initView() {
        this.tvEnd = (TextView) findViewById(R.id.tvEnd);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        this.tvDesc = (TextView) findViewById(R.id.tvDesc);
        this.ivleft = (ImageView) findViewById(R.id.iv_left);
        TextView tvCommit = findViewById(R.id.tvCommit);

        endInquiry = findViewById(R.id.endInquiry);
        llInput = findViewById(R.id.llInput);

        TalkView ivVoice = findViewById(R.id.ivVoice);
        etInput = findViewById(R.id.etInput);

        ImageView choosePic = findViewById(R.id.choosePic);

        sdvImg = findViewById(R.id.sdvImg);
        tvName = findViewById(R.id.tvName);
        tvJob = findViewById(R.id.tvJob);
        tvInfo = findViewById(R.id.tvInfo);
        tvAttent = findViewById(R.id.tvAttent);

        ivleft.setOnClickListener(this);
        choosePic.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
        tvAttent.setOnClickListener(this);
        endInquiry.setOnClickListener(this);
        tvDesc.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
//        recyclerView.addItemDecoration(divider);

        chatAdapter = new ChatAdapter(this, chatInfoList);
        recyclerView.setAdapter(chatAdapter);

        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND) {

                    iInquiryPres.chatReply(etInput.getText().toString(),
                            (String) SaveUtils.get(InquiryDetailAct.this, SpValue.TOKEN, "")
                            , ChatType.CHAT_CONTENT_TYPE_TXT, ChatType.CHAT_USER_TYPE_PATIENT);

                }

                return false;
            }
        });
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


    private void pressure2speak() {
        speakTime = System.currentTimeMillis();//记录按下的时间
        mHandler.post(voiceRunnable);//监听话筒分贝
        showDiago();
    }

    //让progress消失的runnable
    private Runnable dismissRunnable = new Runnable() {
        @Override
        public void run() {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
        }
    };


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

    private MyHandler mHandler = new MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<InquiryDetailAct> mActivity;

        MyHandler(InquiryDetailAct activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            InquiryDetailAct activity = mActivity.get();
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


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_left:
                finish();
                break;

            case R.id.choosePic://选择图片
                showChoose();
                break;

            case R.id.tvCommit://评论

                if (inquiryInfo == null) {
                    showErr("数据错误");
                    return;
                }

                Intent m = new Intent(this, EvaluateActivity.class);
                m.putExtra("consultaid", consultaid);
                m.putExtra("inquiryInfo", inquiryInfo);

                startActivity(m);

                break;

            case R.id.tvAttent://关注 TODO


                break;

            case R.id.tvDesc://详细介绍

                Intent n = new Intent(this, ConditionDetailAct.class);
                n.putExtra("consultaid", iInquiryPres.getConsultaid());
                startActivity(n);
                break;

            case R.id.endInquiry://结束问诊

                iInquiryPres.endInquiry((String) SaveUtils.get(this, SpValue.TOKEN, ""));

                break;

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case IInquiryPres.TAKE_PHOTO_CODE://拍照返回的code

                try {
                    if (iInquiryPres.getCurrentFile() != null && iInquiryPres.getCurrentFile().exists()) {

                        List<String> strings = new ArrayList<>();
                        strings.add(iInquiryPres.getCurrentFile().getPath());
                        iPicPresenter.uploadPic(strings);

//                        File photoFile = new File(cacheDir);
//                        //文件夹不存在就创建文件夹
//                        if (!photoFile.exists())
//                            photoFile.mkdirs();
//
//                        outFile = new File(photoFile.getPath(), UUID.randomUUID().toString() + ".jpg");
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) //如果大于等于7.0使用FileProvider
//                            iSettingPresenter.startPhotoZoom(
//                                    FileProvider.getUriForFile(this, Constant.FILE_URL, iSettingPresenter.getCurrentFile())
//                                    , outFile);//裁剪
//                        else
//                            iSettingPresenter.startPhotoZoom(Uri.fromFile(iSettingPresenter.getCurrentFile()), outFile);//裁剪

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

//            case IInquiryPres.PHOTO_RESOULT://裁剪后的图片
//
//                if (outFile == null)
//                    return;
//
//                upload_pic(this, outFile, get_user_img_upload_url());
//                break;

            case IInquiryPres.PHOTO_ALBUM_CODE://相册选择返回的code
                if (data == null)
                    return;

                try {
                    Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String path = cursor.getString(columnIndex);  //获取照片路径
                    cursor.close();

                    List<String> pics = new ArrayList<>();
                    pics.add(path);
                    iPicPresenter.uploadPic(pics);

                } catch (Exception e) {
                    e.printStackTrace();
                }

//                File photoFile = new File(cacheDir);
//                //文件夹不存在就创建文件夹
//                if (!photoFile.exists())
//                    photoFile.mkdirs();
//
//                outFile = new File(photoFile, UUID.randomUUID().toString() + ".jpg");
//
//                iSettingPresenter.startPhotoZoom(iSettingPresenter.checkSelectPhoto(this, data, this), outFile);//开始裁剪
////                iSettingPresenter.startPhotoZoom(FileProvider.getUriForFile(this, "com.kungfuhacking.wristbandpro.fileprovider", iSettingPresenter.getCurrentFile()));//开始裁剪

                break;


        }
    }

    private PicAskDetailEntry.DataBean inquiryInfo;


    @Override
    public void setDetailInfo(PicAskDetailEntry.DataBean data) {

        inquiryInfo = data;

        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getDocimg()));
        tvName.setText(data.getDoctorname());
        tvJob.setText(data.getDepartname());
        tvInfo.setText(data.getAdeptdesc());
        tvAttent.setText(data.getIscollect() == 0 ? "未关注" : "已关注");
        tvDesc.setText(data.getDescription());

        tvStartTime.setText("问题时间：" + data.getCreatetime());

        String content = "<font color=\"#767676\">" + data.getDescription() + "</font>"
                + "<font color=\"#33a9fa\">" + "[详细介绍]" + "</font>";
        tvDesc.setText(Html.fromHtml(content));
        tvStartTime.setText("问题时间： " + data.getCreatetime());


//
        switch (data.getStatus()) {
            case 3://未结束

                tvEnd.setVisibility(View.GONE);

                break;
            case 4://已结束
//                tvEnd.setText("结束时间：" + data.get());

                break;

        }

    }

    @Override
    public void replySuccess() {
        //回复成功
        etInput.setText("");

        chatInfoList.clear();
        iInquiryPres.chatList((String) SaveUtils.get(this, SpValue.TOKEN, ""), false);

    }

    @Override
    public void setChatInfo(List<ChatEntry.DataBean> data, boolean isLoop) {

        chatInfoList.clear();
        chatInfoList.addAll(data);

        if (chatAdapter != null)
            chatAdapter.notifyDataSetChanged();

        //自动滑动到最后一个条目
        if (recyclerView != null && chatInfoList != null && chatInfoList.size() > 0)
            recyclerView.scrollToPosition(chatInfoList.size() - 1);

        if (isLoop)
            mHandler.postDelayed(chatListRunable, 3000);

    }

    //结束问诊成功
    @Override
    public void endInquirySuccess() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLoad = false;
        mHandler.removeCallbacks(null);
        EventBus.getDefault().unregister(this);
    }

    //上传录音文件
    private void uploadVoiceData() {

        if (TextUtils.isEmpty(MediaRecordManager.getInstance().getMediaPath())) {
            showErr("获取录音文件失败");
            return;
        }

        iPicPresenter.uploadVoice(MediaRecordManager.getInstance().getMediaPath());
    }

    private void stopRecord() {
        //停止录音
        MediaRecordManager.getInstance().stopRecordAndFile();
        //上传录音文件
        uploadVoiceData();
    }

    /**
     * 弹出选择照片的对话框
     */
    @Deprecated
    protected void showChoose() {

        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 拍照
                                checkPicPer();

                            }

                        })
                .addSheetItem("去相册选择", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 从相册中选择
                                checkWritePermission();
                            }
                        }).show();

    }

    //获取拍照权限
    private void checkPicPer() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            iInquiryPres.getTakePhoto(IInquiryPres.TAKE_PHOTO_CODE, this);

        } else {
            //不具有权限，需要进行权限申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_PERMISSION_CAMERA_CODE);
        }
    }


    //获取读写权限
    private void checkWritePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //不具有权限，需要进行权限申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE_CODE);
        } else {
            iInquiryPres.getFromPhotoAlbum(IInquiryPres.PHOTO_ALBUM_CODE, InquiryDetailAct.this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case REQUEST_PERMISSION_CAMERA_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    iInquiryPres.getTakePhoto(IInquiryPres.TAKE_PHOTO_CODE, this);
                }
                break;

            case REQUEST_PERMISSION_WRITE_CODE:
                iInquiryPres.getFromPhotoAlbum(IInquiryPres.PHOTO_ALBUM_CODE, InquiryDetailAct.this);

                break;
        }

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


    @Override
    public void picUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {

        iInquiryPres.chatReply(dataUploadResultEntry.getData().get(0), (String) SaveUtils.get(InquiryDetailAct.this, SpValue.TOKEN, "")
                , ChatType.CHAT_CONTENT_TYPE_PIC, ChatType.CHAT_USER_TYPE_PATIENT);

    }

    @Override
    public void voiceUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {
        iInquiryPres.chatReply(dataUploadResultEntry.getData().get(0), (String) SaveUtils.get(InquiryDetailAct.this, SpValue.TOKEN, "")
                , ChatType.CHAT_CONTENT_TYPE_VIDEO, ChatType.CHAT_USER_TYPE_PATIENT);

//        //录音时长
//        int round = Math.round(MediaPlayer.create(InquiryDetailAct.this,
//                Uri.parse(MediaRecordManager.getInstance().getMediaPath())).getDuration() / 1000);
//
//        Logger.d("时长：：：" + round);
    }


}
