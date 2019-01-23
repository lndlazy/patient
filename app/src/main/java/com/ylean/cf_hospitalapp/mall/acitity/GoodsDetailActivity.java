package com.ylean.cf_hospitalapp.mall.acitity;

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
import com.ylean.cf_hospitalapp.mall.acitity.OrderConfirmActivity;
import com.ylean.cf_hospitalapp.my.bean.GoodsListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 商品详情页面
 * Created by linaidao on 2019/1/7.
 */

public class GoodsDetailActivity extends BaseActivity {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    //    private android.widget.TextView tvNext;
    private WebView wb;
    private GoodsListEntry.DataBean goodsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goodsInfo = getIntent().getParcelableExtra("goodsInfo");
        setContentView(R.layout.act_goods_detail);

        this.wb = (WebView) findViewById(R.id.wb);
//        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        MyWebViewClient client = new MyWebViewClient();

        WebSettings settings = wb.getSettings();

        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);

        wb.setWebViewClient(client);

        wb.setWebChromeClient(new WebChromeClient());

        wb.loadUrl(ApiService.WEB_ROOT + ApiService.GOODDS_DETAIL + "?token=" +
                (String) SaveUtils.get(this, SpValue.TOKEN, "") + "&id=" + goodsInfo.getId());
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Logger.d("url地址::" + url);

            if (goodsInfo == null) {
                view.loadUrl(url);
                return true;
            }

            if (!TextUtils.isEmpty(url)
                    && url.equals(ApiService.WEB_ROOT + "/api/app/pro/toexchange?proid=" + goodsInfo.getId() + "&protype="
                    + goodsInfo.getSkutype() + "&point=" + goodsInfo.getPoints() + "&price=" + goodsInfo.getPrice())) {

                Logger.d("拦截::" + url);

                //拦截url地址
                Intent m = new Intent(GoodsDetailActivity.this, OrderConfirmActivity.class);
                m.putExtra("id", goodsInfo.getId());
                startActivity(m);

//                showErr("暂未开发");

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


}
