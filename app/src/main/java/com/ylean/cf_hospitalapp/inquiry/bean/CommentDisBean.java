package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

public class CommentDisBean extends Basebean {

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

        private String departid;
        private String diseaseid;
        private String diseasename;
        private boolean isSelect;

        public String getDepartid() {
            return departid;
        }

        public void setDepartid(String departid) {
            this.departid = departid;
        }

        public String getDiseaseid() {
            return diseaseid;
        }

        public void setDiseaseid(String diseaseid) {
            this.diseaseid = diseaseid;
        }

        public String getDiseasename() {
            return diseasename;
        }

        public void setDiseasename(String diseasename) {
            this.diseasename = diseasename;
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
            dest.writeString(this.departid);
            dest.writeString(this.diseaseid);
            dest.writeString(this.diseasename);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.departid = in.readString();
            this.diseaseid = in.readString();
            this.diseasename = in.readString();
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

    public CommentDisBean() {
    }

    protected CommentDisBean(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<CommentDisBean> CREATOR = new Creator<CommentDisBean>() {
        @Override
        public CommentDisBean createFromParcel(Parcel source) {
            return new CommentDisBean(source);
        }

        @Override
        public CommentDisBean[] newArray(int size) {
            return new CommentDisBean[size];
        }
    };
}
