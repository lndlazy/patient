package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.ShareUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;

/**
 * 推荐好友
 * Created by linaidao on 2019/1/22.
 */

public class RecommandActivity extends BaseActivity implements View.OnClickListener {

    private WebView wb;
//    private String url;
//    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_recommand);

//        title = getIntent().getStringExtra("title");
//        url = getIntent().getStringExtra("url");

        initView();

    }

    private void initView() {

        this.wb = findViewById(R.id.wv);

        ImageView iv_left = findViewById(R.id.iv_left);
        TextView tvnext = findViewById(R.id.tvnext);
        TextView iv_right = findViewById(R.id.iv_right);

//        WebSettings settings = wb.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setJavaScriptEnabled(true);
//        settings.setBuiltInZoomControls(false);
//        settings.setSupportZoom(false);
//        settings.setDisplayZoomControls(false);
//        settings.setDomStorageEnabled(true);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        tvnext.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        iv_right.setOnClickListener(this);

        MyWebViewClient client = new MyWebViewClient();

        WebSettings settings = wb.getSettings();

        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);

        wb.setWebViewClient(client);
        wb.loadUrl(ApiService.WEB_ROOT + ApiService.SERVICE_INVITE);
        wb.setWebChromeClient(new WebChromeClient());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvnext:

                Logger.d("用户userid::" + (String) SaveUtils.get(RecommandActivity.this, SpValue.USER_ID, ""));

                chooseProferm(ApiService.WEB_ROOT + "/api/app/art/shareapplyUser?userid="
                        + (String) SaveUtils.get(RecommandActivity.this, SpValue.USER_ID, "") + "&ch=1");
                break;

            case R.id.iv_left:
                finish();
                break;

            case R.id.iv_right://邀请情况

                nextActivity(InviteListActivity.class);
                break;

        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

//            if (!TextUtils.isEmpty(url)
//                    && url.equals(ApiService.WEB_ROOT + "/api/app/art/shareapplyUser?userid=" +
//                    (String) SaveUtils.get(RecommandActivity.this, SpValue.USER_ID, "")+ "&ch=1")) {
//
//                //拦截url地址, 进行分享
//                chooseProferm(url);
//
//            } else {
//                view.loadUrl(url);
//            }

            return true;
        }
    }


    private void chooseProferm(String url) {

        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("微信好友", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                share(SHARE_MEDIA.WEIXIN, url);

                            }

                        })
                .addSheetItem("微信朋友圈", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                share(SHARE_MEDIA.WEIXIN_CIRCLE, url);

                            }
                        })
                .addSheetItem("QQ", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                share(SHARE_MEDIA.QQ, url);

                            }
                        })
                .addSheetItem("QQ空间", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                share(SHARE_MEDIA.QZONE, url);
                            }
                        })
                .addSheetItem("微博", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                share(SHARE_MEDIA.SINA, url);
                            }
                        })

                .show();

    }

    private void share(SHARE_MEDIA perform, String url) {

        ShareUtils.shareWeb(RecommandActivity.this, url
                , "好医在手健康无忧，邀请好友下载轻松得积分！", "一款专注于血管瘤及脉管畸形的专业APP"
                , "", R.mipmap.logo, perform);
    }


    @Override
    public void onBackPressed() {

        if (wb.canGoBack())
            wb.goBack();
        else
            super.onBackPressed();

    }

}
