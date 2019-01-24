package com.ylean.cf_hospitalapp.inquiry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.DisEntry;
import com.ylean.cf_hospitalapp.inquiry.adapter.DisLeftAdapter;
import com.ylean.cf_hospitalapp.inquiry.adapter.DisRightAdapter;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 更多疾病
 * Created by linaidao on 2018/12/18.
 */

public class MoreDisAct extends BaseActivity {

    private static final int MORE_DIS_RESULT_CODE = 0x103;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.support.v7.widget.RecyclerView leftRecyclerView;
    private android.support.v7.widget.RecyclerView rightRecyclerView;
    private List<DisEntry.DataBean> departmentList = new ArrayList<>();
    private List<DisEntry.DataBean.DiseaselistBean> diseaselist = new ArrayList<>();
    private DisRightAdapter disRightAdapter;
    private DisLeftAdapter disLeftAdapter;
    private String departid;
    private String diseaseId;
    private String diseasename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_more_dis);
        initWidget();
        addDisInfo();

    }

    private void addDisInfo() {

        RetrofitHttpUtil.getInstance().allDis(new BaseNoTObserver<DisEntry>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);

                showLoading("获取中...");
            }

            @Override
            public void onHandleSuccess(DisEntry disEntry) {
                hideLoading();

                departmentList.addAll(disEntry.getData());
                if (disRightAdapter != null)
                    disRightAdapter.notifyDataSetChanged();
            }

            @Override
            public void onHandleError(String message) {

                hideLoading();
                showErr(message);
            }

        }, SpValue.CH);

    }

    private void initWidget() {
        this.rightRecyclerView = findViewById(R.id.rightRecyclerView);
        this.leftRecyclerView = findViewById(R.id.leftRecyclerView);
        this.tbv = findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tbv.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sure();
            }
        });

        leftInfo();
        rightInfo();
    }

    private void rightInfo() {
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        rightRecyclerView.addItemDecoration(divider);

        disRightAdapter = new DisRightAdapter(this, diseaselist);
        rightRecyclerView.setAdapter(disRightAdapter);

        rightRecyclerView.addOnItemTouchListener(new OnItemClickListener(rightRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                boolean select = diseaselist.get(position).isSelect();

                for (int i = 0; i < diseaselist.size(); i++) {
                    diseaselist.get(i).setSelect(false);
                }

                diseaseId = select ? "" : diseaselist.get(position).getDiseaseid();
                diseasename = diseaselist.get(position).getDiseasename();
                diseaselist.get(position).setSelect(!select);

                if (disRightAdapter != null)
                    disRightAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void leftInfo() {
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        leftRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        leftRecyclerView.addItemDecoration(divider);

        disLeftAdapter = new DisLeftAdapter(this, departmentList);
        leftRecyclerView.setAdapter(disLeftAdapter);
        leftRecyclerView.addOnItemTouchListener(new OnItemClickListener(leftRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                boolean select = departmentList.get(position).isSelect();

                for (int i = 0; i < departmentList.size(); i++) {
                    departmentList.get(i).setSelect(false);
                }

                for (int i = 0; i < diseaselist.size(); i++) {
                    diseaselist.get(i).setSelect(false);
                }

                diseaselist.clear();

                if (select) {
                    //取消选择
                    departid = "";
                    diseaseId = "";
                } else {
                    //确认选择
                    departid = departmentList.get(position).getDepartid();
                    diseaseId = "";
                    diseaselist.addAll(departmentList.get(position).getDiseaselist());
                    departmentList.get(position).setSelect(true);
                }

                if (disLeftAdapter != null)
                    disLeftAdapter.notifyDataSetChanged();

                if (disRightAdapter != null)
                    disRightAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    //确认选择
    private void sure() {

        if (TextUtils.isEmpty(diseaseId) ||
                TextUtils.isEmpty(diseasename)) {
            showErr("请选择具体疾病");
            return;
        }

        Intent m = new Intent();
        m.putExtra("diseaseId", diseaseId);
        m.putExtra("diseasename", diseasename);
        m.putExtra("departid", departid);
        setResult(MORE_DIS_RESULT_CODE, m);
        finish();

    }
}
