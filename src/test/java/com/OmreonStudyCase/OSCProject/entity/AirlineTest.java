package com.OmreonStudyCase.OSCProject.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirlineTest {

    @Test
    public void testAirlineEntity() {
        // Create an instance of Airline
        Airline airline = new Airline();
        
        // Set values using setter methods
        airline.setId(1L);
        airline.setName("Test Airline");
        airline.setCountry("Test Country");
        airline.setCode("TST123");
        
        // Verify values using getter methods
        assertEquals(1L, airline.getId());
        assertEquals("Test Airline", airline.getName());
        assertEquals("Test Country", airline.getCountry());
        assertEquals("TST123", airline.getCode());
    }

    @Test
    public void testAirlineDefaultConstructor() {
        // Create an instance of Airline using the default constructor
        Airline airline = new Airline();
        
        // Verify that fields are initially null or default values
        assertNull(airline.getId());
        assertNull(airline.getName());
        assertNull(airline.getCountry());
        assertNull(airline.getCode());
    }

    @Test
    public void testAirlineSettersAndGetters() {
        Airline airline = new Airline();
        
        airline.setId(2L);
        assertEquals(2L, airline.getId());
        
        airline.setName("New Airline");
        assertEquals("New Airline", airline.getName());
        
        airline.setCountry("New Country");
        assertEquals("New Country", airline.getCountry());
        
        airline.setCode("NEW456");
        assertEquals("NEW456", airline.getCode());
    }
}
