package com.xbdl.xinushop.bean;

import java.util.List;

public class WalletDetailBean {


    /**
     * pageNum : 2
     * pageSize : 10
     * size : 2
     * startRow : 11
     * endRow : 12
     * total : 12
     * pages : 2
     * list : [{"detailType":2,"detailContent":"帐户余额充值","detailPrice":1,"payType":1,"createdDate":"2018-10-08 15:07:47","user_name":"哦里","WalletDetailID":11,"remark":"09996081692c4263a0e5414529360278","updatedDate":"2018-10-08 15:07:47","userId":11,"payStatus":2},{"detailType":2,"detailContent":"帐户余额充值","detailPrice":1,"payType":1,"createdDate":"2018-10-08 16:28:53","user_name":"哦里","WalletDetailID":12,"remark":"858cfbc47515492e9919660bda282fac","updatedDate":"2018-10-08 16:28:53","userId":11,"payStatus":2}]
     * prePage : 1
     * nextPage : 0
     * isFirstPage : false
     * isLastPage : true
     * hasPreviousPage : true
     * hasNextPage : false
     * navigatePages : 5
     * navigatepageNums : [1,2]
     * navigateFirstPage : 1
     * navigateLastPage : 2
     * firstPage : 1
     * lastPage : 2
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
         * detailType : 2
         * detailContent : 帐户余额充值
         * detailPrice : 1
         * payType : 1
         * createdDate : 2018-10-08 15:07:47
         * user_name : 哦里
         * WalletDetailID : 11
         * remark : 09996081692c4263a0e5414529360278
         * updatedDate : 2018-10-08 15:07:47
         * userId : 11
         * payStatus : 2
         */

        private int detailType;
        private String detailContent;
        private int detailPrice;
        private int payType;
        private String createdDate;
        private String user_name;
        private int WalletDetailID;
        private String remark;
        private String updatedDate;
        private int userId;
        private int payStatus;

        public int getDetailType() {
            return detailType;
        }

        public void setDetailType(int detailType) {
            this.detailType = detailType;
        }

        public String getDetailContent() {
            return detailContent;
        }

        public void setDetailContent(String detailContent) {
            this.detailContent = detailContent;
        }

        public int getDetailPrice() {
            return detailPrice;
        }

        public void setDetailPrice(int detailPrice) {
            this.detailPrice = detailPrice;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getWalletDetailID() {
            return WalletDetailID;
        }

        public void setWalletDetailID(int WalletDetailID) {
            this.WalletDetailID = WalletDetailID;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }
    }
}
