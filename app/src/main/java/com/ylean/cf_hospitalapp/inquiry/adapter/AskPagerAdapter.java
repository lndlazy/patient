package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.FragmentThree;

import java.util.ArrayList;
import java.util.List;

public class AskPagerAdapter extends PagerAdapter {

    private List<View> viewList = new ArrayList<>();
    private String[] strs = {"图文问诊", "电话问诊", "视频问诊"};
    private Context context;

    private boolean isLoad1 = false;
    private boolean isLoad2 = false;
    private boolean isLoad3 = false;
    private final View view1;
    private final View view2;
    private final View view3;
    private FragmentThree fragmentThree;

    public AskPagerAdapter(Context context, FragmentThree fragmentThree) {

        this.context = context;

        view1 = View.inflate(context, R.layout.item_recyclerview, null);
        view2 = View.inflate(context, R.layout.item_recyclerview, null);
        view3 = View.inflate(context, R.layout.item_recyclerview, null);
        this.viewList.add(view1);
        this.viewList.add(view2);
        this.viewList.add(view3);

        isLoad1 = false;
        isLoad2 = false;
        isLoad3 = false;

        this.fragmentThree = fragmentThree;

//        loadPageOneInfo(view1, SpValue.ASK_TYPE_PIC);
//        loadPageOneInfo(view2, SpValue.ASK_TYPE_TEL);
//        loadPageOneInfo(view3, SpValue.ASK_TYPE_VIDEO);

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

        switch (position) {

            case 0:

                if (!isLoad1) {
                    isLoad1 = true;

                    if (fragmentThree!=null)
                        fragmentThree.loadPageOne(view1);
                }

                break;

            case 1:

                if (!isLoad2) {
                    isLoad2 = true;
                }

                break;

            case 2:

                if (!isLoad3) {
                    isLoad3 = true;

                }
                break;


        }


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
