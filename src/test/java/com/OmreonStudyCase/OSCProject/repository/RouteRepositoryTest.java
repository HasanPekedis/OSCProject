package com.OmreonStudyCase.OSCProject.repository;

import com.OmreonStudyCase.OSCProject.entity.Airport;
import com.OmreonStudyCase.OSCProject.entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AirportRepository airportRepository;

    private Airport departureAirport;
    private Airport destinationAirport;

    @BeforeEach
    public void setUp() {
        // Create and save a sample departure airport
        departureAirport = new Airport();
        departureAirport.setName("Departure Airport");
        departureAirport.setCode("DEP");
        airportRepository.save(departureAirport);

        // Create and save a sample destination airport
        destinationAirport = new Airport();
        destinationAirport.setName("Destination Airport");
        destinationAirport.setCode("DEST");
        airportRepository.save(destinationAirport);
    }

    @Test
    public void testSaveAndFindById() {
        // Create and save a sample Route
        Route route = new Route();
        route.setCode("RT001");
        route.setDeparture(departureAirport);
        route.setDestination(destinationAirport);

        Route savedRoute = routeRepository.save(route);

        // Verify that the route was saved and can be found by its ID
        assertNotNull(savedRoute.getId());
        Route foundRoute = routeRepository.findById(savedRoute.getId()).orElse(null);
        assertNotNull(foundRoute);
        assertEquals("RT001", foundRoute.getCode());
        assertEquals(departureAirport.getCode(), foundRoute.getDeparture().getCode());
        assertEquals(destinationAirport.getCode(), foundRoute.getDestination().getCode());
    }

    @Test
    public void testFindByCode() {
        // Create and save a route with a specific code
        Route route = new Route();
        route.setCode("RT002");
        route.setDeparture(departureAirport);
        route.setDestination(destinationAirport);
        routeRepository.save(route);

        // Verify that the route can be found by its code
        Route foundRoute = routeRepository.findByCode("RT002");
        assertNotNull(foundRoute);
        assertEquals("RT002", foundRoute.getCode());
        assertEquals(departureAirport.getCode(), foundRoute.getDeparture().getCode());
        assertEquals(destinationAirport.getCode(), foundRoute.getDestination().getCode());
    }

    @Test
    public void testDeleteRoute() {
        // Create and save a route
        Route route = new Route();
        route.setCode("RT003");
        route.setDeparture(departureAirport);
        route.setDestination(destinationAirport);
        Route savedRoute = routeRepository.save(route);

        // Delete the route
        routeRepository.deleteById(savedRoute.getId());

        // Verify that the route has been deleted
        assertFalse(routeRepository.findById(savedRoute.getId()).isPresent());
    }
}
