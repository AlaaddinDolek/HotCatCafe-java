package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/getAll")
      public List<ExtraDTO> getAllExtras(){
        return extraService.getAllExtras();
    }
    @GetMapping("/byCategoryName")
    public List<ExtraDTO> getExtrasByCategory(@RequestParam String categoryName) throws CategoryNotFoundException{
        return extraService.getExtrasByCategory(categoryName);
    }
    @GetMapping("/byId")
    public ExtraDTO getExtraById(@RequestParam Long id) throws ExtraNotFoundException {
        return extraService.getExtraById(id);
    }
    @PostMapping("/add")
    public Long addExtra(@RequestBody ExtraDTO extraDTO) throws CategoryNotFoundException {
        return extraService.addExtra(extraDTO);
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam Long id) throws ExtraNotFoundException {
        return extraService.deleteById(id);
    }

}
