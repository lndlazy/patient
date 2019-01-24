package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.HomeActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryDetailAct;
import com.ylean.cf_hospitalapp.my.bean.MyAskReusltList;
import com.ylean.cf_hospitalapp.my.adapter.AskAdapter;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 我的义诊
 * Created by linaidao on 2018/12/21.
 */
public class MyFreeAskAct extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.support.v7.widget.RecyclerView readRecyclerView;
    int pageNum = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<MyAskReusltList.DataBean> myAskResultList = new ArrayList<>();
    private AskAdapter askAdapter;

    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_my_free_ask);

        initView();
        askList(true);

    }

    private void initView() {
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        this.readRecyclerView = (RecyclerView) findViewById(R.id.readRecyclerView);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(HomeActivity.class);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

        readRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        readRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        readRecyclerView.addItemDecoration(divider);

        askAdapter = new AskAdapter(this, myAskResultList, SpValue.ASK_TYPE_FREE);
        readRecyclerView.setAdapter(askAdapter);

        readRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (askAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == askAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
                    pageNum++;
                    askList(false);

                }

            }

        });

        readRecyclerView.addOnItemTouchListener(new OnItemClickListener(readRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m = new Intent(MyFreeAskAct.this, InquiryDetailAct.class);
                m.putExtra("noedit", true);
                m.putExtra("consultaid", myAskResultList.get(position).getConsultaid());
                m.putExtra("hospitalName", myAskResultList.get(position).getHospitalname());
                m.putExtra("askType", 1);
                startActivity(m);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void askList(final boolean b) {
        RetrofitHttpUtil
                .getInstance()
                .askList(
                        new BaseNoTObserver<MyAskReusltList>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);

                                showLoading("获取中...");
                            }

                            @Override
                            public void onHandleSuccess(MyAskReusltList reusltList) {

                                hideLoading();
                                swipeRefreshLayout.setRefreshing(false);

                                if (reusltList == null)
                                    return;

                                if (b)
                                    myAskResultList.clear();
                                myAskResultList.addAll(reusltList.getData());

                                if (askAdapter != null)
                                    askAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onHandleError(String message) {
                                swipeRefreshLayout.setRefreshing(false);
                                hideLoading();
                                showErr(message);
                            }


                        }
                        , (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , SpValue.CH
                        , SpValue.ASK_TYPE_FREE
                        , pageNum
                        , SpValue.PAGE_SIZE);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        nextActivity(HomeActivity.class);
    }

    @Override
    public void onRefresh() {

        pageNum = 1;
        askList(true);
    }
}
