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
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.my.bean.CityEntry;
import com.ylean.cf_hospitalapp.my.adapter.CityAdapter;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市选择
 */
public class CitySelectAct extends BaseActivity {

    private RecyclerView recyclerView;
    private String provinceCode;
    private String provinceName;
    private String provinceId;
    private List<CityEntry.DataBean> cityEntryData = new ArrayList<>();
    private CityAdapter cityAdapter;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city_select);

        provinceId = getIntent().getStringExtra("provinceId");
        provinceName = getIntent().getStringExtra("provinceName");
        provinceCode = getIntent().getStringExtra("provinceCode");
        type = getIntent().getStringExtra("type");

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);
        cityAdapter = new CityAdapter(this, cityEntryData);
        recyclerView.setAdapter(cityAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                Intent m = new Intent(CitySelectAct.this, AreaSelectAct.class);
                m.putExtra("provinceId", provinceId);
                m.putExtra("type", type);
                m.putExtra("provinceName", provinceName);
                m.putExtra("provinceCode", provinceCode);
                m.putExtra("cityCode", cityEntryData.get(position).getCode());
                m.putExtra("cityName", cityEntryData.get(position).getName());
                m.putExtra("cityId", cityEntryData.get(position).getId());
                startActivity(m);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        RetrofitHttpUtil.getInstance().cityList(new BaseNoTObserver<CityEntry>() {
            @Override
            public void onHandleSuccess(CityEntry cityEntry) {

                if (cityEntry != null)
                    cityEntryData.addAll(cityEntry.getData());

                if (cityAdapter != null)
                    cityAdapter.notifyDataSetChanged();

            }

            @Override
            public void onHandleError(String message) {

            }

        }, SpValue.CH, provinceCode);

    }
}
