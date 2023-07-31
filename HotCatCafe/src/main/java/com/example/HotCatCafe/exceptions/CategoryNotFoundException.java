package com.example.HotCatCafe.exceptions;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(String message) {
        super("Category",message);
    }
}
