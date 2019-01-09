package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 我的申请
 * Created by linaidao on 2019/1/8.
 */

public class MyRequestActivity extends BaseActivity implements View.OnClickListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tvAll;
    private android.widget.TextView tvWaitUse;
    private android.widget.TextView tvTimeOut;
    private android.widget.TextView tvUsed;
    private android.support.v7.widget.RecyclerView recyclerView;
    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;

    //状态 0-未审核  1-未使用 2-已使用  3-审核不通过  4-已过期 查全部不传
    private static final String STATUS_ALL = "";
    private static final String STATUS_NO_CHECK = "0";
    private static final String STATUS_NO_USE = "1";
    private static final String STATUS_USED = "2";
    private static final String STATUS_CHECK_REFUSED = "3";
    private static final String STATUS_TIME_OUT = "4";

    private String currentType = "";
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_my_request);

        currentPage = 1;
        currentType = STATUS_ALL;
        initView();
        requestList();
    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tvUsed = (TextView) findViewById(R.id.tvUsed);
        this.tvTimeOut = (TextView) findViewById(R.id.tvTimeOut);
        this.tvWaitUse = (TextView) findViewById(R.id.tvWaitUse);
        this.tvAll = (TextView) findViewById(R.id.tvAll);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvAll.setSelected(true);

        tvAll.setOnClickListener(this);
        tvUsed.setOnClickListener(this);
        tvTimeOut.setOnClickListener(this);
        tvWaitUse.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
        recyclerView.addItemDecoration(divider);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

//                currentPage++;


//                if (picAskAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
//                        && mPicLastVisibleItemPosition + 1 == picAskAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {
//                    iFragPicPres.nextPage(SpValue.ASK_TYPE_PIC, (String) SaveUtils.get(getActivity(), SpValue.TOKEN, ""));
//                }

            }

        });
    }

    @Override
    public void onClick(View v) {

        setFalse();
        switch (v.getId()) {
            case R.id.tvAll://全部
                tvAll.setSelected(true);
                currentType = STATUS_ALL;
                requestList();
                break;

            case R.id.tvUsed://已使用
                tvUsed.setSelected(true);
                currentType = STATUS_USED;
                requestList();
                break;

            case R.id.tvTimeOut://已过期
                tvTimeOut.setSelected(true);
                currentType = STATUS_TIME_OUT;
                requestList();
                break;

            case R.id.tvWaitUse://等待使用
                tvWaitUse.setSelected(true);
                currentType = STATUS_NO_USE;
                requestList();
                break;

        }

    }

    private void setFalse() {
        tvAll.setSelected(false);
        tvUsed.setSelected(false);
        tvTimeOut.setSelected(false);
        tvWaitUse.setSelected(false);
    }

    //我的申请
    private void requestList() {

        RetrofitHttpUtil.getInstance()
                .requestList(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }


                        }
                        , SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), currentType, currentPage, SpValue.PAGE_SIZE);


    }

}
