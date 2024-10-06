package com.tomas.miproyecto.clients.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureClientDTO {
    private Long id;

    @JsonProperty("location_id")
    private Long locationId;
    private Long temperature;
    private String unit;

    @JsonProperty("created_at")
    private String createdAt;

}
