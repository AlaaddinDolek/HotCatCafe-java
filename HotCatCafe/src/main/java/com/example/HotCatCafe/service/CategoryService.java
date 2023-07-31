package com.example.HotCatCafe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotCatCafe.exceptions.CategoryAlreadyExistsException;
import com.example.HotCatCafe.exceptions.CategoryNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.CategoryDTO;
import com.example.HotCatCafe.persistence.entity.Category;
import com.example.HotCatCafe.persistence.enums.Status;
import com.example.HotCatCafe.persistence.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository=categoryRepository;
    }
    

    public String updateCategory(CategoryDTO categoryDTO, Long id)
            throws CategoryNotFoundException, CategoryAlreadyExistsException {

        Optional<Category> category = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if (category.isPresent())
            throw new CategoryAlreadyExistsException("Category already exists");

        Optional<Category> tobeUpdated = categoryRepository.findById(id);
        if (tobeUpdated.isPresent()) {
            Category updated = categoryRepository.findById(id).get();

            if (categoryDTO.getCategoryName() != null) {
                updated.setCategoryName(categoryDTO.getCategoryName());
                updated.setStatus(Status.Updated);
                categoryRepository.save(updated);
                return "Successfully Updated";
            }
            return "Please provide a Category Name";
        } else {
            throw new CategoryNotFoundException("Category not found");
        }

    }

    public CategoryDTO findCategoryByCategoryName(String categoryName) throws CategoryNotFoundException {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return category.toDTO();
    }

    public CategoryDTO findCategoryById(Long id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return category.toDTO();
    }

    public List<CategoryDTO> getAllCategories() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).map(Category::toDTO).toList();
    }

    public Long addCategory(CategoryDTO categoryDTO) throws CategoryAlreadyExistsException {
        Optional<Category> opCat = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if (opCat.isPresent())
            throw new CategoryAlreadyExistsException("Category already exists");
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryDTO.getCategoryName());
        return categoryRepository.save(newCategory).getId();
    }

    public String removeCategoryById(Long id) throws CategoryNotFoundException {
        categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
                categoryRepository.deleteById(id);
        return "Successfully Deleted";
    }

}
