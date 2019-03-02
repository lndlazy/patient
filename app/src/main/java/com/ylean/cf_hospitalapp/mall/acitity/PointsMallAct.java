package com.ylean.cf_hospitalapp.mall.acitity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.my.adapter.ClassifyAdapter;
import com.ylean.cf_hospitalapp.my.adapter.GoodsAdapter;
import com.ylean.cf_hospitalapp.my.bean.GoodsListEntry;
import com.ylean.cf_hospitalapp.my.bean.PointsEntry;
import com.ylean.cf_hospitalapp.my.presenter.IPointsMallPres;
import com.ylean.cf_hospitalapp.my.view.IPointsView;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 积分商城
 * Created by linaidao on 2019/1/2.
 */

public class PointsMallAct extends BaseActivity implements IPointsView, SwipeRefreshLayout.OnRefreshListener {

    private IPointsMallPres iPointsMallPres = new IPointsMallPres(this);
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<View> viewList = new ArrayList<>();
    //    private ClassifyAdapter classifyAdapter;
    private List<PointsEntry.DataBean> linePointsList = new ArrayList<>();//横向tablayout对象集合
    private SwipeRefreshLayout swipeRefreshLayout;
    private int mPicLastVisibleItemPosition;
    private int mPicFirstVisibleItemPosition;
    private EditText etCommit;
    private Map<Integer, GoodsAdapter> mAdaptersMap = new LinkedHashMap<>();
    private Map<Integer, List<GoodsListEntry.DataBean>> mGoodsListMap = new LinkedHashMap<>();
    private int currentPagePosition;//当前viewpage页面的position


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_points_mall);
        initView();
        iPointsMallPres.setCurrentPage(1);
        iPointsMallPres.goodsClassify();
    }

    private void initView() {

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.vp_viewpager);
        mTabLayout.addOnTabSelectedListener(listener);
        mTabLayout.setupWithViewPager(mViewPager);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        etCommit = findViewById(R.id.etCommit);

        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etCommit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    iPointsMallPres.setCurrentPage(1);
                    iPointsMallPres.setKey(etCommit.getText().toString());
                    iPointsMallPres.goodsClassify();

                }

                return false;
            }
        });
    }

    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            //选择的tab
            currentPagePosition = tab.getPosition();

            loadGoodsInfo(currentPagePosition);
//            Logger.d("onTabSelected:" + tab.getText().toString());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            //离开的那个tab
//            Logger.d("onTabUnselected" + tab.getText().toString());
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            //再次选择tab
//            Logger.d("onTabReselected" + tab.getText().toString());
        }
    };

    @Override
    public void setClassifyInfo(List<PointsEntry.DataBean> data) {

        linePointsList = data;

        for (int i = 0; i < data.size(); i++) {
            viewList.add(View.inflate(this, R.layout.item_classify, null));
        }

        ClassifyAdapter classifyAdapter = new ClassifyAdapter(this, data, viewList);
        mViewPager.setAdapter(classifyAdapter);

    }


    //设置商品信息
    @Override
    public void setGoodsList(List<GoodsListEntry.DataBean> data) {

        try {
            swipeRefreshLayout.setRefreshing(false);
            List<GoodsListEntry.DataBean> dataBeans = mGoodsListMap.get(currentPagePosition);
            GoodsAdapter mGoodsAdapter = mAdaptersMap.get(currentPagePosition);
            if (iPointsMallPres.getCurrentPage() == 1) {
                //刷新数据
                dataBeans.clear();
            }

            dataBeans.addAll(data);
            mGoodsAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void error() {
        swipeRefreshLayout.setRefreshing(false);
    }

//    @Override
//    public void setGoodsInfo(List<GoodsListEntry.DataBean> data, GoodsAdapter goodsAdapter) {
//
//    }

//    private List<Integer> mLists = new ArrayList<>();


    public void loadGoodsInfo(final int i) {

//        if (mLists.contains(position))
//            return;
//
//        mLists.add(position);

        View view = viewList.get(i);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager doctorLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(doctorLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        final List<GoodsListEntry.DataBean> goodsList = new ArrayList<>();
        final GoodsAdapter goodsAdapter = new GoodsAdapter(this, goodsList);
        recyclerView.setAdapter(goodsAdapter);

        mAdaptersMap.put(i, goodsAdapter);
        mGoodsListMap.put(i, goodsList);

        iPointsMallPres.goodsList(linePointsList.get(i).getId());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof GridLayoutManager) {

                    mPicLastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();

                    if (goodsAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                            && mPicLastVisibleItemPosition + 1 == goodsAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {

                        iPointsMallPres.setCurrentPage(iPointsMallPres.getCurrentPage() + 1);
                        iPointsMallPres.goodsList(linePointsList.get(i).getId());
//                            iFragPicPres.nextPage(SpValue.ASK_TYPE_PIC, (String) SaveUtils.get(getActivity(), SpValue.TOKEN, ""));
                    }


                }
            }
        });


        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                //商品详情页面
                Intent m = new Intent(PointsMallAct.this, GoodsDetailActivity.class);
//                GoodsListEntry.DataBean goodsInfo = goodsList.get(position);
                m.putExtra("goodsInfo", goodsList.get(position));
                startActivity(m);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                iPointsMallPres.goodsList("", linePointsList.get(i).getId(), 1, goodsAdapter, goodsList);
//            }
//        });

    }

    @Override
    public void onRefresh() {
        iPointsMallPres.setCurrentPage(1);
        try {
            iPointsMallPres.goodsList(linePointsList.get(currentPagePosition).getId());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
