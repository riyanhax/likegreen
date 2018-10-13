package com.xbdl.xinushop.bean;

import java.util.List;

/**
 * 优惠券
 */
public class DiscountCouponBean {

    /**
     * msg : 查询成功
     * code : 1
     * pageInfo : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"couponsExplain":"","amount":500,"user_name":"xl_94969118","forCommodity":"0","couponsId":2,"discount":"","updatedDate":"2018-10-12 15:38:51","userId":11,"couponsName":"全场满200减100","useForType":2,"UserCouponsID":18,"reduceMoney":100,"useDate":null,"createdDate":"2018-10-12 15:38:49","restrictCount":1,"startTime":"2018-08-09","fullMoney":200,"endTime":"2018-09-08","forClassify":"","head_portrait":"20181010140922108.jpg","couponsType":1,"status":0},{"couponsExplain":"","amount":1000,"user_name":"xl_94969118","forCommodity":"","couponsId":1,"discount":"8","updatedDate":"2018-10-12 15:38:37","userId":11,"couponsName":"全场8折","useForType":1,"UserCouponsID":17,"reduceMoney":null,"useDate":null,"createdDate":"2018-10-12 15:38:35","restrictCount":2,"startTime":"2018-08-09","fullMoney":null,"endTime":"2018-09-08","forClassify":"0","head_portrait":"20181010140922108.jpg","couponsType":2,"status":0}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":5,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
     * userId : 11
     * pn : 1
     * token : 0d1b9f8cbf2f4a5080a96d0c24870c6b
     */

    private String msg;
    private int code;
    private PageInfoBean pageInfo;
    private String userId;
    private String pn;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class PageInfoBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 2
         * startRow : 1
         * endRow : 2
         * total : 2
         * pages : 1
         * list : [{"couponsExplain":"","amount":500,"user_name":"xl_94969118","forCommodity":"0","couponsId":2,"discount":"","updatedDate":"2018-10-12 15:38:51","userId":11,"couponsName":"全场满200减100","useForType":2,"UserCouponsID":18,"reduceMoney":100,"useDate":null,"createdDate":"2018-10-12 15:38:49","restrictCount":1,"startTime":"2018-08-09","fullMoney":200,"endTime":"2018-09-08","forClassify":"","head_portrait":"20181010140922108.jpg","couponsType":1,"status":0},{"couponsExplain":"","amount":1000,"user_name":"xl_94969118","forCommodity":"","couponsId":1,"discount":"8","updatedDate":"2018-10-12 15:38:37","userId":11,"couponsName":"全场8折","useForType":1,"UserCouponsID":17,"reduceMoney":null,"useDate":null,"createdDate":"2018-10-12 15:38:35","restrictCount":2,"startTime":"2018-08-09","fullMoney":null,"endTime":"2018-09-08","forClassify":"0","head_portrait":"20181010140922108.jpg","couponsType":2,"status":0}]
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
             * couponsExplain :
             * amount : 500
             * user_name : xl_94969118
             * forCommodity : 0
             * couponsId : 2
             * discount :        折扣
             * updatedDate : 2018-10-12 15:38:51
             * userId : 11
             * couponsName : 全场满200减100
             * useForType : 2
             * UserCouponsID : 18
             * reduceMoney : 100  优惠金额
             * useDate : null
             * createdDate : 2018-10-12 15:38:49
             * restrictCount : 1   限制数量
             * startTime : 2018-08-09
             * fullMoney : 200
             * endTime : 2018-09-08
             * forClassify :
             * head_portrait : 20181010140922108.jpg
             * couponsType : 1
             * status : 0
             */

            private String couponsExplain;
            private int amount;
            private String user_name;
            private String forCommodity;
            private int couponsId;
            private String discount;
            private String updatedDate;
            private int userId;
            private String couponsName;
            private int useForType;
            private int UserCouponsID;
            private int reduceMoney;
            private Object useDate;
            private String createdDate;
            private int restrictCount;
            private String startTime;
            private int fullMoney;
            private String endTime;
            private String forClassify;
            private String head_portrait;
            private int couponsType;
            private int status;

            public String getCouponsExplain() {
                return couponsExplain;
            }

            public void setCouponsExplain(String couponsExplain) {
                this.couponsExplain = couponsExplain;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getForCommodity() {
                return forCommodity;
            }

            public void setForCommodity(String forCommodity) {
                this.forCommodity = forCommodity;
            }

            public int getCouponsId() {
                return couponsId;
            }

            public void setCouponsId(int couponsId) {
                this.couponsId = couponsId;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getUpdatedDate() {
                return updatedDate;
            }

            public void setUpdatedDate(String updatedDate) {
                this.updatedDate = updatedDate;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getCouponsName() {
                return couponsName;
            }

            public void setCouponsName(String couponsName) {
                this.couponsName = couponsName;
            }

            public int getUseForType() {
                return useForType;
            }

            public void setUseForType(int useForType) {
                this.useForType = useForType;
            }

            public int getUserCouponsID() {
                return UserCouponsID;
            }

            public void setUserCouponsID(int UserCouponsID) {
                this.UserCouponsID = UserCouponsID;
            }

            public int getReduceMoney() {
                return reduceMoney;
            }

            public void setReduceMoney(int reduceMoney) {
                this.reduceMoney = reduceMoney;
            }

            public Object getUseDate() {
                return useDate;
            }

            public void setUseDate(Object useDate) {
                this.useDate = useDate;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public int getRestrictCount() {
                return restrictCount;
            }

            public void setRestrictCount(int restrictCount) {
                this.restrictCount = restrictCount;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public int getFullMoney() {
                return fullMoney;
            }

            public void setFullMoney(int fullMoney) {
                this.fullMoney = fullMoney;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getForClassify() {
                return forClassify;
            }

            public void setForClassify(String forClassify) {
                this.forClassify = forClassify;
            }

            public String getHead_portrait() {
                return head_portrait;
            }

            public void setHead_portrait(String head_portrait) {
                this.head_portrait = head_portrait;
            }

            public int getCouponsType() {
                return couponsType;
            }

            public void setCouponsType(int couponsType) {
                this.couponsType = couponsType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
