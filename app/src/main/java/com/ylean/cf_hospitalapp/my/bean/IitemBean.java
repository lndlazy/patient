package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class IitemBean implements Parcelable {

    private String name;
    private int resId;

    public IitemBean(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.resId);
    }

    public IitemBean() {
    }

    protected IitemBean(Parcel in) {
        this.name = in.readString();
        this.resId = in.readInt();
    }

    public static final Parcelable.Creator<IitemBean> CREATOR = new Parcelable.Creator<IitemBean>() {
        @Override
        public IitemBean createFromParcel(Parcel source) {
            return new IitemBean(source);
        }

        @Override
        public IitemBean[] newArray(int size) {
            return new IitemBean[size];
        }
    };
}
