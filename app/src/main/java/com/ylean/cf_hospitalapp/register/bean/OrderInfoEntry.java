package com.ylean.cf_hospitalapp.register.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/6.
 */

public class OrderInfoEntry extends Basebean {

    private DataBean data;
    private String startTime;
    private String token;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean implements android.os.Parcelable {

        private String appointtime;
        private String appointtype;
        private String birthdate;
        private String birthdatetr;
        private String code;
        private String createtime;
        private String createtimestr;
        private String departid;
        private String departname;
        private String docdocteachname;
        private String doctitle;
        private String doctorid;
        private String doctorimg;
        private String doctorname;
        private String folkname;
        private String hospitalid;
        private String hospitalname;
        private String id;
        private String idcard;
        private String medicalcard;
        private String outdepartid;
        private String outdepartname;
        private String phone;
        private double price;
        private String sex;
        private String status;
        private String iscommend;

        public String getIscommend() {
            return iscommend;
        }

        public void setIscommend(String iscommend) {
            this.iscommend = iscommend;
        }

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

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }

        public String getBirthdatetr() {
            return birthdatetr;
        }

        public void setBirthdatetr(String birthdatetr) {
            this.birthdatetr = birthdatetr;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

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

        public String getDoctorimg() {
            return doctorimg;
        }

        public void setDoctorimg(String doctorimg) {
            this.doctorimg = doctorimg;
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

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getMedicalcard() {
            return medicalcard;
        }

        public void setMedicalcard(String medicalcard) {
            this.medicalcard = medicalcard;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.appointtime);
            dest.writeString(this.appointtype);
            dest.writeString(this.birthdate);
            dest.writeString(this.birthdatetr);
            dest.writeString(this.code);
            dest.writeString(this.createtime);
            dest.writeString(this.createtimestr);
            dest.writeString(this.departid);
            dest.writeString(this.departname);
            dest.writeString(this.docdocteachname);
            dest.writeString(this.doctitle);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorimg);
            dest.writeString(this.doctorname);
            dest.writeString(this.folkname);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.id);
            dest.writeString(this.idcard);
            dest.writeString(this.medicalcard);
            dest.writeString(this.outdepartid);
            dest.writeString(this.outdepartname);
            dest.writeString(this.phone);
            dest.writeDouble(this.price);
            dest.writeString(this.sex);
            dest.writeString(this.status);
            dest.writeString(this.iscommend);
        }

        protected DataBean(Parcel in) {
            this.appointtime = in.readString();
            this.appointtype = in.readString();
            this.birthdate = in.readString();
            this.birthdatetr = in.readString();
            this.code = in.readString();
            this.createtime = in.readString();
            this.createtimestr = in.readString();
            this.departid = in.readString();
            this.departname = in.readString();
            this.docdocteachname = in.readString();
            this.doctitle = in.readString();
            this.doctorid = in.readString();
            this.doctorimg = in.readString();
            this.doctorname = in.readString();
            this.folkname = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.id = in.readString();
            this.idcard = in.readString();
            this.medicalcard = in.readString();
            this.outdepartid = in.readString();
            this.outdepartname = in.readString();
            this.phone = in.readString();
            this.price = in.readDouble();
            this.sex = in.readString();
            this.status = in.readString();
            this.iscommend = in.readString();
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

    public OrderInfoEntry() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.startTime);
        dest.writeString(this.token);
    }

    protected OrderInfoEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<OrderInfoEntry> CREATOR = new Creator<OrderInfoEntry>() {
        @Override
        public OrderInfoEntry createFromParcel(Parcel source) {
            return new OrderInfoEntry(source);
        }

        @Override
        public OrderInfoEntry[] newArray(int size) {
            return new OrderInfoEntry[size];
        }
    };
}
