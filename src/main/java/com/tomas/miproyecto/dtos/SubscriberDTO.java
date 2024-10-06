package com.tomas.miproyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class SubscriberDTO {
    private String email;

    @JsonProperty("temperature_unit")
    private String temperature;
}
