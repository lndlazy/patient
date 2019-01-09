package com.ylean.cf_hospitalapp.register.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * 挂号bean
 * Created by linaidao on 2019/1/4.
 */

public class RegisterDeartmentListEntry extends Basebean {

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

        private String departid;
        private String departname;
        private List<MenzhenlistBean> menzhenlist;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getDepartid() {
            return departid;
        }

        public void setDepartid(String departid) {
            this.departid = departid;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public List<MenzhenlistBean> getMenzhenlist() {
            return menzhenlist;
        }

        public void setMenzhenlist(List<MenzhenlistBean> menzhenlist) {
            this.menzhenlist = menzhenlist;
        }

        public static class MenzhenlistBean implements android.os.Parcelable {

            private String menzhenid;
            private String menzhenname;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getMenzhenid() {
                return menzhenid;
            }

            public void setMenzhenid(String menzhenid) {
                this.menzhenid = menzhenid;
            }

            public String getMenzhenname() {
                return menzhenname;
            }

            public void setMenzhenname(String menzhenname) {
                this.menzhenname = menzhenname;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.menzhenid);
                dest.writeString(this.menzhenname);
                dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
            }

            public MenzhenlistBean() {
            }

            protected MenzhenlistBean(Parcel in) {
                this.menzhenid = in.readString();
                this.menzhenname = in.readString();
                this.isSelect = in.readByte() != 0;
            }

            public static final Creator<MenzhenlistBean> CREATOR = new Creator<MenzhenlistBean>() {
                @Override
                public MenzhenlistBean createFromParcel(Parcel source) {
                    return new MenzhenlistBean(source);
                }

                @Override
                public MenzhenlistBean[] newArray(int size) {
                    return new MenzhenlistBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.departid);
            dest.writeString(this.departname);
            dest.writeTypedList(this.menzhenlist);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.departid = in.readString();
            this.departname = in.readString();
            this.menzhenlist = in.createTypedArrayList(MenzhenlistBean.CREATOR);
            this.isSelect = in.readByte() != 0;
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

    public RegisterDeartmentListEntry() {
    }

    protected RegisterDeartmentListEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<RegisterDeartmentListEntry> CREATOR = new Creator<RegisterDeartmentListEntry>() {
        @Override
        public RegisterDeartmentListEntry createFromParcel(Parcel source) {
            return new RegisterDeartmentListEntry(source);
        }

        @Override
        public RegisterDeartmentListEntry[] newArray(int size) {
            return new RegisterDeartmentListEntry[size];
        }
    };
}
