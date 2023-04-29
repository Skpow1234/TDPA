package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.dtos.FlightDTO;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    FlightDTO saveFlight(FlightDTO flightDTO);
    FlightDTO getFlight(Long id);
    void deleteFlight(Long id);
    List<FlightDTO> searchFlights(String departureAirportIataCode, String arrivalAirportIataCode, LocalDate departureDate);
}
