package com.tomas.miproyecto.services;

import com.tomas.miproyecto.dtos.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherServiceInterface {

    List<WeatherDTO> getWeather(Long locationId,String dateTime);
    ResponseNewSubscriberDTO saveNewSubscriber(SubscriberDTO subscriber);
}
