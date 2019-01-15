package com.ylean.cf_hospitalapp.my.view;

import com.ylean.cf_hospitalapp.base.view.BaseView;
import com.ylean.cf_hospitalapp.my.bean.UnreadMsgEntry;

/**
 * 未读消息的数量
 * Created by linaidao on 2019/1/15.
 */

public interface IReadMsgView extends BaseView {

    void setUnRead(UnreadMsgEntry.DataBean data);

}
