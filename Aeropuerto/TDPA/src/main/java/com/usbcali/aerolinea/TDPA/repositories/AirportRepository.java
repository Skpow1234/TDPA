package com.usbcali.aerolinea.TDPA.repositories;

import com.usbcali.aerolinea.TDPA.domains.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByIataCode(String iataCode);
}
