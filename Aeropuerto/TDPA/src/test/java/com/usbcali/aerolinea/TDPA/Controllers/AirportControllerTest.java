package com.usbcali.aerolinea.TDPA.Controllers;

import com.usbcali.aerolinea.TDPA.controllers.AirportController;
import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;
import com.usbcali.aerolinea.TDPA.services.AirportService;
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
public class AirportControllerTest {
    @InjectMocks
    private AirportController airportController;

    @Mock
    private AirportService airportService;

    public AirportControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAirport() {
        // Preparación de datos de prueba
        AirportDTO airportDTO = new AirportDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(airportService.saveAirport(airportDTO)).thenReturn(airportDTO);

        // Llamar al método del controlador
        ResponseEntity<AirportDTO> response = airportController.createAirport(airportDTO);

        // Verificar el resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(airportDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(airportService, times(1)).saveAirport(airportDTO);
    }
    @Test
    public void testGetAirport() {
        // Preparación de datos de prueba
        Long airportId = 1L;
        AirportDTO airportDTO = new AirportDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(airportService.getAirport(airportId)).thenReturn(airportDTO);

        // Llamar al método del controlador
        ResponseEntity<AirportDTO> response = airportController.getAirport(airportId);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airportDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(airportService, times(1)).getAirport(airportId);
    }

    @Test
    public void testGetAirports() throws Exception {
        // Preparación de datos de prueba
        List<AirportDTO> airportDTOList = new ArrayList<>();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(airportService.getAirports()).thenReturn(airportDTOList);

        // Llamar al método del controlador
        ResponseEntity<List<AirportDTO>> response = airportController.getAirports();

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airportDTOList, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(airportService, times(1)).getAirports();
    }

    @Test
    public void testDeleteAirport() {
        // Preparación de datos de prueba
        Long airportId = 1L;

        // Llamar al método del controlador
        ResponseEntity<Void> response = airportController.deleteAirport(airportId);

        // Verificar el resultado
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verificar que el método del servicio fue llamado una vez
        verify(airportService, times(1)).deleteAirport(airportId);
    }
    @Test
    public void testGetAirportByIataCode() {
        // Preparación de datos de prueba
        String iataCode = "ABC";
        AirportDTO airportDTO = new AirportDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(airportService.getAirportByIataCode(iataCode)).thenReturn(airportDTO);

        // Llamar al método del controlador
        ResponseEntity<AirportDTO> response = airportController.getAirportByIataCode(iataCode);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airportDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez con el iataCode correcto
        verify(airportService, times(1)).getAirportByIataCode(iataCode);
    }

}
