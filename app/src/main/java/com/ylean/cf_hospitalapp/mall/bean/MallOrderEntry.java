package com.ylean.cf_hospitalapp.mall.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/21.
 */

public class MallOrderEntry extends Basebean implements Parcelable {

    /**
     * code : 0
     * data : [{"addOrderDatetr":"2019-01-21 15:23:21","addorderdate":"2019-01-21 15:23:21","code":"192144829299374","freightmoney":0,"iscomment":0,"orderid":55,"ordertype":2,"paytype":7,"points":10,"price":0.01,"skucount":1,"skuid":144,"skuimg":"/upload/imgs/e956cb69-dd01-4632-973a-8a8d1b7c0618.png","skuname":"艾灸","skuprice":0.01,"status":0},{"addOrderDatetr":"2019-01-21 15:19:37","addorderdate":"2019-01-21 15:19:37","code":"192125628126705","freightmoney":0,"iscomment":0,"orderid":54,"ordertype":2,"paytype":0,"points":10,"price":0.01,"skucount":1,"skuid":144,"skuimg":"/upload/imgs/e956cb69-dd01-4632-973a-8a8d1b7c0618.png","skuname":"艾灸","skuprice":0.01,"status":0},{"addOrderDatetr":"2019-01-21 15:06:54","addorderdate":"2019-01-21 15:06:54","code":"192182357546450","freightmoney":0,"iscomment":0,"orderid":51,"ordertype":2,"paytype":0,"points":10,"price":0.01,"skucount":1,"skuid":144,"skuimg":"/upload/imgs/e956cb69-dd01-4632-973a-8a8d1b7c0618.png","skuname":"艾灸","skuprice":0.01,"status":0},{"addOrderDatetr":"2019-01-21 14:21:51","addorderdate":"2019-01-21 14:21:51","code":"192165397528749","freightmoney":0,"iscomment":0,"orderid":49,"ordertype":2,"paytype":0,"points":10,"price":0.01,"skucount":1,"skuid":144,"skuimg":"/upload/imgs/e956cb69-dd01-4632-973a-8a8d1b7c0618.png","skuname":"艾灸","skuprice":0.01,"status":0}]
     * desc : 查询成功
     * maxRow : 4
     * page : 1
     * pageIndex : 1
     * pageSize : 15
     * startTime : 2019-01-21 15:49:24
     * sum : 0
     * titleList :
     * token :
     * totalmoney : 0
     */

    private int code;
    private String desc;
    private int maxRow;
    private int page;
    private int pageIndex;
    private int pageSize;
    private String startTime;
    private int sum;
    private String titleList;
    private String token;
    private int totalmoney;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getTitleList() {
        return titleList;
    }

    public void setTitleList(String titleList) {
        this.titleList = titleList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(int totalmoney) {
        this.totalmoney = totalmoney;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * addOrderDatetr : 2019-01-21 15:23:21
         * addorderdate : 2019-01-21 15:23:21
         * code : 192144829299374
         * freightmoney : 0
         * iscomment : 0
         * orderid : 55
         * ordertype : 2
         * paytype : 7
         * points : 10
         * price : 0.01
         * skucount : 1
         * skuid : 144
         * skuimg : /upload/imgs/e956cb69-dd01-4632-973a-8a8d1b7c0618.png
         * skuname : 艾灸
         * skuprice : 0.01
         * status : 0
         */

        private String addOrderDatetr;
        private String addorderdate;
        private String code;
        private double freightmoney;
        private String iscomment;
        private String orderid;
        private String ordertype;
        private String paytype;
        private int points;
        private double price;
        private int skucount;
        private String skuid;
        private String skuimg;
        private String skuname;
        private double skuprice;
        private String status;

        public String getAddOrderDatetr() {
            return addOrderDatetr;
        }

        public void setAddOrderDatetr(String addOrderDatetr) {
            this.addOrderDatetr = addOrderDatetr;
        }

        public String getAddorderdate() {
            return addorderdate;
        }

        public void setAddorderdate(String addorderdate) {
            this.addorderdate = addorderdate;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public double getFreightmoney() {
            return freightmoney;
        }

        public void setFreightmoney(double freightmoney) {
            this.freightmoney = freightmoney;
        }

        public String getIscomment() {
            return iscomment;
        }

        public void setIscomment(String iscomment) {
            this.iscomment = iscomment;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getSkucount() {
            return skucount;
        }

        public void setSkucount(int skucount) {
            this.skucount = skucount;
        }

        public String getSkuid() {
            return skuid;
        }

        public void setSkuid(String skuid) {
            this.skuid = skuid;
        }

        public String getSkuimg() {
            return skuimg;
        }

        public void setSkuimg(String skuimg) {
            this.skuimg = skuimg;
        }

        public String getSkuname() {
            return skuname;
        }

        public void setSkuname(String skuname) {
            this.skuname = skuname;
        }

        public double getSkuprice() {
            return skuprice;
        }

        public void setSkuprice(double skuprice) {
            this.skuprice = skuprice;
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
            dest.writeString(this.addOrderDatetr);
            dest.writeString(this.addorderdate);
            dest.writeString(this.code);
            dest.writeDouble(this.freightmoney);
            dest.writeString(this.iscomment);
            dest.writeString(this.orderid);
            dest.writeString(this.ordertype);
            dest.writeString(this.paytype);
            dest.writeInt(this.points);
            dest.writeDouble(this.price);
            dest.writeInt(this.skucount);
            dest.writeString(this.skuid);
            dest.writeString(this.skuimg);
            dest.writeString(this.skuname);
            dest.writeDouble(this.skuprice);
            dest.writeString(this.status);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.addOrderDatetr = in.readString();
            this.addorderdate = in.readString();
            this.code = in.readString();
            this.freightmoney = in.readDouble();
            this.iscomment = in.readString();
            this.orderid = in.readString();
            this.ordertype = in.readString();
            this.paytype = in.readString();
            this.points = in.readInt();
            this.price = in.readDouble();
            this.skucount = in.readInt();
            this.skuid = in.readString();
            this.skuimg = in.readString();
            this.skuname = in.readString();
            this.skuprice = in.readDouble();
            this.status = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
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
        dest.writeInt(this.code);
        dest.writeString(this.desc);
        dest.writeInt(this.maxRow);
        dest.writeInt(this.page);
        dest.writeInt(this.pageIndex);
        dest.writeInt(this.pageSize);
        dest.writeString(this.startTime);
        dest.writeInt(this.sum);
        dest.writeString(this.titleList);
        dest.writeString(this.token);
        dest.writeInt(this.totalmoney);
        dest.writeTypedList(this.data);
    }

    public MallOrderEntry() {
    }

    protected MallOrderEntry(Parcel in) {
        this.code = in.readInt();
        this.desc = in.readString();
        this.maxRow = in.readInt();
        this.page = in.readInt();
        this.pageIndex = in.readInt();
        this.pageSize = in.readInt();
        this.startTime = in.readString();
        this.sum = in.readInt();
        this.titleList = in.readString();
        this.token = in.readString();
        this.totalmoney = in.readInt();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<MallOrderEntry> CREATOR = new Parcelable.Creator<MallOrderEntry>() {
        @Override
        public MallOrderEntry createFromParcel(Parcel source) {
            return new MallOrderEntry(source);
        }

        @Override
        public MallOrderEntry[] newArray(int size) {
            return new MallOrderEntry[size];
        }
    };
}
