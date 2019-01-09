package com.ylean.cf_hospitalapp.register.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.my.activity.WebviewActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.register.adapter.DoctorTypeAdapter;
import com.ylean.cf_hospitalapp.register.adapter.NumRegisterAdapter;
import com.ylean.cf_hospitalapp.register.bean.DoctorTypeEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.register.bean.NumListEntry;
import com.ylean.cf_hospitalapp.register.bean.TimeEntry;
import com.ylean.cf_hospitalapp.register.pres.IChooseNumPres;
import com.ylean.cf_hospitalapp.register.view.IChooseNumView;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 选择号源
 * Created by linaidao on 2019/1/4.
 */

public class ChooseNumActivity extends BaseActivity implements IChooseNumView, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private static final int DATE_REQUEST_CODE = 0x1023;
    private static final int DATE_RESULT_CODE = 0x1024;
    private HospitalListEntry.DataBean hospitalInfo;
    private String registerId;//门诊id
    private String registerName;//门诊名称
    private String departid;//部门id
    private String departname;//部门名称

    private IChooseNumPres iChooseNumPres = new IChooseNumPres(this);
    private List<DoctorTypeEntry.DataBean> doctorTypeList;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tvDepartment;
    private android.widget.TextView tvName;
    private android.widget.RelativeLayout rlIntrod;
    private android.widget.TextView tvDate1;
    private android.widget.TextView tvDate2;
    private android.widget.TextView tvDate3;
    private android.widget.TextView tvDate4;
    private android.widget.TextView tvDate5;
    private android.widget.TextView tvDate6;
    private android.widget.TextView tvDate7;
    private android.support.design.widget.TabLayout mTabLayout;
    private android.support.v4.view.ViewPager mViewPager;
    private android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;

    private int currentDoctorPosition = -1;//当前医生类型的 角标

    private Map<Integer, NumRegisterAdapter> amAdapterMap = new LinkedHashMap<>();
    private Map<Integer, NumRegisterAdapter> pmAdapterMap = new LinkedHashMap<>();
    private Map<Integer, List<NumListEntry.DataBean>> amDataMap = new LinkedHashMap<>();
    private Map<Integer, List<NumListEntry.DataBean>> pmDataMap = new LinkedHashMap<>();

    private List<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        departid = getIntent().getStringExtra("departid");
        registerId = getIntent().getStringExtra("registerId");
        registerName = getIntent().getStringExtra("registerName");
        departname = getIntent().getStringExtra("departname");
        hospitalInfo = getIntent().getParcelableExtra("hospitalInfo");

        setContentView(R.layout.act_choose_num);

        initView();

        //添加日期
        iChooseNumPres.initRcentDate();
        //获取医生类型列表
        iChooseNumPres.getHospitalType();

        //设置获取号源详情的请求参数
        iChooseNumPres.setDepartId(departid);
        if (hospitalInfo != null)
            iChooseNumPres.setHospitalId(hospitalInfo.getHospitalid());
        iChooseNumPres.setMenzhenid(registerId);

    }

    private void initView() {

        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        this.mViewPager = (ViewPager) findViewById(R.id.vp_viewpager);
        this.mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        this.tvDate7 = (TextView) findViewById(R.id.tvDate7);
        this.tvDate6 = (TextView) findViewById(R.id.tvDate6);
        this.tvDate5 = (TextView) findViewById(R.id.tvDate5);
        this.tvDate4 = (TextView) findViewById(R.id.tvDate4);
        this.tvDate3 = (TextView) findViewById(R.id.tvDate3);
        this.tvDate2 = (TextView) findViewById(R.id.tvDate2);
        this.tvDate1 = (TextView) findViewById(R.id.tvDate1);
        this.rlIntrod = (RelativeLayout) findViewById(R.id.rlIntrod);
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.tvDepartment = (TextView) findViewById(R.id.tvDepartment);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvDepartment.setText(registerName);//门诊名称
        tvName.setText(hospitalInfo.getHospitalname() + "   " + departname); //科室名称

        swipeRefreshLayout.setOnRefreshListener(this);
        rlIntrod.setOnClickListener(this);
        tvDate1.setOnClickListener(this);
        tvDate2.setOnClickListener(this);
        tvDate3.setOnClickListener(this);
        tvDate4.setOnClickListener(this);
        tvDate5.setOnClickListener(this);
        tvDate6.setOnClickListener(this);
        tvDate7.setOnClickListener(this);

        mTabLayout.addOnTabSelectedListener(listener);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void doctorTypeInfo(List<DoctorTypeEntry.DataBean> data) {
        doctorTypeList = data;

        currentDoctorPosition = 0;

        for (int i = 0; i < doctorTypeList.size(); i++) {
            viewList.add(View.inflate(this, R.layout.item_doctor_type, null));
        }

        DoctorTypeAdapter doctorTypeAdapter = new DoctorTypeAdapter(this, doctorTypeList, viewList);
        mViewPager.setAdapter(doctorTypeAdapter);
    }

    //tab切换监听
    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            //选择的tab
            Logger.d("onTabSelected,选择的tab:" + tab.getText().toString());
            currentDoctorPosition = tab.getPosition();
            iChooseNumPres.setDoctypeid(doctorTypeList.get(tab.getPosition()).getDoctitleid());

            initPageView(viewList.get(currentDoctorPosition), currentDoctorPosition);

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            //离开的那个tab
//            Logger.d("onTabUnselected,离开的那个tab::" + tab.getText().toString());
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            //再次选择tab
//            Logger.d("onTabReselected,再次选择tab::" + tab.getText().toString());
        }
    };

    @Override
    public void fillDate() {

        List<TimeEntry> timeEntryList = iChooseNumPres.getTimeEntryList();

        if (timeEntryList == null)
            return;

        try {

            tvDate1.setText(timeEntryList.get(0).getWeekDesc() + "\n" + timeEntryList.get(0).getDateDes());
            tvDate2.setText(timeEntryList.get(1).getWeekDesc() + "\n" + timeEntryList.get(1).getDateDes());
            tvDate3.setText(timeEntryList.get(2).getWeekDesc() + "\n" + timeEntryList.get(2).getDateDes());
            tvDate4.setText(timeEntryList.get(3).getWeekDesc() + "\n" + timeEntryList.get(3).getDateDes());
            tvDate5.setText(timeEntryList.get(4).getWeekDesc() + "\n" + timeEntryList.get(4).getDateDes());
            tvDate6.setText(timeEntryList.get(5).getWeekDesc() + "\n" + timeEntryList.get(5).getDateDes());
            tvDate7.setText("全部\n日期");

            tvDate1.setSelected(true);
            iChooseNumPres.setTimeEntry(timeEntryList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stopRefush() {
        swipeRefreshLayout.setRefreshing(false);
    }

    //刷新页面
    @Override
    public void onRefresh() {

        getDoctorList();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rlIntrod://科室介绍

                Intent m = new Intent(this, WebviewActivity.class);
                String url = ApiService.WEB_ROOT + ApiService.DEPARTMEN_INTRODUCE + "?id=" + departid;
                Logger.d("url:::" + url);
                m.putExtra("url", url);
                m.putExtra("title", departname);
                startActivity(m);

                break;

            case R.id.tvDate1://

                if (tvDate1.isSelected())
                    return;

                chooseDate(0);

                break;
            case R.id.tvDate2:

                if (tvDate2.isSelected())
                    return;

                chooseDate(1);

                break;
            case R.id.tvDate3:

                if (tvDate3.isSelected())
                    return;

                chooseDate(2);

                break;
            case R.id.tvDate4:

                if (tvDate4.isSelected())
                    return;

                chooseDate(3);

                break;
            case R.id.tvDate5:

                if (tvDate5.isSelected())
                    return;

                chooseDate(4);

                break;
            case R.id.tvDate6:

                if (tvDate6.isSelected())
                    return;

                chooseDate(5);

                break;
            case R.id.tvDate7:
                chooseDate(6);

                // 特殊处理 选择更多日期
                nextActivityWithCode(ChooseDateActivity.class, DATE_REQUEST_CODE);

                break;

        }
    }

//    private List<Integer> loadedLists = new ArrayList<>();

    //初始化viewpage
    public void initPageView(View view, int position) {

        Logger.d("初始化控件===initPageView");

//        try {
//            iChooseNumPres.setDoctypeid(doctorTypeList.get(position).getDoctitleid());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        if (loadedLists.contains(position))
//            return;

//        loadedLists.add(position);

        RecyclerView amRecyclerView = view.findViewById(R.id.rvAM);
        RecyclerView pmRecyclerView = view.findViewById(R.id.rvPM);

        LinearLayoutManager amLayoutManager = new LinearLayoutManager(this);
        amRecyclerView.setLayoutManager(amLayoutManager);
        amLayoutManager.setSmoothScrollbarEnabled(true);
        amLayoutManager.setAutoMeasureEnabled(true);
        amRecyclerView.setHasFixedSize(true);
        amRecyclerView.setNestedScrollingEnabled(false);
        amRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        amRecyclerView.addItemDecoration(divider);

        final List<NumListEntry.DataBean> amRegisterList = new ArrayList<>();
        NumRegisterAdapter amAdapter = new NumRegisterAdapter(this, amRegisterList);
        amRecyclerView.setAdapter(amAdapter);

        amAdapterMap.put(position, amAdapter);
        amDataMap.put(position, amRegisterList);

        LinearLayoutManager pmLayoutManager = new LinearLayoutManager(this);
        pmRecyclerView.setLayoutManager(pmLayoutManager);
        pmLayoutManager.setSmoothScrollbarEnabled(true);
        pmLayoutManager.setAutoMeasureEnabled(true);
        pmRecyclerView.setHasFixedSize(true);
        pmRecyclerView.setNestedScrollingEnabled(false);
        pmRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration ddivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        ddivider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        pmRecyclerView.addItemDecoration(ddivider);

        final List<NumListEntry.DataBean> pmRegisterList = new ArrayList<>();
        NumRegisterAdapter pmAdapter = new NumRegisterAdapter(this, pmRegisterList);
        pmRecyclerView.setAdapter(pmAdapter);

        pmAdapterMap.put(position, pmAdapter);
        pmDataMap.put(position, pmRegisterList);

        //1-上午 2-下午
        iChooseNumPres.getNumInfo("1", amAdapter, amRegisterList);
        iChooseNumPres.getNumInfo("2", pmAdapter, pmRegisterList);

        pmRecyclerView.addOnItemTouchListener(new OnItemClickListener(pmRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int p) {

                registerOrder(p, pmRegisterList, 2);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        amRecyclerView.addOnItemTouchListener(new OnItemClickListener(amRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int i) {

//                Logger.d("");
                registerOrder(i, amRegisterList, 1);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void registerOrder(int position, List<NumListEntry.DataBean> registerList, int i) {

        Logger.d("position：" + position + "，个数：" + registerList.size() + ",时间段：" + i);

        try {

            Logger.d("医生名称" + registerList.get(position).getDocname());
            Intent m = new Intent(ChooseNumActivity.this, RegisterConfirmActivity.class);
//                NumListEntry.DataBean dataBean = amRegisterList.get(position);
            m.putExtra("doctorRegisterInfo", registerList.get(position));
            m.putExtra("date", iChooseNumPres.getTimeEntry());
            m.putExtra("time", i == 1 ? "上午" : "下午");
            m.putExtra("hospitalInfo", hospitalInfo);
            m.putExtra("departname", departname);
            m.putExtra("registerId", registerId);//门诊id
            m.putExtra("departid", departid);//部门id
            startActivity(m);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void chooseDate(int i) {

        tvDate1.setSelected(false);
        tvDate2.setSelected(false);
        tvDate3.setSelected(false);
        tvDate4.setSelected(false);
        tvDate5.setSelected(false);
        tvDate6.setSelected(false);
        tvDate7.setSelected(false);

        switch (i) {

            case 0:
                tvDate1.setSelected(true);
                break;
            case 1:
                tvDate2.setSelected(true);
                break;
            case 2:
                tvDate3.setSelected(true);
                break;
            case 3:
                tvDate4.setSelected(true);
                break;
            case 4:
                tvDate5.setSelected(true);
                break;
            case 5:
                tvDate6.setSelected(true);
                break;
            case 6:
                tvDate7.setSelected(true);
                break;
        }

        try {
            if (i == 6)
                return;

            tvDate7.setText("全部\n日期");
            iChooseNumPres.setTimeEntry(iChooseNumPres.getTimeEntryList().get(i));

        } catch (Exception e) {
            e.printStackTrace();
        }

        getDoctorList();

    }

    private void getDoctorList() {
        if (currentDoctorPosition != -1) {

            NumRegisterAdapter amAdapter = amAdapterMap.get(currentDoctorPosition);
            List<NumListEntry.DataBean> amDataBeans = amDataMap.get(currentDoctorPosition);

            NumRegisterAdapter pmAdapter = pmAdapterMap.get(currentDoctorPosition);
            List<NumListEntry.DataBean> pmDataBeans = pmDataMap.get(currentDoctorPosition);

            iChooseNumPres.getNumInfo("1", amAdapter, amDataBeans);
            iChooseNumPres.getNumInfo("2", pmAdapter, pmDataBeans);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DATE_REQUEST_CODE && resultCode == DATE_RESULT_CODE) {

            if (data != null) {

                TimeEntry timeEntry = data.getParcelableExtra("timeEntry");
                iChooseNumPres.setTimeEntry(timeEntry);
                getDoctorList();
                tvDate7.setText(timeEntry.getWeekDesc() + "\n" + timeEntry.getDateDes());

            }

        }

    }
}
