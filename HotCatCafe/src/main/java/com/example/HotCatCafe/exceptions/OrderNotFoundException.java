package com.example.HotCatCafe.exceptions;

public class OrderNotFoundException extends EntityNotFoundException{
    public OrderNotFoundException(String message) {
        super("Order",message);
    }
}
