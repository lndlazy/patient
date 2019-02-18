package com.ylean.cf_hospitalapp.popular.presenter;

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
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.popular.bean.ExpertEntry;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.ArrayList;
import java.util.List;

public class SpeechAdapter extends PagerAdapter {

    private List<View> viewList;
    private String[] strs = {"专家讲堂", "图文资讯", "疾病百科", "专家直播"};
    private Context context;
    private int page;
    private String input_name;
//    private final View view1;
//    private final View view2;
//    private final View view3;
//    private final View view4;
//    private List<ExpertEntry.DataBean> expertVideoList = new ArrayList<>();
//    private List<ExpertEntry.DataBean> expertSpeechList = new ArrayList<>();

    public void setInput_name(String input_name) {
        this.input_name = input_name;
    }

    public String getInput_name() {
        return input_name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public SpeechAdapter(Context context, List<View> viewList) {

        this.context = context;
        this.viewList = viewList;

//        view1 = View.inflate(context, R.layout.item_recyclerview, null);
//        view2 = View.inflate(context, R.layout.item_recyclerview, null);
//        view3 = View.inflate(context, R.layout.item_recyclerview, null);
//        view4 = View.inflate(context, R.layout.item_recyclerview, null);
//        viewList.add(view1);
//        viewList.add(view2);
//        viewList.add(view3);
//        viewList.add(view4);

//        loaded1 = false;
//        loaded2 = false;
//        loaded3 = false;
//        loaded4 = false;

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

//    private boolean loaded1 = false;
//    private boolean loaded2 = false;
//    private boolean loaded3 = false;
//    private boolean loaded4 = false;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null)
            parent.removeAllViews();
        container.addView(view);

//        Logger.d("实例化 view ::" + position);

//        switch (position) {
//
//            case 0://专家直播
//
//                if (!loaded1) {
//                    expertInfo(SpValue.EXPERT_VIDEO);
//                    loaded1 = true;
//                }
//
//                break;
//            case 1://专家讲堂
//
//                if (!loaded2) {
//                    expertInfo(SpValue.EXPERT_SPEECH);
//                    loaded2 = true;
//                }
//
//                break;
//            case 2:
//
//                if (!loaded3) {
//                    loaded3 = true;
//                }
//                break;
//
//            case 3:
//                if (!loaded4) {
//                    loaded4 = true;
//                }
//                break;
//
//        }

        return view;
    }


//
//    //专家讲堂
//    private void initExpertSpeech(ExpertEntry expertEntry) {
//
//        if (expertEntry == null || expertEntry.getData() == null)
//            return;
//
//        expertSpeechList.addAll(expertEntry.getData());
//        RecyclerView recyclerView = view2.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_recyclerview_item_gray));
//        recyclerView.addItemDecoration(divider);
//        SpeechItemAdapter speechItemAdapter = new SpeechItemAdapter(context, expertVideoList);
//        recyclerView.setAdapter(speechItemAdapter);
//
//    }

//    int mLastVisibleItemPosition;
//    int firstVisibleItemPosition;

    //专家直播
//    private void initExpertVideo(ExpertEntry expertEntry) {
//
//        if (expertEntry == null || expertEntry.getData() == null)
//            return;
//
//        expertVideoList.addAll(expertEntry.getData());
//
//        RecyclerView recyclerView = view1.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_recyclerview_item_gray));
//        recyclerView.addItemDecoration(divider);
//        final SpeechItemAdapter speechItemAdapter = new SpeechItemAdapter(context, expertVideoList);
//        recyclerView.setAdapter(speechItemAdapter);
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//
//                if (layoutManager instanceof LinearLayoutManager) {
//                    mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
//                    firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
//                }
//
//                if (speechItemAdapter != null) {
//                    if (newState == RecyclerView.SCROLL_STATE_IDLE
//                            && mLastVisibleItemPosition + 1 == speechItemAdapter.getItemCount() && firstVisibleItemPosition > 0) {
//
//                        Logger.d("第一个条目::" + firstVisibleItemPosition + ",第二个条目::" + mLastVisibleItemPosition);
//
//                        //发送网络请求获取更多数据
//                        Logger.d("加载更多??????");
//                    }
//                }
//
//            }
//        });
//    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strs[position];
    }
}