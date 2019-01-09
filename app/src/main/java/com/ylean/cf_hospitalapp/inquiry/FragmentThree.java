package com.ylean.cf_hospitalapp.inquiry;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.presenter.IFragPicPres;
import com.ylean.cf_hospitalapp.inquiry.view.IFreagThreeView;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryDetailAct;
import com.ylean.cf_hospitalapp.base.view.BaseFragment;
import com.ylean.cf_hospitalapp.inquiry.adapter.AskPagerAdapter;
import com.ylean.cf_hospitalapp.my.bean.MyAskReusltList;
import com.ylean.cf_hospitalapp.my.adapter.AskAdapter;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentThree extends BaseFragment implements IFreagThreeView {

    private IFragPicPres iFragPicPres = new IFragPicPres(this);

    private List<MyAskReusltList.DataBean> picAskList = new ArrayList<>();
    private AskAdapter picAskAdapter;
//    private SwipeRefreshLayout swipeRefreshLayout;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;

    //    private AskAdapter askItemAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_three, null);

        initView(view);

        Logger.d("====onCreateView==== fragementThree");

        return view;

    }

    private void initView(View view) {

        TabLayout mTabLayout = view.findViewById(R.id.tab_layout);
        ViewPager mViewPager = view.findViewById(R.id.vp_viewpager);

        mTabLayout.addOnTabSelectedListener(listener);
        mTabLayout.setupWithViewPager(mViewPager);

        AskPagerAdapter askPagerAdapter = new AskPagerAdapter(getActivity(), this);
        mViewPager.setAdapter(askPagerAdapter);
    }

    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            //选择的tab
            Logger.d("onTabSelected,选择的tab:" + tab.getText().toString());

            int position = tab.getPosition();

            switch (position) {

                case 0://图文问诊

//                    currentType = SpValue.ASK_TYPE_PIC;

                    break;

                case 1://电话问诊

//                    currentType = SpValue.ASK_TYPE_TEL;

                    break;

                case 2://视频问诊

//                    currentType = SpValue.ASK_TYPE_VIDEO;

                    break;

            }


        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            //离开的那个tab
//            Logger.d("onTabUnselected,离开的那个tab::" + tab.getText().toString());
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            //再次选择tab
//            Logger.d("onTabReselected,再次选择tab::" + tab.getText().toString());
        }
    };


    public void loadPageOne(View view) {

//        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                Logger.d("refush===");
//                iFragPicPres.refush(SpValue.ASK_TYPE_PIC, (String) SaveUtils.get(getActivity(), SpValue.TOKEN, ""));
//            }
//        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        picAskList.clear();
        picAskAdapter = new AskAdapter(getActivity(), picAskList, SpValue.ASK_TYPE_PIC);
        recyclerView.setAdapter(picAskAdapter);

        //获取图文问诊信息
        iFragPicPres.getPicAskInfo(SpValue.ASK_TYPE_PIC, (String) SaveUtils.get(getActivity(), SpValue.TOKEN, ""));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (picAskAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == picAskAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
                    iFragPicPres.nextPage(SpValue.ASK_TYPE_PIC, (String) SaveUtils.get(getActivity(), SpValue.TOKEN, ""));
                }

            }

        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m = new Intent(getActivity(), InquiryDetailAct.class);
                m.putExtra("consultaid", picAskList.get(position).getConsultaid());
                m.putExtra("hospitalName", picAskList.get(position).getHospitalname());
                m.putExtra("askType", 1);//1图文问诊，2电话，3视频
                startActivity(m);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    @Override
    public void setPicAskInfo(List<MyAskReusltList.DataBean> data) {

//        swipeRefreshLayout.setRefreshing(false);

        picAskList.addAll(data);

        if (picAskAdapter != null)
            picAskAdapter.notifyDataSetChanged();

    }

    @Override
    public void clearPicInfo() {
        picAskList.clear();
    }
}
