package com.xbdl.xinushop.bean;

import java.util.List;

public class VideoCommentBean {


    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"page":{"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"commentsId":7,"commentsType":1,"commentsTypeId":1,"commentsUserId":1,"commentsBeCommentedUserId":null,"commentsContent":"嚯嚯","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":null,"count":0,"clickToPraise":true,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},{"commentsId":8,"commentsType":1,"commentsTypeId":1,"commentsUserId":2,"commentsBeCommentedUserId":1,"commentsContent":"嘿嘿","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":{"commentsId":7,"commentsType":1,"commentsTypeId":1,"commentsUserId":1,"commentsBeCommentedUserId":null,"commentsContent":"嚯嚯","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":null,"count":null,"clickToPraise":null,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},"count":0,"clickToPraise":true,"user":{"userId":2,"userName":"2","userPhone":"2","password":"2","xlId":"2","headPortrait":"2","sex":"2","signature":"2","registerTime":"2","backgroundImg":"2","wechat":"2","loginToken":"2","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}}
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
         * page : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"commentsId":7,"commentsType":1,"commentsTypeId":1,"commentsUserId":1,"commentsBeCommentedUserId":null,"commentsContent":"嚯嚯","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":null,"count":0,"clickToPraise":true,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},{"commentsId":8,"commentsType":1,"commentsTypeId":1,"commentsUserId":2,"commentsBeCommentedUserId":1,"commentsContent":"嘿嘿","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":{"commentsId":7,"commentsType":1,"commentsTypeId":1,"commentsUserId":1,"commentsBeCommentedUserId":null,"commentsContent":"嚯嚯","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":null,"count":null,"clickToPraise":null,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},"count":0,"clickToPraise":true,"user":{"userId":2,"userName":"2","userPhone":"2","password":"2","xlId":"2","headPortrait":"2","sex":"2","signature":"2","registerTime":"2","backgroundImg":"2","wechat":"2","loginToken":"2","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
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
             * pageSize : 10
             * size : 2
             * startRow : 1
             * endRow : 2
             * total : 2
             * pages : 1
             * list : [{"commentsId":7,"commentsType":1,"commentsTypeId":1,"commentsUserId":1,"commentsBeCommentedUserId":null,"commentsContent":"嚯嚯","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":null,"count":0,"clickToPraise":true,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},{"commentsId":8,"commentsType":1,"commentsTypeId":1,"commentsUserId":2,"commentsBeCommentedUserId":1,"commentsContent":"嘿嘿","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":{"commentsId":7,"commentsType":1,"commentsTypeId":1,"commentsUserId":1,"commentsBeCommentedUserId":null,"commentsContent":"嚯嚯","commentsCreatedTime":"2018-07-06 12:16:32","commentsUpdateTime":null,"comments":null,"count":null,"clickToPraise":null,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},"count":0,"clickToPraise":true,"user":{"userId":2,"userName":"2","userPhone":"2","password":"2","xlId":"2","headPortrait":"2","sex":"2","signature":"2","registerTime":"2","backgroundImg":"2","wechat":"2","loginToken":"2","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}}]
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
             * lastPage : 1
             * firstPage : 1
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
            private int lastPage;
            private int firstPage;
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

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
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
                 * commentsId : 7
                 * commentsType : 1
                 * commentsTypeId : 1
                 * commentsUserId : 1
                 * commentsBeCommentedUserId : null
                 * commentsContent : 嚯嚯
                 * commentsCreatedTime : 2018-07-06 12:16:32
                 * commentsUpdateTime : null
                 * comments : null
                 * count : 0
                 * clickToPraise : true
                 * user : {"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}
                 */

                private int commentsId;
                private int commentsType;
                private int commentsTypeId;
                private int commentsUserId;
                private Object commentsBeCommentedUserId;
                private String commentsContent;
                private String commentsCreatedTime;
                private Object commentsUpdateTime;
                private Object comments;
                private int count;
                private boolean clickToPraise;
                private UserBean user;

                public int getCommentsId() {
                    return commentsId;
                }

                public void setCommentsId(int commentsId) {
                    this.commentsId = commentsId;
                }

                public int getCommentsType() {
                    return commentsType;
                }

                public void setCommentsType(int commentsType) {
                    this.commentsType = commentsType;
                }

                public int getCommentsTypeId() {
                    return commentsTypeId;
                }

                public void setCommentsTypeId(int commentsTypeId) {
                    this.commentsTypeId = commentsTypeId;
                }

                public int getCommentsUserId() {
                    return commentsUserId;
                }

                public void setCommentsUserId(int commentsUserId) {
                    this.commentsUserId = commentsUserId;
                }

                public Object getCommentsBeCommentedUserId() {
                    return commentsBeCommentedUserId;
                }

                public void setCommentsBeCommentedUserId(Object commentsBeCommentedUserId) {
                    this.commentsBeCommentedUserId = commentsBeCommentedUserId;
                }

                public String getCommentsContent() {
                    return commentsContent;
                }

                public void setCommentsContent(String commentsContent) {
                    this.commentsContent = commentsContent;
                }

                public String getCommentsCreatedTime() {
                    return commentsCreatedTime;
                }

                public void setCommentsCreatedTime(String commentsCreatedTime) {
                    this.commentsCreatedTime = commentsCreatedTime;
                }

                public Object getCommentsUpdateTime() {
                    return commentsUpdateTime;
                }

                public void setCommentsUpdateTime(Object commentsUpdateTime) {
                    this.commentsUpdateTime = commentsUpdateTime;
                }

                public Object getComments() {
                    return comments;
                }

                public void setComments(Object comments) {
                    this.comments = comments;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public boolean isClickToPraise() {
                    return clickToPraise;
                }

                public void setClickToPraise(boolean clickToPraise) {
                    this.clickToPraise = clickToPraise;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public static class UserBean {
                    /**
                     * userId : 1
                     * userName : 123456
                     * userPhone : 18373892212
                     * password : 123456
                     * xlId : null
                     * headPortrait : null
                     * sex : null
                     * signature : null
                     * registerTime : null
                     * backgroundImg : null
                     * wechat : null
                     * loginToken : 902ed5926e3d4b29a9d48ea888fa4a4f
                     * accountBalance : 0.0
                     * clientId : null
                     * realName : null
                     * video : null
                     * accid : null
                     * token : null
                     */

                    private int userId;
                    private String userName;
                    private String userPhone;
                    private String password;
                    private Object xlId;
                    private Object headPortrait;
                    private Object sex;
                    private Object signature;
                    private Object registerTime;
                    private Object backgroundImg;
                    private Object wechat;
                    private String loginToken;
                    private double accountBalance;
                    private Object clientId;
                    private Object realName;
                    private Object video;
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

                    public Object getXlId() {
                        return xlId;
                    }

                    public void setXlId(Object xlId) {
                        this.xlId = xlId;
                    }

                    public Object getHeadPortrait() {
                        return headPortrait;
                    }

                    public void setHeadPortrait(Object headPortrait) {
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

                    public Object getRegisterTime() {
                        return registerTime;
                    }

                    public void setRegisterTime(Object registerTime) {
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

                    public Object getVideo() {
                        return video;
                    }

                    public void setVideo(Object video) {
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
                }
            }
        }
    }
}
