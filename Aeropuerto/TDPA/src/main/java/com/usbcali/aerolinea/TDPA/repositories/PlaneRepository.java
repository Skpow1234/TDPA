package com.usbcali.aerolinea.TDPA.repositories;

import com.usbcali.aerolinea.TDPA.domains.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
}
