package com.ylean.cf_hospitalapp.my.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/9.
 */

public class EvalDetailEntry extends Basebean {

    /**
     * data : {"adeptdesc":"　　下肢静脉曲张(微创手术)、KT综合征、下肢深静脉血栓形成、下肢动脉硬化闭塞症、颈动脉狭窄、人工血管移植创建透析通路、复杂性动静脉內瘘手术、主动脉夹层、肺栓塞、急性肢体动脉栓塞、肾动脉狭窄、腹主动脉瘤、糖尿病足、原发性下肢深静脉瓣膜功能不全。肝癌、肺癌的介入治疗(射频消融、同位素注射、粒子置入)","content":"锦绣江山都好都好快科技多久能到","departname":"血管外科","dtitlename":"副主任医师","hospital":"北京长峰医院","id":31,"img":"","imgs":[""],"imgurl":"/upload/imgs/pro/c9f7b74d-87b7-4a04-96e0-8b3e937db4a9.jpg","name":"尹杰","ordercode":"Q20190109175600014","ordertype":1,"price":0.01,"stardepict":5,"starlevel":0,"starperformance":5,"starservice":5}
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-01-09 17:57:52
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
         * adeptdesc : 　　下肢静脉曲张(微创手术)、KT综合征、下肢深静脉血栓形成、下肢动脉硬化闭塞症、颈动脉狭窄、人工血管移植创建透析通路、复杂性动静脉內瘘手术、主动脉夹层、肺栓塞、急性肢体动脉栓塞、肾动脉狭窄、腹主动脉瘤、糖尿病足、原发性下肢深静脉瓣膜功能不全。肝癌、肺癌的介入治疗(射频消融、同位素注射、粒子置入)
         * content : 锦绣江山都好都好快科技多久能到
         * departname : 血管外科
         * dtitlename : 副主任医师
         * hospital : 北京长峰医院
         * id : 31
         * img :
         * imgs : [""]
         * imgurl : /upload/imgs/pro/c9f7b74d-87b7-4a04-96e0-8b3e937db4a9.jpg
         * name : 尹杰
         * ordercode : Q20190109175600014
         * ordertype : 1
         * price : 0.01
         * stardepict : 5
         * starlevel : 0
         * starperformance : 5
         * starservice : 5
         */

        private String adeptdesc;
        private String content;
        private String departname;
        private String dtitlename;
        private String hospital;
        private String id;
        private String img;
        private String imgurl;
        private String name;
        private String ordercode;
        private String ordertype;
        private double price;
        private int stardepict;
        private int starlevel;
        private int starperformance;
        private int starservice;
        private List<String> imgs;

        public String getAdeptdesc() {
            return adeptdesc;
        }

        public void setAdeptdesc(String adeptdesc) {
            this.adeptdesc = adeptdesc;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getDtitlename() {
            return dtitlename;
        }

        public void setDtitlename(String dtitlename) {
            this.dtitlename = dtitlename;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStardepict() {
            return stardepict;
        }

        public void setStardepict(int stardepict) {
            this.stardepict = stardepict;
        }

        public int getStarlevel() {
            return starlevel;
        }

        public void setStarlevel(int starlevel) {
            this.starlevel = starlevel;
        }

        public int getStarperformance() {
            return starperformance;
        }

        public void setStarperformance(int starperformance) {
            this.starperformance = starperformance;
        }

        public int getStarservice() {
            return starservice;
        }

        public void setStarservice(int starservice) {
            this.starservice = starservice;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.adeptdesc);
            dest.writeString(this.content);
            dest.writeString(this.departname);
            dest.writeString(this.dtitlename);
            dest.writeString(this.hospital);
            dest.writeString(this.id);
            dest.writeString(this.img);
            dest.writeString(this.imgurl);
            dest.writeString(this.name);
            dest.writeString(this.ordercode);
            dest.writeString(this.ordertype);
            dest.writeDouble(this.price);
            dest.writeInt(this.stardepict);
            dest.writeInt(this.starlevel);
            dest.writeInt(this.starperformance);
            dest.writeInt(this.starservice);
            dest.writeStringList(this.imgs);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.adeptdesc = in.readString();
            this.content = in.readString();
            this.departname = in.readString();
            this.dtitlename = in.readString();
            this.hospital = in.readString();
            this.id = in.readString();
            this.img = in.readString();
            this.imgurl = in.readString();
            this.name = in.readString();
            this.ordercode = in.readString();
            this.ordertype = in.readString();
            this.price = in.readDouble();
            this.stardepict = in.readInt();
            this.starlevel = in.readInt();
            this.starperformance = in.readInt();
            this.starservice = in.readInt();
            this.imgs = in.createStringArrayList();
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

    public EvalDetailEntry() {
    }

    protected EvalDetailEntry(Parcel in) {
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

    public static final Creator<EvalDetailEntry> CREATOR = new Creator<EvalDetailEntry>() {
        @Override
        public EvalDetailEntry createFromParcel(Parcel source) {
            return new EvalDetailEntry(source);
        }

        @Override
        public EvalDetailEntry[] newArray(int size) {
            return new EvalDetailEntry[size];
        }
    };
}
