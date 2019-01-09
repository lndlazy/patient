package com.ylean.cf_hospitalapp.register.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/6.
 */

public class RegisterOrderEntry extends Basebean {

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

        private String appointtime;
        private String appointtype;
        private String code;
        private String departid;
        private String departname;
        private String docdocteachname;
        private String doctitle;
        private String doctorid;
        private String doctorname;
        private String folkname;
        private String hospitalid;
        private String hospitalname;
        private String id;
        private String imgurl;
        private String outdepartid;
        private String outdepartname;
        private double price;
        private String status;

        public String getAppointtime() {
            return appointtime;
        }

        public void setAppointtime(String appointtime) {
            this.appointtime = appointtime;
        }

        public String getAppointtype() {
            return appointtype;
        }

        public void setAppointtype(String appointtype) {
            this.appointtype = appointtype;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getDocdocteachname() {
            return docdocteachname;
        }

        public void setDocdocteachname(String docdocteachname) {
            this.docdocteachname = docdocteachname;
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

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public String getFolkname() {
            return folkname;
        }

        public void setFolkname(String folkname) {
            this.folkname = folkname;
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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getOutdepartid() {
            return outdepartid;
        }

        public void setOutdepartid(String outdepartid) {
            this.outdepartid = outdepartid;
        }

        public String getOutdepartname() {
            return outdepartname;
        }

        public void setOutdepartname(String outdepartname) {
            this.outdepartname = outdepartname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.appointtime);
            dest.writeString(this.appointtype);
            dest.writeString(this.code);
            dest.writeString(this.departid);
            dest.writeString(this.departname);
            dest.writeString(this.docdocteachname);
            dest.writeString(this.doctitle);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorname);
            dest.writeString(this.folkname);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.id);
            dest.writeString(this.imgurl);
            dest.writeString(this.outdepartid);
            dest.writeString(this.outdepartname);
            dest.writeDouble(this.price);
            dest.writeString(this.status);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.appointtime = in.readString();
            this.appointtype = in.readString();
            this.code = in.readString();
            this.departid = in.readString();
            this.departname = in.readString();
            this.docdocteachname = in.readString();
            this.doctitle = in.readString();
            this.doctorid = in.readString();
            this.doctorname = in.readString();
            this.folkname = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.id = in.readString();
            this.imgurl = in.readString();
            this.outdepartid = in.readString();
            this.outdepartname = in.readString();
            this.price = in.readDouble();
            this.status = in.readString();
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

    public RegisterOrderEntry() {
    }

    protected RegisterOrderEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<RegisterOrderEntry> CREATOR = new Creator<RegisterOrderEntry>() {
        @Override
        public RegisterOrderEntry createFromParcel(Parcel source) {
            return new RegisterOrderEntry(source);
        }

        @Override
        public RegisterOrderEntry[] newArray(int size) {
            return new RegisterOrderEntry[size];
        }
    };
}
