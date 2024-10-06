package com.tomas.miproyecto.clients.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationClientDTO {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
}
