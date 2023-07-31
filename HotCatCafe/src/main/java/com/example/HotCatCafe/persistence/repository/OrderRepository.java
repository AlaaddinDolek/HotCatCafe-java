package com.example.HotCatCafe.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.HotCatCafe.persistence.entity.Employee;
import com.example.HotCatCafe.persistence.entity.Order;

public interface OrderRepository extends CrudRepository<Order,Long> {
    
    List<Order> findByTableNumber(String tableNumber);
    List<Order> findByTotalPriceGreaterThan(BigDecimal price);
    List<Order> findByTotalPriceLessThan(BigDecimal price);
    List<Order> findByTotalPriceGreaterThanEqualAndTotalPriceLessThanEqual(BigDecimal minPrice, BigDecimal maxPrice);
    List<Order> findByEmployee(Employee employee);
    Optional<Order> findByTableNumberAndSaleIsNull(String tableNumber);
    Optional<Order> findByIdAndSaleIsNull(Long id);
}
