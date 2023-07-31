package com.example.HotCatCafe.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.HotCatCafe.persistence.entity.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByCategoryName(String categoryName);

}
