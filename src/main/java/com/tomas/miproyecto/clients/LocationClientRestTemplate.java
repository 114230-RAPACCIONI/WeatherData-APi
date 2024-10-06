package com.tomas.miproyecto.clients;

import com.tomas.miproyecto.clients.dtos.LocationClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationClientRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    String URL = "https://my-json-server.typicode.com/LCIV-2023/fake-weather/location";

    public ResponseEntity<LocationClientDTO[]> getAllLocations() {

        ResponseEntity<LocationClientDTO[]> response = null;

        try {
            response = restTemplate.getForEntity(URL, LocationClientDTO[].class);

            if (response.getStatusCode().equals(200))
                return response;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return response;
    }
}
