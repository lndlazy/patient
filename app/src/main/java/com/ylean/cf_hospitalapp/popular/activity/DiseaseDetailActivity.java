package com.ylean.cf_hospitalapp.popular.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.comm.pres.IShareTitlePreseter;
import com.ylean.cf_hospitalapp.comm.view.IShareTitleView;
import com.ylean.cf_hospitalapp.home.activity.ArtDetailAct;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.ShareUtils;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 疾病百科
 * Created by linaidao on 2019/1/8.
 */

public class DiseaseDetailActivity extends BaseActivity {

    private WebView wb;
    //    private String url;
//    private String title;
    private String id;
    private String url;
    private String shareTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_disease_detail);

        id = getIntent().getStringExtra("id");
        shareTitle = getIntent().getStringExtra("shareTitle");


        initView();

    }

    private void initView() {

        this.wb = findViewById(R.id.wb);
        TitleBackBarView tbv = findViewById(R.id.tbv);

//        WebSettings settings = wb.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setJavaScriptEnabled(true);
//        settings.setBuiltInZoomControls(false);
//        settings.setSupportZoom(false);
//        settings.setDisplayZoomControls(false);
//        settings.setDomStorageEnabled(true);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        tbv.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProferm();
            }
        });

        MyWebViewClient client = new MyWebViewClient();

        WebSettings settings = wb.getSettings();

        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);

        wb.setWebViewClient(client);
        url = ApiService.WEB_ROOT + ApiService.DISEASE_DETAIL + "?id=" + id;
        wb.setWebChromeClient(new WebChromeClient());

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tbv.setTitle("疾病详情");

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

        ShareUtils.shareWeb(DiseaseDetailActivity.this, url
                , "好医无忧", shareTitle
                , "", R.mipmap.logo, perform);
    }


    @Override
    protected void onResume() {
        super.onResume();
        wb.loadUrl(url);

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {

        if (wb.canGoBack())
            wb.goBack();
        else
            super.onBackPressed();

    }

}
