package com.usbcali.aerolinea.TDPA.mappers;

import com.usbcali.aerolinea.TDPA.domains.Passenger;
import com.usbcali.aerolinea.TDPA.dtos.PassengerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PassengerMapper {
    public static PassengerDTO domainToDto(Passenger passenger) {
        return PassengerDTO.builder()
                .id(passenger.getId())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .email(passenger.getEmail())
                .build();
    }

    public static Passenger dtoToDomain(PassengerDTO passengerDTO) {
        return Passenger.builder()
                .id(passengerDTO.getId())
                .firstName(passengerDTO.getFirstName())
                .lastName(passengerDTO.getLastName())
                .email(passengerDTO.getEmail())
                .build();
    }

    public static List<PassengerDTO> entityListToDtoList(List<Passenger> passengers) {
        return passengers.stream()
                .map(PassengerMapper::domainToDto)
                .collect(Collectors.toList());
    }

    public static List<Passenger> dtoListToEntityList(List<PassengerDTO> passengerDTOs) {
        return passengerDTOs.stream()
                .map(PassengerMapper::dtoToDomain)
                .collect(Collectors.toList());
    }
}
