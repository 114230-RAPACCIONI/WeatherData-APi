package com.tomas.miproyecto.services;

import com.tomas.miproyecto.dtos.WindDTO;
import org.springframework.stereotype.Service;

@Service
public interface WindServiceInterface {
    public WindDTO getWinds(Long locationId,String dateTime);
}
