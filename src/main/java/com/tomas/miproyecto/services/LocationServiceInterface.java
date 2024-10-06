package com.tomas.miproyecto.services;

import com.tomas.miproyecto.clients.dtos.LocationClientDTO;
import com.tomas.miproyecto.dtos.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationServiceInterface {
    LocationDTO getLocation(Long locationId);
    List<LocationClientDTO> getLocations();
}
