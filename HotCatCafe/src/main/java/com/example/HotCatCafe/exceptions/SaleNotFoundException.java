package com.example.HotCatCafe.exceptions;

public class SaleNotFoundException extends EntityNotFoundException{
    public SaleNotFoundException(String message) {
        super("Sale",message);
    }
}
