package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/3/1.
 */

public class EarnPointsBean extends Basebean {

    /**
     * data : [{"iscomplete":1,"name":"新用户注册","points":300,"value":0},{"iscomplete":0,"name":"签到","points":2,"value":0},{"iscomplete":0,"name":"邀请好友","points":500,"value":0},{"iscomplete":1,"name":"手机绑定","points":10,"value":0},{"iscomplete":1,"name":"设置头像","points":30,"value":0},{"iscomplete":1,"name":"首次使用付费服务","points":50,"value":0},{"iscomplete":0,"name":"发帖一次","points":50,"value":0},{"iscomplete":0,"name":"分享文章","points":10,"value":0},{"iscomplete":0,"name":"评论一次","points":5,"value":0},{"iscomplete":0,"name":"反馈意见并被接纳","points":50,"value":0}]
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-03-01 17:39:15
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
         * iscomplete : 1
         * name : 新用户注册
         * points : 300
         * value : 0
         */

        private String iscomplete;
        private String name;
        private String points;
        private String value;

        public String getIscomplete() {
            return iscomplete;
        }

        public void setIscomplete(String iscomplete) {
            this.iscomplete = iscomplete;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.iscomplete);
            dest.writeString(this.name);
            dest.writeString(this.points);
            dest.writeString(this.value);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.iscomplete = in.readString();
            this.name = in.readString();
            this.points = in.readString();
            this.value = in.readString();
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

    public EarnPointsBean() {
    }

    protected EarnPointsBean(Parcel in) {
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

    public static final Creator<EarnPointsBean> CREATOR = new Creator<EarnPointsBean>() {
        @Override
        public EarnPointsBean createFromParcel(Parcel source) {
            return new EarnPointsBean(source);
        }

        @Override
        public EarnPointsBean[] newArray(int size) {
            return new EarnPointsBean[size];
        }
    };
}
