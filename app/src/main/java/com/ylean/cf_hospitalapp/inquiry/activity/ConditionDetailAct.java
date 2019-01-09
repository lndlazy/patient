package com.ylean.cf_hospitalapp.inquiry.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.audio.UPlayer;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.adapter.PicAdapter;
import com.ylean.cf_hospitalapp.inquiry.bean.ChatEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.inquiry.presenter.IInquiryPres;
import com.ylean.cf_hospitalapp.inquiry.view.IInquiryView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.List;

/**
 * 问诊详情
 * Created by linaidao on 2019/1/9.
 */

public class ConditionDetailAct extends BaseActivity implements IInquiryView, View.OnClickListener {

    private android.widget.TextView tvName;
    private android.widget.TextView tvAge;
    private android.widget.TextView tvConditionType;
    private android.widget.TextView tvQues;
    private android.widget.TextView tvDesc;
    private android.support.v7.widget.RecyclerView picRecyclerView;
    //    private android.support.v7.widget.RecyclerView recyclerView;
    private static final int CAMER_PERMISSION_CODE = 0x108;

//    private static final int MDEIAVOICE = 0x67;

    private IInquiryPres iInquiryPres = new IInquiryPres(this);

    //    private List<ChatEntry.DataBean> chatInfoList = new ArrayList<>();
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 0x20;
    private static final int REQUEST_PERMISSION_WRITE_CODE = 0x21;
    private PicAdapter picAdapter;
    private TextView tvVoice;
    private String consultaid;

    private SimpleDraweeView sdvImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_condition_detail);

        initView();

        checkPermisson();
        consultaid = getIntent().getStringExtra("consultaid");
        iInquiryPres.setConsultaid(consultaid);
        iInquiryPres.detailInfo((String) SaveUtils.get(this, SpValue.TOKEN, ""));
//        iInquiryPres.chatList((String) SaveUtils.get(this, SpValue.TOKEN, ""));

    }

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
        this.picRecyclerView = (RecyclerView) findViewById(R.id.picRecyclerView);
//        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tvDesc = (TextView) findViewById(R.id.tvDesc);
        this.tvQues = (TextView) findViewById(R.id.tvQues);
        this.tvConditionType = (TextView) findViewById(R.id.tvConditionType);
        this.tvAge = (TextView) findViewById(R.id.tvAge);
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.sdvImg = findViewById(R.id.sdvImg);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tvVoice = findViewById(R.id.tvVoice);
        RelativeLayout rlInfo = findViewById(R.id.rlInfo);

//        TextView tvReply = findViewById(R.id.tvReply);

//        tvReply.setOnClickListener(this);
        rlInfo.setOnClickListener(this);
//        TalkView ivVoice = findViewById(R.id.ivVoice);
//        etInput = findViewById(R.id.etInput);
//        ImageView choosePic = findViewById(R.id.choosePic);


        picRecycler();
//        choosePic.setOnClickListener(this);
        tbv.setOnLeftClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//
//                if (actionId == EditorInfo.IME_ACTION_SEND) {
//
//                    iInquiryPres.chatReply(etInput.getText().toString(),
//                            (String) SaveUtils.get(ConditionDetailAct.this, SpValue.TOKEN, "")
//                            , ChatType.CHAT_CONTENT_TYPE_TXT, ChatType.CHAT_USER_TYPE_DOCTOR);
//
//                }
//
//                return false;
//            }
//        });
//
//        chatAdapter = new ChatAdapter(this, chatInfoList);
//        recyclerView.setAdapter(chatAdapter);
    }

    private void picRecycler() {
        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        picRecyclerView.setHasFixedSize(true);
        picRecyclerView.setNestedScrollingEnabled(false);
        picRecyclerView.setLayoutManager(manager);
        picRecyclerView.setItemAnimator(new DefaultItemAnimator());

//        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_home_divider));
//        recyclerView.addItemDecoration(divider);
//        String[] images;
//        askPicAdapter = new AskPicAdapter(this, images);
//        picRecyclerView.setAdapter(askPicAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

//            case R.id.tvReply:
//
//                Intent m = new Intent(this, ChatActivity.class);
//                m.putExtra("consultaid", consultaid);
//                startActivity(m);
//
//                break;

            case R.id.rlInfo:

//                Intent n = new Intent(this, PatienetInfoAct.class);
//                n.putExtra("detailInfo", detailInfo);
//                startActivity(n);

                break;
        }

    }


    //图片上传成功回调
    @Override
    public void picUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {


    }

    //语音上传成功回调
    @Override
    public void voiceUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {

    }

    private PicAskDetailEntry.DataBean detailInfo;

    @Override
    public void setDetailInfo(PicAskDetailEntry.DataBean detailInfo) {

        this.detailInfo = detailInfo;
        tvName.setText(detailInfo.getFlokname() + "         " + "身份证    " + detailInfo.getIdcard());

        tvAge.setText(detailInfo.getAge() + "           " + (SpValue.SEX_FEMALE.equals(detailInfo.getSex()) ? "女" : "男") + "     " + "医保     " + detailInfo.getMedicalcard());

        tvConditionType.setText(detailInfo.getDiseasename());

        tvQues.setText(detailInfo.getProblem());
        tvDesc.setText(detailInfo.getDescription());
        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + detailInfo.getImgs()));
        List<String> imglist = detailInfo.getImglist();

        if (imglist != null && imglist.size() > 0) {
            picAdapter = new PicAdapter(this, imglist);
            picRecyclerView.setAdapter(picAdapter);
        }

        tvVoice.setVisibility(TextUtils.isEmpty(detailInfo.getVoiceurl()) ? View.INVISIBLE : View.VISIBLE);
        sdvImg.setVisibility(TextUtils.isEmpty(detailInfo.getVoiceurl()) ? View.INVISIBLE : View.VISIBLE);

        if (TextUtils.isEmpty(detailInfo.getVoiceurl()))
            return;

        try {

            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(detailInfo.getVoiceurl());
            mediaPlayer.prepare();
            mediaPlayer.getDuration();

            int round = Math.round(mediaPlayer.getDuration() / 1000);

            tvVoice.setText(round + "''");
            mediaPlayer.release();

        } catch (Exception e) {
            e.printStackTrace();
        }

        final String url = detailInfo.getVoiceurl();
        tvVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //播放语音
                if (isPlaying) {
                    //播放中,判断当前是否正在播放当前的录音文件, 如果是当前的录音文件 则停止播放,
                    // 如果不是当前的录音文件,则停止播放,并且播放点击的录音文件
                    if (uPlayer != null)
                        uPlayer.stop();

                    //播放点击的录音文件
                    startPlay(url);

                } else
                    //未播放, 开始播放
                    startPlay(url);

            }
        });

    }

    private boolean isPlaying;
    private UPlayer uPlayer;

    /**
     * 开始播放录音文件
     *
     * @param
     * @param url
     */
    private void startPlay(String url) {
        uPlayer = new UPlayer(url);
        uPlayer.start();

        isPlaying = true;
    }

    @Override
    public void replySuccess() {


    }

    @Override
    public void setChatInfo(List<ChatEntry.DataBean> data, boolean isLoop) {


    }

    @Override
    public void endInquirySuccess() {

        showErr("结束问诊成功");
        finish();

    }


}