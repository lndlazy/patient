package com.ylean.cf_hospitalapp.home.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.base.activity.HomeActivity;
import com.ylean.cf_hospitalapp.home.bean.BannerBean;
import com.ylean.cf_hospitalapp.inquiry.bean.HospitalEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.RecommendEntry;
import com.ylean.cf_hospitalapp.home.view.IFragmentOneView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

public class IFragmentOnePrenenter {

    private IFragmentOneView iFragmentOneView;
    private Context context;
    private LocationClient locationClient;

    public IFragmentOnePrenenter(IFragmentOneView iFragmentOneView) {
        this.iFragmentOneView = iFragmentOneView;
    }

    /**
     * 获取轮播图
     *
     * @param pagemark
     * @param hospitalid
     */
    private void getBanner(String pagemark, String hospitalid) {

        RetrofitHttpUtil.getInstance()
                .getBannerList(new BaseNoTObserver<BannerBean>() {

                    @Override
                    public void onHandleSuccess(BannerBean doctorListEntry) {

                        if (doctorListEntry == null)
                            return;

                        List<BannerBean.DataBean> bannerList = doctorListEntry.getData();

                        if (bannerList == null || bannerList.size() == 0)
                            return;
                        if (iFragmentOneView != null)
                            iFragmentOneView.setBannerInfo(bannerList);

                    }

                    @Override
                    public void onHandleError(String message) {
                        if (iFragmentOneView != null)
                            iFragmentOneView.showErr(message);
                    }

                }, SpValue.CH, pagemark, hospitalid);

    }

    /**
     * 开启定位
     */
    public void startLocation(Context context) {

        this.context = context;
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        locationClient = new LocationClient(context.getApplicationContext());
        //声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
        //注册监听函数
        locationClient.registerLocationListener(myLocationListener);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的 设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
//        locationOption.setScanSpan(100);
        //可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
        //可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
        //可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
//        locationOption.setLocationNotify(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        locationOption.setIgnoreKillProcess(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
        //可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(false);
        //可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
//        locationOption.setOpenAutoNotifyMode();
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
//        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
        locationClient.setLocOption(locationOption);
        //开始定位
        locationClient.start();

    }

    /**
     * 实现定位回调
     */
    public class MyLocationListener implements BDLocationListener {

//        @Override
//        public void onLocDiagnosticMessage(int i, int i1, String s) {
//            super.onLocDiagnosticMessage(i, i1, s);
//
//            Logger.d("错误信息::" + s);
//
//        }

        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

//            Logger.d("==onReceiveLocation==" + (location == null));

            if (location == null || HomeActivity.isHandChooseLocation) {
                //没有返回结果
                getLastInfo();
                iFragmentOneView.setCity((String) SaveUtils.get(context, SpValue.CITY, "未知"));

                if (HomeActivity.isHandChooseLocation) {
                    SaveUtils.put(context, SpValue.LOCATION_CITY, location.getCity());
                    SaveUtils.put(context, SpValue.LOCATION_CITY_CODE, location.getCityCode());
                    SaveUtils.put(context, SpValue.LOCATION_LAT, location.getLatitude() + "");
                    SaveUtils.put(context, SpValue.LOCATION_LON, location.getLongitude() + "");
                    SaveUtils.put(context, SpValue.LOCATION_PROVINCE, location.getProvince());
                }

                return;
            }

            if (location.getLatitude() != 0 && location.getLongitude() != 0) {
                if (locationClient != null)
                    locationClient.stop();

                iFragmentOneView.setCity(location.getCity());

                SaveUtils.put(context, SpValue.CITY, location.getCity());
                SaveUtils.put(context, SpValue.CITY_CODE, location.getCityCode());
                SaveUtils.put(context, SpValue.LAT, location.getLatitude() + "");
                SaveUtils.put(context, SpValue.LON, location.getLongitude() + "");
                SaveUtils.put(context, SpValue.PROVINCE, location.getProvince());

                Logger.d("定位城市::" + location.getCity());

                getHospital(location.getLatitude() + "", location.getLongitude() + "", false);
            } else {
                getLastInfo();
            }

        }

    }

    public void getLastInfo() {
        if (!TextUtils.isEmpty((String) SaveUtils.get(context, SpValue.LAT, ""))) {
            getHospital((String) SaveUtils.get(context, SpValue.LAT, "")
                    , (String) SaveUtils.get(context, SpValue.LON, ""), false);
        } else {
            iFragmentOneView.showErr("未获取到位置信息");
            iFragmentOneView.stopRefush();

        }
    }

    //获取附近的医院
    public void getHospital(String latitude, String longitude, boolean refush) {

        RetrofitHttpUtil.getInstance()
                .getHospital(new BaseNoTObserver<HospitalEntry>() {

                    @Override
                    public void onHandleSuccess(HospitalEntry hospitalEntry) {

                        iFragmentOneView.stopRefush();

                        if (hospitalEntry == null || hospitalEntry.getData() == null)
                            return;

                        SaveUtils.put(context, SpValue.HOSPITAL_ID, hospitalEntry.getData().getHospitalid());

                        //获取banner图
                        getBanner("1", hospitalEntry.getData().getHospitalid());

                        getDataInfo(hospitalEntry.getData().getHospitalid(), refush);

                    }

                    @Override
                    public void onHandleError(String message) {

                        iFragmentOneView.stopRefush();
                        if (iFragmentOneView != null)
                            iFragmentOneView.showErr(message);
                    }

                }, SpValue.CH, longitude, latitude);

    }

    public void getDataInfo(String hospitalid, boolean refush) {

        Logger.d("当前选择的position" + iFragmentOneView.getCurrentPosition());

        switch (iFragmentOneView.getCurrentPosition()) {

            case 0: //获取首页 综合推荐

                getRecommand(hospitalid, refush ? 1 : iFragmentOneView.getPageOne(),
                        (String) SaveUtils.get(context, SpValue.TOKEN, ""));
                break;
            case 1://精彩问诊
                hotConsult(hospitalid, refush ? 1 : iFragmentOneView.getPageTwo());
                break;

            case 2://专家讲堂
                speechInfo(hospitalid, refush ? 1 : iFragmentOneView.getPageThree());
                break;

            default:
                getRecommand(hospitalid, refush ? 1 : iFragmentOneView.getPageOne(),
                        (String) SaveUtils.get(context, SpValue.TOKEN, ""));
                break;
        }
    }

    //首页综合推荐
    private void getRecommand(String hospitalid, int page, String token) {

        final int p = page;
        RetrofitHttpUtil.getInstance()
                .getRecommand(new BaseNoTObserver<RecommendEntry>() {

                    @Override
                    public void onHandleSuccess(RecommendEntry recommendEntry) {

                        if (recommendEntry == null || recommendEntry.getData() == null)
                            return;

                        if (iFragmentOneView != null)
                            iFragmentOneView.setRecommand(recommendEntry, p);

                    }

                    @Override
                    public void onHandleError(String message) {
                        iFragmentOneView.showErr(message);
                    }

                }, SpValue.CH, hospitalid, page, SpValue.PAGE_SIZE, token);

    }

    //精彩问诊
    private void hotConsult(String hospitalid, int hotePage) {

        final int p = hotePage;

        RetrofitHttpUtil
                .getInstance()
                .hotConsult(
                        new BaseNoTObserver<RecommendEntry>() {
                            @Override
                            public void onHandleSuccess(RecommendEntry recommendEntry) {

                                if (recommendEntry == null || recommendEntry.getData() == null)
                                    return;

                                for (int i = 0; i < recommendEntry.getData().size(); i++) {
                                    recommendEntry.getData().get(i).setType("3");//设置为问答
                                }

                                if (iFragmentOneView != null)
                                    iFragmentOneView.setRecommand(recommendEntry, p);

                            }

                            @Override
                            public void onHandleError(String message) {
                                iFragmentOneView.showErr(message);
                            }

                        }
                        , SpValue.CH
                        , hospitalid
                        , hotePage
                        , SpValue.PAGE_SIZE);

    }

    //专家讲堂
    private void speechInfo(String hospitalid, int speechPage) {

        final int p = speechPage;

        RetrofitHttpUtil
                .getInstance()
                .speechList(
                        new BaseNoTObserver<RecommendEntry>() {
                            @Override
                            public void onHandleSuccess(RecommendEntry recommendEntry) {

                                if (recommendEntry == null || recommendEntry.getData() == null)
                                    return;

                                for (int i = 0; i < recommendEntry.getData().size(); i++) {
                                    recommendEntry.getData().get(i).setType("1");//设置为专家讲堂
                                }

                                if (iFragmentOneView != null)
                                    iFragmentOneView.setRecommand(recommendEntry, p);

                            }

                            @Override
                            public void onHandleError(String message) {
                                iFragmentOneView.showErr(message);
                            }

                        }, SpValue.CH
                        , hospitalid
                        , speechPage
                        , SpValue.PAGE_SIZE);

    }

}
