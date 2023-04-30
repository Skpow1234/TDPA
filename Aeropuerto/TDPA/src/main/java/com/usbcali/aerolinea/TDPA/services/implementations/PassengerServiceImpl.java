package com.usbcali.aerolinea.TDPA.services.implementations;

import com.usbcali.aerolinea.TDPA.domains.Passenger;
import com.usbcali.aerolinea.TDPA.dtos.PassengerDTO;
import com.usbcali.aerolinea.TDPA.mappers.PassengerMapper;
import com.usbcali.aerolinea.TDPA.repositories.PassengerRepository;
import com.usbcali.aerolinea.TDPA.services.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PassengerDTO savePassenger(PassengerDTO passengerDTO) {
        if (passengerDTO == null) {
            throw new IllegalArgumentException("PassengerDTO is null");
        }
        Passenger savedPassenger = passengerRepository.save(PassengerMapper.dtoToDomain(passengerDTO));
        if (savedPassenger == null) {
            throw new IllegalArgumentException("Failed to save passenger");
        }
        return PassengerMapper.domainToDto(savedPassenger);
    }

    @Override
    public PassengerDTO getPassenger(Long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        if (passenger.isPresent()) {
            return PassengerMapper.domainToDto(passenger.get());
        }
        throw new IllegalArgumentException("Passenger not found with id: " + id);
    }

    @Override
    public List<PassengerDTO> getPassengers() {
        return PassengerMapper.domainToDtoList(passengerRepository.findAll());
    }

    @Override
    public void deletePassenger(Long id) {
        try {
            passengerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new IllegalArgumentException("Passenger not found with id: " + id);
        }
    }



}
