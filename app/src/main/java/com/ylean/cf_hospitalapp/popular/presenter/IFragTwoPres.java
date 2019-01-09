package com.ylean.cf_hospitalapp.popular.presenter;

import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.popular.bean.DiseaseListEntry;
import com.ylean.cf_hospitalapp.popular.bean.ExpertEntry;
import com.ylean.cf_hospitalapp.popular.view.IFragTwoView;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * Created by linaidao on 2019/1/7.
 */

public class IFragTwoPres {

    private IFragTwoView iFragTwoView;
    private int currentPage = 1;
    private String searchContent;

    public IFragTwoPres(IFragTwoView iFragTwoView) {
        this.iFragTwoView = iFragTwoView;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    //专家讲堂， 专家直播
    public void expertInfo(final String expertInfo, String hospitalId, final int currentPosition) {
        RetrofitHttpUtil
                .getInstance()
                .expertTeach(
                        new BaseNoTObserver<ExpertEntry>() {
                            @Override
                            public void onHandleSuccess(ExpertEntry expertEntry) {

                                iFragTwoView.stopRefush();

                                if (expertEntry == null || expertEntry.getData() == null)
//                                    if (expertEntry == null || expertEntry.getData() == null ||
//                                        expertEntry.getData().size() < 1)
                                    return;

                                switch (expertInfo) {

                                    case SpValue.EXPERT_VIDEO://专家直播

                                        iFragTwoView.setExpertVideoInfo(expertEntry.getData(), currentPosition);

                                        break;

                                    case SpValue.EXPERT_SPEECH://专家讲堂

                                        iFragTwoView.setExpertSpeechInfo(expertEntry.getData(), currentPosition);

                                        break;

                                }

                            }

                            @Override
                            public void onHandleError(String message) {
                                iFragTwoView.stopRefush();

                                iFragTwoView.showErr(message);
                            }

                        }, SpValue.CH, hospitalId, searchContent
                        , expertInfo, currentPage, SpValue.PAGE_SIZE);
    }


    //图文资讯
    public void picNewsList(String hospitalId) {

        RetrofitHttpUtil.getInstance()
                .picNewsList(
                        new BaseNoTObserver<ExpertEntry>() {
                            @Override
                            public void onHandleSuccess(ExpertEntry newsListEntry) {
                                iFragTwoView.stopRefush();

                                if (newsListEntry == null || newsListEntry.getData() == null)
                                    return;
//                                List<ExpertEntry.DataBean> data = newsListEntry.getData();
                                iFragTwoView.setNewsInfo(newsListEntry.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iFragTwoView.stopRefush();
                                iFragTwoView.showErr(message);
                            }

                        }, SpValue.CH, hospitalId, searchContent, "1", currentPage, SpValue.PAGE_SIZE);
    }


    //疾病百科
    public void diseaseList() {

        RetrofitHttpUtil.getInstance()
                .diseaseList(
                        new BaseNoTObserver<DiseaseListEntry>() {
                            @Override
                            public void onHandleSuccess(DiseaseListEntry diseaseListEntry) {
                                iFragTwoView.stopRefush();

                                if (diseaseListEntry==null || diseaseListEntry.getData() ==null)
                                    return;

                                iFragTwoView.setDiseaseInfo(diseaseListEntry.getData());
                            }

                            @Override
                            public void onHandleError(String message) {
                                iFragTwoView.stopRefush();
                                iFragTwoView.showErr(message);
                            }

                        }, SpValue.CH, searchContent, currentPage, SpValue.PAGE_SIZE);

    }
}
