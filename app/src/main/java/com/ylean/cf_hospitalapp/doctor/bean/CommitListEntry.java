package com.ylean.cf_hospitalapp.doctor.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linaidao on 2019/1/11.
 */

public class CommitListEntry extends Basebean {

    /**
     * data : [{"content":"全五星好评哦哦","evaluateid":366,"evaluatename":"小鸡","evaluatetime":"2019-01-11 11:26:38","id":35,"imgs":["/upload/imgs/2765fc30-43be-419e-91b3-17a641330377.jpg","/upload/imgs/9ca9e310-11a7-4d83-8f08-4595332d9226.jpg"],"isdz":1,"isfollow":0,"livestars":5,"plimg":"/upload/imgs/0d3a5dd2-9859-4bf8-9ecc-59e19fbbd3d1.jpg","reply":[],"timedesc":"2小时前"},{"content":"不带饭v","evaluateid":366,"evaluatename":"小鸡","evaluatetime":"2019-01-09 18:38:12","id":32,"imgs":["/upload/imgs/47e21122-dcb1-4ab5-ac3b-0687edd7ab1e.jpg","/upload/imgs/2f7f95b2-9d1e-48ea-8a9f-5c8b43b02e48.jpg"],"isdz":1,"isfollow":0,"livestars":5,"plimg":"/upload/imgs/0d3a5dd2-9859-4bf8-9ecc-59e19fbbd3d1.jpg","reply":[],"timedesc":"昨天"},{"content":"锦绣江山都好都好快科技多久能到","evaluateid":366,"evaluatename":"小鸡","evaluatetime":"2019-01-09 17:57:39","id":31,"imgs":[""],"isdz":1,"isfollow":0,"livestars":5,"plimg":"/upload/imgs/0d3a5dd2-9859-4bf8-9ecc-59e19fbbd3d1.jpg","reply":[],"timedesc":"昨天"},{"content":"全五星好评","evaluateid":366,"evaluatename":"小鸡","evaluatetime":"2019-01-09 16:54:07","id":28,"imgs":[""],"isdz":1,"isfollow":0,"livestars":3,"plimg":"/upload/imgs/0d3a5dd2-9859-4bf8-9ecc-59e19fbbd3d1.jpg","reply":[],"timedesc":"昨天"}]
     * maxRow : 4
     * page : 0
     * pageIndex : 1
     * pageSize : 0
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
         * content : 全五星好评哦哦
         * evaluateid : 366
         * evaluatename : 小鸡
         * evaluatetime : 2019-01-11 11:26:38
         * id : 35
         * imgs : ["/upload/imgs/2765fc30-43be-419e-91b3-17a641330377.jpg","/upload/imgs/9ca9e310-11a7-4d83-8f08-4595332d9226.jpg"]
         * isdz : 1
         * isfollow : 0
         * livestars : 5
         * plimg : /upload/imgs/0d3a5dd2-9859-4bf8-9ecc-59e19fbbd3d1.jpg
         * reply : []
         * timedesc : 2小时前
         */

        private String content;
        private String evaluateid;
        private String evaluatename;
        private String evaluatetime;
        private String id;
        private String isdz;
        private String isfollow;
        private String livestars;
        private String plimg;
        private String timedesc;
        private List<String> imgs;
//        private List<T> reply;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getEvaluateid() {
            return evaluateid;
        }

        public void setEvaluateid(String evaluateid) {
            this.evaluateid = evaluateid;
        }

        public String getEvaluatename() {
            return evaluatename;
        }

        public void setEvaluatename(String evaluatename) {
            this.evaluatename = evaluatename;
        }

        public String getEvaluatetime() {
            return evaluatetime;
        }

        public void setEvaluatetime(String evaluatetime) {
            this.evaluatetime = evaluatetime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsdz() {
            return isdz;
        }

        public void setIsdz(String isdz) {
            this.isdz = isdz;
        }

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }

        public String getLivestars() {
            return livestars;
        }

        public void setLivestars(String livestars) {
            this.livestars = livestars;
        }

        public String getPlimg() {
            return plimg;
        }

        public void setPlimg(String plimg) {
            this.plimg = plimg;
        }

        public String getTimedesc() {
            return timedesc;
        }

        public void setTimedesc(String timedesc) {
            this.timedesc = timedesc;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

//        public List<?> getReply() {
//            return reply;
//        }
//
//        public void setReply(List<?> reply) {
//            this.reply = reply;
//        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.content);
            dest.writeString(this.evaluateid);
            dest.writeString(this.evaluatename);
            dest.writeString(this.evaluatetime);
            dest.writeString(this.id);
            dest.writeString(this.isdz);
            dest.writeString(this.isfollow);
            dest.writeString(this.livestars);
            dest.writeString(this.plimg);
            dest.writeString(this.timedesc);
            dest.writeStringList(this.imgs);
//            dest.writeList(this.reply);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.content = in.readString();
            this.evaluateid = in.readString();
            this.evaluatename = in.readString();
            this.evaluatetime = in.readString();
            this.id = in.readString();
            this.isdz = in.readString();
            this.isfollow = in.readString();
            this.livestars = in.readString();
            this.plimg = in.readString();
            this.timedesc = in.readString();
            this.imgs = in.createStringArrayList();

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

    public CommitListEntry() {
    }

    protected CommitListEntry(Parcel in) {
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

    public static final Creator<CommitListEntry> CREATOR = new Creator<CommitListEntry>() {
        @Override
        public CommitListEntry createFromParcel(Parcel source) {
            return new CommitListEntry(source);
        }

        @Override
        public CommitListEntry[] newArray(int size) {
            return new CommitListEntry[size];
        }
    };
}
