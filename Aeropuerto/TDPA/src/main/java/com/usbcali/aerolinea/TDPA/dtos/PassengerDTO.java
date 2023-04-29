package com.usbcali.aerolinea.TDPA.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PassengerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
