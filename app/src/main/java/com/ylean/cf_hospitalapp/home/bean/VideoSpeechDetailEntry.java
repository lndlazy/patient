package com.ylean.cf_hospitalapp.home.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/8.
 */

public class VideoSpeechDetailEntry extends Basebean {

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

        private String browsecount;
        private String collectcount;
        private String commentcount;
        private String content;
        private String doctorid;
        private String doctorimg;
        private String doctorname;
        private String doctortitle;
        private String eductdesc;
        private String fabulouscount;
        private String fileid;
        private String hopedesc;
        private String hospitalid;
        private String hospitalname;
        private String id;
        private String imgurl;
        private String iscollectdoc;
        private String iscollectlive;
        private String looktype;
        private String startime;
        private String startimetr;
        private String status;
        private String timedesc;
        private String title;
        private String type;
        private String videourl;

        public String getBrowsecount() {
            return browsecount;
        }

        public void setBrowsecount(String browsecount) {
            this.browsecount = browsecount;
        }

        public String getCollectcount() {
            return collectcount;
        }

        public void setCollectcount(String collectcount) {
            this.collectcount = collectcount;
        }

        public String getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(String commentcount) {
            this.commentcount = commentcount;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public String getDoctorimg() {
            return doctorimg;
        }

        public void setDoctorimg(String doctorimg) {
            this.doctorimg = doctorimg;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public String getDoctortitle() {
            return doctortitle;
        }

        public void setDoctortitle(String doctortitle) {
            this.doctortitle = doctortitle;
        }

        public String getEductdesc() {
            return eductdesc;
        }

        public void setEductdesc(String eductdesc) {
            this.eductdesc = eductdesc;
        }

        public String getFabulouscount() {
            return fabulouscount;
        }

        public void setFabulouscount(String fabulouscount) {
            this.fabulouscount = fabulouscount;
        }

        public String getFileid() {
            return fileid;
        }

        public void setFileid(String fileid) {
            this.fileid = fileid;
        }

        public String getHopedesc() {
            return hopedesc;
        }

        public void setHopedesc(String hopedesc) {
            this.hopedesc = hopedesc;
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

        public String getIscollectdoc() {
            return iscollectdoc;
        }

        public void setIscollectdoc(String iscollectdoc) {
            this.iscollectdoc = iscollectdoc;
        }

        public String getIscollectlive() {
            return iscollectlive;
        }

        public void setIscollectlive(String iscollectlive) {
            this.iscollectlive = iscollectlive;
        }

        public String getLooktype() {
            return looktype;
        }

        public void setLooktype(String looktype) {
            this.looktype = looktype;
        }

        public String getStartime() {
            return startime;
        }

        public void setStartime(String startime) {
            this.startime = startime;
        }

        public String getStartimetr() {
            return startimetr;
        }

        public void setStartimetr(String startimetr) {
            this.startimetr = startimetr;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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

        public String getVideourl() {
            return videourl;
        }

        public void setVideourl(String videourl) {
            this.videourl = videourl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.browsecount);
            dest.writeString(this.collectcount);
            dest.writeString(this.commentcount);
            dest.writeString(this.content);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorimg);
            dest.writeString(this.doctorname);
            dest.writeString(this.doctortitle);
            dest.writeString(this.eductdesc);
            dest.writeString(this.fabulouscount);
            dest.writeString(this.fileid);
            dest.writeString(this.hopedesc);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.id);
            dest.writeString(this.imgurl);
            dest.writeString(this.iscollectdoc);
            dest.writeString(this.iscollectlive);
            dest.writeString(this.looktype);
            dest.writeString(this.startime);
            dest.writeString(this.startimetr);
            dest.writeString(this.status);
            dest.writeString(this.timedesc);
            dest.writeString(this.title);
            dest.writeString(this.type);
            dest.writeString(this.videourl);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.browsecount = in.readString();
            this.collectcount = in.readString();
            this.commentcount = in.readString();
            this.content = in.readString();
            this.doctorid = in.readString();
            this.doctorimg = in.readString();
            this.doctorname = in.readString();
            this.doctortitle = in.readString();
            this.eductdesc = in.readString();
            this.fabulouscount = in.readString();
            this.fileid = in.readString();
            this.hopedesc = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.id = in.readString();
            this.imgurl = in.readString();
            this.iscollectdoc = in.readString();
            this.iscollectlive = in.readString();
            this.looktype = in.readString();
            this.startime = in.readString();
            this.startimetr = in.readString();
            this.status = in.readString();
            this.timedesc = in.readString();
            this.title = in.readString();
            this.type = in.readString();
            this.videourl = in.readString();
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

    public VideoSpeechDetailEntry() {
    }

    protected VideoSpeechDetailEntry(Parcel in) {
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

    public static final Creator<VideoSpeechDetailEntry> CREATOR = new Creator<VideoSpeechDetailEntry>() {
        @Override
        public VideoSpeechDetailEntry createFromParcel(Parcel source) {
            return new VideoSpeechDetailEntry(source);
        }

        @Override
        public VideoSpeechDetailEntry[] newArray(int size) {
            return new VideoSpeechDetailEntry[size];
        }
    };
}
