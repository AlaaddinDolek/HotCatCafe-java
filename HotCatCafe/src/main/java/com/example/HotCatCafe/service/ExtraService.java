package com.example.HotCatCafe.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotCatCafe.exceptions.CategoryNotFoundException;
import com.example.HotCatCafe.exceptions.ExtraNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.ExtraDTO;
import com.example.HotCatCafe.persistence.entity.Category;
import com.example.HotCatCafe.persistence.entity.Extra;
import com.example.HotCatCafe.persistence.repository.CategoryRepository;
import com.example.HotCatCafe.persistence.repository.ExtraRepository;

@Service
public class ExtraService {

    private final ExtraRepository extraRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ExtraService(ExtraRepository extraRepository, CategoryRepository categoryRepository) {
        this.extraRepository = extraRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ExtraDTO> getAllExtras() {
        return StreamSupport.stream(extraRepository.findAll().spliterator(), false).map(Extra::toDTO).toList();
    }

    public List<ExtraDTO> getExtrasByCategory(String categoryName) throws CategoryNotFoundException {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return StreamSupport.stream(extraRepository.findByCategory(category).spliterator(), false).map(Extra::toDTO)
                .toList();
    }

    public ExtraDTO getExtraById(Long id) throws ExtraNotFoundException {
        Extra extra = extraRepository.findById(id).orElseThrow(() -> new ExtraNotFoundException("Extra not found"));
        return extra.toDTO();

    }

    public Long addExtra(ExtraDTO extraDTO) throws CategoryNotFoundException {
        Extra extra = new Extra();
        Category category = categoryRepository.findByCategoryName(extraDTO.getCategoryName())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        extra.setCategory(category);
        extra.setExtraName(extraDTO.getExtraName());
        extra.setExtraUnitPrice(extraDTO.getExtraUnitPrice());
        extraRepository.save(extra);
        return extra.getId();
    }

    public String deleteById(Long id) throws ExtraNotFoundException {
        extraRepository.findById(id).orElseThrow(()-> new ExtraNotFoundException("Extra not found"));
        extraRepository.deleteById(id);
        return "Successfully Deleted";
    }
}
