package com.ylean.cf_hospitalapp.inquiry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.PeopleEntry;
import com.ylean.cf_hospitalapp.home.adapter.FamilyNumsAdapter;
import com.ylean.cf_hospitalapp.my.activity.FamilyDetailActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.DensityUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 问诊人选择
 */
public class AskPeopleActivity extends BaseActivity {

    private static final int ADD_FAMILY_REQUESTCODE = 0x210;
    private static final int ADD_FAMILY_RESULT_CODE = 0x0116;
    private SwipeMenuRecyclerView recyclerView;
    private List<PeopleEntry.DataBean> familyNumList = new ArrayList<>();
    private FamilyNumsAdapter familyNumsAdapter;

    private static final int CHOOSE_ASK_PEOPEL_RESULE_CODE = 0X211;
    private String title;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ask_choose);

        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");

        initView();
        getFamilyNums();

    }

    private void initView() {
        this.recyclerView = findViewById(R.id.recyclerView);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tbv.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityWithCode(AddFamilyNumActivity.class, ADD_FAMILY_REQUESTCODE);
            }
        });

        tbv.setTitle(title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        recyclerView.setLongPressDragEnabled(false); // 拖拽排序，默认关闭。
        recyclerView.setItemViewSwipeEnabled(false); // 策划删除，默认关闭
        recyclerView.smoothOpenRightMenu(0);

        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent m;
                if ("my".equals(type)) {
                    //我的家人详情

                    m = new Intent(AskPeopleActivity.this, FamilyDetailActivity.class);
                    m.putExtra("id", familyNumList.get(position).getId());
                    startActivity(m);

                } else {
                    m = new Intent();
                    m.putExtra("selectPeople", familyNumList.get(position));
                    setResult(CHOOSE_ASK_PEOPEL_RESULE_CODE, m);
                    finish();
                }

            }
        });
        // 设置监听器。
        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
//                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
//                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
//                Logger.d("点击了忽略:adapterPosition:" + adapterPosition + ",menuPosition:" + menuPosition);

                deleteFamilyNum(adapterPosition);

            }
        };

        // 菜单点击监听。
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

        familyNumsAdapter = new FamilyNumsAdapter(this, familyNumList);
        recyclerView.setAdapter(familyNumsAdapter);

    }

    private void deleteFamilyNum(int adapterPosition) {

        RetrofitHttpUtil.getInstance().deleteFamilyNum(new BaseNoTObserver<Basebean>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                showLoading("正在删除...");
            }

            @Override
            public void onHandleSuccess(Basebean basebean) {

                hideLoading();
                getFamilyNums();

            }

            @Override
            public void onHandleError(String message) {
                hideLoading();
                showErr(message);
            }

        }, (String) SaveUtils.get(this, SpValue.TOKEN, ""), SpValue.CH, familyNumList.get(adapterPosition).getId());

    }

    // 创建菜单：
    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {

            SwipeMenuItem addItem = new SwipeMenuItem(AskPeopleActivity.this)
//                    .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
                    .setBackgroundColor(getResources().getColor(R.color.red))

                    .setText(getResources().getString(R.string.delete))
                    .setTextColor(getResources().getColor(R.color.white))
                    .setTextSize(16)
//                    .setImage(R.mipmap.ic_ignore) // 图标。
                    .setWidth(DensityUtil.dip2px(AskPeopleActivity.this, 80)) // 宽度。
                    .setHeight((int) getResources().getDimension(R.dimen.x60)); // 高度。
            rightMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

        }
    };


    private void getFamilyNums() {

        RetrofitHttpUtil.getInstance().myFamilyList(new BaseNoTObserver<PeopleEntry>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                showLoading("获取中...");
            }

            @Override
            public void onHandleSuccess(PeopleEntry peopleEntry) {

                hideLoading();

                familyNumList.clear();

                if (peopleEntry != null)
                    familyNumList.addAll(peopleEntry.getData());

                if (familyNumsAdapter != null)
                    familyNumsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onHandleError(String message) {
                hideLoading();
                showErr(message);
            }

        }, (String) SaveUtils.get(getApplicationContext(), SpValue.TOKEN, ""), SpValue.CH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ADD_FAMILY_RESULT_CODE)
            getFamilyNums();

    }
}
