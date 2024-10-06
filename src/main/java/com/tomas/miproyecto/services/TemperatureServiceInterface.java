package com.tomas.miproyecto.services;

import com.tomas.miproyecto.dtos.TemperatureDTO;
import org.springframework.stereotype.Service;

@Service
public interface TemperatureServiceInterface {
    public TemperatureDTO getTemperature(Long locationId, String dateTime);
}
