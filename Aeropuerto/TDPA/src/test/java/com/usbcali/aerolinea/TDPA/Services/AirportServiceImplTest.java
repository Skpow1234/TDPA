package com.usbcali.aerolinea.TDPA.Services;

import com.usbcali.aerolinea.TDPA.domains.Airport;
import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;
import com.usbcali.aerolinea.TDPA.repositories.AirportRepository;
import com.usbcali.aerolinea.TDPA.services.implementations.AirportServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AirportServiceImplTest {
    @Mock
    private AirportRepository airportRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AirportServiceImpl airportService;

    private AirportDTO airportDTO;
    private Airport airport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        airport = new Airport();
        airport.setId(1L);
        airport.setName("Airport 1");
        airport.setIataCode("AAA");

        airportDTO = new AirportDTO();
        airportDTO.setId(1L);
        airportDTO.setName("Airport 1");
        airportDTO.setIataCode("AAA");
    }
    @Test
    void saveAirport_withNonNullAirportDTO_shouldSaveAirport() {
        // Given
        when(modelMapper.map(airportDTO, Airport.class)).thenReturn(airport);
        when(airportRepository.save(airport)).thenReturn(airport);
        when(modelMapper.map(airport, AirportDTO.class)).thenReturn(airportDTO);

        // When
        AirportDTO savedAirport = airportService.saveAirport(airportDTO);

        // Then
        assertEquals(airportDTO, savedAirport);
    }
    @Test
    void saveAirport_withNullAirportDTO_shouldThrowIllegalArgumentException() {
        // Given
        doThrow(IllegalArgumentException.class).when(modelMapper).map(null, Airport.class);

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> airportService.saveAirport(null));
    }
    @Test
    void getAirport_withExistingId_shouldReturnAirportDTO() {
        // Given
        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));
        when(modelMapper.map(airport, AirportDTO.class)).thenReturn(airportDTO);

        // When
        AirportDTO returnedAirport = airportService.getAirport(1L);

        // Then
        assertEquals(airportDTO, returnedAirport);
    }
    @Test
    void getAirport_withNonExistingId_shouldThrowEntityNotFoundException() {
        // Given
        when(airportRepository.findById(2L)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(EntityNotFoundException.class, () -> airportService.getAirport(2L));
    }
    @Test
    public void testSaveAirport() {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setName("Test Airport");
        airportDTO.setIataCode("TST");
        Airport airport = new Airport();
        airport.setName("Test Airport");
        airport.setIataCode("TST");

        when(modelMapper.map(airportDTO, Airport.class)).thenReturn(airport);
        when(airportRepository.save(airport)).thenReturn(airport);
        when(modelMapper.map(airport, AirportDTO.class)).thenReturn(airportDTO);

        AirportDTO result = airportService.saveAirport(airportDTO);

        assertEquals("Test Airport", result.getName());
        assertEquals("TST", result.getIataCode());
    }
    @Test
    public void testGetAirport() {
        Airport airport = new Airport();
        airport.setName("Test Airport");
        airport.setIataCode("TST");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));
        when(modelMapper.map(airport, AirportDTO.class)).thenReturn(new AirportDTO());

        AirportDTO result = airportService.getAirport(1L);

        assertNotNull(result);
    }
    @Test
    public void testGetAirports() {
        List<Airport> airports = new ArrayList<>();
        airports.add(new Airport());
        airports.add(new Airport());

        when(airportRepository.findAll()).thenReturn(airports);

        List<AirportDTO> result = airportService.getAirports();

        assertNotNull(result);
        assertEquals(2, result.size());
        // Verificar que se está llamando a airportRepository.findAll()
        verify(airportRepository).findAll();
    }

    @Test
    public void testDeleteAirport() {
        doNothing().when(airportRepository).deleteById(1L);

        airportService.deleteAirport(1L);
    }
    @Test
    public void testGetAirportByIataCode() {
        Airport airport = new Airport();
        airport.setName("Test Airport");
        airport.setIataCode("TST");

        when(airportRepository.findByIataCode("TST")).thenReturn(airport);
        when(modelMapper.map(airport, AirportDTO.class)).thenReturn(new AirportDTO());

        AirportDTO result = airportService.getAirportByIataCode("TST");

        assertNotNull(result);
    }

}
