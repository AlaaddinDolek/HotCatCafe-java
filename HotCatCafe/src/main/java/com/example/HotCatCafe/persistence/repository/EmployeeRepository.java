package com.example.HotCatCafe.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.HotCatCafe.persistence.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long>{
    
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
}
