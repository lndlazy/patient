package com.ylean.cf_hospitalapp.my.presenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.my.adapter.GoodsAdapter;
import com.ylean.cf_hospitalapp.my.bean.GoodsListEntry;
import com.ylean.cf_hospitalapp.my.bean.PointsEntry;
import com.ylean.cf_hospitalapp.my.view.IPointsView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by linaidao on 2019/1/2.
 */

public class IPointsMallPres {

    private IPointsView iPointsView;

    public IPointsMallPres(IPointsView iPointsView) {
        this.iPointsView = iPointsView;
    }

    private String key;
    private int currentPage = 1;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    //获取商品 分类 类型
    public void goodsClassify() {

        RetrofitHttpUtil
                .getInstance()
                .goodsClassify(
                        new BaseNoTObserver<PointsEntry>() {
                            @Override
                            public void onHandleSuccess(PointsEntry basebean) {

                                if (basebean != null && basebean.getData() != null) {

                                    iPointsView.setClassifyInfo(basebean.getData());

                                }

                            }

                            @Override
                            public void onHandleError(String message) {

                                iPointsView.showErr(message);
                            }

                        }
                        , SpValue.CH);

    }


    //获取商品列表
    public void goodsList(String classid) {

        RetrofitHttpUtil.getInstance().goodsList(
                new BaseNoTObserver<GoodsListEntry>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);

                        iPointsView.showLoading("加载中...");
                    }

                    @Override
                    public void onHandleSuccess(GoodsListEntry goodsListEntry) {

                        iPointsView.hideLoading();
                        if (goodsListEntry != null && goodsListEntry.getData() != null) {

                            iPointsView.setGoodsList(goodsListEntry.getData());

//                                    List<GoodsListEntry.DataBean> data = goodsListEntry.getData();

//                                    if (currentPage == 1)
//                                        goodsList.clear();
//                                    goodsList.addAll(goodsListEntry.getData());
//                                    goodsAdapter.notifyDataSetChanged();

//                                    iPointsView.setGoodsInfo(goodsListEntry.getData(), goodsAdapter);

//                                    if (swipeRefreshLayout != null)
//                                        swipeRefreshLayout.setRefreshing(false);

                        }

                    }

                    @Override
                    public void onHandleError(String message) {
                        iPointsView.hideLoading();
                        iPointsView.showErr(message);
                        iPointsView.error();
                    }

                }, SpValue.CH, key, classid, currentPage, SpValue.PAGE_SIZE);

    }

}
