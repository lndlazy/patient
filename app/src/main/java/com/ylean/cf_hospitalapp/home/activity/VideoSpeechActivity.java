package com.ylean.cf_hospitalapp.home.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.Presenter.ICollectionPres;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.ICollectionView;
import com.ylean.cf_hospitalapp.comm.activity.ReplyActivity;
import com.ylean.cf_hospitalapp.comm.pres.IGoodPres;
import com.ylean.cf_hospitalapp.comm.view.IGoodView;
import com.ylean.cf_hospitalapp.doctor.adapter.CommentCommAdapter;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.home.bean.VideoSpeechDetailEntry;
import com.ylean.cf_hospitalapp.home.presenter.IVideoSpeechPres;
import com.ylean.cf_hospitalapp.home.view.IVideoSpeechView;
import com.ylean.cf_hospitalapp.my.activity.SettingActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.ShareUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家讲堂视频页面
 * Created by linaidao on 2019/1/8.
 */

public class VideoSpeechActivity extends BaseActivity implements IVideoSpeechView, View.OnClickListener, ICollectionView, IGoodView {

    private String id;
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
    //当前点击病友信息
    private CommComListEntry.DataBean commentInfo;


    //视频的presenter
    private IVideoSpeechPres iVideoSpeechPres = new IVideoSpeechPres(this);
    //收藏Pers
    private ICollectionPres iCollectionPres = new ICollectionPres(this);
    //点赞
    private IGoodPres iGoodPres = new IGoodPres(this);
    //关注
//    private ICollectionPres iCollectionPres = new ICollectionPres(this);
//    private IAttentionPres iAttentionPres = new IAttentionPres(this);
    //评论列表
    private List<CommComListEntry.DataBean> commentList = new ArrayList<>();

    private String type;
    private RecyclerView recyclerView;
    private CommentCommAdapter commAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_video_speech);

        id = getIntent().getStringExtra("id");
        //类型 直播1 还是 视频3
//        type = getIntent().getStringExtra("type");

        initView();

        iVideoSpeechPres.setId(id);
        iVideoSpeechPres.videoSpeechDetail((String) SaveUtils.get(this, SpValue.TOKEN, ""));
        iVideoSpeechPres.videoCommentList((String) SaveUtils.get(this, SpValue.TOKEN, ""));
    }

    private void initView() {

        videoPlayer = findViewById(R.id.detail_player);

        this.tvgoodcount = findViewById(R.id.tvgoodcount);
        this.tvtalk = findViewById(R.id.tvtalk);
        this.tvTitle = findViewById(R.id.tvTitle);
        TitleBackBarView tbv = findViewById(R.id.tbv);

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
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayout llcomment = findViewById(R.id.llcomment);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        commAdapter = new CommentCommAdapter(this, commentList);
        recyclerView.setAdapter(commAdapter);

        ivlike.setOnClickListener(this);
        llcomment.setOnClickListener(this);

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

                if (videoInfo == null) {
                    showErr("数据错误");
                    return;
                }

                chooseProferm();

            }
        });
    }

    private void chooseProferm() {

        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("微信好友", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                share(SHARE_MEDIA.WEIXIN);

                            }

                        })
                .addSheetItem("微信朋友圈", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                share(SHARE_MEDIA.WEIXIN_CIRCLE);

                            }
                        })
                .addSheetItem("QQ", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                share(SHARE_MEDIA.QQ);

                            }
                        })
                .addSheetItem("QQ空间", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                share(SHARE_MEDIA.QZONE);
                            }
                        })
                .addSheetItem("微博", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                share(SHARE_MEDIA.SINA);
                            }
                        })

                .show();

    }

    private void share(SHARE_MEDIA perform) {

        ShareUtils.shareWeb(VideoSpeechActivity.this, videoInfo.getVideourl()
                , videoInfo.getDoctorname(), videoInfo.getTitle()
                , ApiService.WEB_ROOT + videoInfo.getDoctorimg(), -1, perform);
    }


    private VideoSpeechDetailEntry.DataBean videoInfo;

    @Override
    public void setInfo(VideoSpeechDetailEntry.DataBean data) {

        videoInfo = data;

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
        type = data.getType();
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

    @Override
    public void setCommentData(List<CommComListEntry.DataBean> data) {

        commentList.addAll(data);

        if (commAdapter != null)
            commAdapter.notifyDataSetChanged();

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

                if ("2".equals(type))//如果是讲堂，收藏type为3
                    type = "3";

                iCollectionPres.setId(id);

                if (ivlike.isSelected())
                    //取消收藏
                    iCollectionPres.removeCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), type);
                else
                    //添加收藏
                    iCollectionPres.addCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), type);

                break;

            case R.id.llcomment://评论

                /*
                 with_pic = getIntent().getBooleanExtra("with_pic", false);
                id = getIntent().getStringExtra("id");
                type = getIntent().getStringExtra("type");
                ordertype = getIntent().getStringExtra("ordertype");
                ordercode = getIntent().getStringExtra("ordercode");
                 */
                Intent mIntent = new Intent(this, CommentActivity.class);
                mIntent.putExtra("with_pic", true);
                mIntent.putExtra("id", id);
                mIntent.putExtra("type", "6");
                mIntent.putExtra("ordertype", "");
                mIntent.putExtra("ordercode", "");
                startActivity(mIntent);

                break;
        }

    }

    //点击关注病友
    public void goodAction(CommComListEntry.DataBean dataBean) {

        commentInfo = dataBean;

        iGoodPres.setToken((String) SaveUtils.get(this, SpValue.TOKEN, ""));

        //type 资讯(1),文章(2),直播(3),讲堂(4),帖子(5),评论(6);
//        switch (type) {
//
//            case "1"://直播
//                iGoodPres.setType("3");
//                break;
//
//            case "3"://视频
//                iGoodPres.setType("4");
//                break;
//
//        }
        iGoodPres.setType("6");
        iGoodPres.setRelateid(dataBean.getEvaluateid());
        if ("1".equals(dataBean.getIsdz())) {
            //已经点赞， 取消点赞
            iGoodPres.removeGood();
        } else {
            //未点赞， 点击点赞
            iGoodPres.good();
        }

    }


    //点赞成功
    @Override
    public void goodSuccess() {

        if (commentInfo != null)
            commentInfo.setIsdz("1");

        if (commAdapter != null)
            commAdapter.notifyDataSetChanged();

    }

    //点击关注
    public void attentionAction(CommComListEntry.DataBean dataBean) {

        commentInfo = dataBean;

        iCollectionPres.setId(dataBean.getEvaluateid());
        if ("1".equals(dataBean.getIsfollow())) {

            //已关注，点击取消关注
            iCollectionPres.removeCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "6");

        } else {
            //未关注， 点击关注
            iCollectionPres.addCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "6");

        }

    }

    //取消点赞
    @Override
    public void removeSuccess() {

        if (commentInfo != null)
            commentInfo.setIsdz("0");

        if (commAdapter != null)
            commAdapter.notifyDataSetChanged();

    }

    //收藏， 关注成功
    @Override
    public void collectionSuccess(String type) {

        if (this.type.equals(type)) {
            //收藏视频
            ivlike.setSelected(true);
        } else if ("6".equals(type)) {
            //关注病友
            if (commentInfo != null)
                commentInfo.setIsfollow("1");

            if (commAdapter != null)
                commAdapter.notifyDataSetChanged();
        }

    }

    //取消收藏， 关注成功
    @Override
    public void removeCollectionSuccess(String type) {

        if (this.type.equals(type)) {
            //收藏视频
            ivlike.setSelected(false);
        } else if ("6".equals(type)) {
            //关注病友
            if (commentInfo != null)
                commentInfo.setIsfollow("0");

            if (commAdapter != null)
                commAdapter.notifyDataSetChanged();
        }

    }

    //回复
    public void replyAction(CommComListEntry.DataBean dataBean) {

        Intent m = new Intent(this, ReplyActivity.class);
        m.putExtra("id", dataBean.getId());
        startActivityForResult(m, 0x998);

    }
}
