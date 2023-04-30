package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.dtos.PassengerDTO;

import java.util.List;

public interface PassengerService {
    PassengerDTO savePassenger(PassengerDTO passengerDTO);
    PassengerDTO getPassenger(Long id);
    List<PassengerDTO> getPassengers();
    void deletePassenger(Long id);
}