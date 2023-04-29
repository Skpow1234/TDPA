package com.usbcali.aerolinea.TDPA.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AirportDTO {

    private Long id;
    private String iataCode;
    private String name;

}

