package com.ylean.cf_hospitalapp.home.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.home.bean.SearchListEntry;
import com.ylean.cf_hospitalapp.home.view.ISearchView;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by linaidao on 2019/1/10.
 */

public class ISearchPres {

    private ISearchView iSearchView;

    public ISearchPres(ISearchView iSearchView) {
        this.iSearchView = iSearchView;
    }

    private String token;
    private String name;
    private String hospitalId;
    private int page;

    private int searchType;

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public void startSearch() {

        switch (searchType) {

            case 0://医院
                searchHospital();
                break;

            case 1://医生
                searchDoctor();
                break;

            case 2://科室
                break;

            case 3://文章
                searchArticle();
                break;

            case 4://问诊
                searchInquiry();
                break;

        }

    }

    //搜索医院列表
    private void searchHospital() {

        RetrofitHttpUtil.getInstance()
                .searchHospital(new BaseNoTObserver<SearchListEntry>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        iSearchView.showLoading("搜索中...");
                    }

                    @Override
                    public void onHandleSuccess(SearchListEntry basebean) {
                        iSearchView.hideLoading();

                        if (basebean == null || basebean.getData() == null)
                            return;

                        for (int i = 0; i < basebean.getData().size(); i++) {
                            basebean.getData().get(i).setSearchType(0);
                        }

                        iSearchView.setSearchInfo(basebean.getData());
                        iSearchView.setSearchInfo(basebean);
                    }

                    @Override
                    public void onHandleError(String message) {
                        iSearchView.showErr(message);
                        iSearchView.hideLoading();

                    }
                }, SpValue.CH, name, page, SpValue.PAGE_SIZE);

    }


    //搜索文章列表
    private void searchArticle() {

        RetrofitHttpUtil.getInstance()
                .searchArticle(new BaseNoTObserver<SearchListEntry>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        iSearchView.showLoading("搜索中...");
                    }

                    @Override
                    public void onHandleSuccess(SearchListEntry basebean) {
                        iSearchView.hideLoading();
                        if (basebean == null || basebean.getData() == null)
                            return;

                        for (int i = 0; i < basebean.getData().size(); i++) {
                            basebean.getData().get(i).setSearchType(3);
                        }

                        iSearchView.setSearchInfo(basebean.getData());
                        iSearchView.setSearchInfo(basebean);
                    }

                    @Override
                    public void onHandleError(String message) {
                        iSearchView.showErr(message);
                        iSearchView.hideLoading();

                    }
                }, SpValue.CH, name, hospitalId, page, SpValue.PAGE_SIZE);
    }


    //搜索问诊列表
    private void searchInquiry() {

        RetrofitHttpUtil.getInstance()
                .searchInquiry(new BaseNoTObserver<SearchListEntry>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        iSearchView.showLoading("搜索中...");
                    }

                    @Override
                    public void onHandleSuccess(SearchListEntry basebean) {
                        iSearchView.hideLoading();
                        if (basebean == null || basebean.getData() == null)
                            return;

                        for (int i = 0; i < basebean.getData().size(); i++) {
                            basebean.getData().get(i).setSearchType(4);
                        }

                        iSearchView.setSearchInfo(basebean.getData());
                        iSearchView.setSearchInfo(basebean);
                    }

                    @Override
                    public void onHandleError(String message) {
                        iSearchView.showErr(message);
                        iSearchView.hideLoading();

                    }
                }, SpValue.CH, name, hospitalId, page, SpValue.PAGE_SIZE);
    }


    //搜索医生列表
    private void searchDoctor() {

        RetrofitHttpUtil.getInstance()
                .searchDoctor(new BaseNoTObserver<SearchListEntry>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        iSearchView.showLoading("搜索中...");
                    }

                    @Override
                    public void onHandleSuccess(SearchListEntry basebean) {
                        iSearchView.hideLoading();
                        if (basebean == null || basebean.getData() == null)
                            return;

                        for (int i = 0; i < basebean.getData().size(); i++) {
                            basebean.getData().get(i).setSearchType(1);
                        }

                        iSearchView.setSearchInfo(basebean.getData());
                        iSearchView.setSearchInfo(basebean);
                    }

                    @Override
                    public void onHandleError(String message) {
                        iSearchView.showErr(message);
                        iSearchView.hideLoading();

                    }
                }, SpValue.CH, name, hospitalId, page, SpValue.PAGE_SIZE);
    }

}
