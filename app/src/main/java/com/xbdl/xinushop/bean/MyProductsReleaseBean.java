package com.xbdl.xinushop.bean;

import java.io.Serializable;
import java.util.List;

public class MyProductsReleaseBean {


    /**
     * pageNum : 1
     * pageSize : 10
     * size : 1
     * startRow : 1
     * endRow : 1
     * total : 1
     * pages : 1
     * list : [{"purchaseLimitation":null,"salesVolume":null,"CommodityID":1,"discountPrice":70,"updatedDate":"2018-10-09 11:49:15","commodityContent":"超级炫彩的植物灯","commodityPrice":50,"expressFee":0,"commodityType":0,"createdDate":"2018-10-09 11:49:13","create_id":11,"guaranteePrice":5000,"commodityStatus":1,"auditStatus":1,"yieldly":"广东广州","commodityClassify":1,"createName":"哦里","titleUrl":"http://120.79.173.209:8080/uploadFiles/1c2ff014429c4c4f93406be0d11d213d.png,","classifyName":"植物灯","commodityName":"炫彩植物灯"}]
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

    public static class ListBean implements Serializable{
        /**
         * purchaseLimitation : null  采购限制
         * salesVolume : null   销售量
         * CommodityID : 1  商品标识
         * discountPrice : 70  折扣价格
         * updatedDate : 2018-10-09 11:49:15
         * commodityContent : 超级炫彩的植物灯    商品目录
         * commodityPrice : 50  商品价格
         * expressFee : 0  特快费
         * commodityType : 0  商品类型
         * createdDate : 2018-10-09 11:49:13
         * create_id : 11
         * guaranteePrice : 5000   保证价格
         * commodityStatus : 1    商品状态
         * auditStatus : 1    审计地位
         * yieldly : 广东广州
         * commodityClassify : 1  商品分类
         * createName : 哦里
         * titleUrl : http://120.79.173.209:8080/uploadFiles/1c2ff014429c4c4f93406be0d11d213d.png,
         * classifyName : 植物灯    分类名
         * commodityName : 炫彩植物灯    商品名
         */

        private Object purchaseLimitation;
        private Object salesVolume;
        private int CommodityID;
        private int discountPrice;
        private String updatedDate;
        private String commodityContent;
        private int commodityPrice;
        private int expressFee;
        private int commodityType;
        private String createdDate;
        private int create_id;
        private int guaranteePrice;
        private int commodityStatus;
        private int auditStatus;
        private String yieldly;
        private int commodityClassify;
        private String createName;
        private String titleUrl;
        private String classifyName;
        private String commodityName;

        public Object getPurchaseLimitation() {
            return purchaseLimitation;
        }

        public void setPurchaseLimitation(Object purchaseLimitation) {
            this.purchaseLimitation = purchaseLimitation;
        }

        public Object getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(Object salesVolume) {
            this.salesVolume = salesVolume;
        }

        public int getCommodityID() {
            return CommodityID;
        }

        public void setCommodityID(int CommodityID) {
            this.CommodityID = CommodityID;
        }

        public int getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(int discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }

        public String getCommodityContent() {
            return commodityContent;
        }

        public void setCommodityContent(String commodityContent) {
            this.commodityContent = commodityContent;
        }

        public int getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(int commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public int getExpressFee() {
            return expressFee;
        }

        public void setExpressFee(int expressFee) {
            this.expressFee = expressFee;
        }

        public int getCommodityType() {
            return commodityType;
        }

        public void setCommodityType(int commodityType) {
            this.commodityType = commodityType;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public int getCreate_id() {
            return create_id;
        }

        public void setCreate_id(int create_id) {
            this.create_id = create_id;
        }

        public int getGuaranteePrice() {
            return guaranteePrice;
        }

        public void setGuaranteePrice(int guaranteePrice) {
            this.guaranteePrice = guaranteePrice;
        }

        public int getCommodityStatus() {
            return commodityStatus;
        }

        public void setCommodityStatus(int commodityStatus) {
            this.commodityStatus = commodityStatus;
        }

        public int getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(int auditStatus) {
            this.auditStatus = auditStatus;
        }

        public String getYieldly() {
            return yieldly;
        }

        public void setYieldly(String yieldly) {
            this.yieldly = yieldly;
        }

        public int getCommodityClassify() {
            return commodityClassify;
        }

        public void setCommodityClassify(int commodityClassify) {
            this.commodityClassify = commodityClassify;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getTitleUrl() {
            return titleUrl;
        }

        public void setTitleUrl(String titleUrl) {
            this.titleUrl = titleUrl;
        }

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }
    }
}
