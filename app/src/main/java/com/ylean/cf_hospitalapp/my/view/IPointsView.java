package com.ylean.cf_hospitalapp.my.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.my.adapter.GoodsAdapter;
import com.ylean.cf_hospitalapp.my.bean.GoodsListEntry;
import com.ylean.cf_hospitalapp.my.bean.PointsEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/2.
 */

public interface IPointsView extends BaseView {

    void setClassifyInfo(List<PointsEntry.DataBean> data);

    void setGoodsList(List<GoodsListEntry.DataBean> data);

    void error();


//    void setGoodsInfo(List<GoodsListEntry.DataBean> data, GoodsAdapter recyclerView);
}
