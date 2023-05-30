package com.usbcali.aerolinea.TDPA.Services;

import com.usbcali.aerolinea.TDPA.domains.Reservation;
import com.usbcali.aerolinea.TDPA.dtos.ReservationDTO;
import com.usbcali.aerolinea.TDPA.mappers.ReservationMapper;
import com.usbcali.aerolinea.TDPA.repositories.ReservationRepository;
import com.usbcali.aerolinea.TDPA.services.implementations.ReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReservationServiceImplTest {
    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReservationServiceImpl reservationService;
    private ReservationMapper reservationMapper;

    @Test
    public void testSaveReservation() {
        // Given
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(1L);
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        when(modelMapper.map(reservationDTO, Reservation.class)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(modelMapper.map(reservation, ReservationDTO.class)).thenReturn(reservationDTO);

        // When
        ReservationDTO savedReservationDTO = reservationService.saveReservation(reservationDTO);

        // Then
        assertEquals(reservationDTO, savedReservationDTO);
        verify(reservationRepository).save(reservation);
    }
    @Test
    public void testGetReservation() {
        // Given
        Long id = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(id);
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(id);
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));
        when(modelMapper.map(reservation, ReservationDTO.class)).thenReturn(reservationDTO);

        // When
        ReservationDTO retrievedReservationDTO = reservationService.getReservation(id);

        // Then
        assertEquals(reservationDTO, retrievedReservationDTO);
        verify(reservationRepository).findById(id);
    }
    @Test
    public void testGetReservations() {
        // Given
        Reservation reservation1 = new Reservation();
        reservation1.setId(1L);
        Reservation reservation2 = new Reservation();
        reservation2.setId(2L);
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
        ReservationDTO reservationDTO1 = new ReservationDTO();
        reservationDTO1.setId(1L);
        ReservationDTO reservationDTO2 = new ReservationDTO();
        reservationDTO2.setId(2L);
        List<ReservationDTO> reservationDTOs = Arrays.asList(reservationDTO1, reservationDTO2);

        when(reservationRepository.findAll()).thenReturn(reservations);
        when(modelMapper.map(reservations, ReservationDTO[].class)).thenReturn(reservationDTOs.toArray(new ReservationDTO[0]));

        // When
        List<ReservationDTO> retrievedReservations = reservationService.getReservations();

        // Then
        assertEquals(reservationDTOs, retrievedReservations);
        verify(reservationRepository).findAll();
    }


    @Test
    public void testDeleteReservation() {
        // Given
        Long id = 1L;
        // When
        reservationService.deleteReservation(id);

        // Then
        verify(reservationRepository).deleteById(id);
    }
}
