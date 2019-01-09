package com.ylean.cf_hospitalapp.login.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2018/12/25.
 */

public class RegisterResultEntry extends Basebean {

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

    public RegisterResultEntry() {
    }

    protected RegisterResultEntry(Parcel in) {
        super(in);
        this.data = in.readString();
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<RegisterResultEntry> CREATOR = new Creator<RegisterResultEntry>() {
        @Override
        public RegisterResultEntry createFromParcel(Parcel source) {
            return new RegisterResultEntry(source);
        }

        @Override
        public RegisterResultEntry[] newArray(int size) {
            return new RegisterResultEntry[size];
        }
    };
}
