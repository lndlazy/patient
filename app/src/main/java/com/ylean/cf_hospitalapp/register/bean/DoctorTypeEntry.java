package com.ylean.cf_hospitalapp.register.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/4.
 */

public class DoctorTypeEntry extends Basebean {

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

        private String doctitle;
        private String doctitleid;

        public String getDoctitle() {
            return doctitle;
        }

        public void setDoctitle(String doctitle) {
            this.doctitle = doctitle;
        }

        public String getDoctitleid() {
            return doctitleid;
        }

        public void setDoctitleid(String doctitleid) {
            this.doctitleid = doctitleid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.doctitle);
            dest.writeString(this.doctitleid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.doctitle = in.readString();
            this.doctitleid = in.readString();
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

    public DoctorTypeEntry() {
    }

    protected DoctorTypeEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<DoctorTypeEntry> CREATOR = new Creator<DoctorTypeEntry>() {
        @Override
        public DoctorTypeEntry createFromParcel(Parcel source) {
            return new DoctorTypeEntry(source);
        }

        @Override
        public DoctorTypeEntry[] newArray(int size) {
            return new DoctorTypeEntry[size];
        }
    };
}
