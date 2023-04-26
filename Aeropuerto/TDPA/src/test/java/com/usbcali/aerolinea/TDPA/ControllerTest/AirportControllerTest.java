package com.usbcali.aerolinea.TDPA.ControllerTest;

import com.usbcali.aerolinea.TDPA.controllers.AirportController;
import com.usbcali.aerolinea.TDPA.domains.Airport;
import com.usbcali.aerolinea.TDPA.services.AirportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    @Test
    public void testCreateAirport() {
        Airport airport = new Airport();
        when(airportService.saveAirport(any(Airport.class))).thenReturn(airport);

        ResponseEntity<Airport> responseEntity = airportController.createAirport(airport);

        verify(airportService, times(1)).saveAirport(any(Airport.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(airport, responseEntity.getBody());
    }

    @Test
    public void testGetAirport() {
        Long id = 1L;
        Airport airport = new Airport();
        airport.setId(id);
        when(airportService.getAirport(id)).thenReturn(airport);

        ResponseEntity<Airport> responseEntity = airportController.getAirport(id);

        verify(airportService, times(1)).getAirport(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(airport, responseEntity.getBody());
    }

    @Test
    public void testGetAirportNotFound() {
        Long id = 1L;
        when(airportService.getAirport(id)).thenReturn(null);

        ResponseEntity<Airport> responseEntity = airportController.getAirport(id);

        verify(airportService, times(1)).getAirport(id);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testDeleteAirport() {
        Long id = 1L;

        ResponseEntity<Void> responseEntity = airportController.deleteAirport(id);

        verify(airportService, times(1)).deleteAirport(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testGetAirportByIataCode() {
        String iataCode = "ABC";
        Airport airport = new Airport();
        airport.setIataCode(iataCode);
        when(airportService.getAirportByIataCode(iataCode)).thenReturn(airport);

        ResponseEntity<Airport> responseEntity = airportController.getAirportByIataCode(iataCode);

        verify(airportService, times(1)).getAirportByIataCode(iataCode);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(airport, responseEntity.getBody());
    }

    @Test
    public void testGetAirportByIataCodeNotFound() {
        String iataCode = "ABC";
        when(airportService.getAirportByIataCode(iataCode)).thenReturn(null);

        ResponseEntity<Airport> responseEntity = airportController.getAirportByIataCode(iataCode);

        verify(airportService, times(1)).getAirportByIataCode(iataCode);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
