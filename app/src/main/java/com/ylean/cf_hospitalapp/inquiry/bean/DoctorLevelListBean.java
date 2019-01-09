package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * 医生等级列表
 */
public class DoctorLevelListBean extends Basebean {

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

        private boolean isSelect;
        private String doctitle;
        private String doctitleid;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

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
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
            dest.writeString(this.doctitle);
            dest.writeString(this.doctitleid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.isSelect = in.readByte() != 0;
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

    public DoctorLevelListBean() {
    }

    protected DoctorLevelListBean(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<DoctorLevelListBean> CREATOR = new Creator<DoctorLevelListBean>() {
        @Override
        public DoctorLevelListBean createFromParcel(Parcel source) {
            return new DoctorLevelListBean(source);
        }

        @Override
        public DoctorLevelListBean[] newArray(int size) {
            return new DoctorLevelListBean[size];
        }
    };
}
