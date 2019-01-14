package com.ylean.cf_hospitalapp.comm.pres;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.comm.view.IGoodView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;

/**
 * 点赞
 * Created by linaidao on 2019/1/12.
 */

public class IGoodPres {

    private IGoodView iGoodView;

    public IGoodPres(IGoodView iGoodView) {
        this.iGoodView = iGoodView;
    }

    private String token;
    private String type;
    private String relateid;

    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRelateid(String relateid) {
        this.relateid = relateid;
    }

    /*
        id  收藏关联id（直播id，资讯id…）
        type 资讯(1),文章(2),直播(3),讲堂(4),帖子(5),评论(6);
     */
    //点赞
    public void good() {

        RetrofitHttpUtil.getInstance()
                .good(new BaseNoTObserver<Basebean>() {
                    @Override
                    public void onHandleSuccess(Basebean basebean) {

                        iGoodView.goodSuccess();
                    }

                    @Override
                    public void onHandleError(String message) {
                        iGoodView.showErr(message);
                    }

                }, token, type, relateid);
    }

    //取消点赞
    public void removeGood() {

        RetrofitHttpUtil.getInstance()
                .removeGood(new BaseNoTObserver<Basebean>() {
                    @Override
                    public void onHandleSuccess(Basebean basebean) {
                        iGoodView.removeSuccess();
                    }

                    @Override
                    public void onHandleError(String message) {
                        iGoodView.showErr(message);
                    }

                }, token, type, relateid);

    }

}

