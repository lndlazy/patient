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
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.my.adapter.MyEvalAdapter;
import com.ylean.cf_hospitalapp.my.bean.EvalListEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的评价
 * Created by linaidao on 2019/1/8.
 */

public class MyEvaluationActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.support.v7.widget.RecyclerView recyclerView;
    private int page;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<EvalListEntry.DataBean> commandList = new ArrayList<>();
    private MyEvalAdapter myEvalAdapter;

    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_eval);

        initView();

        commandList();

    }

    //我的评价列表
    private void commandList() {

        RetrofitHttpUtil.getInstance()
                .commandList(
                        new BaseNoTObserver<EvalListEntry>() {
                            @Override
                            public void onHandleSuccess(EvalListEntry evalListEntry) {
                                swipeRefreshLayout.setRefreshing(false);

                                if (evalListEntry == null || evalListEntry.getData() == null)
                                    return;

                                if (page == 1)
                                    commandList.clear();

                                commandList.addAll(evalListEntry.getData());

                                if (myEvalAdapter != null)
                                    myEvalAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onHandleError(String message) {

                                swipeRefreshLayout.setRefreshing(false);
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , page, SpValue.PAGE_SIZE);

    }

    private void initView() {

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        myEvalAdapter = new MyEvalAdapter(this, commandList);
        recyclerView.setAdapter(myEvalAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                switch (commandList.get(position).getCommenttype()) {

                    case SpValue.COMMIT_TYPE_GOODS://"【商品订单】"

                        break;

                    case SpValue.COMMIT_TYPE_SERVICE://"【服务订单】"

                        break;

                    case SpValue.COMMIT_TYPE_DOCTOR://"【医生】"

                        Intent m = new Intent(MyEvaluationActivity.this, InquiryEvaulateDoctorActivity.class);
                        m.putExtra("noedit", true);
                        m.putExtra("consultaid", commandList.get(position).getId());
                        startActivity(m);

                        break;
                }

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (myEvalAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == myEvalAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {

                    page++;
                    commandList();

                }

            }

        });

    }

    @Override
    public void onRefresh() {

        page = 1;
        commandList();
    }
}
