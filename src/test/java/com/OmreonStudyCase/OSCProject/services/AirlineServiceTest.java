package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.entity.Airline;
import com.OmreonStudyCase.OSCProject.repository.AirlineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineService airlineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAirlines() {
        // Arrange
        Airline airline1 = new Airline();
        airline1.setId(1L);
        airline1.setName("Airline One");

        Airline airline2 = new Airline();
        airline2.setId(2L);
        airline2.setName("Airline Two");

        when(airlineRepository.findAll()).thenReturn(Arrays.asList(airline1, airline2));

        // Act
        List<Airline> airlines = airlineService.getAllAirlines();

        // Assert
        assertEquals(2, airlines.size());
        assertEquals("Airline One", airlines.get(0).getName());
        assertEquals("Airline Two", airlines.get(1).getName());
    }

    @Test
    public void testAddAirline() {
        // Arrange
        Airline airline = new Airline();
        airline.setName("New Airline");

        when(airlineRepository.save(airline)).thenReturn(airline);

        // Act
        Airline savedAirline = airlineService.addAirline(airline);

        // Assert
        assertNotNull(savedAirline);
        assertEquals("New Airline", savedAirline.getName());
        verify(airlineRepository, times(1)).save(airline);
    }

    @Test
    public void testGetAirlineById() {
        // Arrange
        Airline airline = new Airline();
        airline.setId(1L);
        airline.setName("Airline One");

        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));

        // Act
        Airline foundAirline = airlineService.getAirlineById(1L);

        // Assert
        assertNotNull(foundAirline);
        assertEquals(1L, foundAirline.getId());
        assertEquals("Airline One", foundAirline.getName());
    }

    @Test
    public void testGetAirlineByCode() {
        // Arrange
        Airline airline = new Airline();
        airline.setCode("AL123");
        airline.setName("Airline Code Test");

        when(airlineRepository.findByCode("AL123")).thenReturn(airline);

        // Act
        Airline foundAirline = airlineService.getAirlineByCode("AL123");

        // Assert
        assertNotNull(foundAirline);
        assertEquals("AL123", foundAirline.getCode());
        assertEquals("Airline Code Test", foundAirline.getName());
    }
}
