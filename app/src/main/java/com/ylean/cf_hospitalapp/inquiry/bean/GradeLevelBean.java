package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

//医院等级
public class GradeLevelBean extends Basebean {

    private String startTime;
    private String token;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {

        private String createtime;
        private String createtimestr;
        private String deltime;
        private String id;
        private String isdel;
        private String name;
        private boolean isSelect;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getCreatetimestr() {
            return createtimestr;
        }

        public void setCreatetimestr(String createtimestr) {
            this.createtimestr = createtimestr;
        }

        public String getDeltime() {
            return deltime;
        }

        public void setDeltime(String deltime) {
            this.deltime = deltime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.createtime);
            dest.writeString(this.createtimestr);
            dest.writeString(this.deltime);
            dest.writeString(this.id);
            dest.writeString(this.isdel);
            dest.writeString(this.name);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.createtime = in.readString();
            this.createtimestr = in.readString();
            this.deltime = in.readString();
            this.id = in.readString();
            this.isdel = in.readString();
            this.name = in.readString();
            this.isSelect = in.readByte() != 0;
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
        dest.writeString(this.startTime);
        dest.writeString(this.token);
        dest.writeTypedList(this.data);
    }

    public GradeLevelBean() {
    }

    protected GradeLevelBean(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<GradeLevelBean> CREATOR = new Creator<GradeLevelBean>() {
        @Override
        public GradeLevelBean createFromParcel(Parcel source) {
            return new GradeLevelBean(source);
        }

        @Override
        public GradeLevelBean[] newArray(int size) {
            return new GradeLevelBean[size];
        }
    };
}
