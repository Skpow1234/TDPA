package com.usbcali.aerolinea.TDPA.controllers;

import com.usbcali.aerolinea.TDPA.dtos.PassengerDTO;
import com.usbcali.aerolinea.TDPA.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping(path = "/createPassenger",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody PassengerDTO passengerDTO) {
        PassengerDTO savedPassengerDTO = passengerService.savePassenger(passengerDTO);
        return new ResponseEntity<>(savedPassengerDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getPassenger/{id}")
    public ResponseEntity<PassengerDTO> getPassenger(@PathVariable Long id) {
        PassengerDTO passengerDTO = passengerService.getPassenger(id);
        if (passengerDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(passengerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deletePassenger/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

