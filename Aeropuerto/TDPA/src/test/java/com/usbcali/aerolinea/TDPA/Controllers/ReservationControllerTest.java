package com.usbcali.aerolinea.TDPA.Controllers;

import com.usbcali.aerolinea.TDPA.controllers.ReservationController;
import com.usbcali.aerolinea.TDPA.dtos.ReservationDTO;
import com.usbcali.aerolinea.TDPA.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReservation_ValidReservation_ReturnsCreatedStatus() {
        // Arrange
        ReservationDTO reservationDTO = new ReservationDTO();
        // Mock the service method
        when(reservationService.saveReservation(reservationDTO)).thenReturn(reservationDTO);

        // Act
        ResponseEntity<ReservationDTO> response = reservationController.createReservation(reservationDTO);

        // Assert
        verify(reservationService, times(1)).saveReservation(reservationDTO);
        assertSame(reservationDTO, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getReservation_ExistingId_ReturnsReservationDTO() {
        // Arrange
        Long reservationId = 1L;
        ReservationDTO reservationDTO = new ReservationDTO();
        // Mock the service method
        when(reservationService.getReservation(reservationId)).thenReturn(reservationDTO);

        // Act
        ResponseEntity<ReservationDTO> response = reservationController.getReservation(reservationId);

        // Assert
        verify(reservationService, times(1)).getReservation(reservationId);
        assertSame(reservationDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getReservation_NonExistingId_ReturnsNotFoundStatus() {
        // Arrange
        Long reservationId = 1L;
        // Mock the service method to return null
        when(reservationService.getReservation(reservationId)).thenReturn(null);

        // Act
        ResponseEntity<ReservationDTO> response = reservationController.getReservation(reservationId);

        // Assert
        verify(reservationService, times(1)).getReservation(reservationId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getReservations_ReturnsListOfReservationDTOs() throws Exception {
        // Arrange
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        // Mock the service method
        when(reservationService.getReservations()).thenReturn(reservationDTOs);

        // Act
        ResponseEntity<List<ReservationDTO>> response = reservationController.getReservations();

        // Assert
        verify(reservationService, times(1)).getReservations();
        assertSame(reservationDTOs, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteReservation_ExistingId_ReturnsNoContentStatus() {
        // Arrange
        Long reservationId = 1L;

        // Act
        ResponseEntity<Void> response = reservationController.deleteReservation(reservationId);

        // Assert
        verify(reservationService, times(1)).deleteReservation(reservationId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
