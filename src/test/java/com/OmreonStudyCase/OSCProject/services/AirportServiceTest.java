package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.entity.Airport;
import com.OmreonStudyCase.OSCProject.repository.AirportRepository;
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

public class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAirport() {
        // Arrange
        Airport airport = new Airport();
        airport.setCode("JFK");
        airport.setName("John F. Kennedy International Airport");

        when(airportRepository.save(airport)).thenReturn(airport);

        // Act
        Airport savedAirport = airportService.addAirport(airport);

        // Assert
        assertNotNull(savedAirport);
        assertEquals("JFK", savedAirport.getCode());
        assertEquals("John F. Kennedy International Airport", savedAirport.getName());
        verify(airportRepository, times(1)).save(airport);
    }

    @Test
    public void testGetAllAirports() {
        // Arrange
        Airport airport1 = new Airport();
        airport1.setCode("JFK");
        airport1.setName("John F. Kennedy International Airport");

        Airport airport2 = new Airport();
        airport2.setCode("LAX");
        airport2.setName("Los Angeles International Airport");

        when(airportRepository.findAll()).thenReturn(Arrays.asList(airport1, airport2));

        // Act
        List<Airport> airports = airportService.getAllAirports();

        // Assert
        assertEquals(2, airports.size());
        assertEquals("JFK", airports.get(0).getCode());
        assertEquals("LAX", airports.get(1).getCode());
    }

    @Test
    public void testGetAirportById() {
        // Arrange
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCode("JFK");
        airport.setName("John F. Kennedy International Airport");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));

        // Act
        Optional<Airport> foundAirport = airportService.getAirportById(1L);

        // Assert
        assertTrue(foundAirport.isPresent());
        assertEquals(1L, foundAirport.get().getId());
        assertEquals("JFK", foundAirport.get().getCode());
    }

    @Test
    public void testGetAirportByCode() {
        // Arrange
        Airport airport = new Airport();
        airport.setCode("LAX");
        airport.setName("Los Angeles International Airport");

        when(airportRepository.findByCode("LAX")).thenReturn(airport);

        // Act
        Airport foundAirport = airportService.getAirportByCode("LAX");

        // Assert
        assertNotNull(foundAirport);
        assertEquals("LAX", foundAirport.getCode());
        assertEquals("Los Angeles International Airport", foundAirport.getName());
    }
}
