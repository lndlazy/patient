package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2018/12/19.
 */

public class DataUploadResultEntry extends Basebean {

    private String startTime;
    private String token;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
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
        dest.writeStringList(this.data);
    }

    public DataUploadResultEntry() {
    }

    protected DataUploadResultEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createStringArrayList();
    }

    public static final Creator<DataUploadResultEntry> CREATOR = new Creator<DataUploadResultEntry>() {
        @Override
        public DataUploadResultEntry createFromParcel(Parcel source) {
            return new DataUploadResultEntry(source);
        }

        @Override
        public DataUploadResultEntry[] newArray(int size) {
            return new DataUploadResultEntry[size];
        }
    };
}
