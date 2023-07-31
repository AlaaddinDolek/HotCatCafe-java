package com.example.HotCatCafe.persistence.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.HotCatCafe.persistence.DTOs.ProductDTO;
import com.example.HotCatCafe.persistence.DTOs.interfaces.DTOConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product extends BaseEntity implements DTOConverter<ProductDTO>{
    
    private String productName;

    private Integer unitInStock;

    private Integer minStockLimit;

    private BigDecimal unitPrice;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private List<OrderProduct> orderProductList;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    
    @Override
    public ProductDTO toDTO() {
        ProductDTO dto = new ProductDTO();
        
        dto.setMinStockLimit(minStockLimit);
        dto.setProductName(productName);
        dto.setUnitInStock(unitInStock);
        dto.setUnitPrice(unitPrice);
        dto.setId(id);
        dto.setCategoryName(category.getCategoryName());
        return dto;
    }

    public Product() {
        orderProductList = new ArrayList<OrderProduct>();;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    
}
