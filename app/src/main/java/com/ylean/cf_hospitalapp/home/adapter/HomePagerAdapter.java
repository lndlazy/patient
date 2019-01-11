package com.ylean.cf_hospitalapp.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.home.activity.NewsActivity;
import com.ylean.cf_hospitalapp.home.bean.BannerBean;
import com.ylean.cf_hospitalapp.my.activity.WebviewActivity;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends PagerAdapter {

    private List<View> viewList = new ArrayList<>();
    private List<BannerBean.DataBean> bannerList;
    private Context context;

    public HomePagerAdapter(Context context, List<View> viewList, List<BannerBean.DataBean> bannerList) {
        this.context = context;
        this.viewList = viewList;
        this.bannerList = bannerList;
    }

    public void setViewList(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = viewList.get(position);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null)
            parent.removeAllViews();
        container.addView(view);

        SimpleDraweeView imageView = view.findViewById(R.id.ivImg);
        if (bannerList.size() > 0)
            imageView.setImageURI(Uri.parse(ApiService.WEB_ROOT + bannerList.get(position).getImgurl()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (bannerList.get(position).getType()) {

                    case "-1"://不需要跳转
                        break;

                    case "1"://外部链接
                        Intent m = new Intent(context, WebviewActivity.class);
                        m.putExtra("url", bannerList.get(position).getUrl());
                        m.putExtra("title", "详情");
                        context.startActivity(m);
                        break;

                    case "2"://医生详情 TODO
                        break;

                }
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
