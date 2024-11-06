package com.OmreonStudyCase.OSCProject.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class FlightTest {

    @Test
    public void testFlightEntity() {
        // Create sample Airline and Route instances
        Airline airline = new Airline();
        airline.setId(1L);
        airline.setName("Test Airline");
        airline.setCountry("Test Country");
        airline.setCode("TA");

        Route route = new Route();
        route.setId(1L);
        route.setCode("ROUTE123");

        // Create an instance of Flight
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("FL123");
        flight.setDepartureTime(LocalDateTime.of(2024, 11, 5, 10, 0));
        flight.setArrivalTime(LocalDateTime.of(2024, 11, 5, 12, 0));
        flight.setTotalSeats(100);
        flight.setBookedSeats(50);
        flight.setCurrentTicketPrice(150.0);
        flight.setBaseTicketPrice(100.0);
        flight.setAirline(airline);
        flight.setRoute(route);

        // Verify that the getters return the expected values
        assertEquals(1L, flight.getId());
        assertEquals("FL123", flight.getFlightNumber());
        assertEquals(LocalDateTime.of(2024, 11, 5, 10, 0), flight.getDepartureTime());
        assertEquals(LocalDateTime.of(2024, 11, 5, 12, 0), flight.getArrivalTime());
        assertEquals(100, flight.getTotalSeats());
        assertEquals(50, flight.getBookedSeats());
        assertEquals(150.0, flight.getCurrentTicketPrice());
        assertEquals(100.0, flight.getBaseTicketPrice());
        assertEquals(airline, flight.getAirline());
        assertEquals(route, flight.getRoute());
    }

    @Test
    public void testFlightDefaultConstructor() {
        // Create an instance using the default constructor
        Flight flight = new Flight();

        // Verify that fields are initially null or default values
        assertNull(flight.getId());
        assertNull(flight.getFlightNumber());
        assertNull(flight.getDepartureTime());
        assertNull(flight.getArrivalTime());
        assertEquals(0, flight.getTotalSeats());
        assertEquals(0, flight.getBookedSeats());
        assertEquals(0.0, flight.getCurrentTicketPrice());
        assertEquals(0.0, flight.getBaseTicketPrice());
        assertNull(flight.getAirline());
        assertNull(flight.getRoute());
    }

    @Test
    public void testFlightSettersAndGetters() {
        // Create an instance and use setters to assign values
        Flight flight = new Flight();
        flight.setId(2L);
        flight.setFlightNumber("FL456");
        flight.setDepartureTime(LocalDateTime.of(2024, 11, 6, 15, 0));
        flight.setArrivalTime(LocalDateTime.of(2024, 11, 6, 17, 0));
        flight.setTotalSeats(200);
        flight.setBookedSeats(100);
        flight.setCurrentTicketPrice(200.0);
        flight.setBaseTicketPrice(150.0);

        // Create sample Airline and Route instances
        Airline airline = new Airline();
        airline.setId(2L);
        airline.setName("Another Airline");
        airline.setCountry("Another Country");
        airline.setCode("AA");

        Route route = new Route();
        route.setId(2L);
        route.setCode("ROUTE456");

        flight.setAirline(airline);
        flight.setRoute(route);

        // Verify that getters return the expected values
        assertEquals(2L, flight.getId());
        assertEquals("FL456", flight.getFlightNumber());
        assertEquals(LocalDateTime.of(2024, 11, 6, 15, 0), flight.getDepartureTime());
        assertEquals(LocalDateTime.of(2024, 11, 6, 17, 0), flight.getArrivalTime());
        assertEquals(200, flight.getTotalSeats());
        assertEquals(100, flight.getBookedSeats());
        assertEquals(200.0, flight.getCurrentTicketPrice());
        assertEquals(150.0, flight.getBaseTicketPrice());
        assertEquals(airline, flight.getAirline());
        assertEquals(route, flight.getRoute());
    }
}
