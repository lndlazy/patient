package com.ylean.cf_hospitalapp.register.pres;

import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.register.adapter.NumRegisterAdapter;
import com.ylean.cf_hospitalapp.register.bean.DoctorTypeEntry;
import com.ylean.cf_hospitalapp.register.bean.NumListEntry;
import com.ylean.cf_hospitalapp.register.bean.TimeEntry;
import com.ylean.cf_hospitalapp.register.view.IChooseNumView;
import com.ylean.cf_hospitalapp.utils.IDateUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by linaidao on 2019/1/4.
 */

public class IChooseNumPres {

    private IChooseNumView iChooseNumView;
    private List<TimeEntry> timeEntryList = new ArrayList<>();
    private TimeEntry timeEntry;

    public TimeEntry getTimeEntry() {
        return timeEntry;
    }

    public void setTimeEntry(TimeEntry timeEntry) {
        this.timeEntry = timeEntry;
    }

    public IChooseNumPres(IChooseNumView iChooseNumView) {
        this.iChooseNumView = iChooseNumView;
    }

    // 初始化日期 获取最近几天的日期
    public void initRcentDate() {

        for (int i = 0; i < 6; i++) {

            TimeEntry timeEntry = new TimeEntry();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i);
            timeEntry.setWeekDesc(IDateUtils.getWeekDay(calendar));
            timeEntry.setDateDes(IDateUtils.formatCalTime(calendar));
            timeEntry.setTimeDesc(IDateUtils.formatCalYMD(calendar));
            timeEntryList.add(timeEntry);
        }

        iChooseNumView.fillDate();
    }

    public List<TimeEntry> getTimeEntryList() {
        return timeEntryList;
    }

    private String hospitalId;//医院id
    private String departId;//科室id
    private String menzhenid;//门诊id
    private String doctypeid;//医生类型
    private String doctorid;//医生id

    public String getDoctypeid() {
        return doctypeid;
    }

    public void setDoctypeid(String doctypeid) {
        this.doctypeid = doctypeid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getMenzhenid() {
        return menzhenid;
    }

    public void setMenzhenid(String menzhenid) {
        this.menzhenid = menzhenid;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    //查询号源
    public void getNumInfo(final String timeType, final NumRegisterAdapter mAdapter, final List<NumListEntry.DataBean> registerList) {

        RetrofitHttpUtil
                .getInstance()
                .orderNumList(new BaseNoTObserver<NumListEntry>() {

                                  @Override
                                  public void onSubscribe(Disposable d) {
                                      super.onSubscribe(d);

                                      if ("1".equals(timeType))
                                          iChooseNumView.showLoading("获取中...");
                                  }

                                  @Override
                                  public void onHandleSuccess(NumListEntry numListEntry) {

                                      if ("2".equals(timeType))
                                          iChooseNumView.hideLoading();

                                      if (numListEntry == null || numListEntry.getData() == null)
                                          return;

                                      if (registerList != null) {
                                          registerList.clear();
                                          registerList.addAll(numListEntry.getData());
                                      }

                                      if (mAdapter != null)
                                          mAdapter.notifyDataSetChanged();

                                      iChooseNumView.stopRefush();

                                  }

                                  @Override
                                  public void onHandleError(String message) {
                                      if ("2".equals(timeType))
                                          iChooseNumView.hideLoading();

                                      iChooseNumView.showErr(message);
                                      iChooseNumView.stopRefush();
                                  }

                              }, SpValue.CH  , hospitalId , departId , menzhenid
                        , timeEntry == null ? IDateUtils.formatDateTime(new Date(), IDateUtils.DF_YYYY_MM_DD) : timeEntry.getTimeDesc()
                        , timeType , doctypeid , doctorid);

    }

    //获取医生类型列表
    public void getHospitalType() {

        RetrofitHttpUtil
                .getInstance()
                .doctorTypeList(
                        new BaseNoTObserver<DoctorTypeEntry>() {
                            @Override
                            public void onHandleSuccess(DoctorTypeEntry doctorTypeEntry) {

                                if (doctorTypeEntry == null || doctorTypeEntry.getData() == null)
                                    return;

                                List<DoctorTypeEntry.DataBean> data = doctorTypeEntry.getData();

                                DoctorTypeEntry.DataBean dataBean = new DoctorTypeEntry.DataBean();
                                dataBean.setDoctitle("");
                                dataBean.setDoctitle("全部");
                                data.add(0, dataBean);
                                iChooseNumView.doctorTypeInfo(data);

                            }

                            @Override
                            public void onHandleError(String message) {
                                iChooseNumView.showErr(message);
                            }
                        }, SpValue.CH);

    }
}
