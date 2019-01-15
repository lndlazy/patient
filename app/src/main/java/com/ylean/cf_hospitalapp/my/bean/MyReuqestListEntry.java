package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * 我的申请列表
 * Created by linaidao on 2019/1/14.
 */

public class MyReuqestListEntry extends Basebean {

    /**
     * data : [{"applytime":"2019-01-15 15:24:00","applytimetr":"2019-01-15","code":"R20190114152300004","hospitalid":3,"hospitalname":"北京长峰医院","id":2,"patientname":"李大爷","relatedid":15,"status":0,"type":2},{"applytime":"2019-01-20 15:11:00","applytimetr":"2019-01-20","code":"R20190114152000002","hospitalid":3,"hospitalname":"北京长峰医院","id":1,"patientname":"老太爷","relatedid":14,"status":0,"type":2}]
     * maxRow : 2
     * page : 0
     * pageIndex : 1
     * pageSize : 0
     * startTime : 2019-01-14 17:50:00
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
         * applytime : 2019-01-15 15:24:00
         * applytimetr : 2019-01-15
         * code : R20190114152300004
         * hospitalid : 3
         * hospitalname : 北京长峰医院
         * id : 2
         * patientname : 李大爷
         * relatedid : 15
         * status : 0
         * type : 2
         */

        private String applytime;
        private String applytimetr;
        private String code;
        private String hospitalid;
        private String hospitalname;
        private String id;
        private String patientname;
        private String relatedid;
        private String status;
        private String type;

        public String getApplytime() {
            return applytime;
        }

        public void setApplytime(String applytime) {
            this.applytime = applytime;
        }

        public String getApplytimetr() {
            return applytimetr;
        }

        public void setApplytimetr(String applytimetr) {
            this.applytimetr = applytimetr;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPatientname() {
            return patientname;
        }

        public void setPatientname(String patientname) {
            this.patientname = patientname;
        }

        public String getRelatedid() {
            return relatedid;
        }

        public void setRelatedid(String relatedid) {
            this.relatedid = relatedid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
            dest.writeString(this.applytime);
            dest.writeString(this.applytimetr);
            dest.writeString(this.code);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.id);
            dest.writeString(this.patientname);
            dest.writeString(this.relatedid);
            dest.writeString(this.status);
            dest.writeString(this.type);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.applytime = in.readString();
            this.applytimetr = in.readString();
            this.code = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.id = in.readString();
            this.patientname = in.readString();
            this.relatedid = in.readString();
            this.status = in.readString();
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
        dest.writeInt(this.pageSize);
        dest.writeString(this.startTime);
        dest.writeInt(this.sum);
        dest.writeString(this.titleList);
        dest.writeString(this.token);
        dest.writeInt(this.totalmoney);
        dest.writeTypedList(this.data);
    }

    public MyReuqestListEntry() {
    }

    protected MyReuqestListEntry(Parcel in) {
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

    public static final Creator<MyReuqestListEntry> CREATOR = new Creator<MyReuqestListEntry>() {
        @Override
        public MyReuqestListEntry createFromParcel(Parcel source) {
            return new MyReuqestListEntry(source);
        }

        @Override
        public MyReuqestListEntry[] newArray(int size) {
            return new MyReuqestListEntry[size];
        }
    };
}
