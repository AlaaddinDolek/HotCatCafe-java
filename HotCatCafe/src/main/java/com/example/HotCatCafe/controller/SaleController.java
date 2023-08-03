package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotCatCafe.exceptions.EmployeeNotFoundException;
import com.example.HotCatCafe.exceptions.OrderNotFoundException;
import com.example.HotCatCafe.exceptions.SaleNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.SaleDTO;
import com.example.HotCatCafe.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    SaleService saleService;

    @GetMapping
    public List<SaleDTO> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/id/{id}")
    public SaleDTO findSaleById(@PathVariable Long id) throws SaleNotFoundException {
        return saleService.findSaleById(id);
    }

    @GetMapping("/emplyee/{id}")
    public List<SaleDTO> getSalesByEmployeeId(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return saleService.getSalesByEmployeeId(employeeId);
    }

    @GetMapping("/order/{id}")
    public SaleDTO getSalesByOrderId(@PathVariable Long orderId) throws OrderNotFoundException, SaleNotFoundException {
        return saleService.getSalesByOrderId(orderId);
    }

    @PostMapping("/{orderId}&{employeeId}")
    public SaleDTO insertSale(@PathVariable Long orderId, @PathVariable Long employeeId) throws OrderNotFoundException, EmployeeNotFoundException{
        return saleService.insertSale(orderId, employeeId);
    }

    @DeleteMapping("/{id}")
    public Long deleteSaleById(@PathVariable Long id) throws SaleNotFoundException{
        return saleService.deleteSaleById(id);
    }
}
