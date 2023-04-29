package com.usbcali.aerolinea.TDPA.services.implementations;

import com.usbcali.aerolinea.TDPA.domains.Airport;
import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;
import com.usbcali.aerolinea.TDPA.repositories.AirportRepository;
import com.usbcali.aerolinea.TDPA.services.AirportService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AirportDTO saveAirport(AirportDTO airportDTO) throws IllegalArgumentException {
        if (airportDTO == null) {
            throw new IllegalArgumentException("AirportDTO is null");
        }
        Airport airport = modelMapper.map(airportDTO, Airport.class);
        airport = airportRepository.save(airport);
        return modelMapper.map(airport, AirportDTO.class);
    }

    @Override
    public AirportDTO getAirport(Long id) throws EntityNotFoundException {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        if (optionalAirport.isPresent()) {
            return modelMapper.map(optionalAirport.get(), AirportDTO.class);
        }
        throw new EntityNotFoundException("Airport with id " + id + " not found");
    }

    @Override
    public void deleteAirport(Long id) throws EntityNotFoundException {
        try {
            airportRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Airport with id " + id + " not found");
        }
    }

    @Override
    public AirportDTO getAirportByIataCode(String iataCode) throws EntityNotFoundException {
        Optional<Airport> optionalAirport = Optional.ofNullable(airportRepository.findByIataCode(iataCode));
        if (optionalAirport.isPresent()) {
            return modelMapper.map(optionalAirport.get(), AirportDTO.class);
        }
        throw new EntityNotFoundException("Airport with IATA code " + iataCode + " not found");
    }

}

