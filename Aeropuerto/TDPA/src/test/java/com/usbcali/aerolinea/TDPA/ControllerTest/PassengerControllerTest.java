package com.usbcali.aerolinea.TDPA.ControllerTest;

import com.usbcali.aerolinea.TDPA.controllers.PassengerController;
import com.usbcali.aerolinea.TDPA.domains.Passenger;
import com.usbcali.aerolinea.TDPA.services.PassengerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PassengerControllerTest {

    @InjectMocks
    private PassengerController passengerController;

    @Mock
    private PassengerService passengerService;

    @Test
    public void testCreatePassengerSuccess() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setEmail("johndoe@example.com");
        when(passengerService.savePassenger(any(Passenger.class))).thenReturn(passenger);

        ResponseEntity<Passenger> response = passengerController.createPassenger(passenger);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(passenger, response.getBody());
    }
    @Test
    public void testGetPassengerSuccess() {
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setEmail("johndoe@example.com");
        when(passengerService.getPassenger(1L)).thenReturn(passenger);

        ResponseEntity<Passenger> response = passengerController.getPassenger(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passenger, response.getBody());
    }
    @Test
    public void testDeletePassengerSuccess() {
        ResponseEntity<Void> response = passengerController.deletePassenger(1L);

        verify(passengerService).deletePassenger(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    public void testGetPassengerNotFound() {
        when(passengerService.getPassenger(1L)).thenReturn(null);

        ResponseEntity<Passenger> response = passengerController.getPassenger(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    
}
