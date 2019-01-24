package com.ylean.cf_hospitalapp.mall.bean;

import android.os.Parcel;

import com.ylean.cf_hospitalapp.base.bean.Basebean;

import java.util.List;

/**
 * Created by linaidao on 2019/1/24.
 */

public class LogisticEntry extends Basebean {

    /**
     * data : {"logisticcode":"3904586483173","name":"韵达快递","shippercode":"YD","state":3,"success":"true","traces":[{"acceptstation":"【山东枣庄公司】已收件","accepttime":"2018-03-13 14:30:42"},{"acceptstation":"【江苏淮安分拨中心】正在进行【称重】扫描","accepttime":"2018-03-13 21:04:47"},{"acceptstation":"【江苏淮安分拨中心】发往【浙江宁波分拨中心】","accepttime":"2018-03-13 21:07:07"},{"acceptstation":"【浙江宁波分拨中心】正在进行【卸车】扫描","accepttime":"2018-03-14 10:46:56"},{"acceptstation":"【浙江宁波分拨中心】发往【浙江宁海县公司】","accepttime":"2018-03-14 10:51:09"},{"acceptstation":"【浙江宁海县公司】正在进行【派送】扫描","accepttime":"2018-03-15 08:07:40"},{"acceptstation":"【浙江宁海县公司黄坛分部】正在进行【分发】扫描","accepttime":"2018-03-15 08:09:02"},{"acceptstation":"【浙江宁海县公司黄坛分部】已签收，签收人是【已签收】","accepttime":"2018-03-15 16:50:32"}]}
     * startTime : 2019-01-24 12:40:29
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
         * logisticcode : 3904586483173
         * name : 韵达快递
         * shippercode : YD
         * state : 3
         * success : true
         * traces : [{"acceptstation":"【山东枣庄公司】已收件","accepttime":"2018-03-13 14:30:42"},{"acceptstation":"【江苏淮安分拨中心】正在进行【称重】扫描","accepttime":"2018-03-13 21:04:47"},{"acceptstation":"【江苏淮安分拨中心】发往【浙江宁波分拨中心】","accepttime":"2018-03-13 21:07:07"},{"acceptstation":"【浙江宁波分拨中心】正在进行【卸车】扫描","accepttime":"2018-03-14 10:46:56"},{"acceptstation":"【浙江宁波分拨中心】发往【浙江宁海县公司】","accepttime":"2018-03-14 10:51:09"},{"acceptstation":"【浙江宁海县公司】正在进行【派送】扫描","accepttime":"2018-03-15 08:07:40"},{"acceptstation":"【浙江宁海县公司黄坛分部】正在进行【分发】扫描","accepttime":"2018-03-15 08:09:02"},{"acceptstation":"【浙江宁海县公司黄坛分部】已签收，签收人是【已签收】","accepttime":"2018-03-15 16:50:32"}]
         */

        private String logisticcode;
        private String name;
        private String shippercode;
        private String state;
        private String success;
        private List<TracesBean> traces;

        public String getLogisticcode() {
            return logisticcode;
        }

        public void setLogisticcode(String logisticcode) {
            this.logisticcode = logisticcode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShippercode() {
            return shippercode;
        }

        public void setShippercode(String shippercode) {
            this.shippercode = shippercode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public List<TracesBean> getTraces() {
            return traces;
        }

        public void setTraces(List<TracesBean> traces) {
            this.traces = traces;
        }

        public static class TracesBean implements android.os.Parcelable {
            /**
             * acceptstation : 【山东枣庄公司】已收件
             * accepttime : 2018-03-13 14:30:42
             */

            private String acceptstation;
            private String accepttime;

            public String getAcceptstation() {
                return acceptstation;
            }

            public void setAcceptstation(String acceptstation) {
                this.acceptstation = acceptstation;
            }

            public String getAccepttime() {
                return accepttime;
            }

            public void setAccepttime(String accepttime) {
                this.accepttime = accepttime;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.acceptstation);
                dest.writeString(this.accepttime);
            }

            public TracesBean() {
            }

            protected TracesBean(Parcel in) {
                this.acceptstation = in.readString();
                this.accepttime = in.readString();
            }

            public static final Creator<TracesBean> CREATOR = new Creator<TracesBean>() {
                @Override
                public TracesBean createFromParcel(Parcel source) {
                    return new TracesBean(source);
                }

                @Override
                public TracesBean[] newArray(int size) {
                    return new TracesBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.logisticcode);
            dest.writeString(this.name);
            dest.writeString(this.shippercode);
            dest.writeString(this.state);
            dest.writeString(this.success);
            dest.writeTypedList(this.traces);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.logisticcode = in.readString();
            this.name = in.readString();
            this.shippercode = in.readString();
            this.state = in.readString();
            this.success = in.readString();
            this.traces = in.createTypedArrayList(TracesBean.CREATOR);
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

    public LogisticEntry() {
    }

    protected LogisticEntry(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.startTime = in.readString();
        this.token = in.readString();
    }

    public static final Creator<LogisticEntry> CREATOR = new Creator<LogisticEntry>() {
        @Override
        public LogisticEntry createFromParcel(Parcel source) {
            return new LogisticEntry(source);
        }

        @Override
        public LogisticEntry[] newArray(int size) {
            return new LogisticEntry[size];
        }
    };
}
