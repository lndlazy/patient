package com.ylean.cf_hospitalapp.doctor.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/11.
 */

public class InquiryListEntry extends Basebean {

    /**
     * data : [{"answer":"","consultaid":131,"createtime":"2019-01-09 18:37:17","createtimetr":"2019-01-09","problem":"付G8v句好v好","userimg":"","username":"小鸡"},{"answer":"","consultaid":130,"createtime":"2019-01-09 17:56:45","createtimetr":"2019-01-09","problem":"比你大觉得见到你","userimg":"","username":"小鸡"}]
     * maxRow : 57
     * page : 29
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
         * answer :
         * consultaid : 131
         * createtime : 2019-01-09 18:37:17
         * createtimetr : 2019-01-09
         * problem : 付G8v句好v好
         * userimg :
         * username : 小鸡
         */

        private String answer;
        private String consultaid;
        private String createtime;
        private String createtimetr;
        private String problem;
        private String userimg;
        private String username;

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.answer);
            dest.writeString(this.consultaid);
            dest.writeString(this.createtime);
            dest.writeString(this.createtimetr);
            dest.writeString(this.problem);
            dest.writeString(this.userimg);
            dest.writeString(this.username);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.answer = in.readString();
            this.consultaid = in.readString();
            this.createtime = in.readString();
            this.createtimetr = in.readString();
            this.problem = in.readString();
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

    public InquiryListEntry() {
    }

    protected InquiryListEntry(Parcel in) {
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

    public static final Creator<InquiryListEntry> CREATOR = new Creator<InquiryListEntry>() {
        @Override
        public InquiryListEntry createFromParcel(Parcel source) {
            return new InquiryListEntry(source);
        }

        @Override
        public InquiryListEntry[] newArray(int size) {
            return new InquiryListEntry[size];
        }
    };
}
