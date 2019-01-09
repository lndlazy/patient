package com.ylean.cf_hospitalapp.popular;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseFragment;
import com.ylean.cf_hospitalapp.home.activity.ArtDetailAct;
import com.ylean.cf_hospitalapp.home.activity.NewsActivity;
import com.ylean.cf_hospitalapp.home.activity.VideoSpeechActivity;
import com.ylean.cf_hospitalapp.popular.activity.DiseaseDetailActivity;
import com.ylean.cf_hospitalapp.popular.adapter.DiseaseListAdapter;
import com.ylean.cf_hospitalapp.popular.bean.DiseaseListEntry;
import com.ylean.cf_hospitalapp.popular.bean.ExpertEntry;
import com.ylean.cf_hospitalapp.popular.presenter.IFragTwoPres;
import com.ylean.cf_hospitalapp.popular.presenter.SpeechAdapter;
import com.ylean.cf_hospitalapp.popular.presenter.SpeechItemAdapter;
import com.ylean.cf_hospitalapp.popular.view.IFragTwoView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.EditPicView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentTwo extends BaseFragment implements IFragTwoView, SwipeRefreshLayout.OnRefreshListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private IFragTwoPres iFragTwoPres = new IFragTwoPres(this);

    private List<View> viewList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private int currentPosition;//当前选择的页面的角标
    private int mLastVisibleItemPosition;
    private int firstVisibleItemPosition;
    private EditPicView epv;
    private RecyclerView videoRecyclerView;
    private SpeechItemAdapter videAdapter;
    private List<ExpertEntry.DataBean> videoList = new ArrayList<>();//专家直播集合

    private RecyclerView speechRecyclerView;
    private SpeechItemAdapter speechAdapter;
    private List<ExpertEntry.DataBean> speechList = new ArrayList<>();//专家讲堂集合

    private RecyclerView newsRecyclerView;
    private SpeechItemAdapter newsAdapter;
    private List<ExpertEntry.DataBean> newsList = new ArrayList<>();//专家讲堂集合


    private RecyclerView disRecyclerView;
    private DiseaseListAdapter diseaseListAdapter;
    private List<DiseaseListEntry.DataBean> diseaseList = new ArrayList<>();//疾病百科

    private List<Integer> cacheList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, null);

        initView(view);

        return view;
    }

    private void initView(View view) {

        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.vp_viewpager);
        mTabLayout.addOnTabSelectedListener(listener);
        epv = view.findViewById(R.id.epv);
        mTabLayout.setupWithViewPager(mViewPager);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        for (int i = 0; i < 4; i++) {
            viewList.add(View.inflate(getActivity(), R.layout.item_recyclerview, null));
        }

        SpeechAdapter speechAdapter = new SpeechAdapter(getActivity(), viewList);
        mViewPager.setAdapter(speechAdapter);

    }

    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            //选择的tab
            Logger.d("onTabSelected:" + tab.getText().toString());

            currentPosition = tab.getPosition();

            //如果加载过当前页面，就不再加载了
            if (cacheList.contains(currentPosition))
                return;

            cacheList.add(currentPosition);

            switch (currentPosition) {

                case 0://专家直播

                    iFragTwoPres.expertInfo(SpValue.EXPERT_VIDEO, (String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""), currentPosition);

                    break;

                case 1://专家讲堂

                    iFragTwoPres.expertInfo(SpValue.EXPERT_SPEECH, (String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""), currentPosition);

                    break;

                case 2://图文资讯

                    iFragTwoPres.picNewsList((String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""));

                    break;

                case 3://疾病百科

                    iFragTwoPres.diseaseList();

                    break;

            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            //离开的那个tab
            Logger.d("onTabUnselected" + tab.getText().toString());
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            //再次选择tab
            Logger.d("onTabReselected" + tab.getText().toString());
        }
    };

    @Override
    public void onRefresh() {

        iFragTwoPres.setCurrentPage(1);

        switch (currentPosition) {

            case 0://直播

                iFragTwoPres.expertInfo(SpValue.EXPERT_VIDEO, (String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""), currentPosition);

                break;

            case 1://讲堂

                iFragTwoPres.expertInfo(SpValue.EXPERT_SPEECH, (String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""), currentPosition);

                break;

            case 2://图文资讯

                iFragTwoPres.picNewsList((String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""));

                break;

            case 3://疾病百科

                iFragTwoPres.diseaseList();

                break;


        }

    }

    @Override
    public void stopRefush() {
        swipeRefreshLayout.setRefreshing(false);
    }


    //设置 专家直播数据
    @Override
    public void setExpertVideoInfo(List<ExpertEntry.DataBean> data, final int i) {

        if (cacheList.contains(i) && videAdapter != null) {
            //加载过当前页面数据，则直接添加并刷新
            if (iFragTwoPres.getCurrentPage() == 1)
                videoList.clear();
            videoList.addAll(data);

            videAdapter.notifyDataSetChanged();
            return;
        }

        View view = viewList.get(i);
        videoRecyclerView = view.findViewById(R.id.recyclerView);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_recyclerview_item_gray));
        videoRecyclerView.addItemDecoration(divider);

        Logger.d("当前页::" + iFragTwoPres.getCurrentPage());
        if (iFragTwoPres.getCurrentPage() == 1)
            videoList.clear();
        videoList.addAll(data);
        videAdapter = new SpeechItemAdapter(getActivity(), videoList);
        videoRecyclerView.setAdapter(videAdapter);

        videoRecyclerView.addOnItemTouchListener(new OnItemClickListener(videoRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m = new Intent(getActivity(), VideoSpeechActivity.class);
                m.putExtra("id", videoList.get(position).getId());
                m.putExtra("type", "1");
                startActivity(m);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        videoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (videAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == videAdapter.getItemCount() && firstVisibleItemPosition > 0) {

                    Logger.d("====加载更多====");
                    iFragTwoPres.setCurrentPage(iFragTwoPres.getCurrentPage() + 1);
                    iFragTwoPres.setSearchContent(epv.getInputInfo());
                    iFragTwoPres.expertInfo(SpValue.EXPERT_VIDEO, (String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""), i);

                }

            }
        });

    }

    //设置专家讲堂
    @Override
    public void setExpertSpeechInfo(List<ExpertEntry.DataBean> data, final int i) {

        if (cacheList.contains(i) && speechAdapter != null) {
            //加载过当前页面数据，则直接添加并刷新
            if (iFragTwoPres.getCurrentPage() == 1)
                speechList.clear();
            speechList.addAll(data);

            speechAdapter.notifyDataSetChanged();
            return;
        }

        View view = viewList.get(i);
        speechRecyclerView = view.findViewById(R.id.recyclerView);
        speechRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        speechRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_recyclerview_item_gray));
        speechRecyclerView.addItemDecoration(divider);

        if (iFragTwoPres.getCurrentPage() == 1)
            speechList.clear();
        speechList.addAll(data);
        speechAdapter = new SpeechItemAdapter(getActivity(), speechList);
        speechRecyclerView.setAdapter(speechAdapter);

        speechRecyclerView.addOnItemTouchListener(new OnItemClickListener(speechRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                if (speechList.get(position) == null || TextUtils.isEmpty(speechList.get(position).getType()))
                    return;
                Intent m;
                if ("2".equals(speechList.get(position).getType())) {
                    //视频 专家讲堂
                    m = new Intent();
                    m.setClass(getActivity(), VideoSpeechActivity.class);
                    m.putExtra("id", speechList.get(position).getId());
                    m.putExtra("type", "2");
                    startActivity(m);
                } else if ("3".equals(speechList.get(position).getType())) {
                    //文章
                    m = new Intent();
                    m.setClass(getActivity(), ArtDetailAct.class);
                    m.putExtra("id", speechList.get(position).getId());
                    startActivity(m);
                }

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        speechRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (speechAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == speechAdapter.getItemCount() && firstVisibleItemPosition > 0) {

                    iFragTwoPres.setCurrentPage(iFragTwoPres.getCurrentPage() + 1);
                    iFragTwoPres.setSearchContent(epv.getInputInfo());
                    iFragTwoPres.expertInfo(SpValue.EXPERT_SPEECH, (String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""), i);

                }

            }
        });

    }

    @Override
    public void setNewsInfo(List<ExpertEntry.DataBean> data) {

        if (cacheList.contains(2) && newsAdapter != null) {
            //加载过当前页面数据，则直接添加并刷新
            if (iFragTwoPres.getCurrentPage() == 1)
                newsList.clear();
            newsList.addAll(data);

            newsAdapter.notifyDataSetChanged();
            return;
        }

        View view = viewList.get(2);
        newsRecyclerView = view.findViewById(R.id.recyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_recyclerview_item_gray));
        newsRecyclerView.addItemDecoration(divider);

        if (iFragTwoPres.getCurrentPage() == 1)
            newsList.clear();
        newsList.addAll(data);
        newsAdapter = new SpeechItemAdapter(getActivity(), newsList);
        newsRecyclerView.setAdapter(newsAdapter);

        newsRecyclerView.addOnItemTouchListener(new OnItemClickListener(newsRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m = new Intent(getActivity(), NewsActivity.class);
                m.putExtra("id", newsList.get(position).getId());
                startActivity(m);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        newsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (newsAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == newsAdapter.getItemCount() && firstVisibleItemPosition > 0) {

                    iFragTwoPres.setCurrentPage(iFragTwoPres.getCurrentPage() + 1);
                    iFragTwoPres.setSearchContent(epv.getInputInfo());
                    iFragTwoPres.picNewsList((String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""));

                }

            }
        });


    }

    //设置疾病百科
    @Override
    public void setDiseaseInfo(List<DiseaseListEntry.DataBean> data) {

        if (cacheList.contains(3) && diseaseListAdapter != null) {
            //加载过当前页面数据，则直接添加并刷新
            if (iFragTwoPres.getCurrentPage() == 1)
                diseaseList.clear();
            diseaseList.addAll(data);

            diseaseListAdapter.notifyDataSetChanged();
            return;
        }

        View view = viewList.get(3);
        disRecyclerView = view.findViewById(R.id.recyclerView);
        disRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        disRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_recyclerview_item_gray));
        disRecyclerView.addItemDecoration(divider);

        Logger.d("当前页::" + iFragTwoPres.getCurrentPage());
        if (iFragTwoPres.getCurrentPage() == 1)
            diseaseList.clear();
        diseaseList.addAll(data);
        diseaseListAdapter = new DiseaseListAdapter(getActivity(), diseaseList);
        disRecyclerView.setAdapter(diseaseListAdapter);

        disRecyclerView.addOnItemTouchListener(new OnItemClickListener(disRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m = new Intent(getActivity(), DiseaseDetailActivity.class);
                m.putExtra("id", diseaseList.get(position).getId());
                startActivity(m);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        disRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (diseaseListAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == diseaseListAdapter.getItemCount() && firstVisibleItemPosition > 0) {

                    Logger.d("====加载更多====");
                    iFragTwoPres.setCurrentPage(iFragTwoPres.getCurrentPage() + 1);
                    iFragTwoPres.setSearchContent(epv.getInputInfo());
                    iFragTwoPres.diseaseList();

                }

            }
        });

    }
}
