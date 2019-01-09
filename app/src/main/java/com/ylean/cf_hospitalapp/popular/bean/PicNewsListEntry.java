//package com.ylean.cf_hospitalapp.popular.bean;
//
//import android.os.Parcel;
//
//import com.ylean.cf_hospitalapp.base.bean.Basebean;
//
//import java.util.List;
//
///**
// * Created by linaidao on 2019/1/8.
// */
//
//public class PicNewsListEntry extends Basebean {
//
//    private int maxRow;
//    private int page;
//    private int pageIndex;
//    private int pageSize;
//    private String startTime;
//    private int sum;
//    private String titleList;
//    private String token;
//    private int totalmoney;
//    private List<DataBean> data;
//
//    public int getMaxRow() {
//        return maxRow;
//    }
//
//    public void setMaxRow(int maxRow) {
//        this.maxRow = maxRow;
//    }
//
//    public int getPage() {
//        return page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public int getPageIndex() {
//        return pageIndex;
//    }
//
//    public void setPageIndex(int pageIndex) {
//        this.pageIndex = pageIndex;
//    }
//
//    public int getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
//
//    public String getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
//
//    public int getSum() {
//        return sum;
//    }
//
//    public void setSum(int sum) {
//        this.sum = sum;
//    }
//
//    public String getTitleList() {
//        return titleList;
//    }
//
//    public void setTitleList(String titleList) {
//        this.titleList = titleList;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public int getTotalmoney() {
//        return totalmoney;
//    }
//
//    public void setTotalmoney(int totalmoney) {
//        this.totalmoney = totalmoney;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean implements android.os.Parcelable {
//
//        private int browsecount;
//        private int commentcount;
//        private int fabulouscount;
//        private String id;
//        private String imgurl;
//        private String tabloid;
//        private String timedesc;
//        private String title;
//        private String updatetime;
//        private String updatetimetr;
//
//        public int getBrowsecount() {
//            return browsecount;
//        }
//
//        public void setBrowsecount(int browsecount) {
//            this.browsecount = browsecount;
//        }
//
//        public int getCommentcount() {
//            return commentcount;
//        }
//
//        public void setCommentcount(int commentcount) {
//            this.commentcount = commentcount;
//        }
//
//        public int getFabulouscount() {
//            return fabulouscount;
//        }
//
//        public void setFabulouscount(int fabulouscount) {
//            this.fabulouscount = fabulouscount;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getImgurl() {
//            return imgurl;
//        }
//
//        public void setImgurl(String imgurl) {
//            this.imgurl = imgurl;
//        }
//
//        public String getTabloid() {
//            return tabloid;
//        }
//
//        public void setTabloid(String tabloid) {
//            this.tabloid = tabloid;
//        }
//
//        public String getTimedesc() {
//            return timedesc;
//        }
//
//        public void setTimedesc(String timedesc) {
//            this.timedesc = timedesc;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getUpdatetime() {
//            return updatetime;
//        }
//
//        public void setUpdatetime(String updatetime) {
//            this.updatetime = updatetime;
//        }
//
//        public String getUpdatetimetr() {
//            return updatetimetr;
//        }
//
//        public void setUpdatetimetr(String updatetimetr) {
//            this.updatetimetr = updatetimetr;
//        }
//
//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeInt(this.browsecount);
//            dest.writeInt(this.commentcount);
//            dest.writeInt(this.fabulouscount);
//            dest.writeString(this.id);
//            dest.writeString(this.imgurl);
//            dest.writeString(this.tabloid);
//            dest.writeString(this.timedesc);
//            dest.writeString(this.title);
//            dest.writeString(this.updatetime);
//            dest.writeString(this.updatetimetr);
//        }
//
//        public DataBean() {
//        }
//
//        protected DataBean(Parcel in) {
//            this.browsecount = in.readInt();
//            this.commentcount = in.readInt();
//            this.fabulouscount = in.readInt();
//            this.id = in.readString();
//            this.imgurl = in.readString();
//            this.tabloid = in.readString();
//            this.timedesc = in.readString();
//            this.title = in.readString();
//            this.updatetime = in.readString();
//            this.updatetimetr = in.readString();
//        }
//
//        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
//            @Override
//            public DataBean createFromParcel(Parcel source) {
//                return new DataBean(source);
//            }
//
//            @Override
//            public DataBean[] newArray(int size) {
//                return new DataBean[size];
//            }
//        };
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        super.writeToParcel(dest, flags);
//        dest.writeInt(this.maxRow);
//        dest.writeInt(this.page);
//        dest.writeInt(this.pageIndex);
//        dest.writeInt(this.pageSize);
//        dest.writeString(this.startTime);
//        dest.writeInt(this.sum);
//        dest.writeString(this.titleList);
//        dest.writeString(this.token);
//        dest.writeInt(this.totalmoney);
//        dest.writeTypedList(this.data);
//    }
//
//    public PicNewsListEntry() {
//    }
//
//    protected PicNewsListEntry(Parcel in) {
//        super(in);
//        this.maxRow = in.readInt();
//        this.page = in.readInt();
//        this.pageIndex = in.readInt();
//        this.pageSize = in.readInt();
//        this.startTime = in.readString();
//        this.sum = in.readInt();
//        this.titleList = in.readString();
//        this.token = in.readString();
//        this.totalmoney = in.readInt();
//        this.data = in.createTypedArrayList(DataBean.CREATOR);
//    }
//
//    public static final Creator<PicNewsListEntry> CREATOR = new Creator<PicNewsListEntry>() {
//        @Override
//        public PicNewsListEntry createFromParcel(Parcel source) {
//            return new PicNewsListEntry(source);
//        }
//
//        @Override
//        public PicNewsListEntry[] newArray(int size) {
//            return new PicNewsListEntry[size];
//        }
//    };
//}
