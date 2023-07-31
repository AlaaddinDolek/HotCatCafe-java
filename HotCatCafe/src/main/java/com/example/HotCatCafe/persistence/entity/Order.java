package com.example.HotCatCafe.persistence.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.HotCatCafe.persistence.DTOs.OrderDTO;
import com.example.HotCatCafe.persistence.DTOs.OrderProductDTO;
import com.example.HotCatCafe.persistence.DTOs.interfaces.DTOConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="orders")
public class Order extends BaseEntity implements DTOConverter<OrderDTO> {
    
    private String tableNumber;

    @OneToMany(mappedBy = "order",cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private List<OrderProduct> orderProductList;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @OneToOne(mappedBy = "order",cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private Sale sale;


    public Order() {
        orderProductList = new ArrayList<OrderProduct>();
        totalPrice = new BigDecimal(0);
    }

    public BigDecimal finalPrice() {
        for (OrderProduct orderProduct : orderProductList) {
            for (Extra extra : orderProduct.getExtraList()) {
                totalPrice = totalPrice.add(extra.getExtraUnitPrice());
            }
            totalPrice = totalPrice.add(orderProduct.getProduct().getUnitPrice());
        }
         return totalPrice;
    }


    @Override
    public OrderDTO toDTO() {
        OrderDTO dto = new OrderDTO();
        List<OrderProductDTO> opList = new ArrayList<>();
        for (OrderProduct orderProduct : orderProductList) {
            opList.add(orderProduct.toDTO());
        }
        dto.setOrderProductDtoList(opList);
        dto.setEmployeeFullName(employee.getFirstName()+" "+ employee.getLastName());
        dto.setTableNumber(tableNumber);
        dto.setTotalPrice(finalPrice());
        dto.setId(id);
        dto.setSaleId((sale != null) ? sale.getId():0);
        return dto;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }


    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


   





    
}
