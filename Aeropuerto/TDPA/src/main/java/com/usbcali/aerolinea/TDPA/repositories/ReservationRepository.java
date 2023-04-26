package com.usbcali.aerolinea.TDPA.repositories;

import com.usbcali.aerolinea.TDPA.domains.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
