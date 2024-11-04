package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.services.AirportService;
import com.OmreonStudyCase.OSCProject.entity.Airport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    // Havaalanı ekleme
    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        Airport savedAirport = airportService.addAirport(airport);
        return new ResponseEntity<>(savedAirport, HttpStatus.CREATED);
    }

    // Tüm havaalanlarını alma
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    // ID'ye göre havaalanı arama
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id)
                .map(airport -> new ResponseEntity<>(airport, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
