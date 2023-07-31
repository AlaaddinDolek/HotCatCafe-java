package com.example.HotCatCafe.persistence.DTOs;

public class SaleDTO extends BaseDTO{
    
    private OrderDTO orderDTO;
    private String employeeFullName;
    
    public OrderDTO getOrderDTO() {
        return orderDTO;
    }
    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }
    public String getEmployeeFullName() {
        return employeeFullName;
    }
    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }
}
