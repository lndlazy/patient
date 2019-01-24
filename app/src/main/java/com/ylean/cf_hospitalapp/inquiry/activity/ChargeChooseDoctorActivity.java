package com.ylean.cf_hospitalapp.inquiry.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.DepartmentListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorLevelListBean;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.GradeLevelBean;
import com.ylean.cf_hospitalapp.inquiry.adapter.DepartmentAdapter;
import com.ylean.cf_hospitalapp.inquiry.adapter.DocterLevelAdapter;
import com.ylean.cf_hospitalapp.inquiry.adapter.HospitalLevelAdapter;
import com.ylean.cf_hospitalapp.inquiry.presenter.IPayPresenter;
import com.ylean.cf_hospitalapp.inquiry.adapter.PayAskDoctorAdapter;
import com.ylean.cf_hospitalapp.inquiry.adapter.RoomRightAdapter;
import com.ylean.cf_hospitalapp.inquiry.view.IPayView;
import com.ylean.cf_hospitalapp.utils.DensityUtil;
import com.ylean.cf_hospitalapp.widget.EditPicView;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 付费问诊, 选择医生页面
 */
public class ChargeChooseDoctorActivity extends BaseActivity implements View.OnClickListener, IPayView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private ImageView ivOne;
    private ImageView ivTwo;
    private ImageView ivThree;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
    private EditPicView epv;

    private String searchname = "";//搜索名称
    private String departid = "";//科室id
    private String doctitleid = "";//医生等级id
    private String hosgradid = "";//医院等级id
    private String asktype = "";//问诊方式 1-图文 2-电话 3-视频
    private String sorttype = "";//排序方式
    private int page = 0;//
    private String size = "15";//

    //医生adapter
    private PayAskDoctorAdapter payAskDoctorAdapter;
    private List<DoctorListEntry.DataBean> doctorInfoList = new ArrayList<>();
    private TextView tvTuWen;
    private TextView tvDianHua;

    //医生等级列表
    private List<DoctorLevelListBean.DataBean> doctorLevelList = new ArrayList<>();
    //医院等级列表
    private List<GradeLevelBean.DataBean> gradeLevelList = new ArrayList<>();
    //科室列表
    private List<DepartmentListEntry.DataBean> departmentList = new ArrayList<>();
    protected RecyclerView doctorRecyclerView;
    //医院等级adapter
    private HospitalLevelAdapter hospitalLevelAdapter;
    //医生等级adapter
    private DocterLevelAdapter docterLevelAdapter;

    private IPayPresenter iPayPresenter = new IPayPresenter(this);
    private PopupWindow choosePopupWindow;
    private PopupWindow sortPopupWindow;
    private TextView tvCount;
    private ImageView ivCount;
    private PopupWindow roomPopupWindow;
    //科室adapter
    private DepartmentAdapter departmentAdapter;
    private RecyclerView roomLeftRecyclerView;
    private List<DepartmentListEntry.DataBean.ChildlistBean> rightRoomList = new ArrayList<>();
    private RoomRightAdapter roomRightAdapter;
    private RecyclerView roomRightRecyclerView;
    private SwipeRefreshLayout swipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pay_ask);

//        askType = getIntent().getStringExtra("askType");
//        String imgs = getIntent().getStringExtra("imgs");
//        String flokId = getIntent().getStringExtra("flokId");
//        String diseaseId = getIntent().getStringExtra("diseaseId");
//        String problem = getIntent().getStringExtra("problem");
//        String describe = getIntent().getStringExtra("describe");
//        String voiceurl = getIntent().getStringExtra("voiceurl");

        initView();
        //获取医生列表
        getDoctorList();
        //获取医院等级
        iPayPresenter.hosGradeLevelList();
        //医生等级列表
        iPayPresenter.doctorLevelList();
        //全部科室
        iPayPresenter.allRoom();

    }

    @Override
    public void defalutInfo(DoctorListEntry doctorListEntry) {

        swipView.setRefreshing(false);

        if (doctorListEntry == null) {
            showErr("未获取到数据");
            return;
        }

//        doctorInfoList.clear();
        doctorInfoList.addAll(doctorListEntry.getData());

        if (doctorInfoList.size() == 0)
            showErr("未查询到数据");

        if (payAskDoctorAdapter == null) {
            payAskDoctorAdapter = new PayAskDoctorAdapter(this, doctorInfoList);
            recyclerView.setAdapter(payAskDoctorAdapter);
        } else
            payAskDoctorAdapter.notifyDataSetChanged();

    }

    private void initView() {

        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        epv = findViewById(R.id.epv);

        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        tvOne = findViewById(R.id.tvOne);
        tvTwo = findViewById(R.id.tvTwo);
        tvThree = findViewById(R.id.tvThree);
        ivOne = findViewById(R.id.ivOne);
        ivTwo = findViewById(R.id.ivTwo);
        ivThree = findViewById(R.id.ivThree);
        swipView = findViewById(R.id.swipView);
        swipView.setOnRefreshListener(this);
        swipView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        this.recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 0 && !recyclerView.canScrollVertically(1)) {
                    page++;
                    getDoctorList();
                }

            }

        });

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);

        epv.setOnSearch(new EditPicView.OnSearch() {
            @Override
            public void startSearch(String s) {

                searchname = s;
                getDoctorList();

            }
        });

    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ll1://综合排序
                showSortDialog();
                break;

            case R.id.ll2://科室选择

                showRoomDialog();

                break;

            case R.id.ll3://筛选
                showChooseDialog();
                break;

            case R.id.rvReset://重置筛选条件

                resetChoice();

                break;
            case R.id.tvSure://确定筛选条件

                startSearch();

                break;
            case R.id.tvTuWen://图文

//                asktype = "";//问诊方式 1-图文 2-电话 3-视频
                updateStyle("1", tvTuWen, tvDianHua);

                break;
            case R.id.tvDianHua://电话

                updateStyle("2", tvDianHua, tvTuWen);

                break;
            case R.id.rlCount://问诊量

                sortSearch();

                break;
        }

    }

    private void sortSearch() {
        if (sortPopupWindow != null)
            sortPopupWindow.dismiss();

        if ("1".equals(sorttype)) {
            //取消选择问诊量
            sorttype = "";
            ivCount.setVisibility(View.GONE);
            tvCount.setTextColor(getResources().getColor(R.color.txt_color_light6));
        } else {
            //选择问诊量
            sorttype = "1";
            ivCount.setVisibility(View.VISIBLE);
            tvCount.setTextColor(getResources().getColor(R.color.tab_colorf9));
        }

        getDoctorList();
    }

    private void startSearch() {
        if (choosePopupWindow != null)
            choosePopupWindow.dismiss();

        doctorInfoList.clear();

        getDoctorList();
    }

    //重置筛选条件
    private void resetChoice() {

        //置空类型
        asktype = "";
        tvTuWen.setTextColor(getResources().getColor(R.color.txt_color_light6));
        tvDianHua.setTextColor(getResources().getColor(R.color.txt_color_light6));

        //置空医院等级
        hosgradid = "";
        if (gradeLevelList != null) {
            for (int i = 0; i < gradeLevelList.size(); i++) {
                gradeLevelList.get(i).setSelect(false);
            }
        }
        if (hospitalLevelAdapter != null)
            hospitalLevelAdapter.notifyDataSetChanged();

        //置空医生级别
        doctitleid = "";
        if (doctorLevelList != null) {
            for (int i = 0; i < doctorLevelList.size(); i++) {
                doctorLevelList.get(i).setSelect(false);
            }
        }

        if (docterLevelAdapter != null)
            docterLevelAdapter.notifyDataSetChanged();

//        departid = "";//科室id
//        sorttype = "";//排序方式
    }

    private void updateStyle(String s, TextView tvTuWen, TextView tvDianHua) {
        if (s.equals(asktype)) {
            //取消图文选择
            asktype = "";
            tvTuWen.setTextColor(getResources().getColor(R.color.txt_color_light6));
        } else {
            //选择图文
            asktype = s;
            tvTuWen.setTextColor(getResources().getColor(R.color.tab_colorf9));
            tvDianHua.setTextColor(getResources().getColor(R.color.txt_color_light6));
        }
    }

    //筛选的dialog
    private void showRoomDialog() {

        View view = View.inflate(this, R.layout.item_recyclerview_small, null);
        roomPopupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 400));

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        roomPopupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        roomPopupWindow.setFocusable(true);
        roomPopupWindow.showAsDropDown(ll2);

        tvTwo.setTextColor(getResources().getColor(R.color.tab_colorf9));
        ivTwo.setSelected(true);

        roomPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvTwo.setTextColor(getResources().getColor(R.color.txt_color_light6));
                ivTwo.setSelected(false);
            }
        });

        roomLeftRecyclerView = view.findViewById(R.id.leftRecyclerView);
        roomLeftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        roomLeftRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        roomLeftRecyclerView.addItemDecoration(divider);
        departmentAdapter = new DepartmentAdapter(this, departmentList);
        roomLeftRecyclerView.setAdapter(departmentAdapter);
        roomLeftRecyclerView.addOnItemTouchListener(new OnItemClickListener(roomLeftRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                boolean isSelect = departmentList.get(position).isSelect();

                chooseDepartment(position, isSelect);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        //加载右边选择框
        roomRightRecyclerView = view.findViewById(R.id.rightRecyclerView);
        roomRightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        roomRightRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration rightdivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rightdivider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        roomRightRecyclerView.addItemDecoration(rightdivider);
        roomRightRecyclerView.addOnItemTouchListener(new OnItemClickListener(roomRightRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                roomRightChoice(position);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        roomRightAdapter = new RoomRightAdapter(this, rightRoomList);
        this.roomRightRecyclerView.setAdapter(roomRightAdapter);

        roomPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                doctorInfoList.clear();
                getDoctorList();

                tvTwo.setTextColor(getResources().getColor(R.color.txt_color_light6));
                ivTwo.setSelected(false);
            }
        });
    }

    private void roomRightChoice(int position) {
        boolean isSelect = rightRoomList.get(position).isSelect();

        for (int i = 0; i < rightRoomList.size(); i++) {
            rightRoomList.get(i).setSelect(false);
        }

        if (isSelect) {
            //取消科室id
            departid = "";
        } else {
            departid = rightRoomList.get(position).getDepartmentname();
            rightRoomList.get(position).setSelect(true);
        }
        if (roomRightAdapter != null)
            roomRightAdapter.notifyDataSetChanged();

//        iPayPresenter.doctorTypeList(ChargeChooseDoctorActivity.this, searchname, departid, doctitleid, hosgradid, asktype
//                , sorttype, page, size);
    }

    private void chooseDepartment(int position, boolean isSelect) {
        for (int i = 0; i < departmentList.size(); i++) {
            departmentList.get(i).setSelect(false);
        }

        for (int i = 0; i < rightRoomList.size(); i++) {
            rightRoomList.get(i).setSelect(false);
        }

        if (isSelect) {
            //取消科室id
            departid = "";
        } else {
            //选择科室id
            departid = departmentList.get(position).getFdepartid();
            departmentList.get(position).setSelect(true);

            rightRoomList.clear();
            if (departmentList.get(position) != null && departmentList.get(position).getChildlist() != null)
                rightRoomList.addAll(departmentList.get(position).getChildlist());
            roomRightAdapter.notifyDataSetChanged();

        }

        if (departmentAdapter != null)
            departmentAdapter.notifyDataSetChanged();

        if (roomRightAdapter != null)
            roomRightAdapter.notifyDataSetChanged();

    }

    //筛选的dialog
    private void showChooseDialog() {

        View view = View.inflate(this, R.layout.dialog_choose, null);
        choosePopupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        choosePopupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        choosePopupWindow.setFocusable(true);
        choosePopupWindow.showAsDropDown(ll3);

        tvThree.setTextColor(getResources().getColor(R.color.tab_colorf9));
        ivThree.setSelected(true);

        initChooseView(view);

        choosePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvThree.setTextColor(getResources().getColor(R.color.txt_color_light6));
                ivThree.setSelected(false);
            }
        });

    }

    private void initChooseView(View view) {

        tvTuWen = view.findViewById(R.id.tvTuWen);
        tvDianHua = view.findViewById(R.id.tvDianHua);

        initHospitalRecyclerView(view);
        initDoctorRecycleView(view);

        TextView rvReset = view.findViewById(R.id.rvReset);
        TextView tvSure = view.findViewById(R.id.tvSure);

        rvReset.setOnClickListener(this);
        tvSure.setOnClickListener(this);
        tvTuWen.setOnClickListener(this);
        tvDianHua.setOnClickListener(this);
        setChooseAllGray();
        setCurrentChoose();
    }

    private void initHospitalRecyclerView(View view) {
        RecyclerView hospitalRecyclerView = view.findViewById(R.id.hospitalRecyclerView);

        GridLayoutManager hospitalLayoutManager = new GridLayoutManager(this, 3);
        hospitalRecyclerView.setLayoutManager(hospitalLayoutManager);
        hospitalLayoutManager.setSmoothScrollbarEnabled(true);
        hospitalLayoutManager.setAutoMeasureEnabled(true);
        hospitalRecyclerView.setHasFixedSize(true);
        hospitalRecyclerView.setNestedScrollingEnabled(false);
        hospitalLevelAdapter = new HospitalLevelAdapter(this, gradeLevelList);
        hospitalRecyclerView.setAdapter(hospitalLevelAdapter);
        hospitalRecyclerView.addOnItemTouchListener(new OnItemClickListener(hospitalRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                boolean isSelect = gradeLevelList.get(position).isSelect();
                for (int i = 0; i < gradeLevelList.size(); i++) {
                    gradeLevelList.get(i).setSelect(false);
                }

                hosgradid = isSelect ? "" : gradeLevelList.get(position).getId();
                gradeLevelList.get(position).setSelect(!isSelect);
                if (hospitalLevelAdapter != null)
                    hospitalLevelAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void initDoctorRecycleView(View view) {
        doctorRecyclerView = view.findViewById(R.id.doctorRecyclerView);

        GridLayoutManager doctorLayoutManager = new GridLayoutManager(this, 3);
        doctorRecyclerView.setLayoutManager(doctorLayoutManager);
        doctorLayoutManager.setSmoothScrollbarEnabled(true);
        doctorLayoutManager.setAutoMeasureEnabled(true);

        doctorRecyclerView.setHasFixedSize(true);
        doctorRecyclerView.setNestedScrollingEnabled(false);
        docterLevelAdapter = new DocterLevelAdapter(this, doctorLevelList);
        doctorRecyclerView.setAdapter(docterLevelAdapter);
        doctorRecyclerView.addOnItemTouchListener(new OnItemClickListener(doctorRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                boolean isSelect = doctorLevelList.get(position).isSelect();
                for (int i = 0; i < doctorLevelList.size(); i++) {
                    doctorLevelList.get(i).setSelect(false);
                }

                doctitleid = isSelect ? "" : doctorLevelList.get(position).getDoctitleid();
                doctorLevelList.get(position).setSelect(!isSelect);

                if (docterLevelAdapter != null)
                    docterLevelAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    //筛选的条目文字颜色全变灰
    private void setChooseAllGray() {

        tvTuWen.setTextColor(getResources().getColor(R.color.txt_color_light6));
        tvDianHua.setTextColor(getResources().getColor(R.color.txt_color_light6));

    }

    private void setCurrentChoose() {

        switch (asktype) {

            //1-图文 2-电话 3-视频
            case "1":
                tvTuWen.setTextColor(getResources().getColor(R.color.tab_colorf9));
                break;
            case "2":
                tvDianHua.setTextColor(getResources().getColor(R.color.tab_colorf9));
                break;
        }

        //当前医生等级选项
        if (doctorLevelList != null && !TextUtils.isEmpty(doctitleid)) {
            for (int i = 0; i < doctorLevelList.size(); i++) {
                doctorLevelList.get(i).setSelect(doctitleid.equals(doctorLevelList.get(i).getDoctitleid()));
            }
        }

        if (gradeLevelList != null && !TextUtils.isEmpty(hosgradid)) {
            for (int i = 0; i < gradeLevelList.size(); i++) {
                gradeLevelList.get(i).setSelect(hosgradid.equals(gradeLevelList.get(i).getId()));
            }
        }

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
        sortPopupWindow.showAsDropDown(ll1);

        RelativeLayout rlCount = view.findViewById(R.id.rlCount);
        tvCount = view.findViewById(R.id.tvCount);
        ivCount = view.findViewById(R.id.ivCount);

        tvOne.setTextColor(getResources().getColor(R.color.tab_colorf9));
        ivOne.setSelected(true);

        if ("1".equals(sorttype)) {
            ivCount.setVisibility(View.VISIBLE);
            tvCount.setTextColor(getResources().getColor(R.color.tab_colorf9));
        } else {
            ivCount.setVisibility(View.GONE);
            tvCount.setTextColor(getResources().getColor(R.color.txt_color_light6));
        }

        sortPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvOne.setTextColor(getResources().getColor(R.color.txt_color_light6));
                ivOne.setSelected(false);
            }
        });
        rlCount.setOnClickListener(this);
    }

    @Override
    public void showInfo(String msg) {
        showErr(msg);
    }

    @Override
    public void setDoctorLevelInfo(List<DoctorLevelListBean.DataBean> data) {
        doctorLevelList.clear();
        doctorLevelList.addAll(data);
    }

    @Override
    public void setGradeLevelInfo(List<GradeLevelBean.DataBean> gradeLevelBeanData) {
        gradeLevelList.clear();
        gradeLevelList.addAll(gradeLevelBeanData);
    }

    @Override
    public void setAllRoomInfo(List<DepartmentListEntry.DataBean> data) {
        departmentList.clear();
        departmentList.addAll(data);

        if (departmentAdapter != null)
            departmentAdapter.notifyDataSetChanged();
    }


    public void getDoctorList() {
        iPayPresenter.doctorList(this, searchname, departid, doctitleid, hosgradid, asktype
                , sorttype, page, size);
    }

    @Override
    public void onRefresh() {
        doctorInfoList.clear();
        page = 1;
        //取消刷新
        getDoctorList();
    }
}
