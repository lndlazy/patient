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
import com.ylean.cf_hospitalapp.inquiry.activity.AddFamilyNumActivity;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.AddressBean;
import com.ylean.cf_hospitalapp.mall.acitity.AddAddressActivity;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;
import com.ylean.cf_hospitalapp.my.adapter.AreaAdapter;
import com.ylean.cf_hospitalapp.my.adapter.CityAdapter;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域选择
 */
public class AreaSelectAct extends BaseActivity {

    private String provinceCode;
    private String provinceName;
    private String provinceId;

    private String cityCode;
    private String cityName;
    private String cityId;
    private CityAdapter cityAdapter;
    private List<AreaEntry.DataBean> areaList = new ArrayList<>();
    private AreaAdapter areaAdapter;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_area_select);

        provinceId = getIntent().getStringExtra("provinceId");
        provinceName = getIntent().getStringExtra("provinceName");
        provinceCode = getIntent().getStringExtra("provinceCode");
        type = getIntent().getStringExtra("type");

        cityId = getIntent().getStringExtra("cityId");
        cityName = getIntent().getStringExtra("cityName");
        cityCode = getIntent().getStringExtra("cityCode");

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        recyclerView.addItemDecoration(divider);

        areaAdapter = new AreaAdapter(this, areaList);
        recyclerView.setAdapter(areaAdapter);
//        cityAdapter = new CityAdapter(this, cityEntryData);
//        recyclerView.setAdapter(cityAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {


//                AddressBean addressBean = new AddressBean();
//                addressBean.setAreaCode();
//                addressBean.setAreaName();
//                addressBean.setCityCode(cityCode);
//                addressBean.setCityName(cityName);
//                addressBean.setProvinceCode(provinceCode);
//                addressBean.setProvinceName(provinceName);

//                EventBus.getDefault().post(addressBean);


                if ("address".equals(type)) {

                    Intent m = new Intent(AreaSelectAct.this, AddAddressActivity.class);
                    m.putExtra("provinceCode", provinceCode);
                    m.putExtra("provinceName", provinceName);
                    m.putExtra("cityCode", cityCode);
                    m.putExtra("cityName", cityName);
                    m.putExtra("areaCode", areaList.get(position).getCode());
                    m.putExtra("areaName", areaList.get(position).getName());
                    startActivity(m);
                }else {
                    Intent m = new Intent(AreaSelectAct.this, AddFamilyNumActivity.class);
                    m.putExtra("provinceCode", provinceCode);
                    m.putExtra("provinceName", provinceName);
                    m.putExtra("cityCode", cityCode);
                    m.putExtra("cityName", cityName);
                    m.putExtra("areaCode", areaList.get(position).getCode());
                    m.putExtra("areaName", areaList.get(position).getName());
                    startActivity(m);
                }

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        RetrofitHttpUtil.getInstance().areaList(new BaseNoTObserver<AreaEntry>() {
            @Override
            public void onHandleSuccess(AreaEntry cityEntry) {

                if (cityEntry != null)
                    areaList.addAll(cityEntry.getData());

                if (areaAdapter != null)
                    areaAdapter.notifyDataSetChanged();

            }

            @Override
            public void onHandleError(String message) {

            }

        }, SpValue.CH, cityCode);

    }
}
