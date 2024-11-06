package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.entity.Airport;
import com.OmreonStudyCase.OSCProject.services.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    private Airport airport;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        airport = new Airport();
        airport.setId(1L);
        airport.setCode("XYZ");
        airport.setName("Test Airport");
    }

    @Test
    public void testAddAirport() {
        // Arrange
        when(airportService.addAirport(airport)).thenReturn(airport);

        // Act
        ResponseEntity<Airport> response = airportController.addAirport(airport);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(airport.getCode(), response.getBody().getCode());
        verify(airportService, times(1)).addAirport(airport);
    }

    @Test
    public void testGetAllAirports() {
        // Arrange
        List<Airport> airports = Arrays.asList(airport);
        when(airportService.getAllAirports()).thenReturn(airports);

        // Act
        ResponseEntity<List<Airport>> response = airportController.getAllAirports();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(airport.getId(), response.getBody().get(0).getId());
        verify(airportService, times(1)).getAllAirports();
    }

    @Test
    public void testGetAirportById() {
        // Arrange
        when(airportService.getAirportById(1L)).thenReturn(Optional.of(airport));

        // Act
        ResponseEntity<Airport> response = airportController.getAirportById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airport.getId(), response.getBody().getId());
        verify(airportService, times(1)).getAirportById(1L);
    }

    @Test
    public void testGetAirportById_NotFound() {
        // Arrange
        when(airportService.getAirportById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Airport> response = airportController.getAirportById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(airportService, times(1)).getAirportById(1L);
    }

    @Test
    public void testGetAirportByCode() {
        // Arrange
        when(airportService.getAirportByCode("XYZ")).thenReturn(airport);

        // Act
        Airport result = airportController.getAirportByCode("XYZ");

        // Assert
        assertNotNull(result);
        assertEquals(airport.getCode(), result.getCode());
        verify(airportService, times(1)).getAirportByCode("XYZ");
    }
}
