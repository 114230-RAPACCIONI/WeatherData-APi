package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.clients.TemperatureClientRestTemplate;
import com.tomas.miproyecto.clients.dtos.TemperatureClientDTO;
import com.tomas.miproyecto.dtos.TemperatureDTO;
import com.tomas.miproyecto.services.TemperatureServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TemperatureServiceImplementation implements TemperatureServiceInterface {

    @Autowired
    TemperatureClientRestTemplate temperatureClientRestTemplate;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public TemperatureDTO getTemperature(Long locationId, String dateTime) {

        TemperatureClientDTO temperatureClientDTO = Arrays.stream(temperatureClientRestTemplate.getTemperatures().getBody())
                .filter(x -> x.getLocationId() == locationId && x.getCreatedAt().equals(dateTime))
                .toList()
                .get(0);

        return new TemperatureDTO(temperatureClientDTO.getTemperature(), temperatureClientDTO.getUnit());
    }
}
