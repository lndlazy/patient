package com.ylean.cf_hospitalapp.base.bean;

import android.os.Parcel;

/**
 * Created by linaidao on 2019/1/8.
 */

public class AddBean extends Basebean {

    private String status;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements android.os.Parcelable {
        /**
         * location : {"lng":120.219375,"lat":30.259244}
         * precise : 0
         * confidence : 10
         * level : 城市
         */

        private LocationBean location;
        private int precise;
        private int confidence;
        private String level;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getPrecise() {
            return precise;
        }

        public void setPrecise(int precise) {
            this.precise = precise;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public static class LocationBean implements android.os.Parcelable {
            /**
             * lng : 120.219375
             * lat : 30.259244
             */

            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeDouble(this.lng);
                dest.writeDouble(this.lat);
            }

            public LocationBean() {
            }

            protected LocationBean(Parcel in) {
                this.lng = in.readDouble();
                this.lat = in.readDouble();
            }

            public static final Creator<LocationBean> CREATOR = new Creator<LocationBean>() {
                @Override
                public LocationBean createFromParcel(Parcel source) {
                    return new LocationBean(source);
                }

                @Override
                public LocationBean[] newArray(int size) {
                    return new LocationBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.location, flags);
            dest.writeInt(this.precise);
            dest.writeInt(this.confidence);
            dest.writeString(this.level);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.location = in.readParcelable(LocationBean.class.getClassLoader());
            this.precise = in.readInt();
            this.confidence = in.readInt();
            this.level = in.readString();
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel source) {
                return new ResultBean(source);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
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
        dest.writeString(this.status);
        dest.writeParcelable(this.result, flags);
    }

    public AddBean() {
    }

    protected AddBean(Parcel in) {
        super(in);
        this.status = in.readString();
        this.result = in.readParcelable(ResultBean.class.getClassLoader());
    }

    public static final Creator<AddBean> CREATOR = new Creator<AddBean>() {
        @Override
        public AddBean createFromParcel(Parcel source) {
            return new AddBean(source);
        }

        @Override
        public AddBean[] newArray(int size) {
            return new AddBean[size];
        }
    };
}
