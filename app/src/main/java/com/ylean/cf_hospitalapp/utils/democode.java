//package com.ylean.cf_hospitalapp.utils;
//
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.ylean.cf_hospitalapp.R;
//
///**
// * Created by linaidao on 2019/1/3.
// */
//
//public class democode {
//
//
////    xmlns:fresco="http://schemas.android.com/apk/res-auto"
////    fresco:placeholderImageScaleType="fitXY"
////    fresco:roundedCornerRadius="@dimen/x5"
////    fresco:roundAsCircle="true"
//
//
//
////    xmlns:custom="http://schemas.android.com/apk/res-auto"
////    xmlns:fresco="http://schemas.android.com/apk/res-auto"
////    android:layout_width="match_parent"
////    android:layout_height="match_parent"
////    android:background="@android:color/white"
////    android:orientation="vertical">
////
////    <com.ylean.cf_hospitalapp.widget.TitleBackBarView
////    android:id="@+id/tbv"
////    android:layout_width="match_parent"
////    android:layout_height="@dimen/x45"
////    custom:mTitleBarColor="@color/tab_colorf9"
////    custom:mTitleBarText="科室选择"
////    custom:mTitleLeft="@mipmap/ic_arrow_left" />
////
////    <include layout="@layout/line_gray" />
//
//
//
//
////
////     recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
////    //添加自定义分割线
////    DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
////        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
////        recyclerView.addItemDecoration(divider);
//
//// amLayoutManager.setSmoothScrollbarEnabled(true);
////        amLayoutManager.setAutoMeasureEnabled(true);
////        recyclerViewAM.setHasFixedSize(true);
////        recyclerViewAM.setNestedScrollingEnabled(false);
//
////    private int mPicFirstVisibleItemPosition;
////    private int mPicLastVisibleItemPosition;
//
////     recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
////
////        @Override
////        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
////            super.onScrollStateChanged(recyclerView, newState);
////
////            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
////
////            if (layoutManager instanceof LinearLayoutManager) {
////                mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
////                mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
////            }
////
////            if (picAskAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
////                    && mPicLastVisibleItemPosition + 1 == picAskAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
////                iFragPicPres.nextPage(SpValue.ASK_TYPE_PIC, (String) SaveUtils.get(getActivity(), SpValue.TOKEN, ""));
////            }
////
////        }
////
////    });
//
//
//
//}
