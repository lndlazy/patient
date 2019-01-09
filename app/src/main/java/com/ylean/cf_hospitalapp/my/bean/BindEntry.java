package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/6.
 */

public class BindEntry extends Basebean {

    private DataBean data;
    private String startTime;
    private String token;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean implements android.os.Parcelable {

        private int qqstatus;
        private int wbstatus;
        private int wxstatus;

        public int getQqstatus() {
            return qqstatus;
        }

        public void setQqstatus(int qqstatus) {
            this.qqstatus = qqstatus;
        }

        public int getWbstatus() {
            return wbstatus;
        }

        public void setWbstatus(int wbstatus) {
            this.wbstatus = wbstatus;
        }

        public int getWxstatus() {
            return wxstatus;
        }

        public void setWxstatus(int wxstatus) {
            this.wxstatus = wxstatus;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.qqstatus);
            dest.writeInt(this.wbstatus);
            dest.writeInt(this.wxstatus);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.qqstatus = in.readInt();
            this.wbstatus = in.readInt();
            this.wxstatus = in.readInt();
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

    public BindEntry() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.startTime);
        dest.writeString(this.token);
    }

    protected BindEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<BindEntry> CREATOR = new Creator<BindEntry>() {
        @Override
        public BindEntry createFromParcel(Parcel source) {
            return new BindEntry(source);
        }

        @Override
        public BindEntry[] newArray(int size) {
            return new BindEntry[size];
        }
    };
}
