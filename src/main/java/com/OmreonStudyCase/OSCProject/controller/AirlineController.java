package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.entity.Airline;
import com.OmreonStudyCase.OSCProject.services.AirlineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @PostMapping
    public Airline addAirline(@RequestBody Airline airline) {
        return airlineService.addAirline(airline);
    }

    // Method to get an airline by ID
    @GetMapping("/{id}")
    public Airline getAirlineById(@PathVariable Long id) {
        return airlineService.getAirlineById(id);
    }

    // Method to get an airline by code
    @GetMapping("/code/{code}")
    public Airline getAirlineByCode(@PathVariable String code) {
        return airlineService.getAirlineByCode(code);
    }
}
