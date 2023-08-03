package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotCatCafe.exceptions.CategoryNotFoundException;
import com.example.HotCatCafe.exceptions.ExtraNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.ExtraDTO;
import com.example.HotCatCafe.service.ExtraService;

@RestController
@RequestMapping("/extra")
public class ExtraController {
    
    @Autowired
    ExtraService extraService;

    @GetMapping
      public List<ExtraDTO> getAllExtras(){
        return extraService.getAllExtras();
    }
    @GetMapping("/name/{categoryName}")
    public List<ExtraDTO> getExtrasByCategory(@PathVariable String categoryName) throws CategoryNotFoundException{
        return extraService.getExtrasByCategory(categoryName);
    }
    @GetMapping("/id/{id}")
    public ExtraDTO getExtraById(@PathVariable Long id) throws ExtraNotFoundException {
        return extraService.getExtraById(id);
    }
    @PostMapping
    public Long addExtra(@RequestBody ExtraDTO extraDTO) throws CategoryNotFoundException {
        return extraService.addExtra(extraDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) throws ExtraNotFoundException {
        return extraService.deleteById(id);
    }

}
