package com.usbcali.aerolinea.TDPA.dtos;

import com.usbcali.aerolinea.TDPA.domains.Flight;
import com.usbcali.aerolinea.TDPA.domains.Passenger;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationDTO {
    private Long id;
    private Passenger passenger;
    private Flight flight;
}
