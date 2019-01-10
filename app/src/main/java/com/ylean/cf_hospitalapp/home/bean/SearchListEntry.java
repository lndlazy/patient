package com.ylean.cf_hospitalapp.home.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/10.
 */

public class SearchListEntry extends Basebean {


    /**
     * data : [{"browsecount":64,"collectcount":0,"commentcount":11,"content":"","dated":"11-20","dateh":"15:03","docimg":"/upload/imgs/pro/591ec8ff-ae63-4c71-bb81-8a7d4907bf28.jpg","doctitle":"主治医师","doctorid":1,"doctorname":"王亚飞","fabulouscount":1,"hospitalid":0,"hospitalname":"","id":581,"imgurl":"http://192.168.1.30/upload/imgs/news/e0bf2f7a-7855-4314-afb7-cf10742e25df.jpg","ishot":0,"isrecommed":0,"status":0,"timedesc":"1月前","title":"【盘点】常见的婴儿血管瘤","type":0,"updatetime":"2018-11-20 15:03:23","updatetimetr":"2018-11-20"}]
     * maxRow : 1
     * page : 1
     * pageIndex : 1
     * pageSize : 0
     * startTime : 2019-01-10 18:27:02
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
         * browsecount : 64
         * collectcount : 0
         * commentcount : 11
         * content :
         * dated : 11-20
         * dateh : 15:03
         * docimg : /upload/imgs/pro/591ec8ff-ae63-4c71-bb81-8a7d4907bf28.jpg
         * doctitle : 主治医师
         * doctorid : 1
         * doctorname : 王亚飞
         * fabulouscount : 1
         * hospitalid : 0
         * hospitalname :
         * id : 581
         * imgurl : http://192.168.1.30/upload/imgs/news/e0bf2f7a-7855-4314-afb7-cf10742e25df.jpg
         * ishot : 0
         * isrecommed : 0
         * status : 0
         * timedesc : 1月前
         * title : 【盘点】常见的婴儿血管瘤
         * type : 0
         * updatetime : 2018-11-20 15:03:23
         * updatetimetr : 2018-11-20
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


        private String answer;
        private String consultaid;
        private String createtime;
        private String createtimetr;
        private String problem;
        private String userimg;
        private String username;


        private String adeptdesc;
        private String departid;
        private String departname;
        private String dhprice;
        private String dhstatus;
//        private String doctorname;
//        private String doctorid;
        private String doctortitle;
//        private String hospitalid;
//        private String hospitalname;
//        private String imgurl;
        private String phone;
        private String spprice;
        private String spstatus;
        private String twprice;
        private String twstatus;

        private String address;
        private String areaname;
        private String cityname;
//        private String hospitalid;
//        private String hospitalname;
//        private String imgurl;
        private String latitude;
        private String longitude;
        private String provincename;
        private String supportel;

        private int searchType;

        public int getSearchType() {
            return searchType;
        }

        public void setSearchType(int searchType) {
            this.searchType = searchType;
        }

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

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getConsultaid() {
            return consultaid;
        }

        public void setConsultaid(String consultaid) {
            this.consultaid = consultaid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getCreatetimetr() {
            return createtimetr;
        }

        public void setCreatetimetr(String createtimetr) {
            this.createtimetr = createtimetr;
        }

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getUserimg() {
            return userimg;
        }

        public void setUserimg(String userimg) {
            this.userimg = userimg;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAdeptdesc() {
            return adeptdesc;
        }

        public void setAdeptdesc(String adeptdesc) {
            this.adeptdesc = adeptdesc;
        }

        public String getDepartid() {
            return departid;
        }

        public void setDepartid(String departid) {
            this.departid = departid;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getDhprice() {
            return dhprice;
        }

        public void setDhprice(String dhprice) {
            this.dhprice = dhprice;
        }

        public String getDhstatus() {
            return dhstatus;
        }

        public void setDhstatus(String dhstatus) {
            this.dhstatus = dhstatus;
        }

        public String getDoctortitle() {
            return doctortitle;
        }

        public void setDoctortitle(String doctortitle) {
            this.doctortitle = doctortitle;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSpprice() {
            return spprice;
        }

        public void setSpprice(String spprice) {
            this.spprice = spprice;
        }

        public String getSpstatus() {
            return spstatus;
        }

        public void setSpstatus(String spstatus) {
            this.spstatus = spstatus;
        }

        public String getTwprice() {
            return twprice;
        }

        public void setTwprice(String twprice) {
            this.twprice = twprice;
        }

        public String getTwstatus() {
            return twstatus;
        }

        public void setTwstatus(String twstatus) {
            this.twstatus = twstatus;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public String getSupportel() {
            return supportel;
        }

        public void setSupportel(String supportel) {
            this.supportel = supportel;
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
            dest.writeString(this.answer);
            dest.writeString(this.consultaid);
            dest.writeString(this.createtime);
            dest.writeString(this.createtimetr);
            dest.writeString(this.problem);
            dest.writeString(this.userimg);
            dest.writeString(this.username);
            dest.writeString(this.adeptdesc);
            dest.writeString(this.departid);
            dest.writeString(this.departname);
            dest.writeString(this.dhprice);
            dest.writeString(this.dhstatus);
            dest.writeString(this.doctortitle);
            dest.writeString(this.phone);
            dest.writeString(this.spprice);
            dest.writeString(this.spstatus);
            dest.writeString(this.twprice);
            dest.writeString(this.twstatus);
            dest.writeString(this.address);
            dest.writeString(this.areaname);
            dest.writeString(this.cityname);
            dest.writeString(this.latitude);
            dest.writeString(this.longitude);
            dest.writeString(this.provincename);
            dest.writeString(this.supportel);
            dest.writeInt(this.searchType);
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
            this.answer = in.readString();
            this.consultaid = in.readString();
            this.createtime = in.readString();
            this.createtimetr = in.readString();
            this.problem = in.readString();
            this.userimg = in.readString();
            this.username = in.readString();
            this.adeptdesc = in.readString();
            this.departid = in.readString();
            this.departname = in.readString();
            this.dhprice = in.readString();
            this.dhstatus = in.readString();
            this.doctortitle = in.readString();
            this.phone = in.readString();
            this.spprice = in.readString();
            this.spstatus = in.readString();
            this.twprice = in.readString();
            this.twstatus = in.readString();
            this.address = in.readString();
            this.areaname = in.readString();
            this.cityname = in.readString();
            this.latitude = in.readString();
            this.longitude = in.readString();
            this.provincename = in.readString();
            this.supportel = in.readString();
            this.searchType = in.readInt();
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

    public SearchListEntry() {
    }

    protected SearchListEntry(Parcel in) {
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

    public static final Creator<SearchListEntry> CREATOR = new Creator<SearchListEntry>() {
        @Override
        public SearchListEntry createFromParcel(Parcel source) {
            return new SearchListEntry(source);
        }

        @Override
        public SearchListEntry[] newArray(int size) {
            return new SearchListEntry[size];
        }
    };
}
