package com.tomas.miproyecto.controllers;

import com.tomas.miproyecto.clients.dtos.LocationClientDTO;
import com.tomas.miproyecto.dtos.ResponseNewSubscriberDTO;
import com.tomas.miproyecto.dtos.SubscriberDTO;
import com.tomas.miproyecto.dtos.WeatherDTO;
import com.tomas.miproyecto.services.LocationServiceInterface;
import com.tomas.miproyecto.services.WeatherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("weather")
public class WeatherController {

    @Autowired
    WeatherServiceInterface weatherServiceInterface;

    @Autowired
    LocationServiceInterface locationServiceInterface;

    @GetMapping("weather/locations")
    public ResponseEntity<List<LocationClientDTO>> getLocations() {
        return ResponseEntity.ok(locationServiceInterface.getLocations());
    }

    @GetMapping("weather/location/{location_id}")
    public ResponseEntity<List<WeatherDTO>> getLocationByIdDateTimeFilter(@PathVariable Long location_id, @RequestParam String datetime) {
        return ResponseEntity.ok(weatherServiceInterface.getWeather(location_id,datetime));
    }

    @PostMapping("weather/subscribe")
    public ResponseEntity<ResponseNewSubscriberDTO> getLocationByIdDateTimeFilter(@RequestBody SubscriberDTO subscriber) {
        return ResponseEntity.ok(weatherServiceInterface.saveNewSubscriber(subscriber));
    }
}
