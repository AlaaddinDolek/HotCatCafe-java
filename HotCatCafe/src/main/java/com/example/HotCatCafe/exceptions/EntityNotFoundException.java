package com.example.HotCatCafe.exceptions;

public class EntityNotFoundException extends Exception{

    private String entityName;
    public EntityNotFoundException(String entityName,String message) {
        super(message);
        this.entityName = entityName;

    }
    public String getEntityName() {
        return entityName;
    }

}
