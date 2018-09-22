package com.xbdl.xinushop.bean;

/**
 * 属性类别
 */
public class CategoryBean {
    private String category = "";//类别
    private String price = "";//价格
    private String stock = "";//库存

    public CategoryBean(String category, String price, String stock) {
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}
