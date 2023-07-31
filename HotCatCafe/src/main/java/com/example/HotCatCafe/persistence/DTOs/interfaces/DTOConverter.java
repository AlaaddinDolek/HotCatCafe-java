package com.example.HotCatCafe.persistence.DTOs.interfaces;

import com.example.HotCatCafe.persistence.DTOs.BaseDTO;

public interface DTOConverter<T extends BaseDTO> {
    public T toDTO();
}
