package com.ylean.cf_hospitalapp.my.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.my.bean.NewsListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/10.
 */

public interface INewsView extends BaseView{


    void setNewsData(List<NewsListEntry.DataBean> data, int i, boolean b);
}
