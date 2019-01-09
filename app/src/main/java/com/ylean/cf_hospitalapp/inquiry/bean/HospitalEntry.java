package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

public class HospitalEntry extends Basebean {

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

        private String address;
        private String areaname;
        private String cityname;
        private String hospitalid;
        private String hospitalname;
        private String imgurl;
        private String latitude;
        private String longitude;
        private String provincename;
        private String supportel;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public String getSupportel() {
            return supportel;
        }

        public void setSupportel(String supportel) {
            this.supportel = supportel;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.address);
            dest.writeString(this.areaname);
            dest.writeString(this.cityname);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.imgurl);
            dest.writeString(this.latitude);
            dest.writeString(this.longitude);
            dest.writeString(this.provincename);
            dest.writeString(this.supportel);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.address = in.readString();
            this.areaname = in.readString();
            this.cityname = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.imgurl = in.readString();
            this.latitude = in.readString();
            this.longitude = in.readString();
            this.provincename = in.readString();
            this.supportel = in.readString();
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
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.startTime);
        dest.writeString(this.token);
    }

    public HospitalEntry() {
    }

    protected HospitalEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<HospitalEntry> CREATOR = new Creator<HospitalEntry>() {
        @Override
        public HospitalEntry createFromParcel(Parcel source) {
            return new HospitalEntry(source);
        }

        @Override
        public HospitalEntry[] newArray(int size) {
            return new HospitalEntry[size];
        }
    };
}
