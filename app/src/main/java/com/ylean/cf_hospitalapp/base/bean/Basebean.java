package com.ylean.cf_hospitalapp.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Basebean implements Parcelable {

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Basebean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.desc);
    }

    protected Basebean(Parcel in) {
        this.code = in.readInt();
        this.desc = in.readString();
    }

}
