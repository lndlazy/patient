package com.ylean.cf_hospitalapp.base.Presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.ICollectionView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * 收藏pers
 * Created by linaidao on 2019/1/9.
 */

public class ICollectionPres {

    private ICollectionView iCollectionView;

    public ICollectionPres(ICollectionView iCollectionView) {
        this.iCollectionView = iCollectionView;
    }

    private String id;
    //直播(1),资讯(2),讲堂(3),帖子(4),医生(5),病友(6),文章(7),商品(8)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //添加收藏  收藏关联id（直播id，资讯id…）   type 收藏类型  1-直播 2-资讯 3-讲堂 4-帖子 5-医生


    //////直播(1),资讯(2),讲堂(3),帖子(4),医生(5),病友(6),文章(7),商品(8)
    public void addCollection(String token) {

        RetrofitHttpUtil.getInstance()
                .addCollection(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                                iCollectionView.collectionSuccess();
                            }

                            @Override
                            public void onHandleError(String message) {

                                iCollectionView.showErr(message);
                            }

                        }, SpValue.CH, token, id, type);

    }

    //取消收藏
    public void removeCollection(String token) {

        RetrofitHttpUtil.getInstance()
                .removeCollection(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                iCollectionView.removeCollectionSuccess();
                            }

                            @Override
                            public void onHandleError(String message) {

                                iCollectionView.showErr(message);
                            }

                        }, SpValue.CH, token, id, type);

    }

}
