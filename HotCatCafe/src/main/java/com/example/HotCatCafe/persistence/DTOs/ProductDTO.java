package com.example.HotCatCafe.persistence.DTOs;

import java.math.BigDecimal;

public class ProductDTO extends BaseDTO{
    
    private String productName;

    private Integer unitInStock;

    private Integer minStockLimit;

    private BigDecimal unitPrice;

    private String categoryName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(Integer unitInStock) {
        this.unitInStock = unitInStock;
    }

    public Integer getMinStockLimit() {
        return minStockLimit;
    }

    public void setMinStockLimit(Integer minStockLimit) {
        this.minStockLimit = minStockLimit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
}
