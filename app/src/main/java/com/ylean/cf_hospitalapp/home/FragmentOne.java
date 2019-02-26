package com.ylean.cf_hospitalapp.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.AddBean;
import com.ylean.cf_hospitalapp.base.view.BaseFragment;
import com.ylean.cf_hospitalapp.home.activity.ArtDetailAct;
import com.ylean.cf_hospitalapp.home.activity.LocationActivity;
import com.ylean.cf_hospitalapp.home.activity.SearchingAcivity;
import com.ylean.cf_hospitalapp.home.activity.VideoSpeechActivity;
import com.ylean.cf_hospitalapp.home.bean.BannerBean;
import com.ylean.cf_hospitalapp.home.adapter.HomeAdapter;
import com.ylean.cf_hospitalapp.home.presenter.IFragmentOnePrenenter;
import com.ylean.cf_hospitalapp.home.view.IFragmentOneView;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryDetailAct;
import com.ylean.cf_hospitalapp.inquiry.bean.RecommendEntry;
import com.ylean.cf_hospitalapp.my.activity.MyNewsListActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class FragmentOne extends BaseFragment implements View.OnClickListener, IFragmentOneView {

    //SwipeRefreshLayout.OnRefreshListener
    private static final int CODE_CHOOSE_LOCATION = 0x01;
    private static final int CODE_CHOOSE_LOCATION_RESULT = 0x02;
    private static final int REQUEST_PERMISSION_OTHER_CODE = 0x03;
    private TextView tvLocation;
    private IFragmentOnePrenenter iOnePrenenter = new IFragmentOnePrenenter(this);
    private static final int REQUEST_PERMISSION_LOCATION_CODE = 0x13;
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;
    private int currentPosition = -1;
    private List<RecommendEntry.DataBean> recommendList = new ArrayList<>();
//    private SwipeRefreshLayout swipeRefreshLayout;

    private int pageOne = 1;//综合推荐 页码
    private int pageTwo = 1;//精彩问诊 页码
    private int pageThree = 1;//专家讲堂 页码

    private int mPicFirstVisibleItemPosition;
    private int mPicLastVisibleItemPosition;
    private Intent m;

//    private List<Integer> loadedPosition = new ArrayList<>(); //已经加载过的页面

//    private String hospitalid;
//
//    @Override
//    public String getHospitalid() {
//        return hospitalid;
//    }
//
//    @Override
//    public void setHospitalid(String hospitalid) {
//        this.hospitalid = hospitalid;
//    }

    @Override
    public int getPageOne() {
        return pageOne;
    }

    @Override
    public int getPageTwo() {
        return pageTwo;
    }

    @Override
    public int getPageThree() {
        return pageThree;
    }

    @Override
    public int getCurrentPosition() {
        return currentPosition;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, null);
//        loadedPosition.clear();
        recommendList.clear();
        pageOne = 1;
        pageTwo = 1;
        pageThree = 1;
//        setCurrentPosition(0);
        initView(view);

        //检查权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkOtherPer();
            checkLocationPer();
        }

        return view;
    }

    private void initView(View view) {

//        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayout llLocation = view.findViewById(R.id.llLocation);
        LinearLayout llSearch = view.findViewById(R.id.llSearch);

        tvLocation = view.findViewById(R.id.tvLocation);
        ImageView ivnews = view.findViewById(R.id.ivnews);

        llLocation.setOnClickListener(this);
        llSearch.setOnClickListener(this);
        ivnews.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_home_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.addOnItemTouchListener(new OnItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                if (position == 0)
                    return;

                Logger.d("position==：" + position);

                switch (homeAdapter.getItemViewType(position)) {

                    case HomeAdapter.TYPE_ARTICLE://文章
                        position--;
                        m = new Intent();
                        m.setClass(getActivity(), ArtDetailAct.class);
                        m.putExtra("id", recommendList.get(position).getId());
                        startActivity(m);

                        break;

                    case HomeAdapter.TYPE_ASK://问答
                        position--;
                        m = new Intent();
//                        m.setClass(getActivity(), AnswerDetailAct.class);
                        m.setClass(getActivity(), InquiryDetailAct.class);
                        m.putExtra("noedit", true);//不能回复，定时刷新
                        m.putExtra("consultaid", recommendList.get(position).getConsultaid());
                        startActivity(m);
                        break;

                    case HomeAdapter.TYPE_VIDEO://专家讲堂
                        position--;

                        //type; //类型   视频(1),文章(2),问答(3);
                        if ("1".equals(recommendList.get(position).getType())) {
                            //视频 专家讲堂
                            m = new Intent();
                            m.setClass(getActivity(), VideoSpeechActivity.class);
//                            m.putExtra("type", "3");//1直播 3视频
                            m.putExtra("id", recommendList.get(position).getId());
                            startActivity(m);
                            break;
                        } else if ("2".equals(recommendList.get(position).getType())) {
                            //文章
                            m = new Intent();
                            m.setClass(getActivity(), ArtDetailAct.class);
                            m.putExtra("id", recommendList.get(position).getId());
                            startActivity(m);
                        }

                }

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        homeAdapter = new HomeAdapter(this, getActivity(), recommendList);
        recyclerView.setAdapter(homeAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof LinearLayoutManager) {
                    mPicLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    mPicFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }

                if (homeAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && mPicLastVisibleItemPosition + 1 == homeAdapter.getItemCount() && mPicFirstVisibleItemPosition > 0) {

                    switch (currentPosition) {

                        case 0:
                            pageOne++;
                            break;

                        case 1:
                            pageTwo++;
                            break;

                        case 2:
                            pageThree++;
                            break;

                    }
                    iOnePrenenter.getDataInfo((String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""), false);

                }

            }

        });

    }

    /**
     * 检查定位权限， 获取定位信息
     */
    private void checkLocationPer() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                ) {
            startLocation();
        } else {
            //不具有定位权限，需要进行权限申请
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION_CODE);
        }

    }

//    /**
//     * 检查定位权限， 获取定位信息
//     */
//    private void checkOtherPer() {
//
//        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//
//        } else {
//            //不具有定位权限，需要进行权限申请
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE
//                            , Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}
//                    , REQUEST_PERMISSION_OTHER_CODE);
//        }
//
//    }

    public void startLocation() {
        iOnePrenenter.startLocation(getActivity());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.llLocation://切换位置

                nextActivityWithCode(LocationActivity.class, CODE_CHOOSE_LOCATION);

                break;

            case R.id.llSearch://搜索

                nextActivity(SearchingAcivity.class);

                break;

            case R.id.ivnews://消息

                nextActivity(MyNewsListActivity.class);

                break;

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case CODE_CHOOSE_LOCATION:

                if (resultCode == CODE_CHOOSE_LOCATION_RESULT && data != null) {
                    setLocationInfo(data);
                }

                break;

        }
    }

    //设置位置信息
    private void setLocationInfo(Intent data) {

        String cityName = data.getStringExtra("cityName");

        tvLocation.setText(cityName);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//log日志
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)//错误重连
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
//                    .addNetworkInterceptor()//网络拦截器
                .build();

        //Retrofit配置
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.WEB_ROOT_ADDRESS)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        service.recodeAddress(cityName, "json", "sxsXsPeoTyb4PoYoe1vVmuqbx6iGM4yL", cityName)
                .compose(schedulersTransformer())
                .subscribe(new BaseNoTObserver<AddBean>() {
                    @Override
                    public void onHandleSuccess(AddBean addBean) {

                        if (addBean == null || !"OK".equals(addBean.getStatus())
                                || addBean.getResult() == null || addBean.getResult().getLocation() == null) {
                            return;
                        }

                        AddBean.ResultBean.LocationBean location = addBean.getResult().getLocation();
                        SaveUtils.put(getActivity(), SpValue.CITY, cityName);
                        SaveUtils.put(getActivity(), SpValue.LAT, location.getLat() + "");
                        SaveUtils.put(getActivity(), SpValue.LON, location.getLng() + "");


                        //重新加载数据
                        iOnePrenenter.getHospital(location.getLat() + "", location.getLng() + "", true);


                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                    }

                });
    }


    /**
     * 转换器
     *
     * @return
     */
    private ObservableTransformer schedulersTransformer() {

        return new ObservableTransformer() {

            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

    }


    @Override
    public void setBannerInfo(List<BannerBean.DataBean> bannerList) {

        Logger.d("设置banner图");
        //设置banner图
        if (homeAdapter != null) {
            homeAdapter.setBannerInfo(bannerList);
            homeAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setRecommand(RecommendEntry recommendEntry, int page) {

        if (recommendEntry == null || recommendEntry.getData() == null)
            return;

        if (page == 1)
            recommendList.clear();
        recommendList.addAll(recommendEntry.getData());

        if (homeAdapter != null)
            homeAdapter.notifyDataSetChanged();

    }

    @Override
    public void setCity(String city) {
        if (!TextUtils.isEmpty(city))
            tvLocation.setText(city);
    }


    @Override
    public void stopRefush() {
//        swipeRefreshLayout.setRefreshing(false);
    }

//    @Override
//    public void onRefresh() {
//
//        recommendList.clear();
//
//        switch (currentPosition) {
//
//            case 0://综合推荐
//
//                iOnePrenenter.getLastInfo();
//
//                break;
//
//            case 1://精彩问诊
//                break;
//
//            case 2://专家讲堂
//                break;
//
//        }
//
//    }

    public void setCurrentPosition(int position) {

        currentPosition = position;

        pageOne = 1;
        pageTwo = 1;
        pageThree = 1;
//        if (!loadedPosition.contains(position)) {

        if (TextUtils.isEmpty((String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, "")))
            iOnePrenenter.getLastInfo();
        else
            iOnePrenenter.getDataInfo((String) SaveUtils.get(getActivity(), SpValue.HOSPITAL_ID, ""), false);

//        }
//        loadedPosition.add(position);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Logger.d("======onDestroyView=======");

        currentPosition = 0;

        pageOne = 1;
        pageTwo = 1;
        pageThree = 1;

    }
}
