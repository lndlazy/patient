package com.ylean.cf_hospitalapp.home.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.home.bean.BannerBean;
import com.ylean.cf_hospitalapp.inquiry.bean.RecommendEntry;

import java.util.List;

public interface IFragmentOneView extends BaseView {

    void setBannerInfo(List<BannerBean.DataBean> bannerList);

    void setRecommand(RecommendEntry recommendEntry, int page);

    void setCity(String city);

    void stopRefush();

    int getPageOne();

    int getPageTwo();

    int getPageThree();

    int getCurrentPosition();


//    String getHospitalid();
//
//    void setHospitalid(String hospitalid);

}
