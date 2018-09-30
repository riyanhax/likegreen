package com.xbdl.xinushop.bean;

public class PlantDetailBean {

    /**
     * p : {"pdId":4,"dayId":"fee15cca595f494db321d3f68e67de23","desc":"持续更新","name":"dadadaad222","imgpath":"/pic/111.jpg","address":"中国广东省广州市天河区天河客运站","plantTime":"2018-09-30","time":1538306510000,"pdType":2,"userId":9,"userName":"xl_16298763","headPortrait":null,"signature":null}
     * isFollow : 0
     * isLike : 2
     * likeCount : 0
     * vs : 1
     * day : 1
     * commentCount : 0
     */

    private PBean p;
    private int isFollow;
    private int isLike;
    private int likeCount;
    private int vs;
    private int day;
    private int commentCount;

    public PBean getP() {
        return p;
    }

    public void setP(PBean p) {
        this.p = p;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getVs() {
        return vs;
    }

    public void setVs(int vs) {
        this.vs = vs;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public static class PBean {
        /**
         * pdId : 4
         * dayId : fee15cca595f494db321d3f68e67de23
         * desc : 持续更新
         * name : dadadaad222
         * imgpath : /pic/111.jpg
         * address : 中国广东省广州市天河区天河客运站
         * plantTime : 2018-09-30
         * time : 1538306510000
         * pdType : 2
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

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }
}
