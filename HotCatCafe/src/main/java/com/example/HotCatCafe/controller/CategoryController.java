package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/add")
    public Long createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws CategoryAlreadyExistsException {
        return categoryService.addCategory(categoryDTO);
    }

    @GetMapping("/getAll")
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("getById")
    public CategoryDTO getCategoryById(Long id) throws CategoryNotFoundException {
       return categoryService.findCategoryById(id);
    }

    @GetMapping("/getByCategoryName")
    public CategoryDTO getCategoryByCategoryName(String categoryName) throws CategoryNotFoundException{
        return categoryService.findCategoryByCategoryName(categoryName);
    }

    @PutMapping("/update")
    public String updateCategory(@RequestBody CategoryDTO categoryDTO, @RequestParam Long id) throws CategoryAlreadyExistsException,CategoryNotFoundException{
        return categoryService.updateCategory(categoryDTO, id);
    }

    @DeleteMapping("/deleteById")
    public String deleteCategoryById(Long id) throws CategoryNotFoundException{
        return categoryService.removeCategoryById(id);
    }
    
}