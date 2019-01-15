package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.Presenter.ICollectionPres;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.ICollectionView;
import com.ylean.cf_hospitalapp.my.adapter.MyFriendAdapter;
import com.ylean.cf_hospitalapp.my.bean.MCollectionListEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的病友
 * Created by linaidao on 2019/1/8.
 */

public class MyFriendActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, ICollectionView {

    //    private android.widget.TextView tvattention;
//    private android.widget.TextView tvfans;
    private android.support.v7.widget.RecyclerView recyclerView;
    private android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;

    private int page = 1;
    private String type = "6";
    private ICollectionPres iCollectionPres = new ICollectionPres(this);

    private List<MCollectionListEntry.DataBean> collectionList = new ArrayList<>();
    private MyFriendAdapter myFriendAdapter;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_friend);

        init();
        collectionList();

    }

    private void init() {

        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        this.tvfans = (TextView) findViewById(R.id.tvfans);
//        this.tvattention = (TextView) findViewById(R.id.tvattention);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

//        tvfans.setOnClickListener(this);
//        tvattention.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        myFriendAdapter = new MyFriendAdapter(this, collectionList);
        recyclerView.setAdapter(myFriendAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (myFriendAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == myFriendAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
                    page++;
                    collectionList();
                }

            }

        });

    }

    @Override
    public void onRefresh() {

        page = 1;
        collectionList();
    }

    @Override
    public void onClick(View v) {

//        switch (v.getId()) {
//
//            case R.id.tvfans://粉丝
//
//                type = "";
//                break;
//            case R.id.tvattention://关注
//
//                type = "6";
//
//                break;
//        }

    }

    /**
     * 我的收藏 1-直播 2-资讯 3-讲堂 4-帖子 5-医生 6-病友 7-文章
     */
    private void collectionList() {

        RetrofitHttpUtil.getInstance()
                .collectList(
                        new BaseNoTObserver<MCollectionListEntry>() {
                            @Override
                            public void onHandleSuccess(MCollectionListEntry listEntry) {

                                swipeRefreshLayout.setRefreshing(false);

                                if (listEntry == null || listEntry.getData() == null)
                                    return;

                                if (page == 1)
                                    collectionList.clear();

                                collectionList.addAll(listEntry.getData());

                                if (myFriendAdapter != null)
                                    myFriendAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onHandleError(String message) {
                                swipeRefreshLayout.setRefreshing(false);
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), type, page, SpValue.PAGE_SIZE);

    }


    //取消关注
    public void attentionAction(String userid) {

        iCollectionPres.setId(userid);
        iCollectionPres.removeCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "6");

    }

    @Override
    public void collectionSuccess(String type) {

    }

    @Override
    public void removeCollectionSuccess(String type) {

        onRefresh();

    }
}
