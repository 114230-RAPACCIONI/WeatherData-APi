package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.clients.WindClientRestTemplate;
import com.tomas.miproyecto.clients.dtos.WindClientDTO;
import com.tomas.miproyecto.dtos.WindDTO;
import com.tomas.miproyecto.services.WindServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class WindServiceImplementation implements WindServiceInterface {

    @Autowired
    WindClientRestTemplate windClientRestTemplate;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public WindDTO getWinds(Long locationId, String date) {

        WindClientDTO wind = Arrays.stream(windClientRestTemplate.getWinds().getBody()).filter(x -> x.getCreatedAt().equals(date) && x.getLocationId() == locationId).toList().get(0);

       return  new WindDTO(wind.getSpeed() + " Km/h" + " from " + getCardinalPoints(wind.getDirection()));

    }

    private String getCardinalPoints(Long degrees) {

        if (degrees >= 0 && degrees < 90)
            return "North";
        else if (degrees >= 90 && degrees < 180) {
            return "East";
        } else if (degrees >= 180 && degrees < 270) {
            return "South";
        } else {
            return "West";
        }
    }
}



