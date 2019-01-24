package com.ylean.cf_hospitalapp.mall.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * 商品服务订单详情的bean
 * Created by linaidao on 2019/1/23.
 */

public class GoodsOrderInfoEntry extends Basebean {

    /**
     * data : {"actualPay":0.01,"address":"","canclereason":"","code":"192198477246499","consignee":"","deliverdate":"","deliverdatetr":"","discount":0,"freight":0,"logisiticcode":"","logisticname":"","orderdate":"2019-01-21 18:37:54","orderdatetr":"2019-01-21 18:37:54","orderid":74,"ordertype":2,"paydate":"2019-01-21 18:38:03","paydatetr":"2019-01-21 18:38:03","paytype":7,"points":10,"price":0.01,"remark":"无","skucount":1,"skuid":144,"skuimg":"/upload/imgs/e956cb69-dd01-4632-973a-8a8d1b7c0618.png","skuname":"艾灸","skuprice":0.01,"status":10,"telphone":"","usepoints":10}
     * startTime : 2019-01-23 14:33:23
     * token :
     */

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
        /**
         * actualPay : 0.01
         * address :
         * canclereason :
         * code : 192198477246499
         * consignee :
         * deliverdate :
         * deliverdatetr :
         * discount : 0
         * freight : 0
         * logisiticcode :
         * logisticname :
         * orderdate : 2019-01-21 18:37:54
         * orderdatetr : 2019-01-21 18:37:54
         * orderid : 74
         * ordertype : 2
         * paydate : 2019-01-21 18:38:03
         * paydatetr : 2019-01-21 18:38:03
         * paytype : 7
         * points : 10
         * price : 0.01
         * remark : 无
         * skucount : 1
         * skuid : 144
         * skuimg : /upload/imgs/e956cb69-dd01-4632-973a-8a8d1b7c0618.png
         * skuname : 艾灸
         * skuprice : 0.01
         * status : 10
         * telphone :
         * usepoints : 10
         */

        private double actualPay;
        private String address;
        private String canclereason;
        private String code;
        private String consignee;
        private String deliverdate;
        private String deliverdatetr;
        private double discount;
        private double freight;
        private String logisiticcode;
        private String logisticname;
        private String orderdate;
        private String orderdatetr;
        private String orderid;
        private String ordertype;
        private String paydate;
        private String paydatetr;
        private String paytype;
        private int points;
        private double price;
        private String remark;
        private int skucount;
        private String skuid;
        private String skuimg;
        private String skuname;
        private double skuprice;
        private String iscomment;

        public String getIscomment() {
            return iscomment;
        }

        public void setIscomment(String iscomment) {
            this.iscomment = iscomment;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String status;
        private String telphone;
        private int usepoints;

        public double getActualPay() {
            return actualPay;
        }

        public void setActualPay(double actualPay) {
            this.actualPay = actualPay;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCanclereason() {
            return canclereason;
        }

        public void setCanclereason(String canclereason) {
            this.canclereason = canclereason;
        }


        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getDeliverdate() {
            return deliverdate;
        }

        public void setDeliverdate(String deliverdate) {
            this.deliverdate = deliverdate;
        }

        public String getDeliverdatetr() {
            return deliverdatetr;
        }

        public void setDeliverdatetr(String deliverdatetr) {
            this.deliverdatetr = deliverdatetr;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }

        public String getLogisiticcode() {
            return logisiticcode;
        }

        public void setLogisiticcode(String logisiticcode) {
            this.logisiticcode = logisiticcode;
        }

        public String getLogisticname() {
            return logisticname;
        }

        public void setLogisticname(String logisticname) {
            this.logisticname = logisticname;
        }

        public String getOrderdate() {
            return orderdate;
        }

        public void setOrderdate(String orderdate) {
            this.orderdate = orderdate;
        }

        public String getOrderdatetr() {
            return orderdatetr;
        }

        public void setOrderdatetr(String orderdatetr) {
            this.orderdatetr = orderdatetr;
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

        public String getPaydate() {
            return paydate;
        }

        public void setPaydate(String paydate) {
            this.paydate = paydate;
        }

        public String getPaydatetr() {
            return paydatetr;
        }

        public void setPaydatetr(String paydatetr) {
            this.paydatetr = paydatetr;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public int getUsepoints() {
            return usepoints;
        }

        public void setUsepoints(int usepoints) {
            this.usepoints = usepoints;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.actualPay);
            dest.writeString(this.address);
            dest.writeString(this.canclereason);
            dest.writeString(this.code);
            dest.writeString(this.consignee);
            dest.writeString(this.deliverdate);
            dest.writeString(this.deliverdatetr);
            dest.writeDouble(this.discount);
            dest.writeDouble(this.freight);
            dest.writeString(this.logisiticcode);
            dest.writeString(this.logisticname);
            dest.writeString(this.orderdate);
            dest.writeString(this.orderdatetr);
            dest.writeString(this.orderid);
            dest.writeString(this.ordertype);
            dest.writeString(this.paydate);
            dest.writeString(this.paydatetr);
            dest.writeString(this.paytype);
            dest.writeInt(this.points);
            dest.writeDouble(this.price);
            dest.writeString(this.remark);
            dest.writeInt(this.skucount);
            dest.writeString(this.skuid);
            dest.writeString(this.skuimg);
            dest.writeString(this.skuname);
            dest.writeDouble(this.skuprice);
            dest.writeString(this.iscomment);
            dest.writeString(this.status);
            dest.writeString(this.telphone);
            dest.writeInt(this.usepoints);
        }

        protected DataBean(Parcel in) {
            this.actualPay = in.readDouble();
            this.address = in.readString();
            this.canclereason = in.readString();
            this.code = in.readString();
            this.consignee = in.readString();
            this.deliverdate = in.readString();
            this.deliverdatetr = in.readString();
            this.discount = in.readDouble();
            this.freight = in.readDouble();
            this.logisiticcode = in.readString();
            this.logisticname = in.readString();
            this.orderdate = in.readString();
            this.orderdatetr = in.readString();
            this.orderid = in.readString();
            this.ordertype = in.readString();
            this.paydate = in.readString();
            this.paydatetr = in.readString();
            this.paytype = in.readString();
            this.points = in.readInt();
            this.price = in.readDouble();
            this.remark = in.readString();
            this.skucount = in.readInt();
            this.skuid = in.readString();
            this.skuimg = in.readString();
            this.skuname = in.readString();
            this.skuprice = in.readDouble();
            this.iscomment = in.readString();
            this.status = in.readString();
            this.telphone = in.readString();
            this.usepoints = in.readInt();
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

    public GoodsOrderInfoEntry() {
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

    protected GoodsOrderInfoEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<GoodsOrderInfoEntry> CREATOR = new Creator<GoodsOrderInfoEntry>() {
        @Override
        public GoodsOrderInfoEntry createFromParcel(Parcel source) {
            return new GoodsOrderInfoEntry(source);
        }

        @Override
        public GoodsOrderInfoEntry[] newArray(int size) {
            return new GoodsOrderInfoEntry[size];
        }
    };
}
