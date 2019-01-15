package com.ylean.cf_hospitalapp.hospital.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/11.
 */

public class ServiceInfoEntry extends Basebean {

    /**
     * data : [{"appointid":0,"asktype":1,"departid":0,"desc":"图文问诊","outdepartid":0,"price":0.01,"timedesc":""},{"appointid":0,"asktype":2,"departid":0,"desc":"电话问诊","outdepartid":0,"price":0.02,"timedesc":""},{"appointid":0,"asktype":3,"departid":0,"desc":"视频问诊","outdepartid":0,"price":0.03,"timedesc":""},{"appointid":737,"asktype":4,"departid":37,"desc":"门诊挂号","outdepartid":14,"price":0.01,"timedesc":"2019-01-15 星期二 上午"}]
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-01-14 17:37:45
     * sum : 0
     * titleList :
     * token :
     * totalmoney : 0
     */

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

    public static class DataBean implements android.os.Parcelable {
        /**
         * appointid : 0
         * asktype : 1
         * departid : 0
         * desc : 图文问诊
         * outdepartid : 0
         * price : 0.01
         * timedesc :
         */

        private String appointid;
        private String asktype;
        private String departid;
        private String desc;
        private String outdepartid;
        private double price;
        private String timedesc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getAppointid() {
            return appointid;
        }

        public void setAppointid(String appointid) {
            this.appointid = appointid;
        }

        public String getAsktype() {
            return asktype;
        }

        public void setAsktype(String asktype) {
            this.asktype = asktype;
        }

        public String getDepartid() {
            return departid;
        }

        public void setDepartid(String departid) {
            this.departid = departid;
        }

        public String getOutdepartid() {
            return outdepartid;
        }

        public void setOutdepartid(String outdepartid) {
            this.outdepartid = outdepartid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTimedesc() {
            return timedesc;
        }

        public void setTimedesc(String timedesc) {
            this.timedesc = timedesc;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.appointid);
            dest.writeString(this.asktype);
            dest.writeString(this.departid);
            dest.writeString(this.desc);
            dest.writeString(this.outdepartid);
            dest.writeDouble(this.price);
            dest.writeString(this.timedesc);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.appointid = in.readString();
            this.asktype = in.readString();
            this.departid = in.readString();
            this.desc = in.readString();
            this.outdepartid = in.readString();
            this.price = in.readDouble();
            this.timedesc = in.readString();
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
        dest.writeInt(this.pageSize);
        dest.writeString(this.startTime);
        dest.writeInt(this.sum);
        dest.writeString(this.titleList);
        dest.writeString(this.token);
        dest.writeInt(this.totalmoney);
        dest.writeTypedList(this.data);
    }

    public ServiceInfoEntry() {
    }

    protected ServiceInfoEntry(Parcel in) {
        super(in);
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

    public static final Creator<ServiceInfoEntry> CREATOR = new Creator<ServiceInfoEntry>() {
        @Override
        public ServiceInfoEntry createFromParcel(Parcel source) {
            return new ServiceInfoEntry(source);
        }

        @Override
        public ServiceInfoEntry[] newArray(int size) {
            return new ServiceInfoEntry[size];
        }
    };
}
