package com.OmreonStudyCase.OSCProject.repository;

import com.OmreonStudyCase.OSCProject.entity.Airline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AirlineRepositoryTest {

    @Autowired
    private AirlineRepository airlineRepository;

    @BeforeEach
    public void setUp() {
        // Create and save a sample Airline instance before each test
        Airline airline = new Airline();
        airline.setCode("AL123");
        airline.setName("Test Airline");
        airlineRepository.save(airline);
    }

    @Test
    public void testFindByCode() {
        // Attempt to find the airline by its code
        Airline foundAirline = airlineRepository.findByCode("AL123");

        // Verify that the returned airline is not null and has the correct code and name
        assertNotNull(foundAirline);
        assertEquals("AL123", foundAirline.getCode());
        assertEquals("Test Airline", foundAirline.getName());
    }

    @Test
    public void testFindByCode_NotFound() {
        // Attempt to find an airline with a code that doesn't exist
        Airline foundAirline = airlineRepository.findByCode("NONEXISTENT");

        // Verify that the returned value is null
        assertNull(foundAirline);
    }
}
