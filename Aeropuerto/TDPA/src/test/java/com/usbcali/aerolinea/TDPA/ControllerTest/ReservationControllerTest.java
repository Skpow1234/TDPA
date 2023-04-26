package com.usbcali.aerolinea.TDPA.ControllerTest;

import com.usbcali.aerolinea.TDPA.controllers.ReservationController;
import com.usbcali.aerolinea.TDPA.domains.Flight;
import com.usbcali.aerolinea.TDPA.domains.Passenger;
import com.usbcali.aerolinea.TDPA.domains.Reservation;
import com.usbcali.aerolinea.TDPA.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {
    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    public void createReservationTest() {
        // Crear una reserva de ejemplo
        Reservation reservation = new Reservation();
        Passenger passenger = new Passenger();
        Flight flight = new Flight();
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);

        // Establecer el comportamiento esperado del servicio
        when(reservationService.saveReservation(reservation)).thenReturn(reservation);

        // Hacer la solicitud POST al controlador
        ResponseEntity<Reservation> responseEntity = reservationController.createReservation(reservation);

        // Verificar que se devuelve el código de estado correcto
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Verificar que se devuelve la reserva creada
        assertEquals(reservation, responseEntity.getBody());
    }
    @Test
    public void getReservationTest() {
        // Crear una reserva de ejemplo y establecer su ID
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        // Establecer el comportamiento esperado del servicio
        when(reservationService.getReservation(1L)).thenReturn(reservation);

        // Hacer la solicitud GET al controlador
        ResponseEntity<Reservation> responseEntity = reservationController.getReservation(1L);

        // Verificar que se devuelve el código de estado correcto
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verificar que se devuelve la reserva correcta
        assertEquals(reservation, responseEntity.getBody());
    }
    @Test
    public void getNonExistentReservationTest() {
        // Establecer el comportamiento esperado del servicio
        when(reservationService.getReservation(1L)).thenReturn(null);

        // Hacer la solicitud GET al controlador
        ResponseEntity<Reservation> responseEntity = reservationController.getReservation(1L);

        // Verificar que se devuelve el código de estado correcto
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verificar que no se devuelve ninguna reserva
        assertNull(responseEntity.getBody());
    }

    @Test
    public void deleteReservationTest() {
        // Establecer el comportamiento esperado del servicio
        doNothing().when(reservationService).deleteReservation(1L);

        // Hacer la solicitud DELETE al controlador
        ResponseEntity<Void> responseEntity = reservationController.deleteReservation(1L);

        // Verificar que se devuelve el código de estado correcto
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verificar que no se devuelve ningún cuerpo
        assertNull(responseEntity.getBody());
    }

}
