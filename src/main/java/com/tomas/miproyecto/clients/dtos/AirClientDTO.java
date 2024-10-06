package com.tomas.miproyecto.clients.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AirClientDTO {
    private Long id;

    @JsonProperty("location_id")
    private Long locationId;
    private Long quality;

    @JsonProperty("created_at")
    private String createdAt;

}
