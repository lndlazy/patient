package com.ylean.cf_hospitalapp.hospital.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * 医院全部科室
 * Created by linaidao on 2019/1/13.
 */

public class HospDepartListEntry extends Basebean {


    /**
     * data : [{"departmentid":37,"name":"血管瘤科"},{"departmentid":84,"name":"胸外科"},{"departmentid":85,"name":"泌尿外科"},{"departmentid":86,"name":"心脏外科"},{"departmentid":87,"name":"乳腺外科"},{"departmentid":88,"name":"肝胆外科"},{"departmentid":89,"name":"血管外科"},{"departmentid":90,"name":"器官移植"},{"departmentid":91,"name":"烧伤科"},{"departmentid":46,"name":"心血管科"},{"departmentid":105,"name":"心血管内科"},{"departmentid":106,"name":"心血管外科"},{"departmentid":48,"name":"肿瘤科"},{"departmentid":107,"name":"肿瘤综合科"},{"departmentid":108,"name":"肿瘤内科"},{"departmentid":83,"name":"功能神经外科"},{"departmentid":82,"name":"神经外科"},{"departmentid":81,"name":"普通外科"},{"departmentid":69,"name":"血管瘤科"},{"departmentid":70,"name":"脉管畸形"},{"departmentid":71,"name":"胎记"},{"departmentid":38,"name":"内科"},{"departmentid":72,"name":"综合内科"},{"departmentid":73,"name":"呼吸内科"},{"departmentid":74,"name":"消化内科"},{"departmentid":75,"name":"神经内科"},{"departmentid":76,"name":"肾内科"},{"departmentid":77,"name":"风湿免疫科"},{"departmentid":78,"name":"内分泌科"},{"departmentid":79,"name":"感染内科"},{"departmentid":80,"name":"变态反应科"},{"departmentid":39,"name":"外科"},{"departmentid":109,"name":"肿瘤外科"}]
     * startTime : 2019-01-13 11:35:25
     * token :
     */

    private String startTime;
    private String token;
    private List<DataBean> data;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {
        /**
         * departmentid : 37
         * name : 血管瘤科
         */

        private String departmentid;
        private String name;

        public String getDepartmentid() {
            return departmentid;
        }

        public void setDepartmentid(String departmentid) {
            this.departmentid = departmentid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.departmentid);
            dest.writeString(this.name);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.departmentid = in.readString();
            this.name = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.startTime);
        dest.writeString(this.token);
        dest.writeTypedList(this.data);
    }

    public HospDepartListEntry() {
    }

    protected HospDepartListEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<HospDepartListEntry> CREATOR = new Creator<HospDepartListEntry>() {
        @Override
        public HospDepartListEntry createFromParcel(Parcel source) {
            return new HospDepartListEntry(source);
        }

        @Override
        public HospDepartListEntry[] newArray(int size) {
            return new HospDepartListEntry[size];
        }
    };
}
