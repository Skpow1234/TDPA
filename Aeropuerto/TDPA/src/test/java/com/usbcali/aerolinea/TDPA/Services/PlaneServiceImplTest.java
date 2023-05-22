package com.usbcali.aerolinea.TDPA.Services;

import com.usbcali.aerolinea.TDPA.domains.Plane;
import com.usbcali.aerolinea.TDPA.dtos.PlaneDTO;
import com.usbcali.aerolinea.TDPA.mappers.PlaneMapper;
import com.usbcali.aerolinea.TDPA.repositories.PlaneRepository;
import com.usbcali.aerolinea.TDPA.services.implementations.PlaneServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlaneServiceImplTest {
    @Mock
    private PlaneRepository planeRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PlaneMapper planeMapper;

    @InjectMocks
    private PlaneServiceImpl planeService;
    private PlaneDTO planeDTO;
    private Plane plane;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();

    }

    @Test
    void savePlane_withNonNullPlaneDTO_shouldSavePlane() {
        // Given
        PlaneDTO planeDTO = new PlaneDTO();
        planeDTO.setId(1L);
        planeDTO.setPlaneNumber("123");
        planeDTO.setPlaneName("Plane 1");
        planeDTO.setPlaneCode("ABC");

        Plane plane = new Plane();
        plane.setId(1L);
        plane.setPlaneNumber("123");
        plane.setPlaneName("Plane 1");
        plane.setPlaneCode("ABC");

        ModelMapper modelMapper = mock(ModelMapper.class);
        PlaneRepository planeRepository = mock(PlaneRepository.class);

        when(modelMapper.map(planeDTO, Plane.class)).thenReturn(plane);
        when(planeRepository.save(plane)).thenReturn(plane);
        when(modelMapper.map(plane, PlaneDTO.class)).thenReturn(planeDTO);

        PlaneServiceImpl planeService = new PlaneServiceImpl(planeRepository, modelMapper);

        // When
        PlaneDTO savedPlane = planeService.savePlane(planeDTO);

        // Then
        assertEquals(planeDTO, savedPlane);
    }
    @Test
    void savePlane_withNullPlaneDTO_shouldThrowIllegalArgumentException() {
        // Given
        ModelMapper modelMapper = mock(ModelMapper.class);
        PlaneRepository planeRepository = mock(PlaneRepository.class);

        doThrow(IllegalArgumentException.class).when(modelMapper).map(null, Plane.class);

        PlaneServiceImpl planeService = new PlaneServiceImpl(planeRepository, modelMapper);

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> planeService.savePlane(null));
    }

    @Test
    void getPlane_withExistingId_shouldReturnPlaneDTO() {
        // Given
        Long id = 1L;
        Plane plane = new Plane();
        plane.setId(id);
        plane.setPlaneNumber("123");
        plane.setPlaneName("Plane 1");
        plane.setPlaneCode("ABC");

        PlaneDTO planeDTO = new PlaneDTO();
        planeDTO.setId(id);
        planeDTO.setPlaneNumber("123");
        planeDTO.setPlaneName("Plane 1");
        planeDTO.setPlaneCode("ABC");

        ModelMapper modelMapper = mock(ModelMapper.class);
        PlaneRepository planeRepository = mock(PlaneRepository.class);

        when(planeRepository.findById(id)).thenReturn(Optional.of(plane));
        when(modelMapper.map(plane, PlaneDTO.class)).thenReturn(planeDTO);

        PlaneServiceImpl planeService = new PlaneServiceImpl(planeRepository, modelMapper);

        // When
        PlaneDTO returnedPlane = planeService.getPlane(id);

        // Then
        assertEquals(planeDTO, returnedPlane);
    }
    @Test
    void getPlane_withNonExistingId_shouldThrowEntityNotFoundException() {
        // Given
        Long id = 2L;
        when(planeRepository.findById(id)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(EntityNotFoundException.class, () -> planeService.getPlane(id));
    }
    @Test
    void deletePlane_withExistingId_shouldDeletePlane() {
        // Given
        Long id = 1L;
        doNothing().when(planeRepository).deleteById(id);

        // When, Then
        assertDoesNotThrow(() -> planeService.deletePlane(id));
    }

}
