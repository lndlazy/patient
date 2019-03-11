package com.ylean.cf_hospitalapp.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.comm.pres.IShareTitlePreseter;
import com.ylean.cf_hospitalapp.comm.view.IShareTitleView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.ShareUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 资讯页面
 * Created by linaidao on 2019/1/1.
 */

public class NewsActivity extends BaseActivity implements IShareTitleView {

    private String id;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private WebView wb;
    private String url;

    private IShareTitlePreseter iShareTitlePreseter = new IShareTitlePreseter(this);

    private String shareTile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_news);
        this.wb = findViewById(R.id.wb);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        id = getIntent().getStringExtra("id");

        url = ApiService.WEB_ROOT + ApiService.H5_NEWS + "?ch=1&token=" +
                (String) SaveUtils.get(this, SpValue.TOKEN, "") + "&id=" + id;

        iShareTitlePreseter.getShareTileByid(id);

        MyWebViewClient client = new MyWebViewClient();

        WebSettings settings = wb.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        wb.setWebViewClient(client);

        wb.setWebChromeClient(new WebChromeClient());

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tbv.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProferm();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        wb.loadUrl(url);

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

        String shareUrl = ApiService.WEB_ROOT + ApiService.H5_NEWS_SHARE
                + "?id=" + id;
        ShareUtils.shareWeb(NewsActivity.this, shareUrl
                , shareTile, "好医无忧"
                , "", R.mipmap.logo, perform);
    }

    @Override
    public void setShareTitle(String data) {
        shareTile = data;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logger.d("url地址::" + url);

            if (!TextUtils.isEmpty(url)
                    && url.equals(ApiService.WEB_ROOT + "/api/app/art/tocomment?id=" + id + "&type=5")) {

                //拦截url地址
                Intent m = new Intent(NewsActivity.this, CommentActivity.class);
                m.putExtra("with_pic", false);
                m.putExtra("id", id);
                m.putExtra("type", "");
                m.putExtra("ordertype", "");
                m.putExtra("ordercode", "");

                startActivity(m);

            } else {
                view.loadUrl(url);
            }

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


//    private void infoDetail() {
//
//        RetrofitHttpUtil
//                .getInstance()
//                .infoDetail(
//                        new BaseNoTObserver<Basebean>() {
//                            @Override
//                            public void onHandleSuccess(Basebean basebean) {
//
//                            }
//
//                            @Override
//                            public void onHandleError(String message) {
//
//                                showErr(message);
//                            }
//
//                        }
//                        , SpValue.CH
//                        , (String) SaveUtils.get(this, SpValue.TOKEN, "")
//                        , id);
//
//
//    }


}
