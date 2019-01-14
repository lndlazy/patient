package com.ylean.cf_hospitalapp.my.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.my.DelCollectType;
import com.ylean.cf_hospitalapp.my.adapter.DoctorListAdapter;
import com.ylean.cf_hospitalapp.my.bean.MyDoctorListEntry;
import com.ylean.cf_hospitalapp.my.view.IDoctorListView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * Created by linaidao on 2019/1/6.
 */

public class IDoctorListPres {

    private IDoctorListView iDoctorListView;

    public IDoctorListPres(IDoctorListView iDoctorListView) {
        this.iDoctorListView = iDoctorListView;
    }

    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void getDoctorList(int type, String token, final boolean isRefush) {

        switch (type) {

            case 1:

                RetrofitHttpUtil
                        .getInstance()
                        .askDoctorList(
                                new BaseNoTObserver<MyDoctorListEntry>() {
                                    @Override
                                    public void onHandleSuccess(MyDoctorListEntry myDoctorListEntry) {

                                        if (myDoctorListEntry == null || myDoctorListEntry.getData() == null)
                                            return;

                                        iDoctorListView.setDoctorListInfo(myDoctorListEntry.getData(), isRefush);

                                    }

                                    @Override
                                    public void onHandleError(String message) {
                                        iDoctorListView.showErr(message);
                                    }

                                }, SpValue.CH, token, page, SpValue.PAGE_SIZE);

                break;
            case 2:

                RetrofitHttpUtil
                        .getInstance()
                        .attentionDoctorList(
                                new BaseNoTObserver<MyDoctorListEntry>() {
                                    @Override
                                    public void onHandleSuccess(MyDoctorListEntry myDoctorListEntry) {
                                        if (myDoctorListEntry == null || myDoctorListEntry.getData() == null)
                                            return;

                                        iDoctorListView.setDoctorListInfo(myDoctorListEntry.getData(), isRefush);

                                    }

                                    @Override
                                    public void onHandleError(String message) {
                                        iDoctorListView.showErr(message);
                                    }

                                }, SpValue.CH, token, page, SpValue.PAGE_SIZE);

                break;

        }

    }

    //关注医生
    public void attentionDoctor(String doctorid, String token, final DoctorListAdapter doctorListAdapter, final int position) {

        RetrofitHttpUtil
                .getInstance()
                .addCollection(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                doctorListAdapter.refush(position, "1");
                                iDoctorListView.showErr("关注成功");
                            }

                            @Override
                            public void onHandleError(String message) {
                                iDoctorListView.showErr(message);
                            }

                        }, SpValue.CH, token, doctorid, "5");

    }

    //取消关注医生
    public void noAttentionDoctor(String doctorid, String token, final DoctorListAdapter doctorListAdapter, final int position) {
        RetrofitHttpUtil
                .getInstance()
                .removeCollection(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                doctorListAdapter.refush(position, "0");
                                iDoctorListView.showErr("取消成功");
                            }

                            @Override
                            public void onHandleError(String message) {
                                iDoctorListView.showErr(message);
                            }

                        }, SpValue.CH, token, doctorid, "5");
    }

}
