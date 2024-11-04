package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.entity.Airline;
import com.OmreonStudyCase.OSCProject.repository.AirlineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline addAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline getAirlineById(Long id) {
        Optional<Airline> airline = airlineRepository.findById(id);
        return airline.orElse(null); // Handle null or throw an exception if needed
    }

    public Airline getAirlineByCode(String code) {
        return airlineRepository.findByCode(code);
    }
}
