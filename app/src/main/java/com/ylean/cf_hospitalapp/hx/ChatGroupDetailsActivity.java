package com.ylean.cf_hospitalapp.hx;

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
import com.ylean.cf_hospitalapp.hx.bean.GroupListEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 群组详情
 * Created by linaidao on 2019/1/15.
 */

public class ChatGroupDetailsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private String roomid;
    private List<GroupListEntry.DataBean> groupList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private GroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_chat_detail);

        init();
        roomid = getIntent().getStringExtra("roomid");
        groupList();
    }

    private void init() {

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

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
        groupAdapter = new GroupAdapter(this, groupList);
        recyclerView.setAdapter(groupAdapter);
    }


    private void groupList() {

        RetrofitHttpUtil.getInstance().groupList(
                new BaseNoTObserver<GroupListEntry>() {
                    @Override
                    public void onHandleSuccess(GroupListEntry basebean) {

                        if (basebean == null || basebean.getData() == null)
                            return;

                        groupList.clear();
                        groupList.addAll(basebean.getData());
                        if (groupAdapter != null)
                            groupAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                        swipeRefreshLayout.setRefreshing(false);

                    }

                }, SpValue.CH, roomid);

    }

    @Override
    public void onRefresh() {
        groupList();
    }
}
