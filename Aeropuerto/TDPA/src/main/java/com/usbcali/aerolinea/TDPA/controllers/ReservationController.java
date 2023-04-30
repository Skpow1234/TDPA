package com.usbcali.aerolinea.TDPA.controllers;

import com.usbcali.aerolinea.TDPA.dtos.ReservationDTO;
import com.usbcali.aerolinea.TDPA.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ModelMapper modelMapper;

    public ReservationController(ReservationService reservationService, ModelMapper modelMapper) {
        this.reservationService = reservationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/createReservation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO savedReservationDTO = reservationService.saveReservation(reservationDTO);
        return new ResponseEntity<>(savedReservationDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getReservation/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable Long id) {
        ReservationDTO reservationDTO = reservationService.getReservation(id);
        if (reservationDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }
    @GetMapping("/getReservations")
    public ResponseEntity<List<ReservationDTO>> getReservations()  throws Exception {
        return new ResponseEntity(reservationService.getReservations(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteReservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
