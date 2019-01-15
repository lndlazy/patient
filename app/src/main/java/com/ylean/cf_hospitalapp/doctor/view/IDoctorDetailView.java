package com.ylean.cf_hospitalapp.doctor.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.InquiryListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.VideoListEntry;
import com.ylean.cf_hospitalapp.my.bean.DoctorDetailEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/11.
 */

public interface IDoctorDetailView extends BaseView{

    void setDoctorInfo(DoctorDetailEntry.DataBean data);

    void removeAttention(String doctorid);

    void addAttention(String doctorid);

    void setPicPrice(double price);

    void setTelPrice(double price);

    void setVideoPrice(double price);

    void setRegisterPrice(double price, String outdepartid);

    void setInquiryData(List<InquiryListEntry.DataBean> data);

    void setVideoData(List<VideoListEntry.DataBean> data);

    void setEvaluateData(List<CommComListEntry.DataBean> data);

    void stopInquiryRefush();

    void stopVideoSpeechRefush();
}
