package com.xbdl.xinushop.bean;

import java.util.List;

public class MyFansBean {

    /**
     * code : 100
     * msg : 处理成功！
     * extend : {"concernList":{"pageNum":1,"pageSize":10,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"concernId":28,"concernUserId":25,"beConcernUserId":24,"creationTime":null,"whetherToBeConcerned":0,"user":{"userId":24,"userName":"xl_28912798","userPhone":"17796653539","password":"123456","xlId":"28912798","headPortrait":null,"sex":null,"signature":null,"registerTime":"2018-10-16 16:33:07","backgroundImg":null,"wechat":null,"loginToken":"9a07a3680f294b03a1099506c7512bb4","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":"","token":""}},{"concernId":29,"concernUserId":25,"beConcernUserId":1,"creationTime":null,"whetherToBeConcerned":1,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},{"concernId":30,"concernUserId":25,"beConcernUserId":2,"creationTime":null,"whetherToBeConcerned":1,"user":{"userId":2,"userName":"2","userPhone":"2","password":"2","xlId":"2","headPortrait":"2","sex":"2","signature":"2","registerTime":"2","backgroundImg":"2","wechat":"2","loginToken":"2","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}}
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
         * concernList : {"pageNum":1,"pageSize":10,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"concernId":28,"concernUserId":25,"beConcernUserId":24,"creationTime":null,"whetherToBeConcerned":0,"user":{"userId":24,"userName":"xl_28912798","userPhone":"17796653539","password":"123456","xlId":"28912798","headPortrait":null,"sex":null,"signature":null,"registerTime":"2018-10-16 16:33:07","backgroundImg":null,"wechat":null,"loginToken":"9a07a3680f294b03a1099506c7512bb4","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":"","token":""}},{"concernId":29,"concernUserId":25,"beConcernUserId":1,"creationTime":null,"whetherToBeConcerned":1,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},{"concernId":30,"concernUserId":25,"beConcernUserId":2,"creationTime":null,"whetherToBeConcerned":1,"user":{"userId":2,"userName":"2","userPhone":"2","password":"2","xlId":"2","headPortrait":"2","sex":"2","signature":"2","registerTime":"2","backgroundImg":"2","wechat":"2","loginToken":"2","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
         */

        private ConcernListBean concernList;

        public ConcernListBean getConcernList() {
            return concernList;
        }

        public void setConcernList(ConcernListBean concernList) {
            this.concernList = concernList;
        }

        public static class ConcernListBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * size : 3
             * startRow : 1
             * endRow : 3
             * total : 3
             * pages : 1
             * list : [{"concernId":28,"concernUserId":25,"beConcernUserId":24,"creationTime":null,"whetherToBeConcerned":0,"user":{"userId":24,"userName":"xl_28912798","userPhone":"17796653539","password":"123456","xlId":"28912798","headPortrait":null,"sex":null,"signature":null,"registerTime":"2018-10-16 16:33:07","backgroundImg":null,"wechat":null,"loginToken":"9a07a3680f294b03a1099506c7512bb4","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":"","token":""}},{"concernId":29,"concernUserId":25,"beConcernUserId":1,"creationTime":null,"whetherToBeConcerned":1,"user":{"userId":1,"userName":"123456","userPhone":"18373892212","password":"123456","xlId":null,"headPortrait":null,"sex":null,"signature":null,"registerTime":null,"backgroundImg":null,"wechat":null,"loginToken":"902ed5926e3d4b29a9d48ea888fa4a4f","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}},{"concernId":30,"concernUserId":25,"beConcernUserId":2,"creationTime":null,"whetherToBeConcerned":1,"user":{"userId":2,"userName":"2","userPhone":"2","password":"2","xlId":"2","headPortrait":"2","sex":"2","signature":"2","registerTime":"2","backgroundImg":"2","wechat":"2","loginToken":"2","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":null,"token":null}}]
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
                 * concernId : 28
                 * concernUserId : 25
                 * beConcernUserId : 24
                 * creationTime : null
                 * whetherToBeConcerned : 0
                 * user : {"userId":24,"userName":"xl_28912798","userPhone":"17796653539","password":"123456","xlId":"28912798","headPortrait":null,"sex":null,"signature":null,"registerTime":"2018-10-16 16:33:07","backgroundImg":null,"wechat":null,"loginToken":"9a07a3680f294b03a1099506c7512bb4","accountBalance":0,"clientId":null,"realName":null,"video":null,"accid":"","token":""}
                 */

                private int concernId;
                private int concernUserId;
                private int beConcernUserId;
                private Object creationTime;
                private int whetherToBeConcerned;
                private UserBean user;

                public int getConcernId() {
                    return concernId;
                }

                public void setConcernId(int concernId) {
                    this.concernId = concernId;
                }

                public int getConcernUserId() {
                    return concernUserId;
                }

                public void setConcernUserId(int concernUserId) {
                    this.concernUserId = concernUserId;
                }

                public int getBeConcernUserId() {
                    return beConcernUserId;
                }

                public void setBeConcernUserId(int beConcernUserId) {
                    this.beConcernUserId = beConcernUserId;
                }

                public Object getCreationTime() {
                    return creationTime;
                }

                public void setCreationTime(Object creationTime) {
                    this.creationTime = creationTime;
                }

                public int getWhetherToBeConcerned() {
                    return whetherToBeConcerned;
                }

                public void setWhetherToBeConcerned(int whetherToBeConcerned) {
                    this.whetherToBeConcerned = whetherToBeConcerned;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                @Override
                public String toString() {
                    return "ListBean{" +
                            "concernId=" + concernId +
                            ", concernUserId=" + concernUserId +
                            ", beConcernUserId=" + beConcernUserId +
                            ", creationTime=" + creationTime +
                            ", whetherToBeConcerned=" + whetherToBeConcerned +
                            ", user=" + user +
                            '}';
                }

                public static class UserBean {
                    /**
                     * userId : 24
                     * userName : xl_28912798
                     * userPhone : 17796653539
                     * password : 123456
                     * xlId : 28912798
                     * headPortrait : null
                     * sex : null
                     * signature : null
                     * registerTime : 2018-10-16 16:33:07
                     * backgroundImg : null
                     * wechat : null
                     * loginToken : 9a07a3680f294b03a1099506c7512bb4
                     * accountBalance : 0.0
                     * clientId : null
                     * realName : null
                     * video : null
                     * accid :
                     * token :
                     */

                    private int userId;
                    private String userName;
                    private String userPhone;
                    private String password;
                    private String xlId;
                    private String headPortrait;
                    private Object sex;
                    private String signature;
                    private String registerTime;
                    private String backgroundImg;
                    private String wechat;
                    private String loginToken;
                    private double accountBalance;
                    private Object clientId;
                    private String realName;
                    private String video;
                    private String accid;
                    private String token;

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

                    public String getSignature() {
                        return signature;
                    }

                    public void setSignature(String signature) {
                        this.signature = signature;
                    }

                    public String getRegisterTime() {
                        return registerTime;
                    }

                    public void setRegisterTime(String registerTime) {
                        this.registerTime = registerTime;
                    }

                    public String getBackgroundImg() {
                        return backgroundImg;
                    }

                    public void setBackgroundImg(String backgroundImg) {
                        this.backgroundImg = backgroundImg;
                    }

                    public String getWechat() {
                        return wechat;
                    }

                    public void setWechat(String wechat) {
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

                    public String getRealName() {
                        return realName;
                    }

                    public void setRealName(String realName) {
                        this.realName = realName;
                    }

                    public String getVideo() {
                        return video;
                    }

                    public void setVideo(String video) {
                        this.video = video;
                    }

                    public String getAccid() {
                        return accid;
                    }

                    public void setAccid(String accid) {
                        this.accid = accid;
                    }

                    public String getToken() {
                        return token;
                    }

                    public void setToken(String token) {
                        this.token = token;
                    }

                    @Override
                    public String toString() {
                        return "UserBean{" +
                                "userId=" + userId +
                                ", userName='" + userName + '\'' +
                                ", userPhone='" + userPhone + '\'' +
                                ", password='" + password + '\'' +
                                ", xlId='" + xlId + '\'' +
                                ", headPortrait='" + headPortrait + '\'' +
                                ", sex=" + sex +
                                ", signature='" + signature + '\'' +
                                ", registerTime='" + registerTime + '\'' +
                                ", backgroundImg='" + backgroundImg + '\'' +
                                ", wechat='" + wechat + '\'' +
                                ", loginToken='" + loginToken + '\'' +
                                ", accountBalance=" + accountBalance +
                                ", clientId=" + clientId +
                                ", realName='" + realName + '\'' +
                                ", video='" + video + '\'' +
                                ", accid='" + accid + '\'' +
                                ", token='" + token + '\'' +
                                '}';
                    }
                }
            }
        }
    }
}
