package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/8.
 */

public class FamilyDetailEntry extends Basebean {

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

        private String IDcard;
        private String acode;
        private String address;
        private String age;
        private String anaphylaxis;
        private String area;
        private String birthdate;
        private String birthdatesr;
        private String ccode;
        private String city;
        private String code;
        private String createtime;
        private String createtimestr;
        private String deltime;
        private String diseasehistory;
        private String id;
        private String imgurl;
        private String isdel;
        private String medicalcard;
        private String name;
        private String operation;
        private String pcode;
        private String phone;
        private String province;
        private String relationship;
        private String sex;
        private String uid;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIDcard() {
            return IDcard;
        }

        public void setIDcard(String IDcard) {
            this.IDcard = IDcard;
        }

        public String getAcode() {
            return acode;
        }

        public void setAcode(String acode) {
            this.acode = acode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAnaphylaxis() {
            return anaphylaxis;
        }

        public void setAnaphylaxis(String anaphylaxis) {
            this.anaphylaxis = anaphylaxis;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }

        public String getBirthdatesr() {
            return birthdatesr;
        }

        public void setBirthdatesr(String birthdatesr) {
            this.birthdatesr = birthdatesr;
        }

        public String getCcode() {
            return ccode;
        }

        public void setCcode(String ccode) {
            this.ccode = ccode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public String getDeltime() {
            return deltime;
        }

        public void setDeltime(String deltime) {
            this.deltime = deltime;
        }

        public String getDiseasehistory() {
            return diseasehistory;
        }

        public void setDiseasehistory(String diseasehistory) {
            this.diseasehistory = diseasehistory;
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

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public String getMedicalcard() {
            return medicalcard;
        }

        public void setMedicalcard(String medicalcard) {
            this.medicalcard = medicalcard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.IDcard);
            dest.writeString(this.acode);
            dest.writeString(this.address);
            dest.writeString(this.age);
            dest.writeString(this.anaphylaxis);
            dest.writeString(this.area);
            dest.writeString(this.birthdate);
            dest.writeString(this.birthdatesr);
            dest.writeString(this.ccode);
            dest.writeString(this.city);
            dest.writeString(this.code);
            dest.writeString(this.createtime);
            dest.writeString(this.createtimestr);
            dest.writeString(this.deltime);
            dest.writeString(this.diseasehistory);
            dest.writeString(this.id);
            dest.writeString(this.imgurl);
            dest.writeString(this.isdel);
            dest.writeString(this.medicalcard);
            dest.writeString(this.name);
            dest.writeString(this.operation);
            dest.writeString(this.pcode);
            dest.writeString(this.phone);
            dest.writeString(this.province);
            dest.writeString(this.relationship);
            dest.writeString(this.sex);
            dest.writeString(this.uid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.IDcard = in.readString();
            this.acode = in.readString();
            this.address = in.readString();
            this.age = in.readString();
            this.anaphylaxis = in.readString();
            this.area = in.readString();
            this.birthdate = in.readString();
            this.birthdatesr = in.readString();
            this.ccode = in.readString();
            this.city = in.readString();
            this.code = in.readString();
            this.createtime = in.readString();
            this.createtimestr = in.readString();
            this.deltime = in.readString();
            this.diseasehistory = in.readString();
            this.id = in.readString();
            this.imgurl = in.readString();
            this.isdel = in.readString();
            this.medicalcard = in.readString();
            this.name = in.readString();
            this.operation = in.readString();
            this.pcode = in.readString();
            this.phone = in.readString();
            this.province = in.readString();
            this.relationship = in.readString();
            this.sex = in.readString();
            this.uid = in.readString();
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

    public FamilyDetailEntry() {
    }

    protected FamilyDetailEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<FamilyDetailEntry> CREATOR = new Creator<FamilyDetailEntry>() {
        @Override
        public FamilyDetailEntry createFromParcel(Parcel source) {
            return new FamilyDetailEntry(source);
        }

        @Override
        public FamilyDetailEntry[] newArray(int size) {
            return new FamilyDetailEntry[size];
        }
    };
}
