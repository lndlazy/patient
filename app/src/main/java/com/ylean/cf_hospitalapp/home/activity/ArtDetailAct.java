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
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 文章详情
 * Created by linaidao on 2019/1/1.
 */

public class ArtDetailAct extends BaseActivity {

    private WebView wb;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_act_detail);

        id = getIntent().getStringExtra("id");

        Logger.d("文章id::" + id);
        initView();
//        articleDetail();
    }

//    private void articleDetail() {
//
//
//        RetrofitHttpUtil
//                .getInstance()
//                .articleDetail(
//                        new BaseNoTObserver<Basebean>() {
//                            @Override
//                            public void onHandleSuccess(Basebean basebean) {
//
//                            }
//
//                            @Override
//                            public void onHandleError(String message) {
//                                showErr(message);
//                            }
//
//
//                        }
//                        , SpValue.CH
//                        , (String) SaveUtils.get(this, SpValue.TOKEN, "")
//                        , id);
//
//
//    }


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

        MyWebViewClient client = new MyWebViewClient();

        WebSettings settings = wb.getSettings();

        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);

        //动态加载js, JsInterface是我们定义的类,用于js调用, android 是动态加载到js代码里(html里面是 javascript:android.closeWindow();)
        //closeWindow 方法是js端调用的本地的方法
//        wb.addJavascriptInterface(new JsInterface(), "android");

        Logger.d("加载的url::" + ((ApiService.WEB_ROOT + ApiService.H5_NEWS + "?ch=1&token=" +
                (String) SaveUtils.get(this, SpValue.TOKEN, "") + "&id=" + id)));
        wb.setWebViewClient(client);
//        wb.loadUrl(ApiService.WEB_ROOT + ApiService.H5_NEWS + "?ch=1&token=" +
//                (String) SaveUtils.get(this, SpValue.TOKEN, "") + "&id=" + id);
        wb.setWebChromeClient(new WebChromeClient());

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        wb.loadUrl(ApiService.WEB_ROOT + ApiService.H5_NEWS + "?ch=1&token=" +
                (String) SaveUtils.get(this, SpValue.TOKEN, "") + "&id=" + id);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Logger.d("url地址::" + url);

            if (!TextUtils.isEmpty(url)
                    && url.equals(ApiService.WEB_ROOT + "/api/app/art/tocomment?id=" + id + "&type=5")) {

                //拦截url地址
                Intent m = new Intent(ArtDetailAct.this, CommentActivity.class);
                m.putExtra("id", id);
                startActivity(m);
                Logger.d("====评论====");

            } else {
                view.loadUrl(url);
            }

            return true;
        }

//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//            return super.shouldInterceptRequest(view, request);
//        }
//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//
//            Logger.d("url地址::" + url);
//
//            return super.shouldInterceptRequest(view, url);
//        }
    }

    @Override
    public void onBackPressed() {

        if (wb.canGoBack())
            wb.goBack();
        else
            super.onBackPressed();

    }

//    @SuppressLint("SetJavaScriptEnabled")
//    class JsInterface {
//
//        public JsInterface() {
//        }
//
//        @JavascriptInterface
//        public void addComment() {
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
////                    finish();
//                    Logger.d("添加评论?????");
//
//                }
//            });
//
//        }
//
//    }

}
