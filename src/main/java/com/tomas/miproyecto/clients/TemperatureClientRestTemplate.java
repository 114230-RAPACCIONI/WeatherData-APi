package com.tomas.miproyecto.clients;

import com.tomas.miproyecto.clients.dtos.TemperatureClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemperatureClientRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<TemperatureClientDTO[]> getTemperatures() {

        String URL = "https://my-json-server.typicode.com/LCIV-2023/fake-weather/temperature";

        ResponseEntity<TemperatureClientDTO[]> response = null;

        try {
            response = restTemplate.getForEntity(URL, TemperatureClientDTO[].class);

            if (response.getStatusCode().equals(200))
                return response;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return response;
    }

}
