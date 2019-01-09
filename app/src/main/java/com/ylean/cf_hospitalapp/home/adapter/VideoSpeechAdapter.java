package com.ylean.cf_hospitalapp.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.FragmentThree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linaidao on 2019/1/8.
 */

public class VideoSpeechAdapter extends PagerAdapter {

    private List<View> viewList;
    private String[] strs = {"讲堂介绍", "讲堂目录"};
    private Context context;

    public VideoSpeechAdapter(Context context, List<View> viewList) {

        this.context = context;
        this.viewList = viewList;

    }

    @Override
    public int getCount() {
        return viewList.size();
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

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strs[position];
    }
}
