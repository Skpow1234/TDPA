package com.usbcali.aerolinea.TDPA.services;

import com.usbcali.aerolinea.TDPA.dtos.PlaneDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface PlaneService {
    PlaneDTO savePlane(PlaneDTO planeDTO) throws IllegalArgumentException;

    PlaneDTO getPlane(Long id) throws EntityNotFoundException;

    List<PlaneDTO> getPlanes();

    void deletePlane(Long id) throws EntityNotFoundException;
}
