package com.ylean.cf_hospitalapp.home.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.home.bean.SearchListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/10.
 */

public interface ISearchView extends BaseView{
    void setSearchInfo(List<SearchListEntry.DataBean> data);

    void setSearchInfo(SearchListEntry basebean);
}
