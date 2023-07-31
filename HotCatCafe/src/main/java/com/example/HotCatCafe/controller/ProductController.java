package com.example.HotCatCafe.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/getAll")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/byId")
    public ProductDTO findById(@RequestParam Long id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @GetMapping("/byProductName")
    public ProductDTO findByProductName(@RequestParam String productName) throws ProductNotFoundException {
        return productService.findByProductName(productName);
    }

    @GetMapping("/byCategoryId")
    public List<ProductDTO> findByCategoryId(@RequestParam Long categoryId) throws CategoryNotFoundException {
        return productService.findByCategoryId(categoryId);
    }

    @GetMapping("/priceGreater")
    public List<ProductDTO> findTotalPriceGreaterThan(@RequestParam BigDecimal price) {
        return productService.findTotalPriceGreaterThan(price);
    }

    @GetMapping("/priceBetween")
    public List<ProductDTO> findTotalPriceBetween(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        return productService.findTotalPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/priceLess")
    public List<ProductDTO> findTotalPriceLessThan(@RequestParam BigDecimal price) {
        return productService.findTotalPriceLessThan(price);
    }

    @GetMapping("/stockLess")
    public List<ProductDTO> findUnitInStockLessThan(@RequestParam Integer stock) {
        return productService.findUnitInStockLessThan(stock);
    }

    @GetMapping("/stockGreater")
    public List<ProductDTO> findUnitInStockGreaterThan(@RequestParam Integer stock) {
        return productService.findUnitInStockGreaterThan(stock);
    }

    @GetMapping("/stockBetween")
    public List<ProductDTO> findUnitInStockBetween(@RequestParam Integer minStock, @RequestParam Integer maxStock) {
        return productService.findUnitInStockBetween(minStock, maxStock);
    }

    @PostMapping("/add")
    public Long addProduct(@RequestBody ProductDTO productDTO) throws CategoryNotFoundException {
        return productService.addProduct(productDTO);
    }

    @PutMapping("/update")
    public ProductDTO updateProducy(@RequestBody ProductDTO productDTO, @RequestParam Long productId) throws ProductNotFoundException {
        return productService.updateProduct(productDTO,productId);
    }
}
