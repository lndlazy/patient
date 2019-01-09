package com.ylean.cf_hospitalapp.home.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.home.adapter.VideoSpeechAdapter;
import com.ylean.cf_hospitalapp.home.bean.VideoSpeechDetailEntry;
import com.ylean.cf_hospitalapp.home.presenter.IVideoSpeechPres;
import com.ylean.cf_hospitalapp.home.view.IVideoSpeechView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家讲堂视频页面
 * Created by linaidao on 2019/1/8.
 */

public class VideoSpeechActivity extends BaseActivity implements IVideoSpeechView {

    private String id;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.support.design.widget.TabLayout mTabLayout;
    private android.support.v4.view.ViewPager vpviewpager;
    private List<View> viewList = new ArrayList<>();
    private VideoSpeechAdapter videoSpeechAdapter;
    private IVideoSpeechPres iVideoSpeechPres = new IVideoSpeechPres(this);
    private StandardGSYVideoPlayer videoPlayer;
    private OrientationUtils orientationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_video_speech);
        id = getIntent().getStringExtra("id");
        initView();

        iVideoSpeechPres.setId(id);
        iVideoSpeechPres.videoSpeechDetail((String) SaveUtils.get(this, SpValue.TOKEN, ""));
    }

    private void initView() {


        videoPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);


        this.vpviewpager = (ViewPager) findViewById(R.id.vp_viewpager);
        this.mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        mTabLayout.addOnTabSelectedListener(listener);
        mTabLayout.setupWithViewPager(vpviewpager);

        viewList.add(View.inflate(this, R.layout.speech_dec, null));
        viewList.add(View.inflate(this, R.layout.speech_contents, null));

        videoSpeechAdapter = new VideoSpeechAdapter(this, viewList);
        vpviewpager.setAdapter(videoSpeechAdapter);

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

    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

            int position = tab.getPosition();

            switch (position) {

                case 0://
                    break;

                case 1://

                    break;
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            //离开的那个tab
//            Logger.d("onTabUnselected,离开的那个tab::" + tab.getText().toString());
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            //再次选择tab
//            Logger.d("onTabReselected,再次选择tab::" + tab.getText().toString());
        }
    };

    @Override
    public void setInfo(VideoSpeechDetailEntry.DataBean data) {

        videoPlayer.setUp(data.getVideourl(), true, "");
//        videoPlayer.setUp("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4", true, "");
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
}
