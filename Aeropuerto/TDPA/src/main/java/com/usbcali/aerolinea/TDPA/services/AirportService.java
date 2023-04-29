package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;

public interface AirportService {

    AirportDTO saveAirport(AirportDTO airportDTO);

    AirportDTO getAirport(Long id);

    void deleteAirport(Long id);

    AirportDTO getAirportByIataCode(String iataCode);
}

