package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.repository.FlightRepository;
import com.OmreonStudyCase.OSCProject.repository.AirlineRepository;
import com.OmreonStudyCase.OSCProject.repository.RouteRepository;

import com.OmreonStudyCase.OSCProject.entity.Flight;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private RouteRepository routeRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight addFlight(Flight flight) {

        if (flight.getAirline().getCode() == null && flight.getRoute().getCode() == null) {
            return flightRepository.save(flight);
        } else if (flight.getAirline().getCode() == null && flight.getRoute().getCode() != null) {
            flight.setRoute(routeRepository.findByCode(flight.getRoute().getCode().toString()));
            return flightRepository.save(flight);    
        } else if (flight.getAirline().getCode() != null && flight.getRoute().getCode() == null) {
            flight.setAirline(airlineRepository.findByCode(flight.getAirline().getCode().toString()));    
            return flightRepository.save(flight);
        } else {

            flight.setAirline(airlineRepository.findByCode(flight.getAirline().getCode().toString()));
            flight.setRoute(routeRepository.findByCode(flight.getRoute().getCode().toString()));
            
            return flightRepository.save(flight);

        }
            
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight != null) {
            flight.setFlightNumber(flightDetails.getFlightNumber());
            flight.setRoute(flightDetails.getRoute());
            flight.setDepartureTime(flightDetails.getDepartureTime());
            flight.setArrivalTime(flightDetails.getArrivalTime());
            return flightRepository.save(flight);
        }
        return null;
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}

