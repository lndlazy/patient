package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * 聊天bean
 * Created by linaidao on 2018/12/29.
 */

public class ChatEntry extends Basebean {

    private String startTime;
    private String token;
    private List<DataBean> data;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {

        private String consultationid;
        private String content;
        private String createtime;
        private String createtimetr;
        private String id;
        private String imgurl;
        private String name;
        private String status;
        private String type;
        private String url;
        private String userid;
        private String usertype;

        public String getConsultationid() {
            return consultationid;
        }

        public void setConsultationid(String consultationid) {
            this.consultationid = consultationid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.consultationid);
            dest.writeString(this.content);
            dest.writeString(this.createtime);
            dest.writeString(this.createtimetr);
            dest.writeString(this.id);
            dest.writeString(this.imgurl);
            dest.writeString(this.name);
            dest.writeString(this.status);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeString(this.userid);
            dest.writeString(this.usertype);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.consultationid = in.readString();
            this.content = in.readString();
            this.createtime = in.readString();
            this.createtimetr = in.readString();
            this.id = in.readString();
            this.imgurl = in.readString();
            this.name = in.readString();
            this.status = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.userid = in.readString();
            this.usertype = in.readString();
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
        dest.writeString(this.startTime);
        dest.writeString(this.token);
        dest.writeTypedList(this.data);
    }

    public ChatEntry() {
    }

    protected ChatEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<ChatEntry> CREATOR = new Creator<ChatEntry>() {
        @Override
        public ChatEntry createFromParcel(Parcel source) {
            return new ChatEntry(source);
        }

        @Override
        public ChatEntry[] newArray(int size) {
            return new ChatEntry[size];
        }
    };
}
