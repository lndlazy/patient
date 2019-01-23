package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.my.adapter.InviteAdapter;
import com.ylean.cf_hospitalapp.my.bean.InviteListEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 邀请列表
 * Created by linaidao on 2019/1/22.
 */

public class InviteListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private int page = 1;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.support.v7.widget.RecyclerView recyclerView;
    private android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;

    private List<InviteListEntry.DataBean> inviteList = new ArrayList<>();
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;
    private InviteAdapter inviteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_invite_list);

        init();
        page = 1;
        inviteList(true);

    }

    private void init() {
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        inviteAdapter = new InviteAdapter(this, inviteList);
        recyclerView.setAdapter(inviteAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (inviteAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == inviteAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {

                    page++;
                    inviteList(false);

                }

            }

        });
    }


    private void inviteList(boolean refush) {

        RetrofitHttpUtil.getInstance().inviteList(
                new BaseNoTObserver<InviteListEntry>() {
                    @Override
                    public void onHandleSuccess(InviteListEntry basebean) {
                        swipeRefreshLayout.setRefreshing(false);

                        if (basebean == null || basebean.getData() == null)
                            return;

                        if (refush)
                            inviteList.clear();

                        inviteList.addAll(basebean.getData());

                        if (inviteAdapter != null)
                            inviteAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onHandleError(String message) {
                        swipeRefreshLayout.setRefreshing(false);
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                , page, SpValue.PAGE_SIZE);

    }

    @Override
    public void onRefresh() {
        page = 1;
        inviteList(true);

    }
}
