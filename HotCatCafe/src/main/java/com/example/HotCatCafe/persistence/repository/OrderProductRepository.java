package com.example.HotCatCafe.persistence.repository;

import com.example.HotCatCafe.persistence.entity.Order;
import com.example.HotCatCafe.persistence.entity.OrderProduct;
import com.example.HotCatCafe.persistence.entity.Product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface OrderProductRepository extends CrudRepository<OrderProduct,Long> {
 
    List<OrderProduct> findByOrder(Order order);
    List<OrderProduct> findByProduct(Product product);
}
