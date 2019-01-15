package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * 未读消息
 * Created by linaidao on 2019/1/15.
 */

public class UnreadMsgEntry extends Basebean {

    /**
     * data : {"ddcount":0,"hdcount":0,"xtcount":0,"zcount":0}
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-01-15 10:40:32
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
         * ddcount : 0
         * hdcount : 0
         * xtcount : 0
         * zcount : 0
         */

        private int ddcount;
        private int hdcount;
        private int xtcount;
        private int zcount;

        public int getDdcount() {
            return ddcount;
        }

        public void setDdcount(int ddcount) {
            this.ddcount = ddcount;
        }

        public int getHdcount() {
            return hdcount;
        }

        public void setHdcount(int hdcount) {
            this.hdcount = hdcount;
        }

        public int getXtcount() {
            return xtcount;
        }

        public void setXtcount(int xtcount) {
            this.xtcount = xtcount;
        }

        public int getZcount() {
            return zcount;
        }

        public void setZcount(int zcount) {
            this.zcount = zcount;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.ddcount);
            dest.writeInt(this.hdcount);
            dest.writeInt(this.xtcount);
            dest.writeInt(this.zcount);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.ddcount = in.readInt();
            this.hdcount = in.readInt();
            this.xtcount = in.readInt();
            this.zcount = in.readInt();
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

    public UnreadMsgEntry() {
    }

    protected UnreadMsgEntry(Parcel in) {
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

    public static final Creator<UnreadMsgEntry> CREATOR = new Creator<UnreadMsgEntry>() {
        @Override
        public UnreadMsgEntry createFromParcel(Parcel source) {
            return new UnreadMsgEntry(source);
        }

        @Override
        public UnreadMsgEntry[] newArray(int size) {
            return new UnreadMsgEntry[size];
        }
    };
}
