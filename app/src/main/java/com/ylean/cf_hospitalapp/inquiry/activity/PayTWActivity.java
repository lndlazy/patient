package com.ylean.cf_hospitalapp.inquiry.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.CommentDisBean;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.MImageItem;
import com.ylean.cf_hospitalapp.inquiry.bean.PeopleEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskResutEntry;
import com.ylean.cf_hospitalapp.inquiry.adapter.AskPicAdapter;
import com.ylean.cf_hospitalapp.inquiry.adapter.CommDisAdapter;
import com.ylean.cf_hospitalapp.inquiry.presenter.IPayTWPresenter;
import com.ylean.cf_hospitalapp.audio.UPlayer;
import com.ylean.cf_hospitalapp.base.IPicPresenter;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.view.IPayTWView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.IDateUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 图文,电话,视频 问诊
 */
public class PayTWActivity extends BaseActivity implements View.OnClickListener, IPayTWView {

    private static final int ASK_PEOPLE_CODE = 0x101;
    private static final int DIS_REQUEST_CODE = 0x102;
    private static final int MORE_DIS_RESULT_CODE = 0x103;
    private static final int CAMER_PERMISSION_CODE = 0x104;
    private static final int IMAGE_PICKER = 0x105;
    private static final int RECORD_REQUEST_CODE = 0x106;
    private static final int RECORD_RESULT_CODE = 0x107;

    private android.widget.ImageView ivAddPeo;
    private android.widget.ImageView ivMoreSick;
    private RecyclerView sickRecyclerView;
    private android.widget.EditText etQues;
    private android.widget.EditText etDesc;
    private RecyclerView picRecyclerView;
    private android.widget.TextView tvNext;
    private List<CommentDisBean.DataBean> commentDisList = new ArrayList<>();
    private IPayTWPresenter iPayTWPresenter = new IPayTWPresenter(this);
    private String disId = "";
    private static final int CHOOSE_ASK_PEOPEL_RESULE_CODE = 0X211;
    private View people;
    private PeopleEntry.DataBean peopleBean;
    private TextView tvP;
    private List<MImageItem> images = new ArrayList<>();
    private SimpleDraweeView sdvPic;
    private TextView tvName;
    private TextView tvId;
    private TextView tvInfo;
    private TextView tvRelat;
    private ImageView iv_enter;
    private String diseasename;
    private String diseaseId;
    private String departid;
    private CommDisAdapter commDisAdapter;
    private AskPicAdapter askPicAdapter;
    private String voiceurl;//录音文件服务器地址
    private String doctorId;
    private String doctorName;
    private String type;//图文，视频，电话问题
    private double price;
    private TextView tvTime;
    private Date currentChooseData;
    private String askType;//付费问诊还是 免费问诊

    private TextView tvVoice;//
    private String mediaPath;//录音文件本地路径

    private IPicPresenter iPicPresenter = new IPicPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paytw);

        doctorId = getIntent().getStringExtra("doctorId");
        doctorName = getIntent().getStringExtra("doctorName");
        type = getIntent().getStringExtra("type");//图文问诊 还是电话问诊 ，视频问诊
        price = getIntent().getDoubleExtra("price", 0.00);
        askType = getIntent().getStringExtra("askType");//付费问诊还是免费问诊SpValue.ASK_CHARGE://付费问诊,SpValue.ASK_FREE://免费问诊

        initView();
        iPayTWPresenter.getCommentSick();
        checkPermisson();

        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMER_PERMISSION_CODE: {//调用系统相机申请拍照权限回调
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                }
                break;

            }

        }

    }

    private void initView() {
        this.tvNext = findViewById(R.id.tvNext);
        this.tvVoice = findViewById(R.id.tvVoice);
        tvVoice.setVisibility(View.GONE);
        ImageView tvUploadVoice = findViewById(R.id.tvUploadVoice);
        ImageView tvUploadPic = findViewById(R.id.tvUploadPic);
        this.etDesc = findViewById(R.id.etDesc);
        this.etQues = findViewById(R.id.etQues);
        this.ivMoreSick = findViewById(R.id.ivMoreSick);
        this.ivAddPeo = findViewById(R.id.ivAddPeo);
        TitleBackBarView tbv = findViewById(R.id.tbv);

        RelativeLayout rlTime = findViewById(R.id.rlTime);
        LinearLayout llt = findViewById(R.id.llt);
        tvTime = findViewById(R.id.tvTime);

        llt.setVisibility(SpValue.ASK_TYPE_PIC.equals(type) ? View.GONE : View.VISIBLE);

        sdvPic = findViewById(R.id.sdvPic);

        tvName = findViewById(R.id.tvName);
        tvId = findViewById(R.id.tvId);
        tvInfo = findViewById(R.id.tvInfo);
        tvRelat = findViewById(R.id.tvRelat);
        iv_enter = findViewById(R.id.iv_enter);

        tvP = findViewById(R.id.tvP);
        people = findViewById(R.id.people);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivAddPeo.setOnClickListener(this);
        ivMoreSick.setOnClickListener(this);
        tvUploadPic.setOnClickListener(this);
        tvUploadVoice.setOnClickListener(this);
        people.setOnClickListener(this);
        tvVoice.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        rlTime.setOnClickListener(this);
        people.setVisibility(View.GONE);
        this.picRecyclerView = findViewById(R.id.picRecyclerView);
        this.sickRecyclerView = findViewById(R.id.sickRecyclerView);

        sickRecycler();
        picRecycler();

    }

    private boolean isPlaying;
    private UPlayer uPlayer;
//    private String currentPlayingVideoUrl = "";//当前播放的录音文件的路径
//    private long speakTime;//开始录音时间

    /**
     * 停止播放语音文件
     */
    public void stopPlayVideo() {

        if (isPlaying && uPlayer != null) {
            uPlayer.stop();

//            currentPlayingVideoUrl = "";
        }

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
        askPicAdapter = new AskPicAdapter(this, images);
        picRecyclerView.setAdapter(askPicAdapter);
    }

    private void sickRecycler() {
        GridLayoutManager doctorLayoutManager = new GridLayoutManager(this, 3);
        sickRecyclerView.setLayoutManager(doctorLayoutManager);
        doctorLayoutManager.setSmoothScrollbarEnabled(true);
        doctorLayoutManager.setAutoMeasureEnabled(true);

        sickRecyclerView.setHasFixedSize(true);
        sickRecyclerView.setNestedScrollingEnabled(false);
        commDisAdapter = new CommDisAdapter(this, commentDisList);
        sickRecyclerView.setAdapter(commDisAdapter);
        sickRecyclerView.addOnItemTouchListener(new OnItemClickListener(sickRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

//                Logger.d("item 点击事件 ===>" + commentDisList.get(position).getDiseasename());

                boolean select = commentDisList.get(position).isSelect();
                for (int i = 0; i < commentDisList.size(); i++) {
                    commentDisList.get(i).setSelect(false);
                }
                commentDisList.get(position).setSelect(!select);

                diseaseId = select ? "" : commentDisList.get(position).getDiseaseid();
                diseasename = select ? "" : commentDisList.get(position).getDiseasename();
                departid = select ? "" : commentDisList.get(position).getDepartid();

                if (commDisAdapter != null)
                    commDisAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivAddPeo://添加问诊人
            case R.id.people://切换问诊人

                Intent m = new Intent(this, AskPeopleActivity.class);
                m.putExtra("title", "问诊人选择");
                startActivityForResult(m, ASK_PEOPLE_CODE);

//                nextActivityWithCode(AskPeopleActivity.class, ASK_PEOPLE_CODE);

                break;

            case R.id.ivMoreSick://更多疾病

                nextActivityWithCode(MoreDisAct.class, DIS_REQUEST_CODE);

                break;

            case R.id.tvUploadPic://上传图片

                choosePic();

                break;

            case R.id.tvUploadVoice://点击语音

                stopPlayVideo();
                nextActivityWithCode(RecordAct.class, RECORD_REQUEST_CODE);

                break;

            case R.id.tvNext://下一步

                nextStep();

                break;
            case R.id.rlTime://选择预约时间

                chooseTime();

                break;

            case R.id.tvVoice:// 播放语音

                playVideo();
                break;
        }

    }

    //    Handler  h = new Handler();
//    private AnimationDrawable playingAnimation;
//    private ImageView playingVoiceView;

    private void playVideo() {

        if (isPlaying) {
            //播放中,判断当前是否正在播放当前的录音文件, 如果是当前的录音文件 则停止播放,
            // 如果不是当前的录音文件,则停止播放,并且播放点击的录音文件
            if (uPlayer != null) {

                uPlayer.stop();
//                playingAnimation.stop();
//                if (playingVoiceView != null)
//                    playingVoiceView.setImageResource(
//                            lastSide == RIGHT
//                                    ? R.mipmap.ic_voice_three_right : R.mipmap.ic_voice_three_left);

            }


            //播放点击的录音文件
            startPlay();
//            if (!TextUtils.isEmpty(currentPlayingVideoUrl) &&
//                    !mb.get(position).content.equals(currentPlayingVideoUrl)) {
//
////                if (h != null)
////                    h.removeCallbacksAndMessages(null);
//
//                //播放点击的录音文件
//                startPlay();
//            }

        } else {
            //未播放, 开始播放
            startPlay();
        }
    }

    /**
     * 开始播放录音文件
     *
     * @param
     */
    private void startPlay() {

//        iv_play_voice.setImageResource(sp.getString(MqttConstant.USERID, "").equals(mb.sendId) ?
//                R.drawable.voice_play_right : R.drawable.voice_play_left);

//        playingAnimation = (AnimationDrawable) iv_play_voice.getDrawable();
//        playingAnimation.start(); //开始播放

//        h = new Handler();
//
//        h.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (playingAnimation != null)
//                    playingAnimation.stop();//停止播放
//                currentPlayingVideoUrl = "";
//
//
//            }   //延迟1秒改变状态
//        }, (int) (mb.voiceLength * 1000 + 1000));

        uPlayer = new UPlayer(mediaPath);
        uPlayer.start();

//        playingVoiceView = iv_play_voice;
//        currentPlayingVideoUrl = mb.content;

        isPlaying = true;
    }


    private void chooseTime() {

        Date date = new Date();
        final Calendar c = Calendar.getInstance();
        c.setTime(date);

        TimePickerView timePickerView = new TimePickerView(this, TimePickerView.Type.ALL);

        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);

        timePickerView.setTitle("选择预约时间");

        timePickerView.setCancelable(true);
        //时间选择后回调
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                currentChooseData = date;
//                String s = IDateUtils.formatDateTime(date, IDateUtils.DF_YYYY_MM_DD_HH_MM_SS);
                tvTime.setText(IDateUtils.formatDateTime(date, IDateUtils.DF_YYYY_MM_DD_HH_MM_SS));
            }
        });
        //弹出时间选择器
        timePickerView.show();

    }

    private void nextStep() {

        if (checkInputInfo()) return;

        String img = "";
        if (images != null && images.size() > 0) {

            for (int i = 0; i < images.size(); i++) {
                img = img + images.get(i).getUrlPath() + ",";
            }
        }

        if (!TextUtils.isEmpty(img))
            img = img.substring(0, img.length() - 1);

        switch (askType) {

            case SpValue.ASK_CHARGE://付费问诊
                chargeAsk(img);
                break;

            case SpValue.ASK_FREE://免费问诊

                Intent freeIntent = new Intent(this, FreeAskDoctorListActivity.class);
//                freeIntent.putExtra("askType", SpValue.ASK_FREE);//类型 免费义诊
                freeIntent.putExtra("imgs", img);//图片
                freeIntent.putExtra("flokId", peopleBean.getId());//就诊人id
                freeIntent.putExtra("diseaseId", diseaseId);//疾病id
                freeIntent.putExtra("problem", etQues.getText().toString());//咨询的问题
                freeIntent.putExtra("describe", etDesc.getText().toString());//问题描述
                freeIntent.putExtra("voiceurl", voiceurl);//语音地址
                startActivity(freeIntent);
                break;
        }

    }

    //创建付费问诊
    private void chargeAsk(String img) {
        RetrofitHttpUtil
                .getInstance()
                .createPayAsk(
                        new BaseNoTObserver<PicAskResutEntry>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                showLoading("提交中...");
                            }

                            @Override
                            public void onHandleSuccess(PicAskResutEntry picAskResutEntry) {
                                hideLoading();
                                Intent m = new Intent(PayTWActivity.this, BuyServiceAct.class);
                                m.putExtra("orderNum", picAskResutEntry.getData());
                                m.putExtra("doctorName", doctorName);
                                m.putExtra("doctorId", doctorId);
                                m.putExtra("price", price);
                                m.putExtra("type", "图片问诊");
                                startActivity(m);
                            }

                            @Override
                            public void onHandleError(String message) {
                                hideLoading();
                                showErr(message);
                            }

                        }, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , SpValue.CH, peopleBean.getId(), diseaseId
                        , (String) SaveUtils.get(this, SpValue.HOSPITAL_ID, "")
                        , etQues.getText().toString(), etDesc.getText().toString()
                        , img, voiceurl, doctorId, type
                        , SpValue.ASK_TYPE_PIC.equals(type) ? "" : tvTime.getText().toString()
                        , CommonUtils.getNum2(price));
    }

    //检查输入的内容信息
    private boolean checkInputInfo() {
        if (peopleBean == null || TextUtils.isEmpty(peopleBean.getId())) {
            showErr("请选择就诊人");
            return true;
        }

        if (TextUtils.isEmpty(diseaseId)) {
            showErr("请选择咨询的疾病");
            return true;
        }

        if (TextUtils.isEmpty(etQues.getText().toString())) {
            showErr("请填写咨询的问题");
            return true;
        }

        if (TextUtils.isEmpty(etQues.getText().toString())) {
            showErr("请填写咨询的问题");
            return true;
        }

        if (TextUtils.isEmpty(etDesc.getText().toString())) {
            showErr("请填写病情描述");
            return true;
        }

        if (TextUtils.isEmpty(etDesc.getText().toString())) {
            showErr("请填写问题描述");
            return true;
        }

        if (!SpValue.ASK_TYPE_PIC.equals(type)) {
            if (TextUtils.isEmpty(tvTime.getText().toString())) {
                showErr("请选择预约时间");
                return true;
            }
        }

        if (currentChooseData != null) {
            if (new Date().getTime() >= currentChooseData.getTime()) {
                showErr("预约时间无效");
                return true;
            }
        }
        return false;
    }

    private void choosePic() {
        if (!checkPermisson()) {
            showErr("请允许权限");
            return;
        }

        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }

    @Override
    public void setCommentDis(List<CommentDisBean.DataBean> data) {

        commentDisList.clear();
        commentDisList.addAll(data);

        if (commDisAdapter != null)
            commDisAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case CHOOSE_ASK_PEOPEL_RESULE_CODE://选中问诊人
                askPeopleDataBack(data);
                break;

            case MORE_DIS_RESULT_CODE://更多疾病

                disDataBack(data);

                break;

            case ImagePicker.RESULT_CODE_ITEMS:

                if (data != null && requestCode == IMAGE_PICKER)
                    uploadPics((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));

                break;

            case RECORD_RESULT_CODE:

                if (data != null) {
                    voiceurl = data.getStringExtra("voiceUrl");
                    mediaPath = data.getStringExtra("mediaPath");
                    int round = Math.round(MediaPlayer.create(this,
                            Uri.parse(mediaPath)).getDuration() / 1000);

                    Logger.d("时长：：：" + round);
                    tvVoice.setVisibility(View.VISIBLE);

                    tvVoice.setText(round + "s");
                }

                break;
        }

    }

    /**
     * 是否取消语音发送, (只是用来改变界面效果, 不做最终决定权)
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(Integer action) {
        switch (action) {

            case UPlayer.STOPPLAYING://手指按下 弹出dialog
                isPlaying = false;
                break;

        }

    }

    private List<ImageItem> imageItems;

    //上传图片
    private void uploadPics(final List<ImageItem> imageItems) {

        if (imageItems == null || imageItems.size() < 1)
            return;

        this.imageItems = imageItems;

        List<String> picPathLists = new ArrayList<>();

        for (int i = 0; i < imageItems.size(); i++) {
            picPathLists.add(imageItems.get(i).path);
        }

        //上传图片
        iPicPresenter.uploadPic(picPathLists);

    }

    private void askPeopleDataBack(Intent data) {
        if (data != null) {
            peopleBean = data.getParcelableExtra("selectPeople");
            if (peopleBean != null)
                setAskPeopleInfo();
        }
    }

    private void disDataBack(Intent data) {
        if (data != null) {
            diseasename = data.getStringExtra("diseasename");
            diseaseId = data.getStringExtra("diseaseId");
            departid = data.getStringExtra("departid");

//          Logger.d("activty返回的departid:" + departid + ",diseaseId:" + diseaseId + ",diseasename:" + diseasename);

            if (commentDisList != null) {

                for (int i = 0; i < commentDisList.size(); i++) {
                    commentDisList.get(i).setSelect(false);
                }

                CommentDisBean.DataBean dataBean = new CommentDisBean.DataBean();
                dataBean.setSelect(true);
                dataBean.setDiseaseid(diseaseId);
                dataBean.setDiseasename(diseasename);
                dataBean.setDepartid(departid);
                commentDisList.add(0, dataBean);
                if (commDisAdapter != null)
                    commDisAdapter.notifyDataSetChanged();
            }

        }
    }

    private void setAskPeopleInfo() {
        ivAddPeo.setVisibility(View.GONE);
        tvP.setVisibility(View.GONE);

        people.setVisibility(View.VISIBLE);
        iv_enter.setVisibility(View.VISIBLE);

        sdvPic.setImageURI(Uri.parse(peopleBean.getImgurl()));
        tvName.setText(peopleBean.getName());
        tvId.setText("身份证    " + peopleBean.getIDcard());
        tvInfo.setText(peopleBean.getAge() + "岁" +
                (SpValue.SEX_FEMALE.equals(peopleBean.getSex()) ? "    女" : "  男")
                + " 医保 " + peopleBean.getMedicalcard());
        tvRelat.setText(CommonUtils.getRelationship(peopleBean.getRelationship()));
    }

    //删除图片
    public void removePic(int position) {

        if (images != null) {
            images.remove(position);
        }
        if (askPicAdapter != null)
            askPicAdapter.notifyDataSetChanged();

    }

    //图片上传成功
    @Override
    public void picUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {

        for (int i = 0; i < imageItems.size(); i++) {
            MImageItem mImageItem = new MImageItem("", imageItems.get(i).name, imageItems.get(i).path);
            mImageItem.setUrlPath(dataUploadResultEntry.getData().get(i));
            images.add(mImageItem);
        }

        if (askPicAdapter != null)
            askPicAdapter.notifyDataSetChanged();
    }

    //语音上传成功
    @Override
    public void voiceUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {

    }
}
