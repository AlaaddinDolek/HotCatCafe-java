package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotCatCafe.exceptions.ExtraNotFoundException;
import com.example.HotCatCafe.exceptions.OrderNotFoundException;
import com.example.HotCatCafe.exceptions.ProductNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.OrderDTO;
import com.example.HotCatCafe.persistence.DTOs.OrderProductDTO;
import com.example.HotCatCafe.persistence.VMs.OrderProductVM;
import com.example.HotCatCafe.service.OrderProductService;

@RestController
@RequestMapping("/orderproduct")
public class OrderProductController {
    @Autowired
    OrderProductService orderProductService;

    @GetMapping
    public List<OrderProductDTO> getAll() {
        return orderProductService.getAllOrderProducts();
    }

    @GetMapping("/product/{productId}")
    public List<OrderProductDTO> getByProductId(@PathVariable Long productId) throws ProductNotFoundException {
        return orderProductService.getOrderProductsByProductId(productId);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderProductDTO> getByOrderId(@RequestParam Long orderId) throws OrderNotFoundException {
        return orderProductService.getOrderProductsByOrderId(orderId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id) {
        orderProductService.deleteById(id);
    }

    @PostMapping
    public OrderDTO insertOrderProduct(@RequestBody OrderProductVM opVM)
            throws OrderNotFoundException, ExtraNotFoundException, ProductNotFoundException {
        return orderProductService.insertOrderProduct(opVM);
    }
}
