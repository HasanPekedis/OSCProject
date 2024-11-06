package com.OmreonStudyCase.OSCProject.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {

    @Test
    public void testRouteEntity() {
        // Create sample Airport instances for departure and destination
        Airport departureAirport = new Airport();
        departureAirport.setId(1L);
        departureAirport.setName("Departure Airport");
        departureAirport.setCode("DPA");
        departureAirport.setCity("City A");
        departureAirport.setCountry("Country A");

        Airport destinationAirport = new Airport();
        destinationAirport.setId(2L);
        destinationAirport.setName("Destination Airport");
        destinationAirport.setCode("DES");
        destinationAirport.setCity("City B");
        destinationAirport.setCountry("Country B");

        // Create an instance of Route
        Route route = new Route();
        route.setId(1L);
        route.setCode("ROUTE123");
        route.setDeparture(departureAirport);
        route.setDestination(destinationAirport);

        // Verify that the getters return the expected values
        assertEquals(1L, route.getId());
        assertEquals("ROUTE123", route.getCode());
        assertEquals(departureAirport, route.getDeparture());
        assertEquals(destinationAirport, route.getDestination());
    }

    @Test
    public void testRouteDefaultConstructor() {
        // Create an instance using the default constructor
        Route route = new Route();

        // Verify that fields are initially null or default values
        assertNull(route.getId());
        assertNull(route.getCode());
        assertNull(route.getDeparture());
        assertNull(route.getDestination());
    }

    @Test
    public void testRouteSettersAndGetters() {
        // Create an instance and use setters to assign values
        Route route = new Route();
        route.setId(3L);
        route.setCode("ROUTE456");

        // Create sample Airport instances for departure and destination
        Airport departureAirport = new Airport();
        departureAirport.setId(3L);
        departureAirport.setName("Test Departure");
        departureAirport.setCode("TDEP");
        departureAirport.setCity("City C");
        departureAirport.setCountry("Country C");

        Airport destinationAirport = new Airport();
        destinationAirport.setId(4L);
        destinationAirport.setName("Test Destination");
        destinationAirport.setCode("TDES");
        destinationAirport.setCity("City D");
        destinationAirport.setCountry("Country D");

        route.setDeparture(departureAirport);
        route.setDestination(destinationAirport);

        // Verify that getters return the expected values
        assertEquals(3L, route.getId());
        assertEquals("ROUTE456", route.getCode());
        assertEquals(departureAirport, route.getDeparture());
        assertEquals(destinationAirport, route.getDestination());
    }
}
