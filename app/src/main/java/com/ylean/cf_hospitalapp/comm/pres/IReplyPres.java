package com.ylean.cf_hospitalapp.comm.pres;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.comm.view.IReplyView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * 回复
 * Created by linaidao on 2019/1/13.
 */

public class IReplyPres {

    private IReplyView iReplyView;

    public IReplyPres(IReplyView iReplyView) {
        this.iReplyView = iReplyView;
    }

    private String token;
    private String id;

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(String id) {
        this.id = id;
    }

    //添加回复
    public void reply(String content) {

        RetrofitHttpUtil.getInstance()
                .reply(new BaseNoTObserver<Basebean>() {
                    @Override
                    public void onHandleSuccess(Basebean basebean) {

                        iReplyView.showErr("回复成功");
                        //回复成功
                        iReplyView.replySuccess();
                    }

                    @Override
                    public void onHandleError(String message) {
                        iReplyView.showErr(message);
                    }

                }, SpValue.CH, token, id, content);

    }

}
