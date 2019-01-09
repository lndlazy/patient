package com.ylean.cf_hospitalapp.popular.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2018/12/27.
 */

public class ExpertEntry extends Basebean {

    private int maxRow;
    private int page;
    private int pageIndex;
    private int pageSize;
    private String startTime;
    private int sum;
    private String titleList;
    private String token;
    private String totalmoney;
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

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {

        private int browsecount;
        private int collectcount;
        private int commentcount;
        private String content;
        private String dated;
        private String dateh;
        private String docimg;
        private String doctitle;
        private String doctorid;
        private String doctorname;
        private int fabulouscount;
        private String hospitalid;
        private String hospitalname;
        private String id;
        private String imgurl;
        private int ishot;
        private int isrecommed;
        private int status;
        private String timedesc;
        private String title;
        private String type;
        private String updatetime;
        private String updatetimetr;

        public int getBrowsecount() {
            return browsecount;
        }

        public void setBrowsecount(int browsecount) {
            this.browsecount = browsecount;
        }

        public int getCollectcount() {
            return collectcount;
        }

        public void setCollectcount(int collectcount) {
            this.collectcount = collectcount;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDated() {
            return dated;
        }

        public void setDated(String dated) {
            this.dated = dated;
        }

        public String getDateh() {
            return dateh;
        }

        public void setDateh(String dateh) {
            this.dateh = dateh;
        }

        public String getDocimg() {
            return docimg;
        }

        public void setDocimg(String docimg) {
            this.docimg = docimg;
        }

        public String getDoctitle() {
            return doctitle;
        }

        public void setDoctitle(String doctitle) {
            this.doctitle = doctitle;
        }

        public String getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public int getFabulouscount() {
            return fabulouscount;
        }

        public void setFabulouscount(int fabulouscount) {
            this.fabulouscount = fabulouscount;
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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getIshot() {
            return ishot;
        }

        public void setIshot(int ishot) {
            this.ishot = ishot;
        }

        public int getIsrecommed() {
            return isrecommed;
        }

        public void setIsrecommed(int isrecommed) {
            this.isrecommed = isrecommed;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTimedesc() {
            return timedesc;
        }

        public void setTimedesc(String timedesc) {
            this.timedesc = timedesc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getUpdatetimetr() {
            return updatetimetr;
        }

        public void setUpdatetimetr(String updatetimetr) {
            this.updatetimetr = updatetimetr;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.browsecount);
            dest.writeInt(this.collectcount);
            dest.writeInt(this.commentcount);
            dest.writeString(this.content);
            dest.writeString(this.dated);
            dest.writeString(this.dateh);
            dest.writeString(this.docimg);
            dest.writeString(this.doctitle);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorname);
            dest.writeInt(this.fabulouscount);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.id);
            dest.writeString(this.imgurl);
            dest.writeInt(this.ishot);
            dest.writeInt(this.isrecommed);
            dest.writeInt(this.status);
            dest.writeString(this.timedesc);
            dest.writeString(this.title);
            dest.writeString(this.type);
            dest.writeString(this.updatetime);
            dest.writeString(this.updatetimetr);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.browsecount = in.readInt();
            this.collectcount = in.readInt();
            this.commentcount = in.readInt();
            this.content = in.readString();
            this.dated = in.readString();
            this.dateh = in.readString();
            this.docimg = in.readString();
            this.doctitle = in.readString();
            this.doctorid = in.readString();
            this.doctorname = in.readString();
            this.fabulouscount = in.readInt();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.id = in.readString();
            this.imgurl = in.readString();
            this.ishot = in.readInt();
            this.isrecommed = in.readInt();
            this.status = in.readInt();
            this.timedesc = in.readString();
            this.title = in.readString();
            this.type = in.readString();
            this.updatetime = in.readString();
            this.updatetimetr = in.readString();
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

    public ExpertEntry() {
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
        dest.writeString(this.totalmoney);
        dest.writeTypedList(this.data);
    }

    protected ExpertEntry(Parcel in) {
        super(in);
        this.maxRow = in.readInt();
        this.page = in.readInt();
        this.pageIndex = in.readInt();
        this.pageSize = in.readInt();
        this.startTime = in.readString();
        this.sum = in.readInt();
        this.titleList = in.readString();
        this.token = in.readString();
        this.totalmoney = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<ExpertEntry> CREATOR = new Creator<ExpertEntry>() {
        @Override
        public ExpertEntry createFromParcel(Parcel source) {
            return new ExpertEntry(source);
        }

        @Override
        public ExpertEntry[] newArray(int size) {
            return new ExpertEntry[size];
        }
    };
}
