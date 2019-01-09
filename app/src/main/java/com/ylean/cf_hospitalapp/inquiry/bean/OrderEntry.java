package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2018/12/24.
 */

public class OrderEntry extends Basebean {

    private int maxRow;
    private int page;
    private int pageIndex;
    private String pageSize;
    private String startTime;
    private int sum;
    private String titleList;
    private String token;
    private int totalmoney;
    private List<DataBean> data;

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

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
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

    public static class DataBean implements android.os.Parcelable {

        private String adeptdesc;
        private String asktime;
        private String asktimetr;
        private String code;
        private String departname;
        private String doctorid;
        private String doctorimgurl;
        private String doctorname;
        private String hospitalid;
        private String hospitalname;
        private int iscomment;
        private String orderid;
        private double price;
        private String status;
        private String titlename;
        private String type;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public int getIscomment() {
            return iscomment;
        }

        public void setIscomment(int iscomment) {
            this.iscomment = iscomment;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitlename() {
            return titlename;
        }

        public void setTitlename(String titlename) {
            this.titlename = titlename;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
            dest.writeString(this.code);
            dest.writeString(this.departname);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorimgurl);
            dest.writeString(this.doctorname);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeInt(this.iscomment);
            dest.writeString(this.orderid);
            dest.writeDouble(this.price);
            dest.writeString(this.status);
            dest.writeString(this.titlename);
            dest.writeString(this.type);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.adeptdesc = in.readString();
            this.asktime = in.readString();
            this.asktimetr = in.readString();
            this.code = in.readString();
            this.departname = in.readString();
            this.doctorid = in.readString();
            this.doctorimgurl = in.readString();
            this.doctorname = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.iscomment = in.readInt();
            this.orderid = in.readString();
            this.price = in.readDouble();
            this.status = in.readString();
            this.titlename = in.readString();
            this.type = in.readString();
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
        dest.writeInt(this.maxRow);
        dest.writeInt(this.page);
        dest.writeInt(this.pageIndex);
        dest.writeString(this.pageSize);
        dest.writeString(this.startTime);
        dest.writeInt(this.sum);
        dest.writeString(this.titleList);
        dest.writeString(this.token);
        dest.writeInt(this.totalmoney);
        dest.writeTypedList(this.data);
    }

    public OrderEntry() {
    }

    protected OrderEntry(Parcel in) {
        super(in);
        this.maxRow = in.readInt();
        this.page = in.readInt();
        this.pageIndex = in.readInt();
        this.pageSize = in.readString();
        this.startTime = in.readString();
        this.sum = in.readInt();
        this.titleList = in.readString();
        this.token = in.readString();
        this.totalmoney = in.readInt();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<OrderEntry> CREATOR = new Creator<OrderEntry>() {
        @Override
        public OrderEntry createFromParcel(Parcel source) {
            return new OrderEntry(source);
        }

        @Override
        public OrderEntry[] newArray(int size) {
            return new OrderEntry[size];
        }
    };
}
