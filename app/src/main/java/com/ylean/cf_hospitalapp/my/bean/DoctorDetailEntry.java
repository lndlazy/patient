package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

/**
 * Created by linaidao on 2019/1/11.
 */

public class DoctorDetailEntry extends Basebean {

    /**
     * data : {"academicdesc":"从事各类血管瘤疾病临床研究和治疗20年，在血管瘤的临床治疗与研究中积累了丰富的临床经验，并将先进治疗理念融入到血管瘤治疗的新技术中，对婴幼儿血管瘤及脉管畸形，卡梅综合症，KTS综合症等疑难疾病治疗经验丰富，成功帮助广大血管瘤患者解除病痛，深受患者家属的一致好评。\u201d","adeptdesc":"飞啊飞发卡机","departid":69,"departname":"血管瘤科","doctorid":1,"doctorname":"王亚飞","doctortitle":"主治医师","eductdesc":"菲拉斯款金额是","hopedesc":"用我的爱心点燃您生命之火，用我的医术解除您疾病之苦。","hosdesc":"<p class=\"MsoNormal\" style=\"text-align:center;text-indent:28pt;\">\n\t<img src=\"/upload/imgs/pro/7aaf7203-5ab1-4eac-8616-6f428e19cbef.jpg\" alt=\"\" /> \n<\/p>\n<p class=\"MsoNormal\" style=\"text-align:left;text-indent:28pt;\">\n\t<span style=\"line-height:1.5;\">北京长峰医院作为长峰连锁医疗机构的旗舰医院，是一所以血管瘤、脉管畸形等疾病为诊疗特色的二级医保定点医院，医院自2011年起连续数年<\/span><span style=\"line-height:1.5;\">通过<\/span><span style=\"line-height:1.5;\">IS09001医疗质量体系认证，<\/span><span style=\"line-height:1.5;\">是北京市医保跨省异地就医住院费用直接结算单位、河南省信阳市新农合直报医院、北京市丰台区医保定点医院及新农合定点医院、国家新型农村合作社医疗信息平台试点单位，被中华中医药学会授予<\/span><span style=\"line-height:1.5;\">\u201c血管瘤医师继续教育培训合作单位\u201d，是中国华侨公益基金会笑玮儿童血管瘤胎记治疗基金定点医院。<\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"text-align:center;text-indent:28pt;\">\n\t<span style=\"line-height:1.5;\"><img src=\"/upload/imgs/pro/5895b846-013f-46c9-83d5-d07698e658cf.jpg\" alt=\"\" /><br />\n<\/span> \n<\/p>\n<p>\n\t医院基础设施配置齐全引进使用美国GE数字减影血管造影机(DSA)、美国GE 64PET \n螺旋CT、西门子1.5T核磁(MRI)等大型高端医疗设备，并建有先进的手术室和重症监护中心，为疾病的诊断与治疗提供了可靠保证。同时积极整合自身资源，与多家权威医疗机构的尖端人才形成良好的血管瘤战略合作伙伴关系，参与门诊、临床治疗、疑难杂症会诊、实施大型手术等，专心致力于我国各种血管瘤的研究与治疗工作，通过坚持不懈的努力，逐渐在血管瘤治疗领域占据了重要地位。\n<\/p>\n<p>\n\t<br />\n<\/p>","hospiatlname":"北京长峰医院","hospitalid":3,"imgurl":"/upload/imgs/pro/591ec8ff-ae63-4c71-bb81-8a7d4907bf28.jpg","iscollect":1,"phone":"010-88248999"}
     * startTime : 2019-01-11 00:41:24
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
         * academicdesc : 从事各类血管瘤疾病临床研究和治疗20年，在血管瘤的临床治疗与研究中积累了丰富的临床经验，并将先进治疗理念融入到血管瘤治疗的新技术中，对婴幼儿血管瘤及脉管畸形，卡梅综合症，KTS综合症等疑难疾病治疗经验丰富，成功帮助广大血管瘤患者解除病痛，深受患者家属的一致好评。”
         * adeptdesc : 飞啊飞发卡机
         * departid : 69
         * departname : 血管瘤科
         * doctorid : 1
         * doctorname : 王亚飞
         * doctortitle : 主治医师
         * eductdesc : 菲拉斯款金额是
         * hopedesc : 用我的爱心点燃您生命之火，用我的医术解除您疾病之苦。
         * hosdesc : <p class="MsoNormal" style="text-align:center;text-indent:28pt;">
         <img src="/upload/imgs/pro/7aaf7203-5ab1-4eac-8616-6f428e19cbef.jpg" alt="" />
         </p>
         <p class="MsoNormal" style="text-align:left;text-indent:28pt;">
         <span style="line-height:1.5;">北京长峰医院作为长峰连锁医疗机构的旗舰医院，是一所以血管瘤、脉管畸形等疾病为诊疗特色的二级医保定点医院，医院自2011年起连续数年</span><span style="line-height:1.5;">通过</span><span style="line-height:1.5;">IS09001医疗质量体系认证，</span><span style="line-height:1.5;">是北京市医保跨省异地就医住院费用直接结算单位、河南省信阳市新农合直报医院、北京市丰台区医保定点医院及新农合定点医院、国家新型农村合作社医疗信息平台试点单位，被中华中医药学会授予</span><span style="line-height:1.5;">“血管瘤医师继续教育培训合作单位”，是中国华侨公益基金会笑玮儿童血管瘤胎记治疗基金定点医院。</span>
         </p>
         <p class="MsoNormal" style="text-align:center;text-indent:28pt;">
         <span style="line-height:1.5;"><img src="/upload/imgs/pro/5895b846-013f-46c9-83d5-d07698e658cf.jpg" alt="" /><br />
         </span>
         </p>
         <p>
         医院基础设施配置齐全引进使用美国GE数字减影血管造影机(DSA)、美国GE 64PET
         螺旋CT、西门子1.5T核磁(MRI)等大型高端医疗设备，并建有先进的手术室和重症监护中心，为疾病的诊断与治疗提供了可靠保证。同时积极整合自身资源，与多家权威医疗机构的尖端人才形成良好的血管瘤战略合作伙伴关系，参与门诊、临床治疗、疑难杂症会诊、实施大型手术等，专心致力于我国各种血管瘤的研究与治疗工作，通过坚持不懈的努力，逐渐在血管瘤治疗领域占据了重要地位。
         </p>
         <p>
         <br />
         </p>
         * hospiatlname : 北京长峰医院
         * hospitalid : 3
         * imgurl : /upload/imgs/pro/591ec8ff-ae63-4c71-bb81-8a7d4907bf28.jpg
         * iscollect : 1
         * phone : 010-88248999
         */

        private String academicdesc;
        private String adeptdesc;
        private String departid;
        private String departname;
        private String doctorid;
        private String doctorname;
        private String doctortitle;
        private String eductdesc;
        private String hopedesc;
        private String hosdesc;
        private String hospiatlname;
        private String hospitalid;
        private String imgurl;
        private String iscollect;
        private String phone;

        public String getAcademicdesc() {
            return academicdesc;
        }

        public void setAcademicdesc(String academicdesc) {
            this.academicdesc = academicdesc;
        }

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

        public String getEductdesc() {
            return eductdesc;
        }

        public void setEductdesc(String eductdesc) {
            this.eductdesc = eductdesc;
        }

        public String getHopedesc() {
            return hopedesc;
        }

        public void setHopedesc(String hopedesc) {
            this.hopedesc = hopedesc;
        }

        public String getHosdesc() {
            return hosdesc;
        }

        public void setHosdesc(String hosdesc) {
            this.hosdesc = hosdesc;
        }

        public String getHospiatlname() {
            return hospiatlname;
        }

        public void setHospiatlname(String hospiatlname) {
            this.hospiatlname = hospiatlname;
        }

        public String getHospitalid() {
            return hospitalid;
        }

        public void setHospitalid(String hospitalid) {
            this.hospitalid = hospitalid;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getIscollect() {
            return iscollect;
        }

        public void setIscollect(String iscollect) {
            this.iscollect = iscollect;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.academicdesc);
            dest.writeString(this.adeptdesc);
            dest.writeString(this.departid);
            dest.writeString(this.departname);
            dest.writeString(this.doctorid);
            dest.writeString(this.doctorname);
            dest.writeString(this.doctortitle);
            dest.writeString(this.eductdesc);
            dest.writeString(this.hopedesc);
            dest.writeString(this.hosdesc);
            dest.writeString(this.hospiatlname);
            dest.writeString(this.hospitalid);
            dest.writeString(this.imgurl);
            dest.writeString(this.iscollect);
            dest.writeString(this.phone);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.academicdesc = in.readString();
            this.adeptdesc = in.readString();
            this.departid = in.readString();
            this.departname = in.readString();
            this.doctorid = in.readString();
            this.doctorname = in.readString();
            this.doctortitle = in.readString();
            this.eductdesc = in.readString();
            this.hopedesc = in.readString();
            this.hosdesc = in.readString();
            this.hospiatlname = in.readString();
            this.hospitalid = in.readString();
            this.imgurl = in.readString();
            this.iscollect = in.readString();
            this.phone = in.readString();
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

    public DoctorDetailEntry() {
    }

    protected DoctorDetailEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<DoctorDetailEntry> CREATOR = new Creator<DoctorDetailEntry>() {
        @Override
        public DoctorDetailEntry createFromParcel(Parcel source) {
            return new DoctorDetailEntry(source);
        }

        @Override
        public DoctorDetailEntry[] newArray(int size) {
            return new DoctorDetailEntry[size];
        }
    };
}
