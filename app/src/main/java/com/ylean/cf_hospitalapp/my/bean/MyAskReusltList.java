package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2018/12/21.
 */

public class MyAskReusltList extends Basebean {

    private int maxRow;
    private int page;
    private int pageIndex;
    private int pageSize;
    private String startTime;
    private int sum;
    private String titleList;
    private String token;
    private Double totalmoney;
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

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {

        private String consultaid;
        private int count;
        private String description;
        private String doctitlename;
        private String doctorid;
        private String doctorimgurl;
        private String doctorname;
        private String hospitalname;
        private String overtime;
        private String replaytime;
        private String replaytimetr;
        private String status;
        private String suretime;

        public String getConsultaid() {
            return consultaid;
        }

        public void setConsultaid(String consultaid) {
            this.consultaid = consultaid;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDoctitlename() {
            return doctitlename;
        }

        public void setDoctitlename(String doctitlename) {
            this.doctitlename = doctitlename;
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

        public String getHospitalname() {
            return hospitalname;
        }

        public void setHospitalname(String hospitalname) {
            this.hospitalname = hospitalname;
        }

        public String getOvertime() {
            return overtime;
        }

        public void setOvertime(String overtime) {
            this.overtime = overtime;
        }

        public String getReplaytime() {
            return replaytime;
        }

        public void setReplaytime(String replaytime) {
            this.replaytime = replaytime;
        }

        public String getReplaytimetr() {
            return replaytimetr;
        }

        public void setReplaytimetr(String replaytimetr) {
            this.replaytimetr = replaytimetr;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.consultaid);
            dest.writeInt(this.count);
            dest.writeString(this.description);
            dest.writeString(this.doctitlename);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorimgurl);
            dest.writeString(this.doctorname);
            dest.writeString(this.hospitalname);
            dest.writeString(this.overtime);
            dest.writeString(this.replaytime);
            dest.writeString(this.replaytimetr);
            dest.writeString(this.status);
            dest.writeString(this.suretime);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.consultaid = in.readString();
            this.count = in.readInt();
            this.description = in.readString();
            this.doctitlename = in.readString();
            this.doctorid = in.readString();
            this.doctorimgurl = in.readString();
            this.doctorname = in.readString();
            this.hospitalname = in.readString();
            this.overtime = in.readString();
            this.replaytime = in.readString();
            this.replaytimetr = in.readString();
            this.status = in.readString();
            this.suretime = in.readString();
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
        dest.writeValue(this.totalmoney);
        dest.writeTypedList(this.data);
    }

    public MyAskReusltList() {
    }

    protected MyAskReusltList(Parcel in) {
        super(in);
        this.maxRow = in.readInt();
        this.page = in.readInt();
        this.pageIndex = in.readInt();
        this.pageSize = in.readInt();
        this.startTime = in.readString();
        this.sum = in.readInt();
        this.titleList = in.readString();
        this.token = in.readString();
        this.totalmoney = (Double) in.readValue(Double.class.getClassLoader());
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<MyAskReusltList> CREATOR = new Creator<MyAskReusltList>() {
        @Override
        public MyAskReusltList createFromParcel(Parcel source) {
            return new MyAskReusltList(source);
        }

        @Override
        public MyAskReusltList[] newArray(int size) {
            return new MyAskReusltList[size];
        }
    };
}
