package com.ylean.cf_hospitalapp.home.activity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.Presenter.ICollectionPres;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.ICollectionView;
import com.ylean.cf_hospitalapp.home.bean.VideoSpeechDetailEntry;
import com.ylean.cf_hospitalapp.home.presenter.IVideoSpeechPres;
import com.ylean.cf_hospitalapp.home.view.IVideoSpeechView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 专家讲堂视频页面
 * Created by linaidao on 2019/1/8.
 */

public class VideoSpeechActivity extends BaseActivity implements IVideoSpeechView, View.OnClickListener, ICollectionView {

    private String id;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    //    private android.support.design.widget.TabLayout mTabLayout;
//    private android.support.v4.view.ViewPager vpviewpager;
//    private List<View> viewList = new ArrayList<>();
//    private VideoSpeechAdapter videoSpeechAdapter;
    private IVideoSpeechPres iVideoSpeechPres = new IVideoSpeechPres(this);
    private StandardGSYVideoPlayer videoPlayer;
    private OrientationUtils orientationUtils;

    private android.widget.TextView tvTitle;
    private android.widget.TextView tvtalk;
    private android.widget.TextView tvgoodcount;
    //    private android.widget.TextView tvDate;
    private SimpleDraweeView sdvImg;
    private TextView tvName;
    private TextView tvDepartment;
    private TextView tvHospitalName;
    private TextView tvIntroduce;
    private TextView tvAttention;
    private TextView tvstarttime;
    private TextView tvTimedesc;
    private TextView tvviewcount;
    private TextView tvlike;
    private ImageView ivlike;
    private ImageView ivgood;

    //收藏Pers
    private ICollectionPres iCollectionPres = new ICollectionPres(this);
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_video_speech);

        id = getIntent().getStringExtra("id");
        //类型 直播1 还是 视频3
        type = getIntent().getStringExtra("type");

        initView();

        iVideoSpeechPres.setId(id);
        iVideoSpeechPres.videoSpeechDetail((String) SaveUtils.get(this, SpValue.TOKEN, ""));
    }

    private void initView() {

        videoPlayer = findViewById(R.id.detail_player);

//        this.tvDate = findViewById(R.id.tvDate);
        this.tvgoodcount = (TextView) findViewById(R.id.tvgoodcount);
        this.tvtalk = (TextView) findViewById(R.id.tvtalk);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        sdvImg = findViewById(R.id.sdvImg);
        tvstarttime = findViewById(R.id.tvstarttime);
        tvName = findViewById(R.id.tvName);
        tvDepartment = findViewById(R.id.tvDepartment);
        tvHospitalName = findViewById(R.id.tvHospitalName);
        tvIntroduce = findViewById(R.id.tvIntroduce);
        tvAttention = findViewById(R.id.tvAttention);
        tvTimedesc = findViewById(R.id.tvTimedesc);
        tvviewcount = findViewById(R.id.tvviewcount);
        tvlike = findViewById(R.id.tvlike);
        ivlike = findViewById(R.id.ivlike);
        ivgood = findViewById(R.id.ivgood);

        ivlike.setOnClickListener(this);
//        this.vpviewpager = (ViewPager) findViewById(R.id.vp_viewpager);
//        this.mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

//        mTabLayout.addOnTabSelectedListener(listener);
//        mTabLayout.setupWithViewPager(vpviewpager);

//        viewList.add(View.inflate(this, R.layout.speech_dec, null));
//        viewList.add(View.inflate(this, R.layout.speech_contents, null));

//        videoSpeechAdapter = new VideoSpeechAdapter(this, viewList);
//        vpviewpager.setAdapter(videoSpeechAdapter);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tbv.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分享 TODO
            }
        });
    }

//    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
//        @Override
//        public void onTabSelected(TabLayout.Tab tab) {
//
//            int position = tab.getPosition();
//
//            switch (position) {
//
//                case 0://
//                    break;
//
//                case 1://
//
//                    break;
//            }
//
//        }
//
//        @Override
//        public void onTabUnselected(TabLayout.Tab tab) {
//            //离开的那个tab
////            Logger.d("onTabUnselected,离开的那个tab::" + tab.getText().toString());
//        }
//
//        @Override
//        public void onTabReselected(TabLayout.Tab tab) {
//            //再次选择tab
////            Logger.d("onTabReselected,再次选择tab::" + tab.getText().toString());
//        }
//    };

    @Override
    public void setInfo(VideoSpeechDetailEntry.DataBean data) {

        //播放视频
        videoPlay(data);
        tvTitle.setText(data.getTitle());
        tvTimedesc.setText(data.getTimedesc());
        tvviewcount.setText(data.getBrowsecount());
        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getDoctorimg()));
        tvDepartment.setText("主讲人:" + data.getDoctorname());
        tvHospitalName.setText(data.getHospitalname() + " " + data.getDoctortitle());
        tvAttention.setText("1".equals(data.getIscollectdoc()) ? "已关注" : "未关注");
        tvIntroduce.setText(data.getEductdesc());
        tvtalk.setText(data.getHopedesc());
        tvgoodcount.setText(data.getFabulouscount());
        tvlike.setText(data.getCollectcount());

//        tvDate.setText(data.getStartime());

        if (!TextUtils.isEmpty(data.getStatus())) {

            switch (data.getStatus()) {

                case "0"://预播
                    tvstarttime.setVisibility(View.VISIBLE);
                    tvstarttime.setText("开播时间：  " + data.getStartime());
                    break;
                case "1"://直播
                    tvstarttime.setVisibility(View.GONE);
                    break;
                case "2"://结束
                    tvstarttime.setVisibility(View.GONE);
                    break;

            }

        }

        ivlike.setSelected(!"0".equals(data.getIscollectlive()));

    }


    //播放视频
    private void videoPlay(VideoSpeechDetailEntry.DataBean data) {
        videoPlayer.setUp(data.getVideourl(), true, "");
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.INVISIBLE);

//设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoPlayer.startPlayLogic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivlike:

                iCollectionPres.setId(id);
                iCollectionPres.setType(type);

                if (ivlike.isSelected())
                    //取消收藏
                    iCollectionPres.removeCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""));
                else
                    //添加收藏
                    iCollectionPres.addCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""));

                break;
        }

    }

    @Override
    public void collectionSuccess() {
        //收藏成功
        ivlike.setSelected(true);
    }

    //取消收藏成功
    @Override
    public void removeCollectionSuccess() {
        ivlike.setSelected(false);
    }

}
