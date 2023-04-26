package com.usbcali.aerolinea.TDPA.ControllerTest;

import com.usbcali.aerolinea.TDPA.controllers.FlightController;
import com.usbcali.aerolinea.TDPA.domains.Flight;
import com.usbcali.aerolinea.TDPA.services.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    // Este método de prueba verifica si la creación de un vuelo es exitosa.
    @Test
    public void createFlightTest() {
        // Crear un objeto de vuelo
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("ABC123");
        LocalDate departureDate = LocalDate.of(2023, Month.JULY, 1);
        flight.setDepartureDate(departureDate);
        // Mockear el método de guardar vuelo para retornar el vuelo creado
        when(flightService.saveFlight(Mockito.any(Flight.class))).thenReturn(flight);
        // Llamar al método de crear vuelo en el controlador y obtener el vuelo creado
        Flight savedFlight = flightController.createFlight(flight).getBody();
        // Verificar que los atributos del vuelo creado sean iguales a los atributos del vuelo guardado
        assertEquals(flight.getId(), savedFlight.getId());
        assertEquals(flight.getFlightNumber(), savedFlight.getFlightNumber());
        assertEquals(flight.getDepartureDate(), savedFlight.getDepartureDate());
        // Verificar que el método de guardar vuelo fue llamado una vez
        Mockito.verify(flightService, Mockito.times(1)).saveFlight(Mockito.any(Flight.class));
    }
    // Este método de prueba verifica si se puede borrar un vuelo exitosamente
    @Test
    public void deleteFlightTest() {
        // Id del vuelo a borrar
        Long flightId = 1L;
        // Mockear el método de borrar vuelo para hacer nada y llamar al método de borrar vuelo en el controlador
        Mockito.doNothing().when(flightService).deleteFlight(flightId);

        ResponseEntity<Void> response = flightController.deleteFlight(flightId);
        // Verificar que el código de respuesta sea NO_CONTENT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // Verificar que el método de borrar vuelo fue llamado una vez
        Mockito.verify(flightService, Mockito.times(1)).deleteFlight(flightId);
    }
    // Este método de prueba verifica si se puede borrar un vuelo que no existe
    @Test
    public void testDeleteFlightNotFound() {
        Long id = 1L;

        // Mockear el método de borrar vuelo para hacer nada y llamar al método de borrar vuelo en el controlador
        Mockito.doNothing().when(flightService).deleteFlight(id);

        ResponseEntity<Void> responseEntity = flightController.deleteFlight(id);

        // Verificar que el código de respuesta sea NO_CONTENT
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
    // Este método de prueba verifica si se puede obtener un vuelo que no existe
    @Test
    public void testGetFlightNotFound() {
        Long id = 1L;

        // Mockear el método de obtener vuelo para retornar null y llamar al método de obtener vuelo en el controlador
        Mockito.when(flightService.getFlight(id)).thenReturn(null);

        ResponseEntity<Flight> responseEntity = flightController.getFlight(id);

        // Verificar que el código de respuesta sea NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
