package com.ylean.cf_hospitalapp.register.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by linaidao on 2019/1/4.
 */

public class TimeEntry implements Parcelable {

    private String weekDesc;//周二
    private String dateDes;//01-01
    private String timeDesc;//2019-01-01

    public String getWeekDesc() {
        return weekDesc;
    }

    public void setWeekDesc(String weekDesc) {
        this.weekDesc = weekDesc;
    }

    public String getDateDes() {
        return dateDes;
    }

    public void setDateDes(String dateDes) {
        this.dateDes = dateDes;
    }

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.weekDesc);
        dest.writeString(this.dateDes);
        dest.writeString(this.timeDesc);
    }

    public TimeEntry() {
    }

    protected TimeEntry(Parcel in) {
        this.weekDesc = in.readString();
        this.dateDes = in.readString();
        this.timeDesc = in.readString();
    }

    public static final Parcelable.Creator<TimeEntry> CREATOR = new Parcelable.Creator<TimeEntry>() {
        @Override
        public TimeEntry createFromParcel(Parcel source) {
            return new TimeEntry(source);
        }

        @Override
        public TimeEntry[] newArray(int size) {
            return new TimeEntry[size];
        }
    };
}
