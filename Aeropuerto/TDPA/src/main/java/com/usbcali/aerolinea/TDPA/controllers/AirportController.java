package com.usbcali.aerolinea.TDPA.controllers;

import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;
import com.usbcali.aerolinea.TDPA.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping(path = "/createAirport",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportDTO> createAirport(@RequestBody AirportDTO airportDTO) {
        AirportDTO savedAirportDTO = airportService.saveAirport(airportDTO);
        return new ResponseEntity<>(savedAirportDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAirport/{id}")
    public ResponseEntity<AirportDTO> getAirport(@PathVariable Long id) {
        AirportDTO airportDTO = airportService.getAirport(id);
        if (airportDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(airportDTO, HttpStatus.OK);
    }
    @GetMapping("/getAirports")
    public ResponseEntity<List<AirportDTO>> getAirports()  throws Exception {
        return new ResponseEntity(airportService.getAirports(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAirport/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/iata/{iataCode}")
    public ResponseEntity<AirportDTO> getAirportByIataCode(@PathVariable String iataCode) {
        AirportDTO airportDTO = airportService.getAirportByIataCode(iataCode);
        if (airportDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(airportDTO, HttpStatus.OK);
    }
}
