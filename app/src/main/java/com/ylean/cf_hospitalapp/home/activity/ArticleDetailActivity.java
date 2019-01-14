//package com.ylean.cf_hospitalapp.home.activity;
//
//import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//
//import com.ylean.cf_hospitalapp.R;
//import com.ylean.cf_hospitalapp.base.view.BaseActivity;
//import com.ylean.cf_hospitalapp.doctor.adapter.CommentCommAdapter;
//import com.ylean.cf_hospitalapp.inquiry.adapter.MoreReadAdapter;
//import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
//
///**
// * 文章详情
// */
//public class ArticleDetailActivity extends BaseActivity {
//
//    private RecyclerView commitRecyclerView;
//    private RecyclerView readRecyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_article_detail);
//        initView();
//        initCommitRecyclerView();
//        initMoreRecyclerView();
//
//    }
//
//    private void initView() {
//
//        TitleBackBarView tbv = findViewById(R.id.tbv);
//        tbv.setOnLeftClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//    }
//
//    private void initMoreRecyclerView() {
//
//        readRecyclerView = findViewById(R.id.readRecyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//
//        readRecyclerView.setLayoutManager(linearLayoutManager);
//
//        linearLayoutManager.setSmoothScrollbarEnabled(true);
//        linearLayoutManager.setAutoMeasureEnabled(true);
//        readRecyclerView.setHasFixedSize(true);
//        readRecyclerView.setNestedScrollingEnabled(false);
//        readRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item));
//        readRecyclerView.addItemDecoration(divider);
//        MoreReadAdapter moreReadAdapter = new MoreReadAdapter(this);
//        readRecyclerView.setAdapter(moreReadAdapter);
//    }
//
//    private void initCommitRecyclerView() {
//
//        commitRecyclerView = findViewById(R.id.recyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        commitRecyclerView.setLayoutManager(linearLayoutManager);
//        linearLayoutManager.setSmoothScrollbarEnabled(true);
//        linearLayoutManager.setAutoMeasureEnabled(true);
//
//        commitRecyclerView.setHasFixedSize(true);
//        commitRecyclerView.setNestedScrollingEnabled(false);
//
//        commitRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item));
//        commitRecyclerView.addItemDecoration(divider);
//        CommentCommAdapter commitAdapter = new CommentCommAdapter(this);
//        commitRecyclerView.setAdapter(commitAdapter);
//
//    }
//}
