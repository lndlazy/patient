package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.my.adapter.MyNewsListAdapter;
import com.ylean.cf_hospitalapp.my.adapter.NewsAdapter;
import com.ylean.cf_hospitalapp.my.bean.NewsListEntry;
import com.ylean.cf_hospitalapp.my.presenter.INewsPres;
import com.ylean.cf_hospitalapp.my.view.INewsView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的消息
 * Created by linaidao on 2019/1/2.
 */

public class MyNewsListActivity extends BaseActivity implements INewsView {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<View> viewList = new ArrayList<>();
    private int currentPosition;
    private INewsPres iNewsPres = new INewsPres(this);
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;

    private Map<Integer, MyNewsListAdapter> mAdapterMap = new LinkedHashMap<>();
    private Map<Integer, List<NewsListEntry.DataBean>> mListMap = new LinkedHashMap<>();

//    private List<Integer> cacheList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_news);

        initView();

    }

    private void initView() {
        this.mViewPager = (ViewPager) findViewById(R.id.vp_viewpager);
        this.mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addOnTabSelectedListener(listener);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < 3; i++) {
            viewList.add(View.inflate(this, R.layout.item_recyclerview, null));
        }

        NewsAdapter newsAdapter = new NewsAdapter(this, viewList);
        mViewPager.setAdapter(newsAdapter);

    }

    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            //选择的tab
            currentPosition = tab.getPosition();

            initData(viewList.get(currentPosition), tab.getPosition());

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            //离开的那个tab
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            //再次选择tab
        }
    };


    private void initData(View view, int i) {

        if (mAdapterMap.get(i) != null) {//说明已经加载过当前页面数据
            return;
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
        recyclerView.addItemDecoration(divider);

        List<NewsListEntry.DataBean> newsList = new ArrayList<>();
        MyNewsListAdapter myNewsListAdapter = new MyNewsListAdapter(this, newsList);
        recyclerView.setAdapter(myNewsListAdapter);

        mAdapterMap.put(i, myNewsListAdapter);
        mListMap.put(i, newsList);

        iNewsPres.setPage(1);

        setPresentType(i);

        iNewsPres.myNewstList((String) SaveUtils.get(this, SpValue.TOKEN, ""), i, true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (myNewsListAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == myNewsListAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
//                iFragPicPres.nextPage(SpValue.ASK_TYPE_PIC, (String) SaveUtils.get(getActivity(), SpValue.TOKEN, ""));

                    iNewsPres.setPage(iNewsPres.getPage() + 1);
                    setPresentType(i);
                    iNewsPres.myNewstList((String) SaveUtils.get(MyNewsListActivity.this, SpValue.TOKEN, ""), i, false);

                }

            }

        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void setPresentType(int i) {
        switch (i) {

            case 0:
                iNewsPres.setType("1");//系统消息
                break;
            case 1:
                iNewsPres.setType("2");//订单消息
                break;
            case 2:
                iNewsPres.setType("3");//互动消息
                break;
        }
    }

    @Override
    public void setNewsData(List<NewsListEntry.DataBean> data, int i, boolean b) {

        try {

            if (mAdapterMap.get(i) != null) {

                if (b)
                    mListMap.get(i).clear();
                mListMap.get(i).addAll(data);
                mAdapterMap.get(i).notifyDataSetChanged();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
