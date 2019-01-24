package com.ylean.cf_hospitalapp.mall.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/23.
 */

public class RefundAddressEntry extends Basebean {


    /**
     * data : {"address":"北京市朝阳区东十里路未来时大厦1201","id":1,"shopname":"北京因联科技有限公司","telphone":"12345678901"}
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-01-23 18:20:53
     * sum : 0
     * titleList :
     * token :
     * totalmoney : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {
        /**
         * address : 北京市朝阳区东十里路未来时大厦1201
         * id : 1
         * shopname : 北京因联科技有限公司
         * telphone : 12345678901
         */

        private String address;
        private String id;
        private String shopname;
        private String telphone;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.address);
            dest.writeString(this.id);
            dest.writeString(this.shopname);
            dest.writeString(this.telphone);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.address = in.readString();
            this.id = in.readString();
            this.shopname = in.readString();
            this.telphone = in.readString();
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
    }

    public RefundAddressEntry() {
    }

    protected RefundAddressEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<RefundAddressEntry> CREATOR = new Creator<RefundAddressEntry>() {
        @Override
        public RefundAddressEntry createFromParcel(Parcel source) {
            return new RefundAddressEntry(source);
        }

        @Override
        public RefundAddressEntry[] newArray(int size) {
            return new RefundAddressEntry[size];
        }
    };
}
