package com.ylean.cf_hospitalapp.comm.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/2/26.
 */

public class ShareTitleBean extends Basebean {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public ShareTitleBean() {
    }

    protected ShareTitleBean(Parcel in) {
        super(in);
        this.data = in.readString();
    }

    public static final Creator<ShareTitleBean> CREATOR = new Creator<ShareTitleBean>() {
        @Override
        public ShareTitleBean createFromParcel(Parcel source) {
            return new ShareTitleBean(source);
        }

        @Override
        public ShareTitleBean[] newArray(int size) {
            return new ShareTitleBean[size];
        }
    };
}
