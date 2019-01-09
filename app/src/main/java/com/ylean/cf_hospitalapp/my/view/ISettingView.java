package com.ylean.cf_hospitalapp.my.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.my.bean.BindEntry;

/**
 * Created by linaidao on 2019/1/2.
 */

public interface ISettingView extends TakePhotoView {

    void setImg(String imgUrl);

    void setName(String name);

    void setBirthday(String birthday);

    void setSex(String eivSex);

    void setLocation(String location);

    void setAddress(String address);

    void bindInfo(BindEntry.DataBean data);
}
