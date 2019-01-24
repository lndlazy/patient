package com.ylean.cf_hospitalapp.register.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.adapter.HospitalLevelAdapter;
import com.ylean.cf_hospitalapp.inquiry.bean.GradeLevelBean;
import com.ylean.cf_hospitalapp.register.adapter.HospitailAdapter;
import com.ylean.cf_hospitalapp.register.adapter.HospitalTypeAdapter;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalTypeEntry;
import com.ylean.cf_hospitalapp.register.pres.IHospitalPres;
import com.ylean.cf_hospitalapp.register.view.IHospitalView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约挂号
 * Created by linaidao on 2019/1/3.
 */
public class OrderRegisterActivity extends BaseActivity implements View.OnClickListener, IHospitalView {

    private android.widget.EditText etCommit;
    private android.widget.LinearLayout llSearch;
    private android.widget.TextView tvSort1;
    private android.widget.ImageView ivOne;
    private android.widget.RelativeLayout rlOne;
    private android.widget.TextView tvSort2;
    private android.widget.ImageView ivTwo;
    private android.widget.RelativeLayout rlTwo;
    private android.support.v7.widget.RecyclerView recyclerView;

    private IHospitalPres iHospitalPres = new IHospitalPres(this);
    private LinearLayout ll;
    private TextView tvCount;
    private ImageView ivCount;

    //医院等级列表
    private List<GradeLevelBean.DataBean> hospitalLevelList;
    //医院类型列表
    private List<HospitalTypeEntry.DataBean> hospitalTypeList;

    private List<HospitalListEntry.DataBean> hospitailList = new ArrayList<>();
    private HospitailAdapter hospitailAdapter;

    private PopupWindow choosePopupWindow;
    private PopupWindow sortPopupWindow;
    private HospitalLevelAdapter hospitalLevelAdapter;
    private HospitalTypeAdapter hospitalTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_order_register);

        initView();

        getHospitailInfo();

        iHospitalPres.hospitalTypeList();
        iHospitalPres.hospitalLevelList();
    }

    private void getHospitailInfo() {

        if (!TextUtils.isEmpty(etCommit.getText().toString()))
            iHospitalPres.setHospitalname(etCommit.getText().toString());
        iHospitalPres.hospitalList((String) SaveUtils.get(this, SpValue.LAT, ""),
                (String) SaveUtils.get(this, SpValue.LON, ""));
    }

    private void initView() {

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.rlTwo = (RelativeLayout) findViewById(R.id.rlTwo);
        this.ivTwo = (ImageView) findViewById(R.id.ivTwo);
        this.tvSort2 = (TextView) findViewById(R.id.tvSort2);
        this.rlOne = (RelativeLayout) findViewById(R.id.rlOne);
        this.ivOne = (ImageView) findViewById(R.id.ivOne);
        this.tvSort1 = (TextView) findViewById(R.id.tvSort1);
        this.llSearch = (LinearLayout) findViewById(R.id.llSearch);
        this.etCommit = (EditText) findViewById(R.id.etCommit);
        ll = findViewById(R.id.ll);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        hospitailAdapter = new HospitailAdapter(this, hospitailList);
        recyclerView.setAdapter(hospitailAdapter);

        rlOne.setOnClickListener(this);
        rlTwo.setOnClickListener(this);

        //搜索
        etCommit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getHospitailInfo();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rlOne:
                showSortDialog();
                break;

            case R.id.rlTwo:

                if (hospitalLevelList == null ||
                        hospitalTypeList == null) {
                    showErr("未获取到医院信息");
                    return;
                }

                showChooseDialog();

                break;

            case R.id.rlCount:

                iHospitalPres.setType("1".equals(iHospitalPres.getType()) ? "" : "1");

                if (sortPopupWindow != null && sortPopupWindow.isShowing())
                    sortPopupWindow.dismiss();

                getHospitailInfo();

                break;

            case R.id.rvReset://重置

                iHospitalPres.setHosgrade("");
                iHospitalPres.setHostype("");

                if (hospitalLevelList != null) {
                    for (int i = 0; i < hospitalLevelList.size(); i++) {
                        hospitalLevelList.get(i).setSelect(false);
                    }
                    if (hospitalLevelAdapter != null)
                        hospitalLevelAdapter.notifyDataSetChanged();
                }

                if (hospitalTypeList != null) {

                    for (int i = 0; i < hospitalTypeList.size(); i++) {
                        hospitalTypeList.get(i).setSelect(false);
                    }
                    if (hospitalTypeAdapter != null)
                        hospitalTypeAdapter.notifyDataSetChanged();
                }

                break;

            case R.id.tvSure://确定

                getHospitailInfo();

                if (choosePopupWindow != null && choosePopupWindow.isShowing())
                    choosePopupWindow.dismiss();

                break;
        }

    }

    //筛选的dialog
    private void showChooseDialog() {

        View view = View.inflate(this, R.layout.dialog_choose_hospital, null);
        choosePopupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        choosePopupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        choosePopupWindow.setFocusable(true);
        choosePopupWindow.showAsDropDown(ll);

        setColor(2);
        initChooseView(view);

        choosePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAllGray();
            }
        });

    }

    private void initChooseView(View view) {

        initHospitalLevelRecyclerView(view);
        initHospitalTypeRecycleView(view);

        TextView rvReset = view.findViewById(R.id.rvReset);
        TextView tvSure = view.findViewById(R.id.tvSure);

        rvReset.setOnClickListener(this);
        tvSure.setOnClickListener(this);

    }

    private void initHospitalTypeRecycleView(View view) {

        RecyclerView hospitalTypeRecyclerView = view.findViewById(R.id.hospitalTypeRecyclerView);
        GridLayoutManager hospitalLayoutManager = new GridLayoutManager(this, 3);
        hospitalTypeRecyclerView.setLayoutManager(hospitalLayoutManager);
        hospitalLayoutManager.setSmoothScrollbarEnabled(true);
        hospitalLayoutManager.setAutoMeasureEnabled(true);
        hospitalTypeRecyclerView.setHasFixedSize(true);
        hospitalTypeRecyclerView.setNestedScrollingEnabled(false);
        hospitalTypeAdapter = new HospitalTypeAdapter(this, hospitalTypeList);
        hospitalTypeRecyclerView.setAdapter(hospitalTypeAdapter);

        hospitalTypeRecyclerView.addOnItemTouchListener(new OnItemClickListener(hospitalTypeRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                boolean select = hospitalTypeList.get(position).isSelect();

                for (int i = 0; i < hospitalTypeList.size(); i++) {
                    hospitalTypeList.get(i).setSelect(false);
                }

                iHospitalPres.setHostype(select ? "" : hospitalTypeList.get(position).getId());
                hospitalTypeList.get(position).setSelect(!select);
                if (hospitalTypeAdapter != null)
                    hospitalTypeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    //医院等级adapter
    private void initHospitalLevelRecyclerView(View view) {

        RecyclerView hospitalLevelRecyclerView = view.findViewById(R.id.hospitalLevelRecyclerView);
        GridLayoutManager hospitalLayoutManager = new GridLayoutManager(this, 3);
        hospitalLevelRecyclerView.setLayoutManager(hospitalLayoutManager);
        hospitalLayoutManager.setSmoothScrollbarEnabled(true);
        hospitalLayoutManager.setAutoMeasureEnabled(true);
        hospitalLevelRecyclerView.setHasFixedSize(true);
        hospitalLevelRecyclerView.setNestedScrollingEnabled(false);
        hospitalLevelAdapter = new HospitalLevelAdapter(this, hospitalLevelList);
        hospitalLevelRecyclerView.setAdapter(hospitalLevelAdapter);
        hospitalLevelRecyclerView.addOnItemTouchListener(new OnItemClickListener(hospitalLevelRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                boolean isSelect = hospitalLevelList.get(position).isSelect();
                for (int i = 0; i < hospitalLevelList.size(); i++) {
                    hospitalLevelList.get(i).setSelect(false);
                }

                //设置医院等级
                iHospitalPres.setHosgrade(isSelect ? "" : hospitalLevelList.get(position).getId());
                hospitalLevelList.get(position).setSelect(!isSelect);
                if (hospitalLevelAdapter != null)
                    hospitalLevelAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }


    /**
     * 弹出选择框
     */
    private void showSortDialog() {

        View view = View.inflate(this, R.layout.dialog_sort, null);
        sortPopupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        sortPopupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        sortPopupWindow.setFocusable(true);
        sortPopupWindow.showAsDropDown(ll);

        RelativeLayout rlCount = view.findViewById(R.id.rlCount);
        tvCount = view.findViewById(R.id.tvCount);
        tvCount.setText("离我最近");
        ivCount = view.findViewById(R.id.ivCount);

        setColor(1);

        if ("1".equals(iHospitalPres.getType())) {//离我最近排序
            ivCount.setVisibility(View.VISIBLE);
            tvCount.setTextColor(getResources().getColor(R.color.tab_colorf9));
        } else {
            ivCount.setVisibility(View.GONE);
            tvCount.setTextColor(getResources().getColor(R.color.txt_color_light6));
        }

        sortPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAllGray();
            }
        });
        rlCount.setOnClickListener(this);
    }


    private void setColor(int i) {

        setAllGray();

        switch (i) {
            case 1:
                tvSort1.setTextColor(getResources().getColor(R.color.tab_colorf9));
                ivOne.setSelected(true);
                break;

            case 2:
                tvSort2.setTextColor(getResources().getColor(R.color.tab_colorf9));
                ivTwo.setSelected(true);
                break;
        }

    }

    private void setAllGray() {

        tvSort1.setTextColor(getResources().getColor(R.color.txt_color_light6));
        tvSort2.setTextColor(getResources().getColor(R.color.txt_color_light6));

        ivOne.setSelected(false);
        ivTwo.setSelected(false);

    }

    @Override
    public void setHospitailList(List<HospitalListEntry.DataBean> datas) {

        hospitailList.clear();
        hospitailList.addAll(datas);
        hospitailAdapter.notifyDataSetChanged();

    }

    //医院等级列表
    @Override
    public void setGradeLevel(List<GradeLevelBean.DataBean> data) {
        hospitalLevelList = data;
    }

    //医院类型
    @Override
    public void setHospitalTypeInfo(List<HospitalTypeEntry.DataBean> data) {
        hospitalTypeList = data;
    }
}
