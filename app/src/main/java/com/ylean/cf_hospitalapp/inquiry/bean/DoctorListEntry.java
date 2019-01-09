package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * 医生列表
 */
public class DoctorListEntry extends Basebean {

    private String desc;
    private int maxRow;
    private int page;
    private int pageIndex;
    private int pageSize;
    private String startTime;
    private int sum;
    private String titleList;
    private String token;
    private Double totalmoney;
    private List<DataBean> data;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {

        private String adeptdesc;
        private String departid;
        private String departname;
        private Double dhprice;
        private int dhstatus;
        private String doctorid;
        private String doctorname;
        private String doctortitle;
        private String hospitalid;
        private String hospitalname;
        private String imgurl;
        private String phone;
        private Double spprice;
        private int spstatus;
        private Double twprice;
        private int twstatus;
        private boolean isCharge;
        private boolean isSelect;

        public String getAdeptdesc() {
            return adeptdesc;
        }

        public void setAdeptdesc(String adeptdesc) {
            this.adeptdesc = adeptdesc;
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

        public Double getDhprice() {
            return dhprice;
        }

        public void setDhprice(Double dhprice) {
            this.dhprice = dhprice;
        }

        public int getDhstatus() {
            return dhstatus;
        }

        public void setDhstatus(int dhstatus) {
            this.dhstatus = dhstatus;
        }

        public String getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public String getDoctortitle() {
            return doctortitle;
        }

        public void setDoctortitle(String doctortitle) {
            this.doctortitle = doctortitle;
        }

        public String getHospitalid() {
            return hospitalid;
        }

        public void setHospitalid(String hospitalid) {
            this.hospitalid = hospitalid;
        }

        public String getHospitalname() {
            return hospitalname;
        }

        public void setHospitalname(String hospitalname) {
            this.hospitalname = hospitalname;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Double getSpprice() {
            return spprice;
        }

        public void setSpprice(Double spprice) {
            this.spprice = spprice;
        }

        public int getSpstatus() {
            return spstatus;
        }

        public void setSpstatus(int spstatus) {
            this.spstatus = spstatus;
        }

        public Double getTwprice() {
            return twprice;
        }

        public void setTwprice(Double twprice) {
            this.twprice = twprice;
        }

        public int getTwstatus() {
            return twstatus;
        }

        public void setTwstatus(int twstatus) {
            this.twstatus = twstatus;
        }

        public boolean isCharge() {
            return isCharge;
        }

        public void setCharge(boolean charge) {
            isCharge = charge;
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
            dest.writeString(this.adeptdesc);
            dest.writeString(this.departid);
            dest.writeString(this.departname);
            dest.writeValue(this.dhprice);
            dest.writeInt(this.dhstatus);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorname);
            dest.writeString(this.doctortitle);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.imgurl);
            dest.writeString(this.phone);
            dest.writeValue(this.spprice);
            dest.writeInt(this.spstatus);
            dest.writeValue(this.twprice);
            dest.writeInt(this.twstatus);
            dest.writeByte(this.isCharge ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.adeptdesc = in.readString();
            this.departid = in.readString();
            this.departname = in.readString();
            this.dhprice = (Double) in.readValue(Double.class.getClassLoader());
            this.dhstatus = in.readInt();
            this.doctorid = in.readString();
            this.doctorname = in.readString();
            this.doctortitle = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.imgurl = in.readString();
            this.phone = in.readString();
            this.spprice = (Double) in.readValue(Double.class.getClassLoader());
            this.spstatus = in.readInt();
            this.twprice = (Double) in.readValue(Double.class.getClassLoader());
            this.twstatus = in.readInt();
            this.isCharge = in.readByte() != 0;
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
        dest.writeString(this.desc);
        dest.writeInt(this.maxRow);
        dest.writeInt(this.page);
        dest.writeInt(this.pageIndex);
        dest.writeInt(this.pageSize);
        dest.writeString(this.startTime);
        dest.writeInt(this.sum);
        dest.writeString(this.titleList);
        dest.writeString(this.token);
        dest.writeValue(this.totalmoney);
        dest.writeTypedList(this.data);
    }

    public DoctorListEntry() {
    }

    protected DoctorListEntry(Parcel in) {
        super(in);
        this.desc = in.readString();
        this.maxRow = in.readInt();
        this.page = in.readInt();
        this.pageIndex = in.readInt();
        this.pageSize = in.readInt();
        this.startTime = in.readString();
        this.sum = in.readInt();
        this.titleList = in.readString();
        this.token = in.readString();
        this.totalmoney = (Double) in.readValue(Double.class.getClassLoader());
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<DoctorListEntry> CREATOR = new Creator<DoctorListEntry>() {
        @Override
        public DoctorListEntry createFromParcel(Parcel source) {
            return new DoctorListEntry(source);
        }

        @Override
        public DoctorListEntry[] newArray(int size) {
            return new DoctorListEntry[size];
        }
    };
}
