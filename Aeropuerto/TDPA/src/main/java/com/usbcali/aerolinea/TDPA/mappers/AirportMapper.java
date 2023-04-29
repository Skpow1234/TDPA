package com.usbcali.aerolinea.TDPA.mappers;

import com.usbcali.aerolinea.TDPA.domains.Airport;
import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AirportMapper {
    public static AirportDTO domainToDto(Airport airport) {
        return AirportDTO.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .build();
    }

    public static Airport dtoToDomain(AirportDTO airportDTO) {
        return Airport.builder()
                .id(airportDTO.getId())
                .iataCode(airportDTO.getIataCode())
                .name(airportDTO.getName())
                .build();
    }

    public static List<AirportDTO> domainToDtoList(List<Airport> airports) {
        return airports.stream()
                .map(airport -> domainToDto(airport))
                .collect(Collectors.toList());
    }

    public static List<Airport> dtoToDomainList(List<AirportDTO> airportDTOS) {
        return airportDTOS.stream()
                .map(airportDTO -> dtoToDomain(airportDTO))
                .collect(Collectors.toList());
    }

}
