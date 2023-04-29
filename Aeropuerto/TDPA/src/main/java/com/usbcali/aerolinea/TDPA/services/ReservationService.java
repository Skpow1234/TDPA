package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.dtos.ReservationDTO;

public interface ReservationService {
    ReservationDTO saveReservation(ReservationDTO reservationDTO);
    ReservationDTO getReservation(Long id);
    void deleteReservation(Long id);
}
