package com.example.HotCatCafe.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotCatCafe.exceptions.CategoryNotFoundException;
import com.example.HotCatCafe.exceptions.ProductNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.ProductDTO;
import com.example.HotCatCafe.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/id/{id}")
    public ProductDTO findById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @GetMapping("/name/{productName}")
    public ProductDTO findByProductName(@PathVariable String productName) throws ProductNotFoundException {
        return productService.findByProductName(productName);
    }

    @GetMapping("/category/{id}")
    public List<ProductDTO> findByCategoryId(@PathVariable Long categoryId) throws CategoryNotFoundException {
        return productService.findByCategoryId(categoryId);
    }

    @GetMapping("/price/greater/{price}")
    public List<ProductDTO> findTotalPriceGreaterThan(@PathVariable BigDecimal price) {
        return productService.findTotalPriceGreaterThan(price);
    }

    @GetMapping("/price/between/{minPrice}&{maxPrice}")
    public List<ProductDTO> findTotalPriceBetween(@PathVariable BigDecimal minPrice, @PathVariable BigDecimal maxPrice) {
        return productService.findTotalPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/price/less/{price}")
    public List<ProductDTO> findTotalPriceLessThan(@PathVariable BigDecimal price) {
        return productService.findTotalPriceLessThan(price);
    }

    @GetMapping("/stock/less/{stock}")
    public List<ProductDTO> findUnitInStockLessThan(@PathVariable Integer stock) {
        return productService.findUnitInStockLessThan(stock);
    }

    @GetMapping("/stock/greater/{stock}")
    public List<ProductDTO> findUnitInStockGreaterThan(@PathVariable Integer stock) {
        return productService.findUnitInStockGreaterThan(stock);
    }

    @GetMapping("/stock/between/{minStock}&{maxStock}")
    public List<ProductDTO> findUnitInStockBetween(@PathVariable Integer minStock, @PathVariable Integer maxStock) {
        return productService.findUnitInStockBetween(minStock, maxStock);
    }

    @PostMapping
    public Long addProduct(@RequestBody ProductDTO productDTO) throws CategoryNotFoundException {
        return productService.addProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProducy(@RequestBody ProductDTO productDTO, @PathVariable Long productId) throws ProductNotFoundException {
        return productService.updateProduct(productDTO,productId);
    }
}
