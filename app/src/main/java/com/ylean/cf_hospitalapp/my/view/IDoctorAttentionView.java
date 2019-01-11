package com.ylean.cf_hospitalapp.my.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;

/**
 * Created by linaidao on 2019/1/11.
 */

public interface IDoctorAttentionView extends BaseView{

    void attentionSuccess();

    void attentionFaile();

    void removeAttentionSuccess();

    void removeAttentionFail();
}
