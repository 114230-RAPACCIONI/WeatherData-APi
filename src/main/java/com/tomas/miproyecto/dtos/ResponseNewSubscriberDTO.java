package com.tomas.miproyecto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseNewSubscriberDTO {

    @JsonProperty("client_id")
    private Long clientId;
    private String secret;
}
