package com.ylean.cf_hospitalapp.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends BaseActivity {

    private CityPicker cityPicker;
    private static final int CODE_CHOOSE_LOCATION_RESULT = 0x02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<HotCity> hotCities = new ArrayList<>();
        hotCities.add(new HotCity("北京", "北京", "131")); //code为城市代码
        hotCities.add(new HotCity("上海", "上海", "289"));
        hotCities.add(new HotCity("广州", "广东", "257"));
        hotCities.add(new HotCity("深圳", "广东", "340"));
        hotCities.add(new HotCity("杭州", "浙江", "179"));

        //activity或者fragment
        cityPicker = CityPicker.from(this);

        cityPicker.enableAnimation(true)    //启用动画效果，默认无
//                .setAnimationStyle(anim)	//自定义动画
                //APP自身已定位的城市，传null会自动定位（默认）
                .setLocatedCity(null)
                .setHotCities(hotCities)    //指定热门城市
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
//                        showErr(data.getName());

                        Intent d = new Intent();
                        d.putExtra("cityName", data.getName());
                        d.putExtra("cityCode", data.getCode());
                        setResult(CODE_CHOOSE_LOCATION_RESULT, d);
                        finish();

                    }

                    @Override
                    public void onLocate() {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //定位完成之后更新数据
                                cityPicker.locateComplete(new LocatedCity((String) SaveUtils.get(getApplicationContext(), SpValue.CITY, "北京")
                                        , (String) SaveUtils.get(getApplicationContext(), SpValue.CITY, "北京")
                                        , (String) SaveUtils.get(getApplicationContext(), SpValue.CITY_CODE, "131")), LocateState.SUCCESS);

//                                cityPicker.locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
                            }
                        }, 10);

                    }

                    @Override
                    public void onCancel() {
                        finish();
                    }

                }).show();
    }


}
