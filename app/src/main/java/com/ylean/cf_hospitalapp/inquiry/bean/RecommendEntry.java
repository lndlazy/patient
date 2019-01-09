package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

public class RecommendEntry extends Basebean {

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

        private String answer;
        private int browsecount;
        private int commentcount;
        private String consultaid;
        private String createtime;
        private String createtimetr;
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
        private String problem;
        private String status;
        private String tabloid;
        private String timedesc;
        private String title;
        private String type;
        private String updatetime;
        private String updatetimetr;
        private String userimg;
        private String username;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getBrowsecount() {
            return browsecount;
        }

        public void setBrowsecount(int browsecount) {
            this.browsecount = browsecount;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
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

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTabloid() {
            return tabloid;
        }

        public void setTabloid(String tabloid) {
            this.tabloid = tabloid;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.answer);
            dest.writeInt(this.browsecount);
            dest.writeInt(this.commentcount);
            dest.writeString(this.consultaid);
            dest.writeString(this.createtime);
            dest.writeString(this.createtimetr);
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
            dest.writeString(this.problem);
            dest.writeString(this.status);
            dest.writeString(this.tabloid);
            dest.writeString(this.timedesc);
            dest.writeString(this.title);
            dest.writeString(this.type);
            dest.writeString(this.updatetime);
            dest.writeString(this.updatetimetr);
            dest.writeString(this.userimg);
            dest.writeString(this.username);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.answer = in.readString();
            this.browsecount = in.readInt();
            this.commentcount = in.readInt();
            this.consultaid = in.readString();
            this.createtime = in.readString();
            this.createtimetr = in.readString();
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
            this.problem = in.readString();
            this.status = in.readString();
            this.tabloid = in.readString();
            this.timedesc = in.readString();
            this.title = in.readString();
            this.type = in.readString();
            this.updatetime = in.readString();
            this.updatetimetr = in.readString();
            this.userimg = in.readString();
            this.username = in.readString();
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

    public RecommendEntry() {
    }

    protected RecommendEntry(Parcel in) {
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

    public static final Creator<RecommendEntry> CREATOR = new Creator<RecommendEntry>() {
        @Override
        public RecommendEntry createFromParcel(Parcel source) {
            return new RecommendEntry(source);
        }

        @Override
        public RecommendEntry[] newArray(int size) {
            return new RecommendEntry[size];
        }
    };
}
