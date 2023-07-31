package com.example.HotCatCafe.persistence.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.HotCatCafe.persistence.DTOs.ExtraDTO;
import com.example.HotCatCafe.persistence.DTOs.interfaces.DTOConverter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="extras")
public class Extra extends BaseEntity implements DTOConverter<ExtraDTO>{
    
    private String extraName;
    private BigDecimal extraUnitPrice;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToMany
    private List<OrderProduct> orderProductList;


    @Override
    public ExtraDTO toDTO() {
        ExtraDTO dto = new ExtraDTO();
        dto.setExtraName(extraName);
        dto.setCategoryName(category.getCategoryName());
        dto.setExtraUnitPrice(extraUnitPrice);
        dto.setId(id);
        return dto;
    }

    public Extra() {
        orderProductList = new ArrayList<>();
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public BigDecimal getExtraUnitPrice() {
        return extraUnitPrice;
    }

    public void setExtraUnitPrice(BigDecimal extraUnitPrice) {
        this.extraUnitPrice = extraUnitPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }


}
