package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

public class InquiryIntrBean extends Basebean {

    /**
     * data : {"adeptdesc":"","age":90,"birthdate":"1929-01-05 00:00:00","consultaid":455,"departname":"血管瘤科","diseasename":"血管瘤","docimg":"/upload/imgs/735bdaa4-f5ff-4d63-bab3-95cede12874b.jpg","doctitlename":"其他","doctorid":229,"doctorname":"杨y","flokid":12,"flokname":"大牛","floktype":0,"hosimg":"/upload/imgs/719b0fe3-0920-4f97-8639-edbacbe6deab.jpg","hospitalid":3,"hospitalname":"北京长峰医院","idcard":"320902198904116114","medicalcard":"64848454","patientid":366,"phone":"17600100541","problem":"闺女如TV他","sex":1,"suggest":"v人拜托拜托","summarydate":"2019-03-11 16:44:02","summarydatetr":"2019-03-11 16:44:02"}
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-03-11 16:47:20
     * sum : 0
     * titleList :
     * token :
     * totalmoney : 0
     */

    private DataBean data;
    private int maxRow;
    private int page;
    private int pageIndex;
    private int pageSize;
    private String startTime;
    private int sum;
    private String titleList;
    private String token;
    private int totalmoney;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public int getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(int totalmoney) {
        this.totalmoney = totalmoney;
    }

    public static class DataBean implements android.os.Parcelable {
        /**
         * adeptdesc :
         * age : 90
         * birthdate : 1929-01-05 00:00:00
         * consultaid : 455
         * departname : 血管瘤科
         * diseasename : 血管瘤
         * docimg : /upload/imgs/735bdaa4-f5ff-4d63-bab3-95cede12874b.jpg
         * doctitlename : 其他
         * doctorid : 229
         * doctorname : 杨y
         * flokid : 12
         * flokname : 大牛
         * floktype : 0
         * hosimg : /upload/imgs/719b0fe3-0920-4f97-8639-edbacbe6deab.jpg
         * hospitalid : 3
         * hospitalname : 北京长峰医院
         * idcard : 320902198904116114
         * medicalcard : 64848454
         * patientid : 366
         * phone : 17600100541
         * problem : 闺女如TV他
         * sex : 1
         * suggest : v人拜托拜托
         * summarydate : 2019-03-11 16:44:02
         * summarydatetr : 2019-03-11 16:44:02
         */

        private String adeptdesc;
        private String age;
        private String birthdate;
        private String consultaid;
        private String departname;
        private String diseasename;
        private String docimg;
        private String doctitlename;
        private String doctorid;
        private String doctorname;
        private String flokid;
        private String flokname;
        private String floktype;
        private String hosimg;
        private String hospitalid;
        private String hospitalname;
        private String idcard;
        private String medicalcard;
        private String patientid;
        private String phone;
        private String problem;
        private String sex;
        private String suggest;
        private String summarydate;
        private String summarydatetr;

        public String getAdeptdesc() {
            return adeptdesc;
        }

        public void setAdeptdesc(String adeptdesc) {
            this.adeptdesc = adeptdesc;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }

        public String getConsultaid() {
            return consultaid;
        }

        public void setConsultaid(String consultaid) {
            this.consultaid = consultaid;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getDiseasename() {
            return diseasename;
        }

        public void setDiseasename(String diseasename) {
            this.diseasename = diseasename;
        }

        public String getDocimg() {
            return docimg;
        }

        public void setDocimg(String docimg) {
            this.docimg = docimg;
        }

        public String getDoctitlename() {
            return doctitlename;
        }

        public void setDoctitlename(String doctitlename) {
            this.doctitlename = doctitlename;
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

        public String getFlokid() {
            return flokid;
        }

        public void setFlokid(String flokid) {
            this.flokid = flokid;
        }

        public String getFlokname() {
            return flokname;
        }

        public void setFlokname(String flokname) {
            this.flokname = flokname;
        }

        public String getFloktype() {
            return floktype;
        }

        public void setFloktype(String floktype) {
            this.floktype = floktype;
        }

        public String getHosimg() {
            return hosimg;
        }

        public void setHosimg(String hosimg) {
            this.hosimg = hosimg;
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

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getMedicalcard() {
            return medicalcard;
        }

        public void setMedicalcard(String medicalcard) {
            this.medicalcard = medicalcard;
        }

        public String getPatientid() {
            return patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSuggest() {
            return suggest;
        }

        public void setSuggest(String suggest) {
            this.suggest = suggest;
        }

        public String getSummarydate() {
            return summarydate;
        }

        public void setSummarydate(String summarydate) {
            this.summarydate = summarydate;
        }

        public String getSummarydatetr() {
            return summarydatetr;
        }

        public void setSummarydatetr(String summarydatetr) {
            this.summarydatetr = summarydatetr;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.adeptdesc);
            dest.writeString(this.age);
            dest.writeString(this.birthdate);
            dest.writeString(this.consultaid);
            dest.writeString(this.departname);
            dest.writeString(this.diseasename);
            dest.writeString(this.docimg);
            dest.writeString(this.doctitlename);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorname);
            dest.writeString(this.flokid);
            dest.writeString(this.flokname);
            dest.writeString(this.floktype);
            dest.writeString(this.hosimg);
            dest.writeString(this.hospitalid);
            dest.writeString(this.hospitalname);
            dest.writeString(this.idcard);
            dest.writeString(this.medicalcard);
            dest.writeString(this.patientid);
            dest.writeString(this.phone);
            dest.writeString(this.problem);
            dest.writeString(this.sex);
            dest.writeString(this.suggest);
            dest.writeString(this.summarydate);
            dest.writeString(this.summarydatetr);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.adeptdesc = in.readString();
            this.age = in.readString();
            this.birthdate = in.readString();
            this.consultaid = in.readString();
            this.departname = in.readString();
            this.diseasename = in.readString();
            this.docimg = in.readString();
            this.doctitlename = in.readString();
            this.doctorid = in.readString();
            this.doctorname = in.readString();
            this.flokid = in.readString();
            this.flokname = in.readString();
            this.floktype = in.readString();
            this.hosimg = in.readString();
            this.hospitalid = in.readString();
            this.hospitalname = in.readString();
            this.idcard = in.readString();
            this.medicalcard = in.readString();
            this.patientid = in.readString();
            this.phone = in.readString();
            this.problem = in.readString();
            this.sex = in.readString();
            this.suggest = in.readString();
            this.summarydate = in.readString();
            this.summarydatetr = in.readString();
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
        dest.writeInt(this.maxRow);
        dest.writeInt(this.page);
        dest.writeInt(this.pageIndex);
        dest.writeInt(this.pageSize);
        dest.writeString(this.startTime);
        dest.writeInt(this.sum);
        dest.writeString(this.titleList);
        dest.writeString(this.token);
        dest.writeInt(this.totalmoney);
    }

    public InquiryIntrBean() {
    }

    protected InquiryIntrBean(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.maxRow = in.readInt();
        this.page = in.readInt();
        this.pageIndex = in.readInt();
        this.pageSize = in.readInt();
        this.startTime = in.readString();
        this.sum = in.readInt();
        this.titleList = in.readString();
        this.token = in.readString();
        this.totalmoney = in.readInt();
    }

    public static final Creator<InquiryIntrBean> CREATOR = new Creator<InquiryIntrBean>() {
        @Override
        public InquiryIntrBean createFromParcel(Parcel source) {
            return new InquiryIntrBean(source);
        }

        @Override
        public InquiryIntrBean[] newArray(int size) {
            return new InquiryIntrBean[size];
        }
    };
}
