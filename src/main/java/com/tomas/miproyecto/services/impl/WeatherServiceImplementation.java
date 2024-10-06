package com.tomas.miproyecto.services.impl;

import com.tomas.miproyecto.dtos.*;
import com.tomas.miproyecto.entities.SubscriberEntity;
import com.tomas.miproyecto.repositories.SubscriberRepository;
import com.tomas.miproyecto.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImplementation implements WeatherServiceInterface {

    @Autowired
    LocationServiceInterface locationServiceInterface;

    @Autowired
    TemperatureServiceInterface temperatureServiceInterface;

    @Autowired
    WindServiceInterface windServiceInterface;

    @Autowired
    AirServiceInterface airServiceInterface;

    @Autowired
    CloudServiceInterface cloudServiceInterface;

    @Autowired
    SubscriberRepository subscriberRepository;

    public List<WeatherDTO> getWeather(Long locationId, String dateTime) {

        List<WeatherDTO> weatherDTOList = new ArrayList<>();

        LocationDTO locationDTO = locationServiceInterface.getLocation(locationId);
        TemperatureDTO temperatureDTO = temperatureServiceInterface.getTemperature(locationId, dateTime);
        WindDTO windDTO = windServiceInterface.getWinds(locationId, dateTime);
        AirDTO airDTO = airServiceInterface.getAirQuality(locationId, dateTime);
        CloudDTO cloudDTO = cloudServiceInterface.getCloudiness(locationId, dateTime);

        WeatherDTO weatherDTO = new WeatherDTO(locationDTO, temperatureDTO, windDTO.getDescription(), airDTO, cloudDTO);

        weatherDTOList.add(weatherDTO);

        return weatherDTOList;
    }

    public ResponseNewSubscriberDTO saveNewSubscriber(SubscriberDTO subscriber) {

        subscriberRepository.save(new SubscriberEntity(0L, subscriber.getEmail(), subscriber.getTemperature()));

        return new ResponseNewSubscriberDTO(0L, "e58ed763-928c-4155-bee9-fdbaaadc15f3");
    }
}
