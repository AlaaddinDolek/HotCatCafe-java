package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/getAll")
    public List<SaleDTO> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/byId")
    public SaleDTO findSaleById(Long id) throws SaleNotFoundException {
        return saleService.findSaleById(id);
    }

    @GetMapping("/byEmployeeId")
    public List<SaleDTO> getSalesByEmployeeId(@RequestParam Long employeeId) throws EmployeeNotFoundException {
        return saleService.getSalesByEmployeeId(employeeId);
    }

    @GetMapping("/byOrderId")
    public SaleDTO getSalesByOrderId(@RequestParam Long orderId) throws OrderNotFoundException, SaleNotFoundException {
        return saleService.getSalesByOrderId(orderId);
    }

    @PostMapping("/insert")
    public SaleDTO insertSale(@RequestParam Long orderId, @RequestParam Long employeeId) throws OrderNotFoundException, EmployeeNotFoundException{
        return saleService.insertSale(orderId, employeeId);
    }

    @DeleteMapping("/delete")
    public Long deleteSaleById(@RequestParam Long id) throws SaleNotFoundException{
        return saleService.deleteSaleById(id);
    }
}
