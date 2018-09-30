package com.xbdl.xinushop.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 种植日记 bean
 */
public class PlantingDiaryBean implements Parcelable {


    /**
     * p1 : {"pdId":2,"dayId":"fee15cca595f494db321d3f68e67de23","desc":"公积金","name":"daawwwww","imgpath":"/pic/e35b4dbb1b8345eb9665d0b71c81708f.jpg","address":"中国广东省广州市番禺区鸿城大街10号","plantTime":"2018-09-29","time":1538189811000,"pdType":1,"userId":9,"userName":"xl_16298763","headPortrait":null,"signature":null}
     * isFollow : 0
     * p2 : {"pdId":3,"dayId":"fee15cca595f494db321d3f68e67de23","name":"sdfname拉萨的积拉萨的积分","desc":"随便","imgpath":"/pic/13f97192ac834e06b3c21bef8bf0981c.jpg","address":"中国广东省江门市","plantTime":"2018-09-30","time":1538192982000,"pdType":2,"userId":9}
     * p3 : {"pdId":4,"dayId":"fee15cca595f494db321d3f68e67de23","name":"dadadaad222","desc":"持续更新","imgpath":"/pic/111.jpg","address":"中国广东省广州市天河区天河客运站","plantTime":"2018-09-30","time":1538306510000,"pdType":2,"userId":9}
     * day2 : 1
     * day3 : 1
     * day1 : 1
     */

    private PBean p1;
    private int isFollow;
    private PBean p2;
    private PBean p3;
    private int day2;
    private int day3;
    private int day1;

    public PBean getP1() {
        return p1;
    }

    public void setP1(PBean p1) {
        this.p1 = p1;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public PBean getP2() {
        return p2;
    }

    public void setP2(PBean p2) {
        this.p2 = p2;
    }

    public PBean getP3() {
        return p3;
    }

    public void setP3(PBean p3) {
        this.p3 = p3;
    }

    public int getDay2() {
        return day2;
    }

    public void setDay2(int day2) {
        this.day2 = day2;
    }

    public int getDay3() {
        return day3;
    }

    public void setDay3(int day3) {
        this.day3 = day3;
    }

    public int getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public static class PBean implements Parcelable {
        /**
         * pdId : 2
         * dayId : fee15cca595f494db321d3f68e67de23
         * desc : 公积金
         * name : daawwwww
         * imgpath : /pic/e35b4dbb1b8345eb9665d0b71c81708f.jpg
         * address : 中国广东省广州市番禺区鸿城大街10号
         * plantTime : 2018-09-29
         * time : 1538189811000
         * pdType : 1
         * userId : 9
         * userName : xl_16298763
         * headPortrait : null
         * signature : null
         */

        private int pdId;
        private String dayId;
        private String desc;
        private String name;
        private String imgpath;
        private String address;
        private String plantTime;
        private long time;
        private int pdType;
        private int userId;
        private String userName;
        private String headPortrait;
        private String signature;

        public int getPdId() {
            return pdId;
        }

        public void setPdId(int pdId) {
            this.pdId = pdId;
        }

        public String getDayId() {
            return dayId;
        }

        public void setDayId(String dayId) {
            this.dayId = dayId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPlantTime() {
            return plantTime;
        }

        public void setPlantTime(String plantTime) {
            this.plantTime = plantTime;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getPdType() {
            return pdType;
        }

        public void setPdType(int pdType) {
            this.pdType = pdType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.pdId);
            dest.writeString(this.dayId);
            dest.writeString(this.desc);
            dest.writeString(this.name);
            dest.writeString(this.imgpath);
            dest.writeString(this.address);
            dest.writeString(this.plantTime);
            dest.writeLong(this.time);
            dest.writeInt(this.pdType);
            dest.writeInt(this.userId);
            dest.writeString(this.userName);
            dest.writeString(this.headPortrait);
            dest.writeString(this.signature);
        }

        public PBean() {
        }

        protected PBean(Parcel in) {
            this.pdId = in.readInt();
            this.dayId = in.readString();
            this.desc = in.readString();
            this.name = in.readString();
            this.imgpath = in.readString();
            this.address = in.readString();
            this.plantTime = in.readString();
            this.time = in.readLong();
            this.pdType = in.readInt();
            this.userId = in.readInt();
            this.userName = in.readString();
            this.headPortrait = in.readParcelable(Object.class.getClassLoader());
            this.signature = in.readParcelable(Object.class.getClassLoader());
        }

        public static final Creator<PBean> CREATOR = new Creator<PBean>() {
            @Override
            public PBean createFromParcel(Parcel source) {
                return new PBean(source);
            }

            @Override
            public PBean[] newArray(int size) {
                return new PBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.p1, flags);
        dest.writeInt(this.isFollow);
        dest.writeParcelable(this.p2, flags);
        dest.writeParcelable(this.p3, flags);
        dest.writeInt(this.day2);
        dest.writeInt(this.day3);
        dest.writeInt(this.day1);
    }

    public PlantingDiaryBean() {
    }

    protected PlantingDiaryBean(Parcel in) {
        this.p1 = in.readParcelable(PBean.class.getClassLoader());
        this.isFollow = in.readInt();
        this.p2 = in.readParcelable(PBean.class.getClassLoader());
        this.p3 = in.readParcelable(PBean.class.getClassLoader());
        this.day2 = in.readInt();
        this.day3 = in.readInt();
        this.day1 = in.readInt();
    }

    public static final Parcelable.Creator<PlantingDiaryBean> CREATOR = new Parcelable.Creator<PlantingDiaryBean>() {
        @Override
        public PlantingDiaryBean createFromParcel(Parcel source) {
            return new PlantingDiaryBean(source);
        }

        @Override
        public PlantingDiaryBean[] newArray(int size) {
            return new PlantingDiaryBean[size];
        }
    };
}
