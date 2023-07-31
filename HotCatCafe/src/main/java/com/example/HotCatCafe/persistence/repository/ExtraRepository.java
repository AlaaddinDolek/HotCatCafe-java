package com.example.HotCatCafe.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.HotCatCafe.persistence.entity.Category;
import com.example.HotCatCafe.persistence.entity.Extra;

public interface ExtraRepository extends CrudRepository<Extra,Long> {
    
    List<Extra> findByCategory(Category category);
    List<Extra> findByExtraUnitPriceGreaterThan(BigDecimal price);
    List<Extra> findByExtraUnitPriceLessThan(BigDecimal price);
    List<Extra> findByExtraUnitPriceGreaterThanEqualAndExtraUnitPriceLessThanEqual(BigDecimal minPrice, BigDecimal maxPrice);
}
