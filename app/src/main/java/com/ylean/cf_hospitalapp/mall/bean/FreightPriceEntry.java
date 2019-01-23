package com.ylean.cf_hospitalapp.mall.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/21.
 */

public class FreightPriceEntry extends Basebean {

    /**
     * data : {"basefreight":0.01,"byfreight":0.2}
     * startTime : 2019-01-21 10:42:20
     * token :
     */

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
        /**
         * basefreight : 0.01
         * byfreight : 0.2
         */

        private double basefreight;
        private double byfreight;

        public double getBasefreight() {
            return basefreight;
        }

        public void setBasefreight(double basefreight) {
            this.basefreight = basefreight;
        }

        public double getByfreight() {
            return byfreight;
        }

        public void setByfreight(double byfreight) {
            this.byfreight = byfreight;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.basefreight);
            dest.writeDouble(this.byfreight);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.basefreight = in.readDouble();
            this.byfreight = in.readDouble();
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

    public FreightPriceEntry() {
    }

    protected FreightPriceEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<FreightPriceEntry> CREATOR = new Creator<FreightPriceEntry>() {
        @Override
        public FreightPriceEntry createFromParcel(Parcel source) {
            return new FreightPriceEntry(source);
        }

        @Override
        public FreightPriceEntry[] newArray(int size) {
            return new FreightPriceEntry[size];
        }
    };
}
