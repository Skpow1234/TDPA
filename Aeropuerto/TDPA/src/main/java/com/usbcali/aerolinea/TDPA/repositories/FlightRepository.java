package com.usbcali.aerolinea.TDPA.repositories;

import com.usbcali.aerolinea.TDPA.domains.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndDepartureDate(String departureAirportIataCode, String arrivalAirportIataCode, LocalDate departureDate);
}