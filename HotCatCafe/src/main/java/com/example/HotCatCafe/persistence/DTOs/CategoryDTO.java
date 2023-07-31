package com.example.HotCatCafe.persistence.DTOs;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;



public class CategoryDTO extends BaseDTO {

    @NotEmpty(message = "Category name is required")
    private String categoryName;

    private List<ExtraDTO> extraDtoList;


    public List<ExtraDTO> getExtraDtoList() {
        return extraDtoList;
    }

    public void setExtraDtoList(List<ExtraDTO> extraDtoList) {
        this.extraDtoList = extraDtoList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

   


}
