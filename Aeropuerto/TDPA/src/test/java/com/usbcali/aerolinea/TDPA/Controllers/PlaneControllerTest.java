package com.usbcali.aerolinea.TDPA.Controllers;

import com.usbcali.aerolinea.TDPA.controllers.PlaneController;
import com.usbcali.aerolinea.TDPA.dtos.PlaneDTO;
import com.usbcali.aerolinea.TDPA.services.PlaneService;
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
public class PlaneControllerTest {
    @InjectMocks
    private PlaneController planeController;

    @Mock
    private PlaneService planeService;

    public PlaneControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePlane() {
        // Preparación de datos de prueba
        PlaneDTO planeDTO = new PlaneDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(planeService.savePlane(planeDTO)).thenReturn(planeDTO);

        // Llamar al método del controlador
        ResponseEntity<PlaneDTO> response = planeController.createPlane(planeDTO);

        // Verificar el resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(planeDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(planeService, times(1)).savePlane(planeDTO);
    }

    @Test
    public void testGetPlane() {
        // Preparación de datos de prueba
        Long planeId = 1L;
        PlaneDTO planeDTO = new PlaneDTO();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(planeService.getPlane(planeId)).thenReturn(planeDTO);

        // Llamar al método del controlador
        ResponseEntity<PlaneDTO> response = planeController.getPlane(planeId);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(planeDTO, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(planeService, times(1)).getPlane(planeId);
    }

    @Test
    public void testGetPlanes() {
        // Preparación de datos de prueba
        List<PlaneDTO> planeDTOList = new ArrayList<>();
        // ... configurar los datos de prueba

        // Mock del servicio
        when(planeService.getPlanes()).thenReturn(planeDTOList);

        // Llamar al método del controlador
        ResponseEntity<List<PlaneDTO>> response = planeController.getPlanes();

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(planeDTOList, response.getBody());

        // Verificar que el método del servicio fue llamado una vez
        verify(planeService, times(1)).getPlanes();
    }

    @Test
    public void testDeletePlane() {
        // Preparación de datos de prueba
        Long planeId = 1L;

        // Llamar al método del controlador
        ResponseEntity<Void> response = planeController.deletePlane(planeId);

        // Verificar el resultado
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verificar que el método del servicio fue llamado una vez
        verify(planeService, times(1)).deletePlane(planeId);
    }
}

