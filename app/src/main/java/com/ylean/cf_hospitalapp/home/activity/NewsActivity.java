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
 * 资讯页面
 * Created by linaidao on 2019/1/1.
 */

public class NewsActivity extends BaseActivity {

    private String id;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_news);
        this.wb = findViewById(R.id.wb);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        id = getIntent().getStringExtra("id");

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
                Intent m = new Intent(NewsActivity.this, CommentActivity.class);
                m.putExtra("id", id);
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
