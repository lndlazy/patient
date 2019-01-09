package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/8.
 */

public class OrderInquiryDetailEntry extends Basebean {

    private DataBean data;
    private int maxRow;
    private int page;
    private int pageIndex;
    private int pageSize;
    private String startTime;
    private int sum;
    private String titleList;
    private String token;
    private int totalmoney;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean implements android.os.Parcelable {

        private String adeptdesc;
        private String asktime;
        private String asktimetr;
        private String asktype;
        private String cancletime;
        private String cancletimetr;
        private String checktime;
        private String checktimetr;
        private String code;
        private String createtime;
        private String createtimetr;
        private String departname;
        private String doctorid;
        private String doctorimgurl;
        private String doctorname;
        private String hospitalid;
        private String hospitalname;
        private String iscomment;
        private String orderid;
        private String paytime;
        private String paytimetr;
        private String paytype;
        private double price;
        private String returntime;
        private String returntimetr;
        private String status;
        private String suretime;
        private String suretimetr;
        private String titlename;

        public String getAdeptdesc() {
            return adeptdesc;
        }

        public void setAdeptdesc(String adeptdesc) {
            this.adeptdesc = adeptdesc;
        }

        public String getAsktime() {
            return asktime;
        }

        public void setAsktime(String asktime) {
            this.asktime = asktime;
        }

        public String getAsktimetr() {
            return asktimetr;
        }

        public void setAsktimetr(String asktimetr) {
            this.asktimetr = asktimetr;
        }

        public String getAsktype() {
            return asktype;
        }

        public void setAsktype(String asktype) {
            this.asktype = asktype;
        }

        public String getCancletime() {
            return cancletime;
        }

        public void setCancletime(String cancletime) {
            this.cancletime = cancletime;
        }

        public String getCancletimetr() {
            return cancletimetr;
        }

        public void setCancletimetr(String cancletimetr) {
            this.cancletimetr = cancletimetr;
        }

        public String getChecktime() {
            return checktime;
        }

        public void setChecktime(String checktime) {
            this.checktime = checktime;
        }

        public String getChecktimetr() {
            return checktimetr;
        }

        public void setChecktimetr(String checktimetr) {
            this.checktimetr = checktimetr;
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

        public String getCreatetimetr() {
            return createtimetr;
        }

        public void setCreatetimetr(String createtimetr) {
            this.createtimetr = createtimetr;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public String getDoctorimgurl() {
            return doctorimgurl;
        }

        public void setDoctorimgurl(String doctorimgurl) {
            this.doctorimgurl = doctorimgurl;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
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

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getPaytimetr() {
            return paytimetr;
        }

        public void setPaytimetr(String paytimetr) {
            this.paytimetr = paytimetr;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getReturntime() {
            return returntime;
        }

        public void setReturntime(String returntime) {
            this.returntime = returntime;
        }

        public String getReturntimetr() {
            return returntimetr;
        }

        public void setReturntimetr(String returntimetr) {
            this.returntimetr = returntimetr;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSuretime() {
            return suretime;
        }

        public void setSuretime(String suretime) {
            this.suretime = suretime;
        }

        public String getSuretimetr() {
            return suretimetr;
        }

        public void setSuretimetr(String suretimetr) {
            this.suretimetr = suretimetr;
        }

        public String getTitlename() {
            return titlename;
        }

        public void setTitlename(String titlename) {
            this.titlename = titlename;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.adeptdesc);
            dest.writeString(this.asktime);
            dest.writeString(this.asktimetr);
            dest.writeString(this.asktype);
            dest.writeString(this.cancletime);
            dest.writeString(this.cancletimetr);
            dest.writeString(this.checktime);
            dest.writeString(this.checktimetr);
            dest.writeString(this.code);
            dest.writeString(this.createtime);
            dest.writeString(this.createtimetr);
            dest.writeString(this.departname);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorimgurl);
            dest.writeString(this.doctorname);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.iscomment);
            dest.writeString(this.orderid);
            dest.writeString(this.paytime);
            dest.writeString(this.paytimetr);
            dest.writeString(this.paytype);
            dest.writeDouble(this.price);
            dest.writeString(this.returntime);
            dest.writeString(this.returntimetr);
            dest.writeString(this.status);
            dest.writeString(this.suretime);
            dest.writeString(this.suretimetr);
            dest.writeString(this.titlename);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.adeptdesc = in.readString();
            this.asktime = in.readString();
            this.asktimetr = in.readString();
            this.asktype = in.readString();
            this.cancletime = in.readString();
            this.cancletimetr = in.readString();
            this.checktime = in.readString();
            this.checktimetr = in.readString();
            this.code = in.readString();
            this.createtime = in.readString();
            this.createtimetr = in.readString();
            this.departname = in.readString();
            this.doctorid = in.readString();
            this.doctorimgurl = in.readString();
            this.doctorname = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.iscomment = in.readString();
            this.orderid = in.readString();
            this.paytime = in.readString();
            this.paytimetr = in.readString();
            this.paytype = in.readString();
            this.price = in.readDouble();
            this.returntime = in.readString();
            this.returntimetr = in.readString();
            this.status = in.readString();
            this.suretime = in.readString();
            this.suretimetr = in.readString();
            this.titlename = in.readString();
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
        dest.writeInt(this.maxRow);
        dest.writeInt(this.page);
        dest.writeInt(this.pageIndex);
        dest.writeInt(this.pageSize);
        dest.writeString(this.startTime);
        dest.writeInt(this.sum);
        dest.writeString(this.titleList);
        dest.writeString(this.token);
        dest.writeInt(this.totalmoney);
    }

    public OrderInquiryDetailEntry() {
    }

    protected OrderInquiryDetailEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.maxRow = in.readInt();
        this.page = in.readInt();
        this.pageIndex = in.readInt();
        this.pageSize = in.readInt();
        this.startTime = in.readString();
        this.sum = in.readInt();
        this.titleList = in.readString();
        this.token = in.readString();
        this.totalmoney = in.readInt();
    }

    public static final Creator<OrderInquiryDetailEntry> CREATOR = new Creator<OrderInquiryDetailEntry>() {
        @Override
        public OrderInquiryDetailEntry createFromParcel(Parcel source) {
            return new OrderInquiryDetailEntry(source);
        }

        @Override
        public OrderInquiryDetailEntry[] newArray(int size) {
            return new OrderInquiryDetailEntry[size];
        }
    };
}
