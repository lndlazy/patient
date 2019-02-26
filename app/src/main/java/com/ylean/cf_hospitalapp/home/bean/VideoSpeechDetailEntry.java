package com.ylean.cf_hospitalapp.home.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/8.
 */

public class VideoSpeechDetailEntry extends Basebean {

    /**
     * data : {"browsecount":31,"collectcount":0,"commentcount":3,"content":"大腿外侧肌腱静脉畸形患者分享","doctorid":1,"doctorimg":"/upload/imgs/80b650ca-9824-4bd8-9785-cd865eac96f0.jpg","doctorname":"王亚飞","doctortitle":"主治医师","eductdesc":"毕业于湖北中医学院","fabulouscount":0,"fileid":"1271550540213841","hopedesc":"用我的爱心点燃您生命之火，用我的医术解除您疾病之苦。","hospitalid":3,"hospitalname":"北京长峰医院","id":274,"imgurl":"/upload/imgs/7a65d636-59cd-4f0b-8846-860cb1a961ba.jpg","iscollectdoc":1,"iscollectlive":0,"isfabulous":0,"looktype":0,"startime":"2019-02-19 09:01:20","startimetr":"2019-02-19 09:01:20","status":2,"timedesc":"2小时前","title":"大腿外侧肌腱静脉畸形患者分享","type":2,"videourl":"https://apd-ef6e399c23807847f2601f3d3a790eb5.v.smtcdns.com/vhot2.qqvideo.tc.qq.com/AHnN91mRJr0o6GT-Y799tqjJfJ-2ZkJzNhIGd_CG5v2o/d0806uj1iyw.mp4?sdtfrom=v1010&guid=a4b403fbc558c8b45fcf4860f559c1a5&vkey=D334169DCC59F1F119737609F211C0A3080F8F71F13DB0BB0293AB021E7E8E995E734611122F9FA3012B9A130FBAEF01D6FFFCAB084113B2936D6F36E40454CDD7061336BB00527BE561A914DE5D4C4C8D6F66FB89E53802D35266FD5888B9642843418DA78F8581606BF8EDED2DB9FF7859BAF6D65A716B"}
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-02-19 11:32:39
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
         * browsecount : 31
         * collectcount : 0
         * commentcount : 3
         * content : 大腿外侧肌腱静脉畸形患者分享
         * doctorid : 1
         * doctorimg : /upload/imgs/80b650ca-9824-4bd8-9785-cd865eac96f0.jpg
         * doctorname : 王亚飞
         * doctortitle : 主治医师
         * eductdesc : 毕业于湖北中医学院
         * fabulouscount : 0
         * fileid : 1271550540213841
         * hopedesc : 用我的爱心点燃您生命之火，用我的医术解除您疾病之苦。
         * hospitalid : 3
         * hospitalname : 北京长峰医院
         * id : 274
         * imgurl : /upload/imgs/7a65d636-59cd-4f0b-8846-860cb1a961ba.jpg
         * iscollectdoc : 1
         * iscollectlive : 0
         * isfabulous : 0
         * looktype : 0
         * startime : 2019-02-19 09:01:20
         * startimetr : 2019-02-19 09:01:20
         * status : 2
         * timedesc : 2小时前
         * title : 大腿外侧肌腱静脉畸形患者分享
         * type : 2
         * videourl : https://apd-ef6e399c23807847f2601f3d3a790eb5.v.smtcdns.com/vhot2.qqvideo.tc.qq.com/AHnN91mRJr0o6GT-Y799tqjJfJ-2ZkJzNhIGd_CG5v2o/d0806uj1iyw.mp4?sdtfrom=v1010&guid=a4b403fbc558c8b45fcf4860f559c1a5&vkey=D334169DCC59F1F119737609F211C0A3080F8F71F13DB0BB0293AB021E7E8E995E734611122F9FA3012B9A130FBAEF01D6FFFCAB084113B2936D6F36E40454CDD7061336BB00527BE561A914DE5D4C4C8D6F66FB89E53802D35266FD5888B9642843418DA78F8581606BF8EDED2DB9FF7859BAF6D65A716B
         */

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
        private String isfabulous;
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

        public String getIsfabulous() {
            return isfabulous;
        }

        public void setIsfabulous(String isfabulous) {
            this.isfabulous = isfabulous;
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
            dest.writeString(this.isfabulous);
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
            this.isfabulous = in.readString();
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
