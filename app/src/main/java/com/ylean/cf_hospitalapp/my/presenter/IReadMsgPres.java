package com.ylean.cf_hospitalapp.my.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.comm.view.IReplyView;
import com.ylean.cf_hospitalapp.my.bean.UnreadMsgEntry;
import com.ylean.cf_hospitalapp.my.view.IReadMsgView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * 我的未读消息个数
 * Created by linaidao on 2019/1/15.
 */

public class IReadMsgPres {

    private IReadMsgView iReadMsgView;

    public IReadMsgPres(IReadMsgView iReadMsgView) {
        this.iReadMsgView = iReadMsgView;
    }


    /**
     * 未读消息个数
     * @param token
     */
    public void unreadMsg(String token) {

        RetrofitHttpUtil.getInstance()
                .unreadMsg(new BaseNoTObserver<UnreadMsgEntry>() {
                    @Override
                    public void onHandleSuccess(UnreadMsgEntry basebean) {


                        if (basebean!=null && basebean.getData()!=null)

                            iReadMsgView.setUnRead(basebean.getData());

                    }

                    @Override
                    public void onHandleError(String message) {
                        iReadMsgView.showErr(message);
                    }

                }, SpValue.CH, token);


    }


}
