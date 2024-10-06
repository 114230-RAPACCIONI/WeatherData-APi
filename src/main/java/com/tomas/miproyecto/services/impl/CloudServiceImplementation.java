package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.clients.CloudClientRestTemplate;
import com.tomas.miproyecto.clients.dtos.CloudClientDTO;
import com.tomas.miproyecto.dtos.CloudDTO;
import com.tomas.miproyecto.services.CloudServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CloudServiceImplementation implements CloudServiceInterface {

    @Autowired
    CloudClientRestTemplate cloudClientRestTemplate;

    @Autowired
    ModelMapper modelMapper;

    public CloudDTO getCloudiness(Long locationId, String dateTime) {

        CloudClientDTO cloud = Arrays.stream(cloudClientRestTemplate.getCloudiness().getBody()).filter(x -> x.getCreatedAt().equals(dateTime) && x.getLocationId() == locationId).toList().get(0);

        return new CloudDTO(cloud.getCloudiness(), getCloudinessIndex(cloud.getCloudiness()));
    }

    private String getCloudinessIndex(Long cloudiness) {

        if (cloudiness == 0) {

            return "Clear sky";
        } else if (cloudiness >= 1 && cloudiness <= 3) {

            return "Few clouds";
        } else if (cloudiness >= 4 && cloudiness <= 6) {


            return "Sky half cludy";
        } else {

            return "Sky completely cludy";
        }
    }
}
