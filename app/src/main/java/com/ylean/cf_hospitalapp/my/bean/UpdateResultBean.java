package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/3/1.
 */

public class UpdateResultBean extends Basebean {


    /**
     * data : {"id":2,"oldversion":"1.3.5","type":0,"url":"/upload/imgs/07097360-4b81-4a61-ac69-b5b706e831d5.apk","version":"1.3.5"}
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-03-01 21:54:44
     * sum : 0
     * titleList :
     * token :
     * totalmoney : 0
     */

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
        /**
         * id : 2
         * oldversion : 1.3.5
         * type : 0
         * url : /upload/imgs/07097360-4b81-4a61-ac69-b5b706e831d5.apk
         * version : 1.3.5
         */

        private String id;
        private String oldversion;
        private String type;
        private String url;
        private String version;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOldversion() {
            return oldversion;
        }

        public void setOldversion(String oldversion) {
            this.oldversion = oldversion;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.oldversion);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeString(this.version);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.oldversion = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.version = in.readString();
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

    public UpdateResultBean() {
    }

    protected UpdateResultBean(Parcel in) {
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

    public static final Creator<UpdateResultBean> CREATOR = new Creator<UpdateResultBean>() {
        @Override
        public UpdateResultBean createFromParcel(Parcel source) {
            return new UpdateResultBean(source);
        }

        @Override
        public UpdateResultBean[] newArray(int size) {
            return new UpdateResultBean[size];
        }
    };
}
