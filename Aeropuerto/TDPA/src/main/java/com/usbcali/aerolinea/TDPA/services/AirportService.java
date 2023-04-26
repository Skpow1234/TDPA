package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.domains.Airport;
import com.usbcali.aerolinea.TDPA.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport getAirport(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    public Airport getAirportByIataCode(String iataCode) {
        return airportRepository.findByIataCode(iataCode);
    }
}
