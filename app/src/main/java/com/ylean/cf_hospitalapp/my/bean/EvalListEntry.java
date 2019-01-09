package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/9.
 */

public class EvalListEntry extends Basebean {

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
         * commenttype : 3
         * content : 全五星好评
         * doctorname : 赵利杰
         * fwskuname :
         * id : 28
         * relateid : 123
         * spskuname :
         */

        private String commenttype;
        private String content;
        private String doctorname;
        private String fwskuname;
        private String id;
        private String relateid;
        private String spskuname;

        public String getCommenttype() {
            return commenttype;
        }

        public void setCommenttype(String commenttype) {
            this.commenttype = commenttype;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public String getFwskuname() {
            return fwskuname;
        }

        public void setFwskuname(String fwskuname) {
            this.fwskuname = fwskuname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRelateid() {
            return relateid;
        }

        public void setRelateid(String relateid) {
            this.relateid = relateid;
        }

        public String getSpskuname() {
            return spskuname;
        }

        public void setSpskuname(String spskuname) {
            this.spskuname = spskuname;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.commenttype);
            dest.writeString(this.content);
            dest.writeString(this.doctorname);
            dest.writeString(this.fwskuname);
            dest.writeString(this.id);
            dest.writeString(this.relateid);
            dest.writeString(this.spskuname);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.commenttype = in.readString();
            this.content = in.readString();
            this.doctorname = in.readString();
            this.fwskuname = in.readString();
            this.id = in.readString();
            this.relateid = in.readString();
            this.spskuname = in.readString();
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

    public EvalListEntry() {
    }

    protected EvalListEntry(Parcel in) {
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

    public static final Creator<EvalListEntry> CREATOR = new Creator<EvalListEntry>() {
        @Override
        public EvalListEntry createFromParcel(Parcel source) {
            return new EvalListEntry(source);
        }

        @Override
        public EvalListEntry[] newArray(int size) {
            return new EvalListEntry[size];
        }
    };
}
