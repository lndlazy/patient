package com.ylean.cf_hospitalapp.doctor.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/11.
 */

public class VideoListEntry extends Basebean {

    /**
     * data : [{"browsecount":0,"collectcount":0,"commentcount":0,"content":"","dated":"05-11","dateh":"14:31","docimg":"/upload/imgs/pro/a4944d31-86cc-475f-832c-487c732aec19.png","doctitle":"主任医师","doctorid":137,"doctorname":"陈会通（因联测试勿删）","fabulouscount":0,"hospitalid":3,"hospitalname":"北京长峰医院","id":185,"imgurl":"/upload/imgs/user/5b0e3927-f6ee-4502-899b-fb20394a9f80.jpg","ishot":0,"isrecommed":0,"status":2,"timedesc":"8月前","title":"治疗血管瘤的方法","type":2,"updatetime":"2018-05-11 14:31:10","updatetimetr":"2018-05-11"},{"browsecount":0,"collectcount":0,"commentcount":0,"content":"","dated":"05-11","dateh":"09:18","docimg":"/upload/imgs/pro/a4944d31-86cc-475f-832c-487c732aec19.png","doctitle":"主任医师","doctorid":137,"doctorname":"陈会通（因联测试勿删）","fabulouscount":0,"hospitalid":3,"hospitalname":"北京长峰医院","id":181,"imgurl":"/upload/imgs/user/07df7a0d-365a-4b35-975d-fc3fce13d4b4.jpg","ishot":0,"isrecommed":0,"status":2,"timedesc":"8月前","title":"血管瘤治疗","type":2,"updatetime":"2018-05-11 09:18:27","updatetimetr":"2018-05-11"}]
     * maxRow : 4
     * page : 2
     * pageIndex : 1
     * pageSize : 2
     * startTime : 2019-01-11 13:48:09
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
         * browsecount : 0
         * collectcount : 0
         * commentcount : 0
         * content :
         * dated : 05-11
         * dateh : 14:31
         * docimg : /upload/imgs/pro/a4944d31-86cc-475f-832c-487c732aec19.png
         * doctitle : 主任医师
         * doctorid : 137
         * doctorname : 陈会通（因联测试勿删）
         * fabulouscount : 0
         * hospitalid : 3
         * hospitalname : 北京长峰医院
         * id : 185
         * imgurl : /upload/imgs/user/5b0e3927-f6ee-4502-899b-fb20394a9f80.jpg
         * ishot : 0
         * isrecommed : 0
         * status : 2
         * timedesc : 8月前
         * title : 治疗血管瘤的方法
         * type : 2
         * updatetime : 2018-05-11 14:31:10
         * updatetimetr : 2018-05-11
         */

        private String browsecount;
        private String collectcount;
        private String commentcount;
        private String content;
        private String dated;
        private String dateh;
        private String docimg;
        private String doctitle;
        private String doctorid;
        private String doctorname;
        private String fabulouscount;
        private String hospitalid;
        private String hospitalname;
        private String id;
        private String imgurl;
        private String ishot;
        private String isrecommed;
        private String status;
        private String timedesc;
        private String title;
        private String type;
        private String updatetime;
        private String updatetimetr;

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

        public String getFabulouscount() {
            return fabulouscount;
        }

        public void setFabulouscount(String fabulouscount) {
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

        public String getIshot() {
            return ishot;
        }

        public void setIshot(String ishot) {
            this.ishot = ishot;
        }

        public String getIsrecommed() {
            return isrecommed;
        }

        public void setIsrecommed(String isrecommed) {
            this.isrecommed = isrecommed;
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
            dest.writeString(this.browsecount);
            dest.writeString(this.collectcount);
            dest.writeString(this.commentcount);
            dest.writeString(this.content);
            dest.writeString(this.dated);
            dest.writeString(this.dateh);
            dest.writeString(this.docimg);
            dest.writeString(this.doctitle);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorname);
            dest.writeString(this.fabulouscount);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.id);
            dest.writeString(this.imgurl);
            dest.writeString(this.ishot);
            dest.writeString(this.isrecommed);
            dest.writeString(this.status);
            dest.writeString(this.timedesc);
            dest.writeString(this.title);
            dest.writeString(this.type);
            dest.writeString(this.updatetime);
            dest.writeString(this.updatetimetr);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.browsecount = in.readString();
            this.collectcount = in.readString();
            this.commentcount = in.readString();
            this.content = in.readString();
            this.dated = in.readString();
            this.dateh = in.readString();
            this.docimg = in.readString();
            this.doctitle = in.readString();
            this.doctorid = in.readString();
            this.doctorname = in.readString();
            this.fabulouscount = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.id = in.readString();
            this.imgurl = in.readString();
            this.ishot = in.readString();
            this.isrecommed = in.readString();
            this.status = in.readString();
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

    public VideoListEntry() {
    }

    protected VideoListEntry(Parcel in) {
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

    public static final Creator<VideoListEntry> CREATOR = new Creator<VideoListEntry>() {
        @Override
        public VideoListEntry createFromParcel(Parcel source) {
            return new VideoListEntry(source);
        }

        @Override
        public VideoListEntry[] newArray(int size) {
            return new VideoListEntry[size];
        }
    };
}
