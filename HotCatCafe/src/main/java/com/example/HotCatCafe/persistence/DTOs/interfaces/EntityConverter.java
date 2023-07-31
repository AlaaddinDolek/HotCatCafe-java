package com.example.HotCatCafe.persistence.DTOs.interfaces;

import com.example.HotCatCafe.persistence.entity.BaseEntity;

public interface EntityConverter<T extends BaseEntity>{
    public T toEntity();
}
