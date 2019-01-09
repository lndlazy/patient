package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

public class PeopleEntry extends Basebean {

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

        private String IDcard;
        private String age;
        private String code;
        private boolean isSelect;
        private String id;
        private String imgurl;
        private String medicalcard;
        private String name;
        private String relationship;
        private String sex;
        private String uid;

        public String getIDcard() {
            return IDcard;
        }

        public void setIDcard(String IDcard) {
            this.IDcard = IDcard;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
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
            dest.writeString(this.age);
            dest.writeString(this.code);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
            dest.writeString(this.id);
            dest.writeString(this.imgurl);
            dest.writeString(this.medicalcard);
            dest.writeString(this.name);
            dest.writeString(this.relationship);
            dest.writeString(this.sex);
            dest.writeString(this.uid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.IDcard = in.readString();
            this.age = in.readString();
            this.code = in.readString();
            this.isSelect = in.readByte() != 0;
            this.id = in.readString();
            this.imgurl = in.readString();
            this.medicalcard = in.readString();
            this.name = in.readString();
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
        dest.writeString(this.startTime);
        dest.writeString(this.token);
        dest.writeTypedList(this.data);
    }

    public PeopleEntry() {
    }

    protected PeopleEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<PeopleEntry> CREATOR = new Creator<PeopleEntry>() {
        @Override
        public PeopleEntry createFromParcel(Parcel source) {
            return new PeopleEntry(source);
        }

        @Override
        public PeopleEntry[] newArray(int size) {
            return new PeopleEntry[size];
        }
    };
}
