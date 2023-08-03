package com.example.HotCatCafe.controller;

import java.math.BigDecimal;
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
import com.example.HotCatCafe.exceptions.TableIsNotAvailableException;
import com.example.HotCatCafe.persistence.DTOs.OrderDTO;
import com.example.HotCatCafe.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/id/{id}")
    public OrderDTO findOrderById(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderService.findOrderById(orderId);
    }

    @GetMapping("/employee/{id}")
    public List<OrderDTO> findByEmployeeId(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return orderService.findByEmployeeId(employeeId);
    }

    @GetMapping("/table/{tableNumber}}")
    public List<OrderDTO> findByTableNumber(@PathVariable String tableNumber) throws OrderNotFoundException {
        return orderService.findByTableNumber(tableNumber);
    }

    @GetMapping("/price/greater/{price}")
    public List<OrderDTO> findTotalPriceGreaterThan(@PathVariable BigDecimal price) {
        return orderService.findTotalPriceGreaterThan(price);
    }

    @GetMapping("/price/between/{minPrice}&{maxPrice}")
    public List<OrderDTO> findTotalPriceBetween(@PathVariable BigDecimal minPrice, @PathVariable BigDecimal maxPrice) {
        return orderService.findTotalPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/price/less/{price}")
    public List<OrderDTO> findTotalPriceLessThan(@PathVariable BigDecimal price) {
        return orderService.findTotalPriceLessThan(price);
    }

    @PostMapping("/{tableNumber}&{employeeId}")
    public Long insertOrder(@PathVariable String tableNumber, @PathVariable Long employeeId) throws TableIsNotAvailableException {
        return orderService.insertOrder(tableNumber, employeeId);
    }

    @DeleteMapping("/{id}")
    public Long deleteOrderById(@PathVariable Long orderId) throws OrderNotFoundException{
        return orderService.deleteById(orderId);
    }

    
}
