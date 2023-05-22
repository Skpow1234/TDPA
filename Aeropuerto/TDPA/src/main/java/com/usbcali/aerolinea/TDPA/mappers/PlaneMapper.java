package com.usbcali.aerolinea.TDPA.mappers;

import com.usbcali.aerolinea.TDPA.domains.Plane;
import com.usbcali.aerolinea.TDPA.dtos.PlaneDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PlaneMapper {
    public static PlaneDTO domainToDto(Plane plane) {
        return PlaneDTO.builder()
                .id(plane.getId())
                .planeNumber(plane.getPlaneNumber())
                .planeName(plane.getPlaneName())
                .planeCode(plane.getPlaneCode())
                .build();
    }

    public static Plane dtoToDomain(PlaneDTO planeDTO) {
        return Plane.builder()
                .id(planeDTO.getId())
                .planeNumber(planeDTO.getPlaneNumber())
                .planeName(planeDTO.getPlaneName())
                .planeCode(planeDTO.getPlaneCode())
                .build();
    }

    public static List<PlaneDTO> domainToDtoList(List<Plane> planes) {
        return planes.stream()
                .map(plane -> domainToDto(plane))
                .collect(Collectors.toList());
    }

    public static List<Plane> dtoToDomainList(List<PlaneDTO> planeDTOS) {
        return planeDTOS.stream()
                .map(planeDTO -> dtoToDomain(planeDTO))
                .collect(Collectors.toList());
    }
}
