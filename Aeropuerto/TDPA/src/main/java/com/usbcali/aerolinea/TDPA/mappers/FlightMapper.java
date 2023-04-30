package com.usbcali.aerolinea.TDPA.mappers;

import com.usbcali.aerolinea.TDPA.domains.Airport;
import com.usbcali.aerolinea.TDPA.domains.Flight;
import com.usbcali.aerolinea.TDPA.dtos.FlightDTO;

import java.util.List;
import java.util.stream.Collectors;

public class FlightMapper {
    public static FlightDTO domainToDto(Flight flight){
        if (flight == null) {
            return null;
        }
        return  FlightDTO.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .departureDate(flight.getDepartureDate())
                .departureAirportId(flight.getDepartureAirport().getId())
                .arrivalDate(flight.getArrivalDate())
                .arrivalAirportId(flight.getArrivalAirport().getId())
                .build();
    }

    public  static Flight dtoToDomain(FlightDTO flightDTO){
        return Flight.builder()
                .id(flightDTO.getId())
                .flightNumber(flightDTO.getFlightNumber())
                .departureDate(flightDTO.getDepartureDate())
                .departureAirport(new Airport(flightDTO.getDepartureAirportId()))
                .arrivalDate(flightDTO.getArrivalDate())
                .arrivalAirport(new Airport(flightDTO.getArrivalAirportId()))
                .build();
    }

    public static List<FlightDTO> domainToDtoList(List<Flight> flights){
        return flights.stream().map(f -> domainToDto(f)).collect(Collectors.toList());
    }

    public static List<Flight> dtoListToDomain(List<FlightDTO> flightDTOs){
        return flightDTOs.stream().map(f -> dtoToDomain(f)).collect(Collectors.toList());
    }
}
