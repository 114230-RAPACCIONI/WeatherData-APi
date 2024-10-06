package com.tomas.miproyecto.services;

import com.tomas.miproyecto.dtos.CloudDTO;
import org.springframework.stereotype.Service;

@Service
public interface CloudServiceInterface {
    CloudDTO getCloudiness(Long id, String dateTime);
}
