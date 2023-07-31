package com.example.HotCatCafe.exceptions;

public class ExtraNotFoundException extends EntityNotFoundException {
    public ExtraNotFoundException(String message) {
        super("Extra",message);
    }
}
