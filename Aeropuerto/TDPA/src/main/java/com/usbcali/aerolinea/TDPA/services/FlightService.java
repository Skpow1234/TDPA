package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.domains.Flight;
import com.usbcali.aerolinea.TDPA.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight getFlight(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> searchFlights(String departureAirportIataCode, String arrivalAirportIataCode, LocalDate departureDate) {
        return flightRepository.findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndDepartureDate(departureAirportIataCode, arrivalAirportIataCode, departureDate);
    }
}
