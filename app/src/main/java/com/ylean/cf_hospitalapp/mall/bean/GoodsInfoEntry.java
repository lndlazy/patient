package com.ylean.cf_hospitalapp.mall.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/21.
 */

public class GoodsInfoEntry extends Basebean {

    /**
     * data : {"cid":13,"cmtcount":1,"cmtgood":"100.0%","description":"","id":142,"imgs":["/upload/imgs/73013f88-1de4-433d-b406-7c518535e52b.jpg","/upload/imgs/2016c4d2-539e-46de-891a-e2848a6e633d.jpg","/upload/imgs/f719f185-1003-4e7d-97b5-6f604d06b16e.jpg"],"imgurl":"/upload/imgs/73013f88-1de4-433d-b406-7c518535e52b.jpg","iscollect":0,"isfavorites":false,"oldprice":20,"points":5,"price":0.01,"stock":969,"subtitle":"拉杆箱","title":"严选20寸拉杆箱","type":1}
     * maxRow : 0
     * page : 0
     * pageIndex : 0
     * pageSize : 0
     * startTime : 2019-01-21 10:48:31
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
         * cid : 13
         * cmtcount : 1
         * cmtgood : 100.0%
         * description :
         * id : 142
         * imgs : ["/upload/imgs/73013f88-1de4-433d-b406-7c518535e52b.jpg","/upload/imgs/2016c4d2-539e-46de-891a-e2848a6e633d.jpg","/upload/imgs/f719f185-1003-4e7d-97b5-6f604d06b16e.jpg"]
         * imgurl : /upload/imgs/73013f88-1de4-433d-b406-7c518535e52b.jpg
         * iscollect : 0
         * isfavorites : false
         * oldprice : 20
         * points : 5
         * price : 0.01
         * stock : 969
         * subtitle : 拉杆箱
         * title : 严选20寸拉杆箱
         * type : 1
         */

        private String cid;
        private String cmtcount;
        private String cmtgood;
        private String description;
        private String id;
        private String imgurl;
        private String iscollect;
        private boolean isfavorites;
        private double oldprice;
        private String points;
        private double price;
        private String stock;
        private String subtitle;
        private String title;
        private String type;
        private List<String> imgs;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCmtcount() {
            return cmtcount;
        }

        public void setCmtcount(String cmtcount) {
            this.cmtcount = cmtcount;
        }

        public String getCmtgood() {
            return cmtgood;
        }

        public void setCmtgood(String cmtgood) {
            this.cmtgood = cmtgood;
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

        public boolean isIsfavorites() {
            return isfavorites;
        }

        public void setIsfavorites(boolean isfavorites) {
            this.isfavorites = isfavorites;
        }

        public double getOldprice() {
            return oldprice;
        }

        public void setOldprice(double oldprice) {
            this.oldprice = oldprice;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
            dest.writeString(this.cid);
            dest.writeString(this.cmtcount);
            dest.writeString(this.cmtgood);
            dest.writeString(this.description);
            dest.writeString(this.id);
            dest.writeString(this.imgurl);
            dest.writeString(this.iscollect);
            dest.writeByte(this.isfavorites ? (byte) 1 : (byte) 0);
            dest.writeDouble(this.oldprice);
            dest.writeString(this.points);
            dest.writeDouble(this.price);
            dest.writeString(this.stock);
            dest.writeString(this.subtitle);
            dest.writeString(this.title);
            dest.writeString(this.type);
            dest.writeStringList(this.imgs);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.cid = in.readString();
            this.cmtcount = in.readString();
            this.cmtgood = in.readString();
            this.description = in.readString();
            this.id = in.readString();
            this.imgurl = in.readString();
            this.iscollect = in.readString();
            this.isfavorites = in.readByte() != 0;
            this.oldprice = in.readDouble();
            this.points = in.readString();
            this.price = in.readDouble();
            this.stock = in.readString();
            this.subtitle = in.readString();
            this.title = in.readString();
            this.type = in.readString();
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

    public GoodsInfoEntry() {
    }

    protected GoodsInfoEntry(Parcel in) {
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

    public static final Creator<GoodsInfoEntry> CREATOR = new Creator<GoodsInfoEntry>() {
        @Override
        public GoodsInfoEntry createFromParcel(Parcel source) {
            return new GoodsInfoEntry(source);
        }

        @Override
        public GoodsInfoEntry[] newArray(int size) {
            return new GoodsInfoEntry[size];
        }
    };
}
