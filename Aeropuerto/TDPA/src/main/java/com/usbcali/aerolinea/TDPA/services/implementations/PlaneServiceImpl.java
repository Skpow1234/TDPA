package com.usbcali.aerolinea.TDPA.services.implementations;

import com.usbcali.aerolinea.TDPA.domains.Plane;
import com.usbcali.aerolinea.TDPA.dtos.PlaneDTO;
import com.usbcali.aerolinea.TDPA.mappers.PlaneMapper;
import com.usbcali.aerolinea.TDPA.repositories.PlaneRepository;
import com.usbcali.aerolinea.TDPA.services.PlaneService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PlaneDTO savePlane(PlaneDTO planeDTO) throws IllegalArgumentException {
        if (planeDTO == null) {
            throw new IllegalArgumentException("PlaneDTO is null");
        }
        Plane plane = modelMapper.map(planeDTO, Plane.class);
        plane = planeRepository.save(plane);
        return modelMapper.map(plane, PlaneDTO.class);
    }

    @Override
    public PlaneDTO getPlane(Long id) throws EntityNotFoundException {
        Optional<Plane> optionalPlane = planeRepository.findById(id);
        if (optionalPlane.isPresent()) {
            return modelMapper.map(optionalPlane.get(), PlaneDTO.class);
        }
        throw new EntityNotFoundException("Plane with id " + id + " not found");
    }

    @Override
    public List<PlaneDTO> getPlanes() {
        return PlaneMapper.domainToDtoList(planeRepository.findAll());
    }

    @Override
    public void deletePlane(Long id) throws EntityNotFoundException {
        try {
            planeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Plane with id " + id + " not found");
        }
    }
}

