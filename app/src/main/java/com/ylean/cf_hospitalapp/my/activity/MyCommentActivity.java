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
import com.ylean.cf_hospitalapp.home.activity.ArtDetailAct;
import com.ylean.cf_hospitalapp.home.activity.VideoSpeechActivity;
import com.ylean.cf_hospitalapp.my.adapter.MyCommentAdapter;
import com.ylean.cf_hospitalapp.my.bean.CommentListEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的评论列表
 * Created by linaidao on 2019/1/7.
 */

public class MyCommentActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private android.support.v7.widget.RecyclerView recyclerView;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;
    private MyCommentAdapter commentAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private List<CommentListEntry.DataBean> commentList = new ArrayList<>();
    private Intent m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_comment);
        initView();
        commentList(true);
    }

    public void commentList(final boolean isRefush) {

        RetrofitHttpUtil.getInstance()
                .commentList(new BaseNoTObserver<CommentListEntry>() {
                    @Override
                    public void onHandleSuccess(CommentListEntry commentListEntry) {

                        swipeRefreshLayout.setRefreshing(false);

                        if (commentListEntry != null && commentListEntry.getData() != null) {

                            if (isRefush)
                                commentList.clear();
                            commentList.addAll(commentListEntry.getData());

                            if (commentAdapter != null)
                                commentAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onHandleError(String message) {
                        swipeRefreshLayout.setRefreshing(false);
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), page, SpValue.PAGE_SIZE);

    }

    private void initView() {

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
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

        commentAdapter = new MyCommentAdapter(this, commentList);
        recyclerView.setAdapter(commentAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (commentAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == commentAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
                    //下一页
                    page++;
                    commentList(false);

                }

            }

        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                switch (commentList.get(position).getCommenttype()) {

                    //1-商品订单 2-服务订单 3-医生 4-医院 5-文章 6-视频 7-帖子
                    case "1":

                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5"://文章
                        m = new Intent(MyCommentActivity.this, ArtDetailAct.class);
                        m.putExtra("id", commentList.get(position).getRelateid());
                        startActivity(m);
                        break;
                    case "6"://视频
                        m = new Intent(MyCommentActivity.this, VideoSpeechActivity.class);
                        m.putExtra("id", commentList.get(position).getRelateid());
                        startActivity(m);
                        break;
                    case "7":
                        break;
                    case "8":
                        break;


                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    @Override
    public void onRefresh() {

        page = 1;
        commentList(true);
    }
}
