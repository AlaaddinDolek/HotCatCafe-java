package com.example.HotCatCafe.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotCatCafe.exceptions.CategoryNotFoundException;
import com.example.HotCatCafe.exceptions.ProductNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.ProductDTO;
import com.example.HotCatCafe.persistence.entity.Category;
import com.example.HotCatCafe.persistence.entity.Product;
import com.example.HotCatCafe.persistence.repository.CategoryRepository;
import com.example.HotCatCafe.persistence.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).map(Product::toDTO).toList();
    }

    public ProductDTO findById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return product.toDTO();
    }

    public ProductDTO findByProductName(String productName) throws ProductNotFoundException {
        Product product = productRepository.findByProductName(productName)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return product.toDTO();
    }

    public List<ProductDTO> findByCategoryId(Long categoryId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return StreamSupport.stream(productRepository.findByCategory(category).spliterator(), false).map(Product::toDTO)
                .toList();
    }

    public List<ProductDTO> findTotalPriceGreaterThan(BigDecimal price) {
        return StreamSupport.stream(productRepository.findByUnitPriceGreaterThan(price).spliterator(), false)
                .map(Product::toDTO).toList();
    }

    public List<ProductDTO> findTotalPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return StreamSupport.stream(productRepository
                .findByUnitPriceGreaterThanEqualAndUnitPriceLessThanEqual(minPrice, maxPrice).spliterator(), false)
                .map(Product::toDTO).toList();
    }

    public List<ProductDTO> findTotalPriceLessThan(BigDecimal price) {
        return StreamSupport.stream(productRepository.findByUnitPriceLessThan(price).spliterator(), false)
                .map(Product::toDTO).toList();
    }

    public List<ProductDTO> findUnitInStockLessThan(Integer stock) {
        return StreamSupport.stream(productRepository.findByUnitInStockLessThan(stock).spliterator(), false)
                .map(Product::toDTO).toList();
    }

    public List<ProductDTO> findUnitInStockGreaterThan(Integer stock) {
        return StreamSupport.stream(productRepository.findByUnitInStockGreaterThan(stock).spliterator(), false)
                .map(Product::toDTO).toList();
    }

    public List<ProductDTO> findUnitInStockBetween(Integer minStock, Integer maxStock) {
        return StreamSupport.stream(productRepository
                .findByUnitInStockGreaterThanEqualAndUnitInStockLessThanEqual(minStock, maxStock).spliterator(), false)
                .map(Product::toDTO).toList();
    }

    public Long addProduct(ProductDTO productDTO) throws CategoryNotFoundException {
        Product product = new Product();
        Category category = categoryRepository.findByCategoryName(productDTO.getCategoryName())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        product.setCategory(category);
        product.setMinStockLimit(productDTO.getMinStockLimit());
        product.setProductName(productDTO.getProductName());
        product.setUnitInStock(productDTO.getUnitInStock());
        product.setUnitPrice(productDTO.getUnitPrice());

        productRepository.save(product);
        return product.getId();
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return StreamSupport.stream(productRepository.findByCategory(category).spliterator(), false).map(Product::toDTO)
                .toList();
    }


    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        if(productDTO.getMinStockLimit()!=null) product.setMinStockLimit(productDTO.getMinStockLimit());
        if(productDTO.getProductName()!=null) product.setProductName(productDTO.getProductName());
        if(productDTO.getUnitPrice()!=null) product.setUnitPrice(productDTO.getUnitPrice());
        productRepository.save(product);
        return product.toDTO();
    }
}
