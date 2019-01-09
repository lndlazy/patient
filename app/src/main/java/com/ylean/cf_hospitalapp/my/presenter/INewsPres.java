package com.ylean.cf_hospitalapp.my.presenter;

import com.ylean.cf_hospitalapp.my.bean.NewsListEntry;
import com.ylean.cf_hospitalapp.my.view.INewsView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * Created by linaidao on 2019/1/10.
 */

public class INewsPres {

    private INewsView iNewsView;

    public INewsPres(INewsView iNewsView) {
        this.iNewsView = iNewsView;
    }

    private String type;
    private int page;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    //我的消息列表  消息类型1 系统消息2 订单消息3 互动消息
    public void myNewstList(String token, int i, boolean b) {

        RetrofitHttpUtil.getInstance()
                .myNewstList(
                        new BaseNoTObserver<NewsListEntry>() {
                            @Override
                            public void onHandleSuccess(NewsListEntry newsListEntry) {

                                if (newsListEntry == null || newsListEntry.getData() == null)
                                    return;

//                                List<NewsListEntry.DataBean> data = newsListEntry.getData();

                                iNewsView.setNewsData(newsListEntry.getData(), i, b);

                            }

                            @Override
                            public void onHandleError(String message) {
                                iNewsView.showErr(message);
                            }

                        }, SpValue.CH, token, type, page, SpValue.PAGE_SIZE);

    }
}
