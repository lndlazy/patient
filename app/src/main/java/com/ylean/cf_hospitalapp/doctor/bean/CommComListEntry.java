package com.ylean.cf_hospitalapp.doctor.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 *
 * Created by linaidao on 2019/1/11.
 */

public class CommComListEntry extends Basebean {

    /**
     * data : [{"content":"frfeggrs","evaluateid":291,"evaluatename":"","evaluatetime":"2018-11-19 17:20:14","id":8,"imgs":[],"isdz":1,"isfollow":0,"livestars":0,"plimg":"https://thirdwx.qlogo.cn/mmopen/vi_32/lPgP1IUpXxCcqicePYpopNa6iaYIjsdnfyxiclwXLlOYm6CIt6GThZqlLSNUYCOqgszgVAQofFVvoBWB9YT1eepmw/132","reply":[{"id":2,"replycontent":"分开了发","replyname":"小米"}],"timedesc":"1月前"}]
     * maxRow : 1
     * page : 0
     * pageIndex : 1
     * pageSize : 0
     * startTime : 2019-01-13 11:35:25
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
         * content : frfeggrs
         * evaluateid : 291
         * evaluatename :
         * evaluatetime : 2018-11-19 17:20:14
         * id : 8
         * imgs : []
         * isdz : 1
         * isfollow : 0
         * livestars : 0
         * plimg : https://thirdwx.qlogo.cn/mmopen/vi_32/lPgP1IUpXxCcqicePYpopNa6iaYIjsdnfyxiclwXLlOYm6CIt6GThZqlLSNUYCOqgszgVAQofFVvoBWB9YT1eepmw/132
         * reply : [{"id":2,"replycontent":"分开了发","replyname":"小米"}]
         * timedesc : 1月前
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
        private List<ReplyBean> reply;

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

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

        public List<ReplyBean> getReply() {
            return reply;
        }

        public void setReply(List<ReplyBean> reply) {
            this.reply = reply;
        }

        public static class ReplyBean implements android.os.Parcelable {
            /**
             * id : 2
             * replycontent : 分开了发
             * replyname : 小米
             */

            private String id;
            private String replycontent;
            private String replyname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getReplycontent() {
                return replycontent;
            }

            public void setReplycontent(String replycontent) {
                this.replycontent = replycontent;
            }

            public String getReplyname() {
                return replyname;
            }

            public void setReplyname(String replyname) {
                this.replyname = replyname;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.replycontent);
                dest.writeString(this.replyname);
            }

            public ReplyBean() {
            }

            protected ReplyBean(Parcel in) {
                this.id = in.readString();
                this.replycontent = in.readString();
                this.replyname = in.readString();
            }

            public static final Creator<ReplyBean> CREATOR = new Creator<ReplyBean>() {
                @Override
                public ReplyBean createFromParcel(Parcel source) {
                    return new ReplyBean(source);
                }

                @Override
                public ReplyBean[] newArray(int size) {
                    return new ReplyBean[size];
                }
            };
        }

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
            dest.writeTypedList(this.reply);
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
            this.reply = in.createTypedArrayList(ReplyBean.CREATOR);
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

    public CommComListEntry() {
    }

    protected CommComListEntry(Parcel in) {
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

    public static final Creator<CommComListEntry> CREATOR = new Creator<CommComListEntry>() {
        @Override
        public CommComListEntry createFromParcel(Parcel source) {
            return new CommComListEntry(source);
        }

        @Override
        public CommComListEntry[] newArray(int size) {
            return new CommComListEntry[size];
        }
    };
}
