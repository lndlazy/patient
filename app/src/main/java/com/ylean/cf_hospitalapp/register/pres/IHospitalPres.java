package com.ylean.cf_hospitalapp.register.pres;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.inquiry.bean.GradeLevelBean;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalTypeEntry;
import com.ylean.cf_hospitalapp.register.view.IHospitalView;
import com.ylean.cf_hospitalapp.utils.SpValue;

/**
 * 医院列表p
 * Created by linaidao on 2019/1/3.
 */

public class IHospitalPres {

    private IHospitalView iHospitalView;

    public IHospitalPres(IHospitalView iHospitalView) {
        this.iHospitalView = iHospitalView;
    }

    private String type;//排序方式
    private String hospitalname;//医院名称
    private String hostype;//医院类型
    private String hosgrade;//医院等级

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getHostype() {
        return hostype;
    }

    public void setHostype(String hostype) {
        this.hostype = hostype;
    }

    public String getHosgrade() {
        return hosgrade;
    }

    public void setHosgrade(String hosgrade) {
        this.hosgrade = hosgrade;
    }

    public void hospitalList(String lat, String lon) {

        RetrofitHttpUtil.getInstance()
                .hospitalList(
                        new BaseNoTObserver<HospitalListEntry>() {
                            @Override
                            public void onHandleSuccess(HospitalListEntry hospitalEntry) {

                                if (hospitalEntry != null && hospitalEntry.getData() != null)
                                    iHospitalView.setHospitailList(hospitalEntry.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iHospitalView.showErr(message);
                            }

                        }, SpValue.CH, type, hospitalname, lon
                        , lat, hostype, hosgrade);

    }

    //医院类型列表
    public void hospitalTypeList() {

        RetrofitHttpUtil
                .getInstance()
                .hospitalTypeList(
                        new BaseNoTObserver<HospitalTypeEntry>() {
                            @Override
                            public void onHandleSuccess(HospitalTypeEntry hospitalTypeEntry) {

                                if (hospitalTypeEntry != null && hospitalTypeEntry.getData() != null)
                                    iHospitalView.setHospitalTypeInfo(hospitalTypeEntry.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iHospitalView.showErr(message);
                                Logger.d("医院类型列表错误++" + message);
                            }

                        }, SpValue.CH);

    }

    //医院等级列表
    public void hospitalLevelList() {

        RetrofitHttpUtil
                .getInstance()
                .getHosGradeLevelList(
                        new BaseNoTObserver<GradeLevelBean>() {
                            @Override
                            public void onHandleSuccess(GradeLevelBean gradeLevelBean) {

                                if (gradeLevelBean != null && gradeLevelBean.getData() != null)
                                    iHospitalView.setGradeLevel(gradeLevelBean.getData());

                            }

                            @Override
                            public void onHandleError(String message) {
                                iHospitalView.showErr(message);
                                Logger.d("医院等级列表错误++" + message);
                            }

                        }, SpValue.CH);

    }
}
