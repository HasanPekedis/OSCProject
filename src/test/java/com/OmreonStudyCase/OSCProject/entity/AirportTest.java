package com.OmreonStudyCase.OSCProject.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @Test
    public void testAirportEntity() {
        // Create an instance using the parameterized constructor
        Airport airport = new Airport("Test Airport", "TST", "Test City", "Test Country");

        // Verify initial values set by the constructor
        assertNull(airport.getId()); // Since the ID is auto-generated and not set in the constructor
        assertEquals("Test Airport", airport.getName());
        assertEquals("TST", airport.getCode());
        assertEquals("Test City", airport.getCity());
        assertEquals("Test Country", airport.getCountry());
    }

    @Test
    public void testAirportDefaultConstructor() {
        // Create an instance using the default constructor
        Airport airport = new Airport();

        // Verify that fields are initially null
        assertNull(airport.getId());
        assertNull(airport.getName());
        assertNull(airport.getCode());
        assertNull(airport.getCity());
        assertNull(airport.getCountry());
    }

    @Test
    public void testAirportSettersAndGetters() {
        // Create an instance and use setters to assign values
        Airport airport = new Airport();
        
        airport.setId(1L);
        airport.setName("New Airport");
        airport.setCode("NEW");
        airport.setCity("New City");
        airport.setCountry("New Country");
        
        // Verify that getters return the expected values
        assertEquals(1L, airport.getId());
        assertEquals("New Airport", airport.getName());
        assertEquals("NEW", airport.getCode());
        assertEquals("New City", airport.getCity());
        assertEquals("New Country", airport.getCountry());
    }
}
