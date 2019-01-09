package com.ylean.cf_hospitalapp.popular.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.popular.bean.DiseaseListEntry;
import com.ylean.cf_hospitalapp.popular.bean.ExpertEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/7.
 */

public interface IFragTwoView extends BaseView{


    void stopRefush();

    void setExpertVideoInfo(List<ExpertEntry.DataBean> data, int currentPosition);

    void setExpertSpeechInfo(List<ExpertEntry.DataBean> data, int currentPosition);

    void setNewsInfo(List<ExpertEntry.DataBean> data);

    void setDiseaseInfo(List<DiseaseListEntry.DataBean> data);
}
