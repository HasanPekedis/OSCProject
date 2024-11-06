package com.OmreonStudyCase.OSCProject.repository;

import com.OmreonStudyCase.OSCProject.entity.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AirportRepositoryTest {

    @Autowired
    private AirportRepository airportRepository;

    @BeforeEach
    public void setUp() {
        // Create and save a sample Airport instance before each test
        Airport airport = new Airport();
        airport.setCode("AP123");
        airport.setName("Sample Airport");
        airport.setCity("Sample City");
        airport.setCountry("Sample Country");
        airportRepository.save(airport);
    }

    @Test
    public void testFindByCode() {
        // Attempt to find the airport by its code
        Airport foundAirport = airportRepository.findByCode("AP123");

        // Verify that the returned airport is not null and has the correct code and name
        assertNotNull(foundAirport);
        assertEquals("AP123", foundAirport.getCode());
        assertEquals("Sample Airport", foundAirport.getName());
        assertEquals("Sample City", foundAirport.getCity());
        assertEquals("Sample Country", foundAirport.getCountry());
    }

    @Test
    public void testFindByCode_NotFound() {
        // Attempt to find an airport with a code that doesn't exist
        Airport foundAirport = airportRepository.findByCode("NONEXISTENT");

        // Verify that the returned value is null
        assertNull(foundAirport);
    }
}
