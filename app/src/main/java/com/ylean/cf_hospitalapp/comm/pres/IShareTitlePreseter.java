package com.ylean.cf_hospitalapp.comm.pres;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.comm.bean.ShareTitleBean;
import com.ylean.cf_hospitalapp.comm.view.IShareTitleView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;

/**
 * 根据id查询分享的标题
 * Created by linaidao on 2019/2/26.
 */

public class IShareTitlePreseter {

    private IShareTitleView iShareTitleView;

    public IShareTitlePreseter(IShareTitleView iShareTitleView) {
        this.iShareTitleView = iShareTitleView;
    }

    public void getShareTileByid(String id) {

        RetrofitHttpUtil.getInstance().getShareTileByid(
                new BaseNoTObserver<ShareTitleBean>() {
                    @Override
                    public void onHandleSuccess(ShareTitleBean basebean) {
                        iShareTitleView.setShareTitle(basebean.getData());
                    }

                    @Override
                    public void onHandleError(String message) {
                        iShareTitleView.showErr(message);
                    }

                }, id);

    }

}
