package com.tomas.miproyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonPropertyOrder({"location", "temperature", "wind", "air_quality", "cloudiness"})
public class WeatherDTO {

    @JsonProperty("location")
    private LocationDTO locationDTO;

    @JsonProperty("temperature")
    private TemperatureDTO temperatureDTO;
    private String wind;

    @JsonProperty("air_quality")
    private AirDTO airDTO;

    @JsonProperty("cloudiness")
    private CloudDTO cloudDTO;

}
