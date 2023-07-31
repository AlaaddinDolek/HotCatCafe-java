package com.example.HotCatCafe.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.HotCatCafe.persistence.DTOs.CategoryDTO;
import com.example.HotCatCafe.persistence.DTOs.ExtraDTO;
import com.example.HotCatCafe.persistence.DTOs.interfaces.DTOConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="categories")
public class Category extends BaseEntity implements DTOConverter<CategoryDTO> {
    
    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private List<Product> productList;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Extra> extraList;


    @Override
    public CategoryDTO toDTO() {
        CategoryDTO dto = new CategoryDTO();
        List<ExtraDTO> extraDtoList = new ArrayList<>();
        for (Extra extra : extraList) {
            extraDtoList.add(extra.toDTO());
        }
        dto.setCategoryName(categoryName);
        dto.setExtraDtoList(extraDtoList);
        dto.setId(id);

        return dto;
    }

    public Category() {
        productList = new ArrayList<Product>();
        extraList = new ArrayList<Extra>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Extra> getExtraList() {
        return extraList;
    }

    public void setExtraList(List<Extra> extraList) {
        this.extraList = extraList;
    }

   
}
