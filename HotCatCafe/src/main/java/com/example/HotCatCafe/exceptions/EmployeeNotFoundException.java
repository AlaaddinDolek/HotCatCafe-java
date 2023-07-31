package com.example.HotCatCafe.exceptions;

public class EmployeeNotFoundException extends EntityNotFoundException {
    public EmployeeNotFoundException(String message) {
        super("Employee",message);
    }
}
