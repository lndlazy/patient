package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

public class DepartmentListEntry extends Basebean {

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

        private String fdepartid;
        private String fdepartname;
        private List<ChildlistBean> childlist;
        private boolean isSelect;

        public String getFdepartid() {
            return fdepartid;
        }

        public void setFdepartid(String fdepartid) {
            this.fdepartid = fdepartid;
        }

        public String getFdepartname() {
            return fdepartname;
        }

        public void setFdepartname(String fdepartname) {
            this.fdepartname = fdepartname;
        }

        public List<ChildlistBean> getChildlist() {
            return childlist;
        }

        public void setChildlist(List<ChildlistBean> childlist) {
            this.childlist = childlist;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public static class ChildlistBean implements android.os.Parcelable {

            private String departmentid;
            private String departmentname;
            private String description;
            private boolean isSelect;

            public String getDepartmentid() {
                return departmentid;
            }

            public void setDepartmentid(String departmentid) {
                this.departmentid = departmentid;
            }

            public String getDepartmentname() {
                return departmentname;
            }

            public void setDepartmentname(String departmentname) {
                this.departmentname = departmentname;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.departmentid);
                dest.writeString(this.departmentname);
                dest.writeString(this.description);
                dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
            }

            public ChildlistBean() {
            }

            protected ChildlistBean(Parcel in) {
                this.departmentid = in.readString();
                this.departmentname = in.readString();
                this.description = in.readString();
                this.isSelect = in.readByte() != 0;
            }

            public static final Creator<ChildlistBean> CREATOR = new Creator<ChildlistBean>() {
                @Override
                public ChildlistBean createFromParcel(Parcel source) {
                    return new ChildlistBean(source);
                }

                @Override
                public ChildlistBean[] newArray(int size) {
                    return new ChildlistBean[size];
                }
            };
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.fdepartid);
            dest.writeString(this.fdepartname);
            dest.writeTypedList(this.childlist);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.fdepartid = in.readString();
            this.fdepartname = in.readString();
            this.childlist = in.createTypedArrayList(ChildlistBean.CREATOR);
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

    public DepartmentListEntry() {
    }

    protected DepartmentListEntry(Parcel in) {
        super(in);
        this.startTime = in.readString();
        this.token = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<DepartmentListEntry> CREATOR = new Creator<DepartmentListEntry>() {
        @Override
        public DepartmentListEntry createFromParcel(Parcel source) {
            return new DepartmentListEntry(source);
        }

        @Override
        public DepartmentListEntry[] newArray(int size) {
            return new DepartmentListEntry[size];
        }
    };
}
