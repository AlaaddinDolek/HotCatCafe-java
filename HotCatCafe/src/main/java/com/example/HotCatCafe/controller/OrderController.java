package com.example.HotCatCafe.controller;

import java.math.BigDecimal;
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
import com.example.HotCatCafe.exceptions.TableIsNotAvailableException;
import com.example.HotCatCafe.persistence.DTOs.OrderDTO;
import com.example.HotCatCafe.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/getAll")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/byId")
    public OrderDTO findOrderById(@RequestParam Long orderId) throws OrderNotFoundException {
        return orderService.findOrderById(orderId);
    }

    @GetMapping("/byEmployeeId")
    public List<OrderDTO> findByEmployeeId(@RequestParam Long employeeId) throws EmployeeNotFoundException {
        return orderService.findByEmployeeId(employeeId);
    }

    @GetMapping("/byTableNumber")
    public List<OrderDTO> findByTableNumber(@RequestParam String tableNumber) throws OrderNotFoundException {
        return orderService.findByTableNumber(tableNumber);
    }

    @GetMapping("/greaterThan")
    public List<OrderDTO> findTotalPriceGreaterThan(@RequestParam BigDecimal price) {
        return orderService.findTotalPriceGreaterThan(price);
    }

    @GetMapping("/between")
    public List<OrderDTO> findTotalPriceBetween(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        return orderService.findTotalPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/lessThan")
    public List<OrderDTO> findTotalPriceLessThan(@RequestParam BigDecimal price) {
        return orderService.findTotalPriceLessThan(price);
    }

    @PostMapping("/insert")
    public Long insertOrder(@RequestParam String tableNumber, @RequestParam Long employeeId) throws TableIsNotAvailableException {
        return orderService.insertOrder(tableNumber, employeeId);
    }

    @DeleteMapping("/delete")
    public Long deleteOrderById(@RequestParam Long orderId) throws OrderNotFoundException{
        return orderService.deleteById(orderId);
    }

    
}
