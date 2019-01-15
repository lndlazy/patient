package com.ylean.cf_hospitalapp.register.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ylean.cf_hospitalapp.register.activity.ChooseNumActivity;
import com.ylean.cf_hospitalapp.register.bean.DoctorTypeEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/4.
 */

public class DoctorTypeAdapter extends PagerAdapter {

    private List<View> viewList;

    private ChooseNumActivity activity;

    private List<DoctorTypeEntry.DataBean> doctorTypeList;

    public DoctorTypeAdapter(ChooseNumActivity activity, List<DoctorTypeEntry.DataBean> doctorTypeList, List<View> viewList) {

        this.activity = activity;
        this.doctorTypeList = doctorTypeList;

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

//        activity.initPageView(view, position);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return doctorTypeList.get(position).getDoctitle();
    }
}
