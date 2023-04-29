package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.dtos.PassengerDTO;

public interface PassengerService {
    PassengerDTO savePassenger(PassengerDTO passengerDTO);
    PassengerDTO getPassenger(Long id);
    void deletePassenger(Long id);
}