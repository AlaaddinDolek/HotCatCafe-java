package com.example.HotCatCafe.persistence.DTOs;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO extends BaseDTO{
    
    private String tableNumber;

    private BigDecimal totalPrice;

    private String employeeFullName;

    private List<OrderProductDTO> orderProductDtoList;

    private Long saleId;

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<OrderProductDTO> getOrderProductDtoList() {
        return orderProductDtoList;
    }

    public void setOrderProductDtoList(List<OrderProductDTO> orderProductDtoList) {
        this.orderProductDtoList = orderProductDtoList;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
