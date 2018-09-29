package com.xbdl.xinushop.bean;

/**
 * 种植日记 bean
 */
public class PlantingDiaryBean {
    /**
     * p1 : {"pdId":1,"dayId":"bfb5d88162554c93ab1107a6743cfa77","desc":"VLOOKUP","name":null,"imgpath":"/pic/960f9d93131e4da597400e3737f74e04.jpg","address":"中国广东省广州市番禺区鸿城大街10号","plantTime":"2018年09月28日","time":1538121074000,"pdType":1,"userId":9,"userName":"xl_16298763","headPortrait":null,"signature":null}
     * isFollow : 0
     * day1 : 1
     */

    private P1Bean p1;
    private int isFollow;
    private int day1;

    public P1Bean getP1() {
        return p1;
    }

    public void setP1(P1Bean p1) {
        this.p1 = p1;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public static class P1Bean {
        /**
         * pdId : 1
         * dayId : bfb5d88162554c93ab1107a6743cfa77
         * desc : VLOOKUP
         * name : null
         * imgpath : /pic/960f9d93131e4da597400e3737f74e04.jpg
         * address : 中国广东省广州市番禺区鸿城大街10号
         * plantTime : 2018年09月28日
         * time : 1538121074000
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

        public Object getName() {
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
        public String toString() {
            return "P1Bean{" +
                    "pdId=" + pdId +
                    ", dayId='" + dayId + '\'' +
                    ", desc='" + desc + '\'' +
                    ", name='" + name + '\'' +
                    ", imgpath='" + imgpath + '\'' +
                    ", address='" + address + '\'' +
                    ", plantTime='" + plantTime + '\'' +
                    ", time=" + time +
                    ", pdType=" + pdType +
                    ", userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", headPortrait='" + headPortrait + '\'' +
                    ", signature='" + signature + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PlantingDiaryBean{" +
                "p1=" + p1 +
                ", isFollow=" + isFollow +
                ", day1=" + day1 +
                '}';
    }
}
