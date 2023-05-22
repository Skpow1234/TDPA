package com.usbcali.aerolinea.TDPA.controllers;

import com.usbcali.aerolinea.TDPA.dtos.PlaneDTO;
import com.usbcali.aerolinea.TDPA.services.PlaneService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @PostMapping(path = "/createPlane",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaneDTO> createPlane(@RequestBody PlaneDTO planeDTO) {
        PlaneDTO savedPlaneDTO = planeService.savePlane(planeDTO);
        return new ResponseEntity<>(savedPlaneDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getPlane/{id}")
    public ResponseEntity<PlaneDTO> getPlane(@PathVariable Long id) {
        try {
            PlaneDTO planeDTO = planeService.getPlane(id);
            return new ResponseEntity<>(planeDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getPlanes")
    public ResponseEntity<List<PlaneDTO>> getPlanes() {
        List<PlaneDTO> planeDTOs = planeService.getPlanes();
        return new ResponseEntity<>(planeDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/deletePlane/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long id) {
        try {
            planeService.deletePlane(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

