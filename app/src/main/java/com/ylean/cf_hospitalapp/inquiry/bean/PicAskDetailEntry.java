package com.ylean.cf_hospitalapp.inquiry.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2018/12/28.
 */

public class PicAskDetailEntry extends Basebean {

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

        private String adeptdesc;
        private int age;
        private String birthdate;
        private String consultaid;
        private String createtime;
        private String createtimetr;
        private String departname;
        private String description;
        private String diseasename;
        private String docimg;
        private String doctitlename;
        private String doctorid;
        private String doctorname;
        private String flokname;
        private int floktype;
        private String idcard;
        private String imgs;
        private int iscollect;
        private int iscomment;
        private int issummary;
        private String medicalcard;
        private String patientid;
        private String phone;
        private String problem;
        private int sex;
        private int status;
        private String voiceurl;
        private List<String> imglist;

        public String getAdeptdesc() {
            return adeptdesc;
        }

        public void setAdeptdesc(String adeptdesc) {
            this.adeptdesc = adeptdesc;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
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

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getFlokname() {
            return flokname;
        }

        public void setFlokname(String flokname) {
            this.flokname = flokname;
        }

        public int getFloktype() {
            return floktype;
        }

        public void setFloktype(int floktype) {
            this.floktype = floktype;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public int getIscollect() {
            return iscollect;
        }

        public void setIscollect(int iscollect) {
            this.iscollect = iscollect;
        }

        public int getIscomment() {
            return iscomment;
        }

        public void setIscomment(int iscomment) {
            this.iscomment = iscomment;
        }

        public int getIssummary() {
            return issummary;
        }

        public void setIssummary(int issummary) {
            this.issummary = issummary;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getVoiceurl() {
            return voiceurl;
        }

        public void setVoiceurl(String voiceurl) {
            this.voiceurl = voiceurl;
        }

        public List<String> getImglist() {
            return imglist;
        }

        public void setImglist(List<String> imglist) {
            this.imglist = imglist;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.adeptdesc);
            dest.writeInt(this.age);
            dest.writeString(this.birthdate);
            dest.writeString(this.consultaid);
            dest.writeString(this.createtime);
            dest.writeString(this.createtimetr);
            dest.writeString(this.departname);
            dest.writeString(this.description);
            dest.writeString(this.diseasename);
            dest.writeString(this.docimg);
            dest.writeString(this.doctitlename);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorname);
            dest.writeString(this.flokname);
            dest.writeInt(this.floktype);
            dest.writeString(this.idcard);
            dest.writeString(this.imgs);
            dest.writeInt(this.iscollect);
            dest.writeInt(this.iscomment);
            dest.writeInt(this.issummary);
            dest.writeString(this.medicalcard);
            dest.writeString(this.patientid);
            dest.writeString(this.phone);
            dest.writeString(this.problem);
            dest.writeInt(this.sex);
            dest.writeInt(this.status);
            dest.writeString(this.voiceurl);
            dest.writeStringList(this.imglist);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.adeptdesc = in.readString();
            this.age = in.readInt();
            this.birthdate = in.readString();
            this.consultaid = in.readString();
            this.createtime = in.readString();
            this.createtimetr = in.readString();
            this.departname = in.readString();
            this.description = in.readString();
            this.diseasename = in.readString();
            this.docimg = in.readString();
            this.doctitlename = in.readString();
            this.doctorid = in.readString();
            this.doctorname = in.readString();
            this.flokname = in.readString();
            this.floktype = in.readInt();
            this.idcard = in.readString();
            this.imgs = in.readString();
            this.iscollect = in.readInt();
            this.iscomment = in.readInt();
            this.issummary = in.readInt();
            this.medicalcard = in.readString();
            this.patientid = in.readString();
            this.phone = in.readString();
            this.problem = in.readString();
            this.sex = in.readInt();
            this.status = in.readInt();
            this.voiceurl = in.readString();
            this.imglist = in.createStringArrayList();
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

    public PicAskDetailEntry() {
    }

    protected PicAskDetailEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<PicAskDetailEntry> CREATOR = new Creator<PicAskDetailEntry>() {
        @Override
        public PicAskDetailEntry createFromParcel(Parcel source) {
            return new PicAskDetailEntry(source);
        }

        @Override
        public PicAskDetailEntry[] newArray(int size) {
            return new PicAskDetailEntry[size];
        }
    };
}
