package com.usbcali.aerolinea.TDPA.mappers;

import com.usbcali.aerolinea.TDPA.domains.Passenger;
import com.usbcali.aerolinea.TDPA.dtos.PassengerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PassengerMapper {
    @Autowired
    private ModelMapper modelMapper;

    public static PassengerDTO domainToDto(Passenger passenger) {
        if (passenger == null) {
            return null;
        }
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

    public static List<PassengerDTO> domainToDtoList(List<Passenger> passengers) {
        return passengers.stream()
                .map(PassengerMapper::domainToDto)
                .collect(Collectors.toList());
    }

    public static List<Passenger> dtoToDomainList(List<PassengerDTO> passengerDTOs) {
        return passengerDTOs.stream()
                .map(PassengerMapper::dtoToDomain)
                .collect(Collectors.toList());
    }

}
