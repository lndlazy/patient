package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/10.
 */

public class NewsListEntry extends Basebean {

    /**
     * data : [{"content":"尊敬的用户,您的问诊服务订单已支付成功，单号Q20190109183700015，请及时和医生沟通。","id":5842,"sendtime":"2019-01-09 18:37:35","status":0,"title":"支付成功","type":2},{"content":"尊敬的用户,您的问诊服务订单已支付成功，单号Q20190109175600014，请及时和医生沟通。","id":5840,"sendtime":"2019-01-09 17:56:56","status":0,"title":"支付成功","type":2},{"content":"尊敬的用户,您的问诊服务订单已支付成功，单号Q20190109135500007，请及时和医生沟通。","id":5826,"sendtime":"2019-01-09 13:55:45","status":0,"title":"支付成功","type":2},{"content":"尊敬的用户,您的问诊服务订单已支付成功，单号Q20190109133500006，请及时和医生沟通。","id":5824,"sendtime":"2019-01-09 13:36:22","status":0,"title":"支付成功","type":2},{"content":"尊敬的用户,您的问诊服务订单已支付成功，单号Q20190109105800002，请及时和医生沟通。","id":5820,"sendtime":"2019-01-09 10:58:34","status":0,"title":"支付成功","type":2},{"content":"尊敬的用户,您的预约挂号订单已支付成功，单号O20190108175100001，请及时去医院就诊。","id":5818,"sendtime":"2019-01-08 17:51:39","status":0,"title":"支付成功","type":2},{"content":"尊敬的用户,您的问诊服务订单已支付成功，单号Q20190108135200024，请及时和医生沟通。","id":5816,"sendtime":"2019-01-08 16:25:23","status":0,"title":"支付成功","type":2},{"content":"尊敬的用户,您的预约挂号订单已支付成功，单号O20190107110200001，请及时去医院就诊。","id":5807,"sendtime":"2019-01-07 11:02:48","status":0,"title":"支付成功","type":2},{"content":"尊敬的用户,您的预约挂号订单已支付成功，单号O20190106194000002，请及时去医院就诊。","id":5805,"sendtime":"2019-01-07 10:58:17","status":0,"title":"支付成功","type":2}]
     * maxRow : 9
     * page : 0
     * pageIndex : 1
     * pageSize : 0
     * startTime : 2019-01-10 00:48:54
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
         * content : 尊敬的用户,您的问诊服务订单已支付成功，单号Q20190109183700015，请及时和医生沟通。
         * id : 5842
         * sendtime : 2019-01-09 18:37:35
         * status : 0
         * title : 支付成功
         * type : 2
         */

        private String content;
        private String id;
        private String sendtime;
        private String status;
        private String title;
        private String type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.content);
            dest.writeString(this.id);
            dest.writeString(this.sendtime);
            dest.writeString(this.status);
            dest.writeString(this.title);
            dest.writeString(this.type);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.content = in.readString();
            this.id = in.readString();
            this.sendtime = in.readString();
            this.status = in.readString();
            this.title = in.readString();
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

    public NewsListEntry() {
    }

    protected NewsListEntry(Parcel in) {
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

    public static final Creator<NewsListEntry> CREATOR = new Creator<NewsListEntry>() {
        @Override
        public NewsListEntry createFromParcel(Parcel source) {
            return new NewsListEntry(source);
        }

        @Override
        public NewsListEntry[] newArray(int size) {
            return new NewsListEntry[size];
        }
    };
}
