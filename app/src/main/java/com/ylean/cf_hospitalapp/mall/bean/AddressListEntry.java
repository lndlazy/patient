package com.ylean.cf_hospitalapp.mall.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/7.
 */

public class AddressListEntry extends Basebean {

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

        private String address;
        private String areacode;
        private String areaname;
        private String citycode;
        private String cityname;
        private String id;
        private String isdefault;
        private String ishome;
        private boolean isreceive;
        private String mobile;
        private String name;
        private String provincecode;
        private String provincename;
        private String sex;
        private String telAreacode;
        private String telExtension;
        private String telNumber;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(String isdefault) {
            this.isdefault = isdefault;
        }

        public String getIshome() {
            return ishome;
        }

        public void setIshome(String ishome) {
            this.ishome = ishome;
        }

        public boolean isIsreceive() {
            return isreceive;
        }

        public void setIsreceive(boolean isreceive) {
            this.isreceive = isreceive;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTelAreacode() {
            return telAreacode;
        }

        public void setTelAreacode(String telAreacode) {
            this.telAreacode = telAreacode;
        }

        public String getTelExtension() {
            return telExtension;
        }

        public void setTelExtension(String telExtension) {
            this.telExtension = telExtension;
        }

        public String getTelNumber() {
            return telNumber;
        }

        public void setTelNumber(String telNumber) {
            this.telNumber = telNumber;
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
            dest.writeString(this.citycode);
            dest.writeString(this.cityname);
            dest.writeString(this.id);
            dest.writeString(this.isdefault);
            dest.writeString(this.ishome);
            dest.writeByte(this.isreceive ? (byte) 1 : (byte) 0);
            dest.writeString(this.mobile);
            dest.writeString(this.name);
            dest.writeString(this.provincecode);
            dest.writeString(this.provincename);
            dest.writeString(this.sex);
            dest.writeString(this.telAreacode);
            dest.writeString(this.telExtension);
            dest.writeString(this.telNumber);
            dest.writeString(this.userid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.address = in.readString();
            this.areacode = in.readString();
            this.areaname = in.readString();
            this.citycode = in.readString();
            this.cityname = in.readString();
            this.id = in.readString();
            this.isdefault = in.readString();
            this.ishome = in.readString();
            this.isreceive = in.readByte() != 0;
            this.mobile = in.readString();
            this.name = in.readString();
            this.provincecode = in.readString();
            this.provincename = in.readString();
            this.sex = in.readString();
            this.telAreacode = in.readString();
            this.telExtension = in.readString();
            this.telNumber = in.readString();
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
        dest.writeString(this.startTime);
        dest.writeString(this.token);
        dest.writeTypedList(this.data);
    }

    public AddressListEntry() {
    }

    protected AddressListEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<AddressListEntry> CREATOR = new Creator<AddressListEntry>() {
        @Override
        public AddressListEntry createFromParcel(Parcel source) {
            return new AddressListEntry(source);
        }

        @Override
        public AddressListEntry[] newArray(int size) {
            return new AddressListEntry[size];
        }
    };
}
