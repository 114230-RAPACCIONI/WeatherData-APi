package com.tomas.miproyecto.clients;

import com.tomas.miproyecto.clients.dtos.CloudClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CloudClientRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<CloudClientDTO[]> getCloudiness() {

        ResponseEntity<CloudClientDTO[]> response = null;

        try {
            response = restTemplate.getForEntity("https://my-json-server.typicode.com/LCIV-2023/fake-weather/cloudiness", CloudClientDTO[].class);

            if (response.getStatusCode().equals(200)) {
                return response;
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return response;
    }
}
