package com.usbcali.aerolinea.TDPA.repositories;

import com.usbcali.aerolinea.TDPA.domains.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
