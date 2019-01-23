package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/22.
 */

public class InviteListEntry extends Basebean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {

        private String patientid;
        private String name;
        private String imgurl;
        private String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPatientid() {
            return patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.patientid);
            dest.writeString(this.name);
            dest.writeString(this.imgurl);
            dest.writeString(this.phone);
        }

        protected DataBean(Parcel in) {
            this.patientid = in.readString();
            this.name = in.readString();
            this.imgurl = in.readString();
            this.phone = in.readString();
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

    public InviteListEntry() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.data);
    }

    protected InviteListEntry(Parcel in) {
        super(in);
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<InviteListEntry> CREATOR = new Creator<InviteListEntry>() {
        @Override
        public InviteListEntry createFromParcel(Parcel source) {
            return new InviteListEntry(source);
        }

        @Override
        public InviteListEntry[] newArray(int size) {
            return new InviteListEntry[size];
        }
    };
}
