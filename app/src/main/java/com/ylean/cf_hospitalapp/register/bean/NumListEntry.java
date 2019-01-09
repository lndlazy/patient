package com.ylean.cf_hospitalapp.register.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/4.
 */

public class NumListEntry extends Basebean {

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

        private int count;
        private String departid;
        private String departname;
        private String description;
        private String docimg;
        private String docname;
        private String doctitle;
        private String doctorid;
        private String hospitalid;
        private String hospitalname;
        private String id;
        private String menzhenid;
        private String menzhenname;
        private double price;
        private String reserveType;
        private int status;
        private String teachname;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getDepartid() {
            return departid;
        }

        public void setDepartid(String departid) {
            this.departid = departid;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDocimg() {
            return docimg;
        }

        public void setDocimg(String docimg) {
            this.docimg = docimg;
        }

        public String getDocname() {
            return docname;
        }

        public void setDocname(String docname) {
            this.docname = docname;
        }

        public String getDoctitle() {
            return doctitle;
        }

        public void setDoctitle(String doctitle) {
            this.doctitle = doctitle;
        }

        public String getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public String getHospitalid() {
            return hospitalid;
        }

        public void setHospitalid(String hospitalid) {
            this.hospitalid = hospitalid;
        }

        public String getHospitalname() {
            return hospitalname;
        }

        public void setHospitalname(String hospitalname) {
            this.hospitalname = hospitalname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMenzhenid() {
            return menzhenid;
        }

        public void setMenzhenid(String menzhenid) {
            this.menzhenid = menzhenid;
        }

        public String getMenzhenname() {
            return menzhenname;
        }

        public void setMenzhenname(String menzhenname) {
            this.menzhenname = menzhenname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getReserveType() {
            return reserveType;
        }

        public void setReserveType(String reserveType) {
            this.reserveType = reserveType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTeachname() {
            return teachname;
        }

        public void setTeachname(String teachname) {
            this.teachname = teachname;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.count);
            dest.writeString(this.departid);
            dest.writeString(this.departname);
            dest.writeString(this.description);
            dest.writeString(this.docimg);
            dest.writeString(this.docname);
            dest.writeString(this.doctitle);
            dest.writeString(this.doctorid);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.id);
            dest.writeString(this.menzhenid);
            dest.writeString(this.menzhenname);
            dest.writeDouble(this.price);
            dest.writeString(this.reserveType);
            dest.writeInt(this.status);
            dest.writeString(this.teachname);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.count = in.readInt();
            this.departid = in.readString();
            this.departname = in.readString();
            this.description = in.readString();
            this.docimg = in.readString();
            this.docname = in.readString();
            this.doctitle = in.readString();
            this.doctorid = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.id = in.readString();
            this.menzhenid = in.readString();
            this.menzhenname = in.readString();
            this.price = in.readDouble();
            this.reserveType = in.readString();
            this.status = in.readInt();
            this.teachname = in.readString();
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

    public NumListEntry() {
    }

    protected NumListEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<NumListEntry> CREATOR = new Creator<NumListEntry>() {
        @Override
        public NumListEntry createFromParcel(Parcel source) {
            return new NumListEntry(source);
        }

        @Override
        public NumListEntry[] newArray(int size) {
            return new NumListEntry[size];
        }
    };
}
