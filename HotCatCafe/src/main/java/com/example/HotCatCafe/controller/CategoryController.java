package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotCatCafe.exceptions.CategoryAlreadyExistsException;
import com.example.HotCatCafe.exceptions.CategoryNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.CategoryDTO;
import com.example.HotCatCafe.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")

public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Long createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws CategoryAlreadyExistsException {
        return categoryService.addCategory(categoryDTO);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/id/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
       return categoryService.findCategoryById(id);
    }

    @GetMapping("/name/{categoryName}")
    public CategoryDTO getCategoryByCategoryName(@PathVariable String categoryName) throws CategoryNotFoundException{
        return categoryService.findCategoryByCategoryName(categoryName);
    }

    @PutMapping("{id}")
    public String updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) throws CategoryAlreadyExistsException,CategoryNotFoundException{
        return categoryService.updateCategory(categoryDTO, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id) throws CategoryNotFoundException{
        return categoryService.removeCategoryById(id);
    }
    
}