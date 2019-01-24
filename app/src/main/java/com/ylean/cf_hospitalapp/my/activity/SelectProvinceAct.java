package com.ylean.cf_hospitalapp.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.my.bean.ProvinceEntry;
import com.ylean.cf_hospitalapp.my.adapter.ProvinceAdapter;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择省份
 */
public class SelectProvinceAct extends BaseActivity {

    private List<ProvinceEntry.DataBean> provinceDataList = new ArrayList<>();
    private ProvinceAdapter adapter;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_province);

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        type = getIntent().getStringExtra("type");
        initRecyclerView();
        province();
    }

    private void province() {

        RetrofitHttpUtil.getInstance().provinceList(new BaseNoTObserver<ProvinceEntry>() {
            @Override
            public void onHandleSuccess(ProvinceEntry provinceEntry) {

                provinceDataList.addAll(provinceEntry.getData());
                if (adapter != null)
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onHandleError(String message) {

            }

        }, SpValue.CH);

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        adapter = new ProvinceAdapter(this, provinceDataList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m = new Intent(SelectProvinceAct.this, CitySelectAct.class);
                m.putExtra("provinceId", provinceDataList.get(position).getId());
                m.putExtra("type", type);
                m.putExtra("provinceName", provinceDataList.get(position).getName());
                m.putExtra("provinceCode", provinceDataList.get(position).getCode());
                startActivity(m);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
