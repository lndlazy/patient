//package com.ylean.cf_hospitalapp.login.presenter;
//
//import android.content.Context;
//import android.net.Uri;
//import android.support.v4.view.PagerAdapter;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.ylean.cf_hospitalapp.R;
//import com.ylean.cf_hospitalapp.inquiry.FragmentThree;
//import com.ylean.cf_hospitalapp.utils.SpValue;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by linaidao on 2019/1/25.
// */
//
//public class SplishAdapter extends PagerAdapter {
//
//    private List<View> viewList = new ArrayList<>();
//    private Context context;
//
//
//    public SplishAdapter(Context context) {
//
//        this.context = context;
//
//        View view1 = View.inflate(context, R.layout.item_splish, null);
//        View view2 = View.inflate(context, R.layout.item_splish, null);
//        viewList.add(view1);
//        viewList.add(view2);
//
//        SimpleDraweeView s1= view1.findViewById(R.id.sdvImg);
//        SimpleDraweeView s2= view2.findViewById(R.id.sdvImg);
//
//
//        s1.setImageURI(Uri.parse(SpValue.FRESCO_RES + R.mipmap.));
//
//    }
//
//    @Override
//    public int getCount() {
//        return viewList.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        View view = viewList.get(position);
//        ViewGroup parent = (ViewGroup) view.getParent();
//        if (parent != null)
//            parent.removeAllViews();
//        container.addView(view);
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }
//
//}
