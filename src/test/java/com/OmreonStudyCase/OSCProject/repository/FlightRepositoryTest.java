package com.OmreonStudyCase.OSCProject.repository;

import com.OmreonStudyCase.OSCProject.entity.Airline;
import com.OmreonStudyCase.OSCProject.entity.Flight;
import com.OmreonStudyCase.OSCProject.entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private RouteRepository routeRepository;

    private Airline airline;
    private Route route;

    @BeforeEach
    public void setUp() {
        // Create and save a sample Airline
        airline = new Airline();
        airline.setName("Sample Airline");
        airline.setCode("SA123");
        airlineRepository.save(airline);

        // Create and save a sample Route
        route = new Route();
        route.setCode("RT456");
        routeRepository.save(route);
    }

    @Test
    public void testSaveAndFindById() {
        // Create and save a sample Flight
        Flight flight = new Flight();
        flight.setFlightNumber("FL123");
        flight.setDepartureTime(LocalDateTime.now());
        flight.setArrivalTime(LocalDateTime.now().plusHours(2));
        flight.setTotalSeats(150);
        flight.setBookedSeats(50);
        flight.setCurrentTicketPrice(200.00);
        flight.setBaseTicketPrice(180.00);
        flight.setAirline(airline);
        flight.setRoute(route);

        Flight savedFlight = flightRepository.save(flight);

        // Verify the flight was saved and can be found by its ID
        assertNotNull(savedFlight.getId());
        Flight foundFlight = flightRepository.findById(savedFlight.getId()).orElse(null);
        assertNotNull(foundFlight);
        assertEquals("FL123", foundFlight.getFlightNumber());
        assertEquals(150, foundFlight.getTotalSeats());
        assertEquals(200.00, foundFlight.getCurrentTicketPrice());
        assertEquals(airline.getId(), foundFlight.getAirline().getId());
        assertEquals(route.getId(), foundFlight.getRoute().getId());
    }

    @Test
    public void testDeleteFlight() {
        // Create and save a flight
        Flight flight = new Flight();
        flight.setFlightNumber("FL789");
        flight.setDepartureTime(LocalDateTime.now());
        flight.setArrivalTime(LocalDateTime.now().plusHours(3));
        flight.setTotalSeats(200);
        flight.setBookedSeats(100);
        flight.setCurrentTicketPrice(250.00);
        flight.setBaseTicketPrice(220.00);
        flight.setAirline(airline);
        flight.setRoute(route);

        Flight savedFlight = flightRepository.save(flight);
        Long flightId = savedFlight.getId();

        // Delete the flight
        flightRepository.deleteById(flightId);

        // Verify that the flight has been deleted
        assertFalse(flightRepository.findById(flightId).isPresent());
    }
}
