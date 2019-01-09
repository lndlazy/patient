package com.ylean.cf_hospitalapp.register.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.my.adapter.RegisterLeftAdapter;
import com.ylean.cf_hospitalapp.my.adapter.RegisterRightAdapter;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.register.bean.RegisterDeartmentListEntry;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择挂号科室
 * Created by linaidao on 2019/1/3.
 */

public class DepartmentChooseActivity extends BaseActivity {

    private com.facebook.drawee.view.SimpleDraweeView sdvPic;
    private android.widget.TextView tvHospitalName;
    private android.widget.TextView tvAddress;
    private android.widget.TextView tel;
    private android.support.v7.widget.RecyclerView leftRecyclerView;
    private android.support.v7.widget.RecyclerView rightRecyclerView;
    private HospitalListEntry.DataBean hospitalInfo;
    private List<RegisterDeartmentListEntry.DataBean> leftData = new ArrayList<>();
    private RegisterLeftAdapter registerLeftAdapter;
    private RegisterRightAdapter registerRightAdapter;
    private List<RegisterDeartmentListEntry.DataBean.MenzhenlistBean> menzhenlist = new ArrayList<>();
    private String departid;
    private String departname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_department_choose);
        hospitalInfo = getIntent().getParcelableExtra("hospitalInfo");
        initView();
        getRegisterDeartment();
    }

    private void getRegisterDeartment() {

        if (hospitalInfo == null) {
            showErr("数据错误");
            return;
        }

        RetrofitHttpUtil
                .getInstance()
                .menzenList(
                        new BaseNoTObserver<RegisterDeartmentListEntry>() {
                            @Override
                            public void onHandleSuccess(RegisterDeartmentListEntry registerDeartmentListEntry) {

                                if (registerDeartmentListEntry != null) {
                                    registerDepartmentInfo(registerDeartmentListEntry);
                                }

                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH
                        , hospitalInfo.getHospitalid());

    }

    private void registerDepartmentInfo(RegisterDeartmentListEntry registerDeartmentListEntry) {

        leftData.addAll(registerDeartmentListEntry.getData());
        if (registerLeftAdapter != null) {

            if (registerDeartmentListEntry.getData() != null && registerDeartmentListEntry.getData().size() > 0) {

                registerDeartmentListEntry.getData().get(0).setSelect(true);

                departid = registerDeartmentListEntry.getData().get(0).getDepartid();
                departname = registerDeartmentListEntry.getData().get(0).getDepartname();

            }

            registerLeftAdapter.notifyDataSetChanged();

            if (registerDeartmentListEntry.getData() != null
                    && registerDeartmentListEntry.getData().size() > 0) {
                RegisterDeartmentListEntry.DataBean dataBean = registerDeartmentListEntry.getData().get(0);

                menzhenlist.clear();
                menzhenlist.addAll(dataBean.getMenzhenlist());

                if (registerRightAdapter != null)
                    registerRightAdapter.notifyDataSetChanged();

            }

        }
    }

    private void initView() {

        this.rightRecyclerView = (RecyclerView) findViewById(R.id.rightRecyclerView);
        this.leftRecyclerView = (RecyclerView) findViewById(R.id.leftRecyclerView);
        this.tel = (TextView) findViewById(R.id.tel);
        this.tvAddress = (TextView) findViewById(R.id.tvAddress);
        this.tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
        this.sdvPic = (SimpleDraweeView) findViewById(R.id.sdvPic);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setHospitalInfo();
        departementInfo();
    }

    private void setHospitalInfo() {

        if (hospitalInfo == null)
            return;

        sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + hospitalInfo.getImgurl()));
        tvHospitalName.setText(hospitalInfo.getHospitalname());
        tvAddress.setText(hospitalInfo.getAddress());
        tel.setText(hospitalInfo.getSupportel());

    }

    private void departementInfo() {

        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        leftRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        leftRecyclerView.addItemDecoration(divider);
        registerLeftAdapter = new RegisterLeftAdapter(this, leftData);
        leftRecyclerView.setAdapter(registerLeftAdapter);
        leftRecyclerView.addOnItemTouchListener(new OnItemClickListener(leftRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                for (int i = 0; i < leftData.size(); i++) {
                    leftData.get(i).setSelect(false);
                }

                if (registerLeftAdapter != null)
                    registerLeftAdapter.notifyDataSetChanged();

                menzhenlist.clear();
                menzhenlist.addAll(leftData.get(position).getMenzhenlist());
                if (registerRightAdapter != null)
                    registerRightAdapter.notifyDataSetChanged();

                departid = leftData.get(position).getDepartid();
                departname = leftData.get(position).getDepartname();
                leftData.get(position).setSelect(true);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        //加载右边选择框
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration rightdivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rightdivider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        rightRecyclerView.addItemDecoration(rightdivider);
        registerRightAdapter = new RegisterRightAdapter(this, menzhenlist);
        rightRecyclerView.setAdapter(registerRightAdapter);
        rightRecyclerView.addOnItemTouchListener(new OnItemClickListener(rightRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m = new Intent(DepartmentChooseActivity.this, ChooseNumActivity.class);
                m.putExtra("hospitalInfo", hospitalInfo);
                m.putExtra("departid", departid);//部门id
                m.putExtra("registerId", menzhenlist.get(position).getMenzhenid());//门诊id
                m.putExtra("registerName", menzhenlist.get(position).getMenzhenname());//门诊名称
                m.putExtra("departname", departname);//部门名称
                startActivity(m);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }


}
