package com.ylean.cf_hospitalapp.inquiry.presenter;

import com.ylean.cf_hospitalapp.inquiry.bean.CommentDisBean;
import com.ylean.cf_hospitalapp.inquiry.view.IPayTWView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

public class IPayTWPresenter {

    private IPayTWView iPayTWView;

    public IPayTWPresenter(IPayTWView iPayTWView) {
        this.iPayTWView = iPayTWView;
    }

    public void getCommentSick() {

        RetrofitHttpUtil.getInstance()
                .getCommentDisList(new BaseNoTObserver<CommentDisBean>() {

                    @Override
                    public void onHandleSuccess(CommentDisBean doctorListEntry) {
                        iPayTWView.setCommentDis(doctorListEntry.getData());
                    }

                    @Override
                    public void onHandleError(String message) {
                        iPayTWView.showErr(message);
                    }

                }, SpValue.CH);

    }
}
