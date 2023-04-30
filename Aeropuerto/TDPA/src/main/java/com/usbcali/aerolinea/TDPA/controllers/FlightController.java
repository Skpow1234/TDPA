package com.usbcali.aerolinea.TDPA.controllers;

import com.usbcali.aerolinea.TDPA.dtos.AirportDTO;
import com.usbcali.aerolinea.TDPA.dtos.FlightDTO;
import com.usbcali.aerolinea.TDPA.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping(path = "/createFlight",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlightDTO> createFlight(@RequestBody FlightDTO flightDTO) {
        FlightDTO savedFlightDTO = flightService.saveFlight(flightDTO);
        return new ResponseEntity<>(savedFlightDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getFlight/{id}")
    public ResponseEntity<FlightDTO> getFlight(@PathVariable Long id) {
        FlightDTO flightDTO = flightService.getFlight(id);
        if (flightDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flightDTO, HttpStatus.OK);
    }
    @GetMapping("/getFlights")
    public ResponseEntity<List<AirportDTO>> getFlights()  throws Exception {
        return new ResponseEntity(flightService.getFlights(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFlight/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/searchFlights")
    public ResponseEntity<List<FlightDTO>> searchFlights(@RequestParam String departureAirportIataCode, @RequestParam String arrivalAirportIataCode, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        List<FlightDTO> flightsDTO = flightService.searchFlights(departureAirportIataCode, arrivalAirportIataCode, departureDate);
        if (flightsDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flightsDTO, HttpStatus.OK);
    }
}

