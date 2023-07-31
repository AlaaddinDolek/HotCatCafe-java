package com.example.HotCatCafe.persistence.entity;

import com.example.HotCatCafe.persistence.DTOs.SaleDTO;
import com.example.HotCatCafe.persistence.DTOs.interfaces.DTOConverter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sales")
public class Sale extends BaseEntity implements DTOConverter<SaleDTO>{
    
    
    @OneToOne
    @JoinColumn(name="order_id")
    private Order order;

    
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;


    @Override
    public SaleDTO toDTO() {
        SaleDTO dto = new SaleDTO();
        dto.setEmployeeFullName(employee.getFirstName()+" "+employee.getLastName());
        dto.setOrderDTO(order.toDTO());
        dto.setId(id);
        return dto;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

   
}
