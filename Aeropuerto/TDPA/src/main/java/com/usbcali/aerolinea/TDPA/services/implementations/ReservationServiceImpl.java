package com.usbcali.aerolinea.TDPA.services.implementations;

import com.usbcali.aerolinea.TDPA.domains.Reservation;
import com.usbcali.aerolinea.TDPA.dtos.ReservationDTO;
import com.usbcali.aerolinea.TDPA.repositories.ReservationRepository;
import com.usbcali.aerolinea.TDPA.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {
        if (reservationDTO == null) {
            throw new NullPointerException("ReservationDTO object cannot be null");
        }
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        try {
            Reservation savedReservation = reservationRepository.save(reservation);
            return modelMapper.map(savedReservation, ReservationDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error saving reservation to database", e);
        }
    }

    @Override
    public ReservationDTO getReservation(Long id) {
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            return optionalReservation.map(reservation -> modelMapper.map(reservation, ReservationDTO.class)).orElse(null);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Reservation not found for given id", e);
        }
    }

    @Override
    public void deleteReservation(Long id) {
        try {
            reservationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Reservation not found for given id", e);
        }
    }

}

