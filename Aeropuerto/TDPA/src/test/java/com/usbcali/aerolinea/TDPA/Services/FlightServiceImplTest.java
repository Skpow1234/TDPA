package com.usbcali.aerolinea.TDPA.Services;

import com.usbcali.aerolinea.TDPA.domains.Flight;
import com.usbcali.aerolinea.TDPA.dtos.FlightDTO;
import com.usbcali.aerolinea.TDPA.repositories.FlightRepository;
import com.usbcali.aerolinea.TDPA.services.implementations.FlightServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FlightServiceImplTest {
    @Mock
    private FlightRepository flightRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    public void testSaveFlight() {
        FlightDTO flightDTO = new FlightDTO();
        Flight flight = new Flight();
        when(modelMapper.map(flightDTO, Flight.class)).thenReturn(flight);
        when(flightRepository.save(flight)).thenReturn(flight);
        when(modelMapper.map(flight, FlightDTO.class)).thenReturn(flightDTO);

        FlightDTO result = flightService.saveFlight(flightDTO);

        assertNotNull(result);
        assertEquals(flightDTO, result);
    }
    @Test
    public void testGetFlight() {
        Flight flight = new Flight();
        FlightDTO flightDTO = new FlightDTO();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(modelMapper.map(flight, FlightDTO.class)).thenReturn(flightDTO);

        FlightDTO result = flightService.getFlight(1L);

        assertNotNull(result);
        assertEquals(flightDTO, result);
    }
    @Test
    public void testDeleteFlight() {
        flightService.deleteFlight(1L);

        verify(flightRepository).deleteById(1L);
    }
    @Test
    public void testSearchFlights() {
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        List<FlightDTO> flightDTOs = Arrays.asList(new FlightDTO(), new FlightDTO());
        when(flightRepository.findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndDepartureDate("LAX", "JFK", LocalDate.of(2023, 5, 1))).thenReturn(flights);
        when(modelMapper.map(any(Flight.class), eq(FlightDTO.class))).thenReturn(new FlightDTO());

        List<FlightDTO> result = flightService.searchFlights("LAX", "JFK", LocalDate.of(2023, 5, 1));

        assertNotNull(result);
        assertEquals(flightDTOs.size(), result.size());
    }
}
