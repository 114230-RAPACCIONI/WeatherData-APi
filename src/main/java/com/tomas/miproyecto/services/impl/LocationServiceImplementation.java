package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.clients.LocationClientRestTemplate;
import com.tomas.miproyecto.clients.dtos.LocationClientDTO;
import com.tomas.miproyecto.dtos.LocationDTO;
import com.tomas.miproyecto.services.LocationServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LocationServiceImplementation implements LocationServiceInterface {

    @Autowired
    LocationClientRestTemplate locationClientRestTemplate;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public LocationDTO getLocation(Long locationId) {
        return modelMapper.map(Arrays.stream(locationClientRestTemplate.getAllLocations().getBody())
                .filter(x -> x.getId() == locationId)
                .toList()
                .get(0), LocationDTO.class);
    }

    @Override
    public List<LocationClientDTO> getLocations() {
        return Arrays.stream(locationClientRestTemplate.getAllLocations().getBody()).toList();
    }
}
