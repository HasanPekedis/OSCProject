package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.entity.Route;
import com.OmreonStudyCase.OSCProject.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    // Save a new route
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    // Retrieve all routes
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Retrieve a route by ID
    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    // Delete a route by ID
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}

