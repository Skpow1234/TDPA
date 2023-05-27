package com.usbcali.aerolinea.TDPA.Controllers;

import com.usbcali.aerolinea.TDPA.controllers.FlightController;
import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;
import com.usbcali.aerolinea.TDPA.dtos.FlightDTO;
import com.usbcali.aerolinea.TDPA.services.FlightService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FlightControllerTest {
    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightService flightService;

    public FlightControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFlight() {
        // Preparación de datos de prueba
        FlightDTO flightDTO = new FlightDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(flightService.saveFlight(flightDTO)).thenReturn(flightDTO);

        // Llamar al método del controlador
        ResponseEntity<FlightDTO> response = flightController.createFlight(flightDTO);

        // Verificar el resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(flightDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(flightService, times(1)).saveFlight(flightDTO);
    }

    @Test
    public void testGetFlight() {
        // Preparación de datos de prueba
        Long flightId = 1L;
        FlightDTO flightDTO = new FlightDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(flightService.getFlight(flightId)).thenReturn(flightDTO);

        // Llamar al método del controlador
        ResponseEntity<FlightDTO> response = flightController.getFlight(flightId);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flightDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(flightService, times(1)).getFlight(flightId);
    }

    @Test
    public void testGetFlights() throws Exception {
        // Preparación de datos de prueba
        List<FlightDTO> flightDTOList = new ArrayList<>();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(flightService.getFlights()).thenReturn(flightDTOList);

        // Llamar al método del controlador
        ResponseEntity<List<AirportDTO>> response = flightController.getFlights();

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flightDTOList, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(flightService, times(1)).getFlights();
    }

    @Test
    public void testDeleteFlight() {
        // Preparación de datos de prueba
        Long flightId = 1L;

        // Llamar al método del controlador
        ResponseEntity<Void> response = flightController.deleteFlight(flightId);

        // Verificar el resultado
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verificar que el método del servicio fue llamado una vez
        verify(flightService, times(1)).deleteFlight(flightId);
    }

    @Test
    public void testSearchFlights() {
        // Preparación de datos de prueba
        String departureAirportIataCode = "ABC";
        String arrivalAirportIataCode = "XYZ";
        LocalDate departureDate = LocalDate.now();
        List<FlightDTO> flightsDTO = new ArrayList<>();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(flightService.searchFlights(departureAirportIataCode, arrivalAirportIataCode, departureDate)).thenReturn(flightsDTO);

        // Llamar al método del controlador
        ResponseEntity<List<FlightDTO>> response = flightController.searchFlights(departureAirportIataCode, arrivalAirportIataCode, departureDate);

        // Verificar el resultado
        if (flightsDTO.isEmpty()) {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertNull(response.getBody());
        } else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(flightsDTO, response.getBody());
        }

        // Verificar que el método del servicio fue llamado una vez con los parámetros correctos
        verify(flightService, times(1)).searchFlights(departureAirportIataCode, arrivalAirportIataCode, departureDate);
    }

}
