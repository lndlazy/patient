package com.ylean.cf_hospitalapp.home.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

public class LoginEntry extends Basebean {

    private String data;
    private String startTime;
    private String token;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.data);
        dest.writeString(this.startTime);
        dest.writeString(this.token);
    }

    public LoginEntry() {
    }

    protected LoginEntry(Parcel in) {
        super(in);
        this.data = in.readString();
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<LoginEntry> CREATOR = new Creator<LoginEntry>() {
        @Override
        public LoginEntry createFromParcel(Parcel source) {
            return new LoginEntry(source);
        }

        @Override
        public LoginEntry[] newArray(int size) {
            return new LoginEntry[size];
        }
    };
}
