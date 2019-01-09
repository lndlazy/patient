package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.activity.PointsMallAct;
import com.ylean.cf_hospitalapp.my.bean.PointsEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.popular.bean.ExpertEntry;
import com.ylean.cf_hospitalapp.popular.presenter.SpeechItemAdapter;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linaidao on 2019/1/2.
 */

public class ClassifyAdapter extends PagerAdapter {

    private Context context;
    private List<PointsEntry.DataBean> pointsClassifys;
    private List<View> viewList;

    public ClassifyAdapter(Context context, List<PointsEntry.DataBean> pointsClassifys, List<View> viewList) {

        this.context = context;
        this.pointsClassifys = pointsClassifys;
        this.viewList = viewList;

//        for (int i = 0; i < pointsClassifys.size(); i++) {
//            viewList.add(View.inflate(context, R.layout.item_classify, null));
//        }

    }

    public void setPointsClassifys(List<PointsEntry.DataBean> pointsClassifys) {
        this.pointsClassifys = pointsClassifys;
    }

    @Override
    public int getCount() {
        return pointsClassifys.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null)
            parent.removeAllViews();
        container.addView(view);

//        context.setGoodsInfo(viewList.get(position), position, pointsClassifys.get(position));

        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pointsClassifys.get(position).getName();
    }
}