package com.example.HotCatCafe.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdviceException {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails entityNotFoundException(EntityNotFoundException ex) {
        return new CustomErrorDetails(new Date(), ex.getEntityName()+" not found!", ex.getMessage());
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails categoryAlreadyExistException(CategoryAlreadyExistsException ex) {
        return new CustomErrorDetails(new Date(), "Category already exists!", ex.getMessage());
    }

    @ExceptionHandler(TableIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails tableIsNotAvailableException(TableIsNotAvailableException ex) {
        return new CustomErrorDetails(new Date(), "Table is not available!", ex.getMessage());
    }
}
