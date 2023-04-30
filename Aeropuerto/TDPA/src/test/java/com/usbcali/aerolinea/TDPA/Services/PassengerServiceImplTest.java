package com.usbcali.aerolinea.TDPA.Services;

import com.usbcali.aerolinea.TDPA.domains.Passenger;
import com.usbcali.aerolinea.TDPA.dtos.PassengerDTO;
import com.usbcali.aerolinea.TDPA.mappers.PassengerMapper;
import com.usbcali.aerolinea.TDPA.repositories.PassengerRepository;
import com.usbcali.aerolinea.TDPA.services.implementations.PassengerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PassengerServiceImplTest {
    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PassengerMapper passengerMapper;

    @InjectMocks
    private PassengerServiceImpl passengerService;
    private PassengerDTO passengerDTO;
    private Passenger passenger;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();

    }
    @Test
    public void testDeletePassenger() {
        Long passengerId = 1L;

        doNothing().when(passengerRepository).deleteById(passengerId);

        assertDoesNotThrow(() -> passengerService.deletePassenger(passengerId));
    }

    @Test
    public void testDeletePassengerNotFoundException() {
        Long passengerId = 1L;

        doThrow(new EmptyResultDataAccessException(1)).when(passengerRepository).deleteById(passengerId);

        assertThrows(IllegalArgumentException.class, () -> passengerService.deletePassenger(passengerId));
    }
    @Test
    public void testSavePassenger() {
        // Given
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setFirstName("John");
        passengerDTO.setLastName("Doe");
        passengerDTO.setEmail("johndoe@example.com");

        Passenger passengerToSave = PassengerMapper.dtoToDomain(passengerDTO);
        Passenger savedPassenger = new Passenger();
        savedPassenger.setId(1L);
        savedPassenger.setFirstName(passengerToSave.getFirstName());
        savedPassenger.setLastName(passengerToSave.getLastName());
        savedPassenger.setEmail(passengerToSave.getEmail());

        when(passengerRepository.save(passengerToSave)).thenReturn(savedPassenger);

        // When
        PassengerDTO savedPassengerDTO = passengerService.savePassenger(passengerDTO);

        // Then
        assertEquals(savedPassenger.getId(), savedPassengerDTO.getId());
        assertEquals(savedPassenger.getFirstName(), savedPassengerDTO.getFirstName());
        assertEquals(savedPassenger.getLastName(), savedPassengerDTO.getLastName());
        assertEquals(savedPassenger.getEmail(), savedPassengerDTO.getEmail());
        verify(passengerRepository, times(1)).save(passengerToSave);
    }



    @Test
    public void testGetPassenger() {
        Long passengerId = 1L;

        Passenger passenger = new Passenger();
        passenger.setId(passengerId);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setEmail("johndoe@example.com");

        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(passenger));

        PassengerDTO passengerDTO = passengerService.getPassenger(passengerId);

        Assertions.assertNotNull(passengerDTO);
        assertEquals(passenger.getId(), passengerDTO.getId());
        assertEquals(passenger.getFirstName(), passengerDTO.getFirstName());
        assertEquals(passenger.getLastName(), passengerDTO.getLastName());
        assertEquals(passenger.getEmail(), passengerDTO.getEmail());
    }



    @Test
    public void testGetPassengers() {
        // Given
        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger1 = new Passenger();
        passenger1.setId(1L);
        passenger1.setFirstName("John");
        passenger1.setLastName("Doe");
        passenger1.setEmail("johndoe@example.com");
        Passenger passenger2 = new Passenger();
        passenger2.setId(2L);
        passenger2.setFirstName("Jane");
        passenger2.setLastName("Doe");
        passenger2.setEmail("janedoe@example.com");
        passengers.add(passenger1);
        passengers.add(passenger2);

        when(passengerRepository.findAll()).thenReturn(passengers);

        // When
        List<PassengerDTO> passengerDTOs = passengerService.getPassengers();

        // Then
        assertEquals(passengers.size(), passengerDTOs.size());
        for (int i = 0; i < passengers.size(); i++) {
            Passenger passenger = passengers.get(i);
            PassengerDTO passengerDTO = passengerDTOs.get(i);
            assertEquals(passenger.getId(), passengerDTO.getId());
            assertEquals(passenger.getFirstName(), passengerDTO.getFirstName());
            assertEquals(passenger.getLastName(), passengerDTO.getLastName());
            assertEquals(passenger.getEmail(), passengerDTO.getEmail());
        }
        verify(passengerRepository, times(1)).findAll();
    }
}
