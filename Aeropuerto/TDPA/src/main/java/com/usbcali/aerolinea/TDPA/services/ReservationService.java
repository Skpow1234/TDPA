package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.dtos.ReservationDTO;

import java.util.List;

public interface ReservationService {
    ReservationDTO saveReservation(ReservationDTO reservationDTO);
    ReservationDTO getReservation(Long id);
    List<ReservationDTO> getReservations();
    void deleteReservation(Long id);
}
