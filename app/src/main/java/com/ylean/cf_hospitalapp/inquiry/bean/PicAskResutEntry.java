package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2018/12/18.
 */

public class PicAskResutEntry extends Basebean {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public PicAskResutEntry() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.data);
    }

    protected PicAskResutEntry(Parcel in) {
        super(in);
        this.data = in.readString();
    }

    public static final Creator<PicAskResutEntry> CREATOR = new Creator<PicAskResutEntry>() {
        @Override
        public PicAskResutEntry createFromParcel(Parcel source) {
            return new PicAskResutEntry(source);
        }

        @Override
        public PicAskResutEntry[] newArray(int size) {
            return new PicAskResutEntry[size];
        }
    };
}
