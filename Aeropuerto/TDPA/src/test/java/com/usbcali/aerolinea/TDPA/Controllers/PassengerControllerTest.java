package com.usbcali.aerolinea.TDPA.Controllers;

import com.usbcali.aerolinea.TDPA.controllers.PassengerController;
import com.usbcali.aerolinea.TDPA.dtos.PassengerDTO;
import com.usbcali.aerolinea.TDPA.services.PassengerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PassengerControllerTest {
    @InjectMocks
    private PassengerController passengerController;

    @Mock
    private PassengerService passengerService;

    public PassengerControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePassenger() {
        // Preparación de datos de prueba
        PassengerDTO passengerDTO = new PassengerDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(passengerService.savePassenger(passengerDTO)).thenReturn(passengerDTO);

        // Llamar al método del controlador
        ResponseEntity<PassengerDTO> response = passengerController.createPassenger(passengerDTO);

        // Verificar el resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(passengerDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(passengerService, times(1)).savePassenger(passengerDTO);
    }

    @Test
    public void testGetPassenger() {
        // Preparación de datos de prueba
        Long passengerId = 1L;
        PassengerDTO passengerDTO = new PassengerDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(passengerService.getPassenger(passengerId)).thenReturn(passengerDTO);

        // Llamar al método del controlador
        ResponseEntity<PassengerDTO> response = passengerController.getPassenger(passengerId);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passengerDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(passengerService, times(1)).getPassenger(passengerId);
    }

    @Test
    public void testGetPassengers() throws Exception {
        // Preparación de datos de prueba
        List<PassengerDTO> passengerDTOList = new ArrayList<>();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(passengerService.getPassengers()).thenReturn(passengerDTOList);

        // Llamar al método del controlador
        ResponseEntity<List<PassengerDTO>> response = passengerController.getPassengers();

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passengerDTOList, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(passengerService, times(1)).getPassengers();
    }

    @Test
    public void testDeletePassenger() {
        // Preparación de datos de prueba
        Long passengerId = 1L;

        // Llamar al método del controlador
        ResponseEntity<Void> response = passengerController.deletePassenger(passengerId);

        // Verificar el resultado
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verificar que el método del servicio fue llamado una vez
        verify(passengerService, times(1)).deletePassenger(passengerId);
    }
}

