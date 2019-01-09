package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/8.
 */

public class AlipayEntry extends Basebean {

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

        private String appid;
        private String notifyurl;
        private String privatekey;
        private String sellerid;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNotifyurl() {
            return notifyurl;
        }

        public void setNotifyurl(String notifyurl) {
            this.notifyurl = notifyurl;
        }

        public String getPrivatekey() {
            return privatekey;
        }

        public void setPrivatekey(String privatekey) {
            this.privatekey = privatekey;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.appid);
            dest.writeString(this.notifyurl);
            dest.writeString(this.privatekey);
            dest.writeString(this.sellerid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.appid = in.readString();
            this.notifyurl = in.readString();
            this.privatekey = in.readString();
            this.sellerid = in.readString();
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
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.startTime);
        dest.writeString(this.token);
    }

    public AlipayEntry() {
    }

    protected AlipayEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<AlipayEntry> CREATOR = new Creator<AlipayEntry>() {
        @Override
        public AlipayEntry createFromParcel(Parcel source) {
            return new AlipayEntry(source);
        }

        @Override
        public AlipayEntry[] newArray(int size) {
            return new AlipayEntry[size];
        }
    };
}
