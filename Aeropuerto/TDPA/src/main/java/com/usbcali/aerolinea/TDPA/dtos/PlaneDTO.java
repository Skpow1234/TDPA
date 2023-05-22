package com.usbcali.aerolinea.TDPA.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlaneDTO {
    private Long id;
    private String planeNumber;
    private String planeName;
    private String planeCode;
}
