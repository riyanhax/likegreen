package com.xbdl.xinushop.bean;

/**
 * 根据信息获取商品分类数据
 */
public class CommodityClassifyBean {

    /**
     * CommodityClassifyID : 5
     * createdDate : 2018-08-09 18:56:33
     * updatedDate : 2018-08-09 18:56:33
     * classifyName : BD
     */

    private int CommodityClassifyID;
    private String createdDate;
    private String updatedDate;
    private String classifyName;

    public int getCommodityClassifyID() {
        return CommodityClassifyID;
    }

    public void setCommodityClassifyID(int CommodityClassifyID) {
        this.CommodityClassifyID = CommodityClassifyID;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
}
