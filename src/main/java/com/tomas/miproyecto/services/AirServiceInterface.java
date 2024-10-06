package com.tomas.miproyecto.services;

import com.tomas.miproyecto.dtos.AirDTO;
import org.springframework.stereotype.Service;


@Service
public interface AirServiceInterface {
    AirDTO getAirQuality(Long locationId, String dateTime);
}
