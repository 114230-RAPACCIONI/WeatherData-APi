package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.clients.AirClientRestTemplate;
import com.tomas.miproyecto.clients.dtos.AirClientDTO;
import com.tomas.miproyecto.dtos.AirDTO;
import com.tomas.miproyecto.services.AirServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AirServiceImplementation implements AirServiceInterface {

    @Autowired
    AirClientRestTemplate airClientRestTemplate;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public AirDTO getAirQuality(Long locationId, String dateTime) {
        AirClientDTO airClientDTO = Arrays.stream(airClientRestTemplate.getAirQuality().getBody()).filter(x -> x.getCreatedAt().equals(dateTime) && x.getLocationId() == locationId).toList().get(0);

        return new AirDTO(airClientDTO.getQuality(), getAirQualityDescription(airClientDTO.getQuality()));
    }

    private String getAirQualityDescription(Long quality) {

        if (quality >= 0 && quality <= 50)
            return "Good";
        else if (quality >= 51 && quality <= 100)
            return "Moderate";
        else if (quality >= 101 && quality <= 150)
            return "Unhealthy for Sensitive Groups";
        else if (quality >= 151 && quality <= 200)
            return "Unhealthy";
        else if (quality >= 201 && quality <= 300)
            return "Very Unhealthy";
        else return "Hazardous";

    }

}
