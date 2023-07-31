package com.example.HotCatCafe.exceptions;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(String message) {
        super("Product",message);
    }
}
