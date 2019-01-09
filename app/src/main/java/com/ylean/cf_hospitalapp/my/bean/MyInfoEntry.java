package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * 个人信息bean
 * Created by linaidao on 2019/1/2.
 */

public class MyInfoEntry extends Basebean {

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
        private String areacode;
        private String areaname;
        private String birthday;
        private String birthdayer;
        private String citycode;
        private String cityname;
        private String idcard;
        private String imgurl;
        private String level;
        private String medicalhistory;
        private String mobile;
        private String nickname;
        private String patientid;
        private String points;
        private String provincecode;
        private String provincename;
        private String realname;
        private String sex;
        private String totalPoints;
        private String userid;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBirthdayer() {
            return birthdayer;
        }

        public void setBirthdayer(String birthdayer) {
            this.birthdayer = birthdayer;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getMedicalhistory() {
            return medicalhistory;
        }

        public void setMedicalhistory(String medicalhistory) {
            this.medicalhistory = medicalhistory;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPatientid() {
            return patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getProvincecode() {
            return provincecode;
        }

        public void setProvincecode(String provincecode) {
            this.provincecode = provincecode;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTotalPoints() {
            return totalPoints;
        }

        public void setTotalPoints(String totalPoints) {
            this.totalPoints = totalPoints;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.address);
            dest.writeString(this.areacode);
            dest.writeString(this.areaname);
            dest.writeString(this.birthday);
            dest.writeString(this.birthdayer);
            dest.writeString(this.citycode);
            dest.writeString(this.cityname);
            dest.writeString(this.idcard);
            dest.writeString(this.imgurl);
            dest.writeString(this.level);
            dest.writeString(this.medicalhistory);
            dest.writeString(this.mobile);
            dest.writeString(this.nickname);
            dest.writeString(this.patientid);
            dest.writeString(this.points);
            dest.writeString(this.provincecode);
            dest.writeString(this.provincename);
            dest.writeString(this.realname);
            dest.writeString(this.sex);
            dest.writeString(this.totalPoints);
            dest.writeString(this.userid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.address = in.readString();
            this.areacode = in.readString();
            this.areaname = in.readString();
            this.birthday = in.readString();
            this.birthdayer = in.readString();
            this.citycode = in.readString();
            this.cityname = in.readString();
            this.idcard = in.readString();
            this.imgurl = in.readString();
            this.level = in.readString();
            this.medicalhistory = in.readString();
            this.mobile = in.readString();
            this.nickname = in.readString();
            this.patientid = in.readString();
            this.points = in.readString();
            this.provincecode = in.readString();
            this.provincename = in.readString();
            this.realname = in.readString();
            this.sex = in.readString();
            this.totalPoints = in.readString();
            this.userid = in.readString();
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

    public MyInfoEntry() {
    }

    protected MyInfoEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<MyInfoEntry> CREATOR = new Creator<MyInfoEntry>() {
        @Override
        public MyInfoEntry createFromParcel(Parcel source) {
            return new MyInfoEntry(source);
        }

        @Override
        public MyInfoEntry[] newArray(int size) {
            return new MyInfoEntry[size];
        }
    };
}
