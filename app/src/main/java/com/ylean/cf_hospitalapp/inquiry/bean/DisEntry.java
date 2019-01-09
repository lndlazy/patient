package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2018/12/18.
 */

public class DisEntry extends Basebean {

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
        private List<DiseaselistBean> diseaselist;
        private boolean select;

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

        public List<DiseaselistBean> getDiseaselist() {
            return diseaselist;
        }

        public void setDiseaselist(List<DiseaselistBean> diseaselist) {
            this.diseaselist = diseaselist;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public static class DiseaselistBean implements android.os.Parcelable {

            private String diseaseid;
            private String diseasename;
            private boolean select;

            public String getDiseaseid() {
                return diseaseid;
            }

            public void setDiseaseid(String diseaseid) {
                this.diseaseid = diseaseid;
            }

            public String getDiseasename() {
                return diseasename;
            }

            public void setDiseasename(String diseasename) {
                this.diseasename = diseasename;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.diseaseid);
                dest.writeString(this.diseasename);
                dest.writeByte(this.select ? (byte) 1 : (byte) 0);
            }

            public DiseaselistBean() {
            }

            protected DiseaselistBean(Parcel in) {
                this.diseaseid = in.readString();
                this.diseasename = in.readString();
                this.select = in.readByte() != 0;
            }

            public static final Creator<DiseaselistBean> CREATOR = new Creator<DiseaselistBean>() {
                @Override
                public DiseaselistBean createFromParcel(Parcel source) {
                    return new DiseaselistBean(source);
                }

                @Override
                public DiseaselistBean[] newArray(int size) {
                    return new DiseaselistBean[size];
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
            dest.writeTypedList(this.diseaselist);
            dest.writeByte(this.select ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.departid = in.readString();
            this.departname = in.readString();
            this.diseaselist = in.createTypedArrayList(DiseaselistBean.CREATOR);
            this.select = in.readByte() != 0;
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

    public DisEntry() {
    }

    protected DisEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<DisEntry> CREATOR = new Creator<DisEntry>() {
        @Override
        public DisEntry createFromParcel(Parcel source) {
            return new DisEntry(source);
        }

        @Override
        public DisEntry[] newArray(int size) {
            return new DisEntry[size];
        }
    };
}
