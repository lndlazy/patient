package com.ylean.cf_hospitalapp.hx.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/15.
 */

public class GroupListEntry extends Basebean {

    /**
     * data : [{"imgurl":"/upload/imgs/0d3a5dd2-9859-4bf8-9ecc-59e19fbbd3d1.jpg","nickname":"小鸡","number":"P000131","type":1,"userid":166},{"imgurl":"/upload/imgs/pro/591ec8ff-ae63-4c71-bb81-8a7d4907bf28.jpg","nickname":"王亚飞","number":"D000001","type":2,"userid":1},{"imgurl":"/upload/imgs/pro/a4944d31-86cc-475f-832c-487c732aec19.png","nickname":"陈会通（因联测试勿删）","number":"D000132","type":2,"userid":137}]
     * startTime : 2019-01-15 19:12:08
     * token :
     */

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
        /**
         * imgurl : /upload/imgs/0d3a5dd2-9859-4bf8-9ecc-59e19fbbd3d1.jpg
         * nickname : 小鸡
         * number : P000131
         * type : 1
         * userid : 166
         */

        private String imgurl;
        private String nickname;
        private String number;
        private int type;
        private String userid;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.imgurl);
            dest.writeString(this.nickname);
            dest.writeString(this.number);
            dest.writeInt(this.type);
            dest.writeString(this.userid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.imgurl = in.readString();
            this.nickname = in.readString();
            this.number = in.readString();
            this.type = in.readInt();
            this.userid = in.readString();
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

    public GroupListEntry() {
    }

    protected GroupListEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<GroupListEntry> CREATOR = new Creator<GroupListEntry>() {
        @Override
        public GroupListEntry createFromParcel(Parcel source) {
            return new GroupListEntry(source);
        }

        @Override
        public GroupListEntry[] newArray(int size) {
            return new GroupListEntry[size];
        }
    };
}
