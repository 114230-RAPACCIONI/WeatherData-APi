package com.tomas.miproyecto.clients;

import com.tomas.miproyecto.clients.dtos.WindClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WindClientRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<WindClientDTO[]> getWinds() {

        ResponseEntity<WindClientDTO[]> response = null;

        try{
            response = restTemplate.getForEntity("https://my-json-server.typicode.com/LCIV-2023/fake-weather/wind", WindClientDTO[].class);

            if (response.getStatusCode().equals(200)) {
                return response;
            }

        } catch(Exception ex){
            System.out.println("El error es: " + ex.getMessage());
            return response;
        }
        return response;
    }
}
