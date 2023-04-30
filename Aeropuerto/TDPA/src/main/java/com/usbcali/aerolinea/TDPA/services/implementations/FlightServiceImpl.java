package com.usbcali.aerolinea.TDPA.services.implementations;

import com.usbcali.aerolinea.TDPA.domains.Flight;
import com.usbcali.aerolinea.TDPA.dtos.FlightDTO;
import com.usbcali.aerolinea.TDPA.mappers.FlightMapper;
import com.usbcali.aerolinea.TDPA.repositories.FlightRepository;
import com.usbcali.aerolinea.TDPA.services.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FlightDTO saveFlight(FlightDTO flightDTO) throws IllegalArgumentException {
        if(flightDTO == null) {
            throw new IllegalArgumentException("FlightDTO cannot be null.");
        }
        Flight flight = modelMapper.map(flightDTO, Flight.class);
        Flight savedFlight = flightRepository.save(flight);
        return modelMapper.map(savedFlight, FlightDTO.class);
    }

    @Override
    public FlightDTO getFlight(Long id) throws NoSuchElementException {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            return modelMapper.map(optionalFlight.get(), FlightDTO.class);
        } else {
            throw new NoSuchElementException("Flight with ID " + id + " not found.");
        }
    }
    @Override
    public List<FlightDTO> getFlights(){
        return FlightMapper.domainToDtoList(flightRepository.findAll());
    }

    @Override
    public void deleteFlight(Long id) throws IllegalArgumentException {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid flight ID.");
        }
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightDTO> searchFlights(String departureAirportIataCode, String arrivalAirportIataCode, LocalDate departureDate) throws IllegalArgumentException {
        if(departureAirportIataCode == null || arrivalAirportIataCode == null || departureDate == null) {
            throw new IllegalArgumentException("All search parameters are required.");
        }
        List<Flight> flights = flightRepository.findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndDepartureDate(departureAirportIataCode, arrivalAirportIataCode, departureDate);
        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class))
                .collect(Collectors.toList());
    }
}
