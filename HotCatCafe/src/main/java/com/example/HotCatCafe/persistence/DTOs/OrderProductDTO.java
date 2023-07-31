package com.example.HotCatCafe.persistence.DTOs;

import java.math.BigDecimal;
import java.util.List;

public class OrderProductDTO extends BaseDTO{
    
    private String productName;
    private BigDecimal unitPrice;
    private List<ExtraDTO> extraDtoList;
    private String tableNumber;

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public String getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }
    public List<ExtraDTO> getExtraDtoList() {
        return extraDtoList;
    }
    public void setExtraDtoList(List<ExtraDTO> extraDtoList) {
        this.extraDtoList = extraDtoList;
    }
}
