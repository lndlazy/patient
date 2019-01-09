package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.home.activity.VideoSpeechActivity;
import com.ylean.cf_hospitalapp.my.adapter.MCollectionAdapter;
import com.ylean.cf_hospitalapp.my.bean.MCollectionListEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的收藏
 * Created by linaidao on 2019/1/8.
 */

public class MyCollectionListActivity extends BaseActivity implements View.OnClickListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tv1;
    private android.widget.TextView tv2;
    private android.widget.TextView tv3;
    private android.widget.TextView tv4;
    private android.widget.TextView tv5;
    private android.support.v7.widget.RecyclerView recyclerView;
    private String type;
    private int page;
    private List<MCollectionListEntry.DataBean> collectionList = new ArrayList<>();
    private MCollectionAdapter collectionAdapter;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_collection);
        initView();

        tv1.setSelected(true);
        type = "7";
        collectionList(type);
    }

    private void initView() {

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tv5 = (TextView) findViewById(R.id.tv5);
        this.tv4 = (TextView) findViewById(R.id.tv4);
        this.tv3 = (TextView) findViewById(R.id.tv3);
        this.tv2 = (TextView) findViewById(R.id.tv2);
        this.tv1 = (TextView) findViewById(R.id.tv1);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        collectionAdapter = new MCollectionAdapter(this, collectionList);
        recyclerView.setAdapter(collectionAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (collectionAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == collectionAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
                    //加载更多
                    page++;
                    collectionList(type);
                }

            }

        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m;

                switch (collectionList.get(position).getType()) {
                    //1-直播 2-资讯 3-讲堂 4-帖子 5-医生 6-病友 7-文章
                    case "1":
                        m = new Intent(MyCollectionListActivity.this, VideoSpeechActivity.class);
                        m.putExtra("id", collectionList.get(position).getLiveid());
                        m.putExtra("type", "1");
                        startActivity(m);
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "7":
                        break;

                }

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv1:
                type = "7";
                setAllGray();
                tv1.setSelected(true);
                break;

            case R.id.tv2:
                type = "1";
                setAllGray();
                tv2.setSelected(true);

                break;

            case R.id.tv3:
                type = "2";
                setAllGray();
                tv3.setSelected(true);

                break;

            case R.id.tv4:
                type = "3";
                setAllGray();
                tv4.setSelected(true);
                break;

            case R.id.tv5:
                type = "4";
                setAllGray();
                tv5.setSelected(true);

                break;

        }

        collectionList(type);
    }


    private void setAllGray() {

        tv1.setSelected(false);
        tv2.setSelected(false);
        tv3.setSelected(false);
        tv4.setSelected(false);
        tv5.setSelected(false);
        page = 1;

    }

    /**
     * 我的收藏 1-直播 2-资讯 3-讲堂 4-帖子 5-医生 6-病友 7-文章
     *
     * @param type
     */
    private void collectionList(String type) {

        RetrofitHttpUtil.getInstance()
                .collectList(
                        new BaseNoTObserver<MCollectionListEntry>() {
                            @Override
                            public void onHandleSuccess(MCollectionListEntry listEntry) {

                                if (listEntry == null || listEntry.getData() == null)
                                    return;

                                if (page == 1)
                                    collectionList.clear();

                                collectionList.addAll(listEntry.getData());

                                if (collectionList.size() > 0)
                                    for (int i = 0; i < collectionList.size(); i++) {
                                        collectionList.get(i).setType(type);
                                    }

                                if (collectionAdapter != null)
                                    collectionAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), this.type, page, SpValue.PAGE_SIZE);

    }

}
