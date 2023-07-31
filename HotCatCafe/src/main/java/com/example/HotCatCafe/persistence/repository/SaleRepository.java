package com.example.HotCatCafe.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.HotCatCafe.persistence.entity.Employee;
import com.example.HotCatCafe.persistence.entity.Order;
import com.example.HotCatCafe.persistence.entity.Sale;

public interface SaleRepository extends CrudRepository<Sale, Long> {
    
    Optional<Sale> findByOrder(Order order);
    List<Sale> findByEmployee(Employee employee);
}
