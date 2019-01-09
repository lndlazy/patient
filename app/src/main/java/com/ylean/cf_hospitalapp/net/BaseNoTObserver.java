package com.ylean.cf_hospitalapp.net;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.base.bean.Basebean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseNoTObserver<T extends Basebean> implements Observer<T> {

    @Override
    public void onNext(T t) {

//        if (t.getCode() == Constant.ERR0R_RELOGIN)
////            onReLogin();
//            onReLogin(t.getCode_msg());
//        else
        if (t.getCode() == 0)
            onHandleSuccess(t);
        else {
            onHandleError(t.getDesc());
            Logger.d("错误提示::" + t.getDesc());
        }
    }

    @Override
    public void onError(Throwable e) {
        Logger.d("  ******  onError  ******  ");
        onHandleError("网络错误 : " + e.getMessage());
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public abstract void onHandleSuccess(T t);

    public abstract void onHandleError(String message);

//    public abstract void onReLogin(String message);

}