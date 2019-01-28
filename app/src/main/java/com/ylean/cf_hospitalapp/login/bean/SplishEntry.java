package com.ylean.cf_hospitalapp.login.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/25.
 */

public class SplishEntry extends Basebean {

    /**
     * data : {"createtime":"2019-01-25 22:01:54","id":1,"img":"/upload/imgs/19e653a2-463c-4668-858d-b064a987c19f.png","type":0,"version":1}
     * startTime : 2019-01-25 22:03:59
     * token :
     */

    private DataBean data;
    private String startTime;
    private String token;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean implements android.os.Parcelable {
        /**
         * createtime : 2019-01-25 22:01:54
         * id : 1
         * img : /upload/imgs/19e653a2-463c-4668-858d-b064a987c19f.png
         * type : 0
         * version : 1
         */

        private String createtime;
        private String id;
        private String img;
        private String type;
        private String version;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.createtime);
            dest.writeString(this.id);
            dest.writeString(this.img);
            dest.writeString(this.type);
            dest.writeString(this.version);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.createtime = in.readString();
            this.id = in.readString();
            this.img = in.readString();
            this.type = in.readString();
            this.version = in.readString();
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
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.startTime);
        dest.writeString(this.token);
    }

    public SplishEntry() {
    }

    protected SplishEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<SplishEntry> CREATOR = new Creator<SplishEntry>() {
        @Override
        public SplishEntry createFromParcel(Parcel source) {
            return new SplishEntry(source);
        }

        @Override
        public SplishEntry[] newArray(int size) {
            return new SplishEntry[size];
        }
    };
}
