package com.ylean.cf_hospitalapp.popular.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.my.activity.WebviewActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_disease_detail);

        id = getIntent().getStringExtra("id");
//        title = getIntent().getStringExtra("title");
//        url = getIntent().getStringExtra("url");

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
        wb.loadUrl(ApiService.WEB_ROOT + ApiService.DISEASE_DETAIL + "?id=" + id);
        wb.setWebChromeClient(new WebChromeClient());

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tbv.setTitle("疾病详情");

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
