package com.example.HotCatCafe.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.HotCatCafe.persistence.DTOs.EmployeeDTO;
import com.example.HotCatCafe.persistence.DTOs.interfaces.DTOConverter;
import com.example.HotCatCafe.persistence.enums.JobDefinition;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee extends BaseEntity implements DTOConverter<EmployeeDTO> {
    
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private JobDefinition jobDefinition;

    @OneToMany(mappedBy = "employee",cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private List<Order> orderList;

    @OneToMany(mappedBy = "employee",cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private List<Sale> saleList;


    @Override
    public EmployeeDTO toDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeFullName(firstName+" "+lastName);
        dto.setJobDefinition(jobDefinition.toString());
        dto.setId(id);
        return dto;
    }

    public Employee() {
        orderList = new ArrayList<Order>();
        saleList = new ArrayList<Sale>();
        
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public JobDefinition getJobDefinition() {
        return jobDefinition;
    }
    public void setJobDefinition(JobDefinition jobDefinition) {
        this.jobDefinition = jobDefinition;
    }
    public List<Order> getOrderList() {
        return orderList;
    }
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

   
}
