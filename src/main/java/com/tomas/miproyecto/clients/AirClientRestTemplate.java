package com.tomas.miproyecto.clients;

import com.tomas.miproyecto.clients.dtos.AirClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirClientRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<AirClientDTO[]> getAirQuality() {
        ResponseEntity<AirClientDTO[]> response = null;

        try {
            response = restTemplate.getForEntity("https://my-json-server.typicode.com/LCIV-2023/fake-weather/air_quality", AirClientDTO[].class);

            if (response.getStatusCode().equals(200)) {
                return response;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return response;
    }
}
