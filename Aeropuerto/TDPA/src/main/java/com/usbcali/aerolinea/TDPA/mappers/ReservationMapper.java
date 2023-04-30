package com.usbcali.aerolinea.TDPA.mappers;

import com.usbcali.aerolinea.TDPA.domains.Reservation;
import com.usbcali.aerolinea.TDPA.dtos.ReservationDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {
    public static ReservationDTO domainToDto(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .passenger(PassengerMapper.domainToDto(reservation.getPassenger()))
                .flight(FlightMapper.domainToDto(reservation.getFlight()))
                .build();
    }

    public static Reservation dtoToDomain(ReservationDTO reservationDTO) {
        return Reservation.builder()
                .id(reservationDTO.getId())
                .passenger(PassengerMapper.dtoToDomain(reservationDTO.getPassenger()))
                .flight(FlightMapper.dtoToDomain(reservationDTO.getFlight()))
                .build();
    }

    public static List<ReservationDTO> domainToDtoList(List<Reservation> reservations) {
        return reservations.stream().map(reservation -> domainToDto(reservation)).collect(Collectors.toList());
    }

    public static List<Reservation> dtoToDomainList(List<ReservationDTO> reservationDTOs) {
        return reservationDTOs.stream().map(reservationDTO -> dtoToDomain(reservationDTO)).collect(Collectors.toList());
    }
}
