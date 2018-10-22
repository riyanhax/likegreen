package com.xbdl.xinushop.bean;

import java.util.List;

public class FocusVideoBean {


    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"page":{"pageNum":1,"pageSize":2,"size":2,"startRow":0,"endRow":1,"total":2,"pages":1,"list":[{"userId":25,"userName":"xl_52926565","userPhone":"13533419133","password":"12345678","xlId":"52926565","headPortrait":"20181018180311664.jpg","sex":null,"signature":null,"registerTime":"2018-10-18 15:43:00","backgroundImg":null,"wechat":null,"loginToken":"4a9a68b79eed436dad134333dbca1b34","accountBalance":0,"clientId":null,"realName":null,"video":{"videoId":4,"type":1,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4","headline":"体来","music":null,"commodityId":null,"userId":25,"status":0,"createTime":"2018-10-18 16:37:38","updateTime":"2018-10-18 16:37:38","recommend":null,"clickNum":null,"numberOfForwards":null},"accid":null,"token":null}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
     * object : null
     */

    private int code;
    private String msg;
    private ExtendBean extend;
    private Object object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ExtendBean getExtend() {
        return extend;
    }

    public void setExtend(ExtendBean extend) {
        this.extend = extend;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static class ExtendBean {
        /**
         * page : {"pageNum":1,"pageSize":2,"size":2,"startRow":0,"endRow":1,"total":2,"pages":1,"list":[{"userId":25,"userName":"xl_52926565","userPhone":"13533419133","password":"12345678","xlId":"52926565","headPortrait":"20181018180311664.jpg","sex":null,"signature":null,"registerTime":"2018-10-18 15:43:00","backgroundImg":null,"wechat":null,"loginToken":"4a9a68b79eed436dad134333dbca1b34","accountBalance":0,"clientId":null,"realName":null,"video":{"videoId":4,"type":1,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4","headline":"体来","music":null,"commodityId":null,"userId":25,"status":0,"createTime":"2018-10-18 16:37:38","updateTime":"2018-10-18 16:37:38","recommend":null,"clickNum":null,"numberOfForwards":null},"accid":null,"token":null}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
         */

        private PageBean page;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public static class PageBean {
            /**
             * pageNum : 1
             * pageSize : 2
             * size : 2
             * startRow : 0
             * endRow : 1
             * total : 2
             * pages : 1
             * list : [{"userId":25,"userName":"xl_52926565","userPhone":"13533419133","password":"12345678","xlId":"52926565","headPortrait":"20181018180311664.jpg","sex":null,"signature":null,"registerTime":"2018-10-18 15:43:00","backgroundImg":null,"wechat":null,"loginToken":"4a9a68b79eed436dad134333dbca1b34","accountBalance":0,"clientId":null,"realName":null,"video":{"videoId":4,"type":1,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4","headline":"体来","music":null,"commodityId":null,"userId":25,"status":0,"createTime":"2018-10-18 16:37:38","updateTime":"2018-10-18 16:37:38","recommend":null,"clickNum":null,"numberOfForwards":null},"accid":null,"token":null}]
             * prePage : 0
             * nextPage : 0
             * isFirstPage : true
             * isLastPage : true
             * hasPreviousPage : false
             * hasNextPage : false
             * navigatePages : 5
             * navigatepageNums : [1]
             * navigateFirstPage : 1
             * navigateLastPage : 1
             * firstPage : 1
             * lastPage : 1
             */

            private int pageNum;
            private int pageSize;
            private int size;
            private int startRow;
            private int endRow;
            private int total;
            private int pages;
            private int prePage;
            private int nextPage;
            private boolean isFirstPage;
            private boolean isLastPage;
            private boolean hasPreviousPage;
            private boolean hasNextPage;
            private int navigatePages;
            private int navigateFirstPage;
            private int navigateLastPage;
            private int firstPage;
            private int lastPage;
            private List<ListBean> list;
            private List<Integer> navigatepageNums;

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public int getEndRow() {
                return endRow;
            }

            public void setEndRow(int endRow) {
                this.endRow = endRow;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public boolean isIsFirstPage() {
                return isFirstPage;
            }

            public void setIsFirstPage(boolean isFirstPage) {
                this.isFirstPage = isFirstPage;
            }

            public boolean isIsLastPage() {
                return isLastPage;
            }

            public void setIsLastPage(boolean isLastPage) {
                this.isLastPage = isLastPage;
            }

            public boolean isHasPreviousPage() {
                return hasPreviousPage;
            }

            public void setHasPreviousPage(boolean hasPreviousPage) {
                this.hasPreviousPage = hasPreviousPage;
            }

            public boolean isHasNextPage() {
                return hasNextPage;
            }

            public void setHasNextPage(boolean hasNextPage) {
                this.hasNextPage = hasNextPage;
            }

            public int getNavigatePages() {
                return navigatePages;
            }

            public void setNavigatePages(int navigatePages) {
                this.navigatePages = navigatePages;
            }

            public int getNavigateFirstPage() {
                return navigateFirstPage;
            }

            public void setNavigateFirstPage(int navigateFirstPage) {
                this.navigateFirstPage = navigateFirstPage;
            }

            public int getNavigateLastPage() {
                return navigateLastPage;
            }

            public void setNavigateLastPage(int navigateLastPage) {
                this.navigateLastPage = navigateLastPage;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<Integer> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<Integer> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public static class ListBean {
                /**
                 * userId : 25
                 * userName : xl_52926565
                 * userPhone : 13533419133
                 * password : 12345678
                 * xlId : 52926565
                 * headPortrait : 20181018180311664.jpg
                 * sex : null
                 * signature : null
                 * registerTime : 2018-10-18 15:43:00
                 * backgroundImg : null
                 * wechat : null
                 * loginToken : 4a9a68b79eed436dad134333dbca1b34
                 * accountBalance : 0.0
                 * clientId : null
                 * realName : null
                 * video : {"videoId":4,"type":1,"url":"http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4","headline":"体来","music":null,"commodityId":null,"userId":25,"status":0,"createTime":"2018-10-18 16:37:38","updateTime":"2018-10-18 16:37:38","recommend":null,"clickNum":null,"numberOfForwards":null}
                 * accid : null
                 * token : null
                 */

                private int userId;
                private String userName;
                private String userPhone;
                private String password;
                private String xlId;
                private String headPortrait;
                private Object sex;
                private Object signature;
                private String registerTime;
                private Object backgroundImg;
                private Object wechat;
                private String loginToken;
                private double accountBalance;
                private Object clientId;
                private Object realName;
                private VideoBean video;
                private Object accid;
                private Object token;

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

                public String getUserPhone() {
                    return userPhone;
                }

                public void setUserPhone(String userPhone) {
                    this.userPhone = userPhone;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getXlId() {
                    return xlId;
                }

                public void setXlId(String xlId) {
                    this.xlId = xlId;
                }

                public String getHeadPortrait() {
                    return headPortrait;
                }

                public void setHeadPortrait(String headPortrait) {
                    this.headPortrait = headPortrait;
                }

                public Object getSex() {
                    return sex;
                }

                public void setSex(Object sex) {
                    this.sex = sex;
                }

                public Object getSignature() {
                    return signature;
                }

                public void setSignature(Object signature) {
                    this.signature = signature;
                }

                public String getRegisterTime() {
                    return registerTime;
                }

                public void setRegisterTime(String registerTime) {
                    this.registerTime = registerTime;
                }

                public Object getBackgroundImg() {
                    return backgroundImg;
                }

                public void setBackgroundImg(Object backgroundImg) {
                    this.backgroundImg = backgroundImg;
                }

                public Object getWechat() {
                    return wechat;
                }

                public void setWechat(Object wechat) {
                    this.wechat = wechat;
                }

                public String getLoginToken() {
                    return loginToken;
                }

                public void setLoginToken(String loginToken) {
                    this.loginToken = loginToken;
                }

                public double getAccountBalance() {
                    return accountBalance;
                }

                public void setAccountBalance(double accountBalance) {
                    this.accountBalance = accountBalance;
                }

                public Object getClientId() {
                    return clientId;
                }

                public void setClientId(Object clientId) {
                    this.clientId = clientId;
                }

                public Object getRealName() {
                    return realName;
                }

                public void setRealName(Object realName) {
                    this.realName = realName;
                }

                public VideoBean getVideo() {
                    return video;
                }

                public void setVideo(VideoBean video) {
                    this.video = video;
                }

                public Object getAccid() {
                    return accid;
                }

                public void setAccid(Object accid) {
                    this.accid = accid;
                }

                public Object getToken() {
                    return token;
                }

                public void setToken(Object token) {
                    this.token = token;
                }

                public static class VideoBean {
                    /**
                     * videoId : 4
                     * type : 1
                     * url : http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/f56f405f-5b58-4e11-8ce9-b464cde8249c.mp4
                     * headline : 体来
                     * music : null
                     * commodityId : null
                     * userId : 25
                     * status : 0
                     * createTime : 2018-10-18 16:37:38
                     * updateTime : 2018-10-18 16:37:38
                     * recommend : null
                     * clickNum : null
                     * numberOfForwards : null
                     */

                    private int videoId;
                    private int type;
                    private String url;
                    private String headline;
                    private Object music;
                    private Object commodityId;
                    private int userId;
                    private int status;
                    private String createTime;
                    private String updateTime;
                    private Object recommend;
                    private Object clickNum;
                    private Object numberOfForwards;

                    public int getVideoId() {
                        return videoId;
                    }

                    public void setVideoId(int videoId) {
                        this.videoId = videoId;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public String getHeadline() {
                        return headline;
                    }

                    public void setHeadline(String headline) {
                        this.headline = headline;
                    }

                    public Object getMusic() {
                        return music;
                    }

                    public void setMusic(Object music) {
                        this.music = music;
                    }

                    public Object getCommodityId() {
                        return commodityId;
                    }

                    public void setCommodityId(Object commodityId) {
                        this.commodityId = commodityId;
                    }

                    public int getUserId() {
                        return userId;
                    }

                    public void setUserId(int userId) {
                        this.userId = userId;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public String getUpdateTime() {
                        return updateTime;
                    }

                    public void setUpdateTime(String updateTime) {
                        this.updateTime = updateTime;
                    }

                    public Object getRecommend() {
                        return recommend;
                    }

                    public void setRecommend(Object recommend) {
                        this.recommend = recommend;
                    }

                    public Object getClickNum() {
                        return clickNum;
                    }

                    public void setClickNum(Object clickNum) {
                        this.clickNum = clickNum;
                    }

                    public Object getNumberOfForwards() {
                        return numberOfForwards;
                    }

                    public void setNumberOfForwards(Object numberOfForwards) {
                        this.numberOfForwards = numberOfForwards;
                    }
                }
            }
        }
    }
}
