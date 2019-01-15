package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/15.
 */

public class HelpEntry extends Basebean {

    /**
     * data : [{"createtime":"2019-01-15 15:25:44","createtimetr":"2019-01-15 15:25:44","description":"爱心帮帮团","id":20,"owner":"cfyypatient166","roomid":"71448661131265","roomname":"爱心帮帮团"}]
     * startTime : 2019-01-15 15:26:32
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
         * createtime : 2019-01-15 15:25:44
         * createtimetr : 2019-01-15 15:25:44
         * description : 爱心帮帮团
         * id : 20
         * owner : cfyypatient166
         * roomid : 71448661131265
         * roomname : 爱心帮帮团
         */

        private String createtime;
        private String createtimetr;
        private String description;
        private String id;
        private String owner;
        private String roomid;
        private String roomname;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public String getRoomname() {
            return roomname;
        }

        public void setRoomname(String roomname) {
            this.roomname = roomname;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.createtime);
            dest.writeString(this.createtimetr);
            dest.writeString(this.description);
            dest.writeString(this.id);
            dest.writeString(this.owner);
            dest.writeString(this.roomid);
            dest.writeString(this.roomname);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.createtime = in.readString();
            this.createtimetr = in.readString();
            this.description = in.readString();
            this.id = in.readString();
            this.owner = in.readString();
            this.roomid = in.readString();
            this.roomname = in.readString();
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

    public HelpEntry() {
    }

    protected HelpEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<HelpEntry> CREATOR = new Creator<HelpEntry>() {
        @Override
        public HelpEntry createFromParcel(Parcel source) {
            return new HelpEntry(source);
        }

        @Override
        public HelpEntry[] newArray(int size) {
            return new HelpEntry[size];
        }
    };
}
