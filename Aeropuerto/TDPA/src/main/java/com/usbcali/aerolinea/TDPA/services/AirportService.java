package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;

import java.util.List;

public interface AirportService {

    AirportDTO saveAirport(AirportDTO airportDTO);

    AirportDTO getAirport(Long id);

    List<AirportDTO> getAirports();

    void deleteAirport(Long id);

    AirportDTO getAirportByIataCode(String iataCode);
}

