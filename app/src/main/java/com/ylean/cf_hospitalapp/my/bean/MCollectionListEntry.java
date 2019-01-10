package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/9.
 */

public class MCollectionListEntry extends Basebean{

    /**
     * data : [{"collectid":51,"docimg":"/upload/imgs/pro/a4944d31-86cc-475f-832c-487c732aec19.png","doctorname":"陈会通（因联测试勿删）","hospitalname":"北京长峰医院","imgurl":"/upload/imgs/user/5b0e3927-f6ee-4502-899b-fb20394a9f80.jpg","liveid":185,"livetime":"2018-05-11 14:31:10","pageview":0,"praisenum":0,"timedesc":"8月前","title":"治疗血管瘤的方法","titlename":"主任医师"}]
     * maxRow : 1
     * page : 0
     * pageIndex : 1
     * pageSize : 0
     * startTime : 2019-01-09 20:34:38
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
         * collectid : 51
         * docimg : /upload/imgs/pro/a4944d31-86cc-475f-832c-487c732aec19.png
         * doctorname : 陈会通（因联测试勿删）
         * hospitalname : 北京长峰医院
         * imgurl : /upload/imgs/user/5b0e3927-f6ee-4502-899b-fb20394a9f80.jpg
         * liveid : 185
         * livetime : 2018-05-11 14:31:10
         * pageview : 0
         * praisenum : 0
         * timedesc : 8月前
         * title : 治疗血管瘤的方法
         * titlename : 主任医师
         */

        private String collectid;
        private String docimg;
        private String doctorname;
        private String hospitalname;
        private String imgurl;
        private String liveid;
        private String livetime;
        private String pageview;
        private String praisenum;
        private String timedesc;
        private String title;
        private String informationid;
        private String titlename;
        private String type;

        public String getInformationid() {
            return informationid;
        }

        public void setInformationid(String informationid) {
            this.informationid = informationid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCollectid() {
            return collectid;
        }

        public void setCollectid(String collectid) {
            this.collectid = collectid;
        }

        public String getDocimg() {
            return docimg;
        }

        public void setDocimg(String docimg) {
            this.docimg = docimg;
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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getLiveid() {
            return liveid;
        }

        public void setLiveid(String liveid) {
            this.liveid = liveid;
        }

        public String getLivetime() {
            return livetime;
        }

        public void setLivetime(String livetime) {
            this.livetime = livetime;
        }

        public String getPageview() {
            return pageview;
        }

        public void setPageview(String pageview) {
            this.pageview = pageview;
        }

        public String getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(String praisenum) {
            this.praisenum = praisenum;
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

        public String getTitlename() {
            return titlename;
        }

        public void setTitlename(String titlename) {
            this.titlename = titlename;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.collectid);
            dest.writeString(this.docimg);
            dest.writeString(this.doctorname);
            dest.writeString(this.hospitalname);
            dest.writeString(this.imgurl);
            dest.writeString(this.liveid);
            dest.writeString(this.livetime);
            dest.writeString(this.pageview);
            dest.writeString(this.praisenum);
            dest.writeString(this.timedesc);
            dest.writeString(this.title);
            dest.writeString(this.informationid);
            dest.writeString(this.titlename);
            dest.writeString(this.type);
        }

        protected DataBean(Parcel in) {
            this.collectid = in.readString();
            this.docimg = in.readString();
            this.doctorname = in.readString();
            this.hospitalname = in.readString();
            this.imgurl = in.readString();
            this.liveid = in.readString();
            this.livetime = in.readString();
            this.pageview = in.readString();
            this.praisenum = in.readString();
            this.timedesc = in.readString();
            this.title = in.readString();
            this.informationid = in.readString();
            this.titlename = in.readString();
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

    public MCollectionListEntry() {
    }

    protected MCollectionListEntry(Parcel in) {
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

    public static final Creator<MCollectionListEntry> CREATOR = new Creator<MCollectionListEntry>() {
        @Override
        public MCollectionListEntry createFromParcel(Parcel source) {
            return new MCollectionListEntry(source);
        }

        @Override
        public MCollectionListEntry[] newArray(int size) {
            return new MCollectionListEntry[size];
        }
    };
}
