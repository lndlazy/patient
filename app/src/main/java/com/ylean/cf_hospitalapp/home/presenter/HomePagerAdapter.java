package com.ylean.cf_hospitalapp.home.presenter;

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

                Intent m = new Intent(context, NewsActivity.class);
                m.putExtra("id", bannerList.get(position).getId());
                context.startActivity(m);

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
