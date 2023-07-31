package com.example.HotCatCafe.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.HotCatCafe.persistence.entity.Category;
import com.example.HotCatCafe.persistence.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    

    List<Product> findByUnitPriceGreaterThan(BigDecimal price);
    List<Product> findByUnitPriceLessThan(BigDecimal price);
    List<Product> findByUnitPriceGreaterThanEqualAndUnitPriceLessThanEqual(BigDecimal minPrice, BigDecimal maxPrice);
    Optional<Product> findByProductName(String productName);
    List<Product> findByUnitInStockGreaterThan(Integer stock);
    List<Product> findByUnitInStockLessThan(Integer stock);
    List<Product> findByUnitInStockGreaterThanEqualAndUnitInStockLessThanEqual(Integer minStock, Integer maxStock);
    List<Product> findByCategory(Category category);
}
