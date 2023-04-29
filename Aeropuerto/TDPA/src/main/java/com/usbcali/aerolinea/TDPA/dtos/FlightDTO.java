package com.usbcali.aerolinea.TDPA.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FlightDTO {
    private Long id;
    private String flightNumber;
    private LocalDate departureDate;
    private Long departureAirportId;
    private LocalDate arrivalDate;
    private Long arrivalAirportId;
}
