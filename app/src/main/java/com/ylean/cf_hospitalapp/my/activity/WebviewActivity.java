package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * webview 页面
 * Created by linaidao on 2018/12/25.
 */

public class WebviewActivity extends BaseActivity {

    private WebView wb;
    private String url;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_webview);

        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");

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


        MyWebViewClient client = new MyWebViewClient();

        WebSettings settings = wb.getSettings();

        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);

        wb.setWebViewClient(client);
        wb.loadUrl(url);
        wb.setWebChromeClient(new WebChromeClient());

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tbv.setTitle(title);

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
