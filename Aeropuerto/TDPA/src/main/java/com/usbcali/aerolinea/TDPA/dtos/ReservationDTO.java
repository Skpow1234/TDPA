package com.usbcali.aerolinea.TDPA.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationDTO {
    private Long id;
    private PassengerDTO passenger;
    private FlightDTO flight;
}
