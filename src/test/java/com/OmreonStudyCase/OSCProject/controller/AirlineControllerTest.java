package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.entity.Airline;
import com.OmreonStudyCase.OSCProject.services.AirlineService;
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

public class AirlineControllerTest {

    @Mock
    private AirlineService airlineService;

    @InjectMocks
    private AirlineController airlineController;

    private Airline airline;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        airline = new Airline();
        airline.setId(1L);
        airline.setCode("AA123");
        airline.setName("Airline Example");
    }

    @Test
    public void testGetAllAirlines() {
        // Arrange
        List<Airline> airlineList = Arrays.asList(airline);
        when(airlineService.getAllAirlines()).thenReturn(airlineList);

        // Act
        List<Airline> result = airlineController.getAllAirlines();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(airline.getId(), result.get(0).getId());
        verify(airlineService, times(1)).getAllAirlines();
    }

    @Test
    public void testAddAirline() {
        // Arrange
        when(airlineService.addAirline(airline)).thenReturn(airline);

        // Act
        Airline result = airlineController.addAirline(airline);

        // Assert
        assertNotNull(result);
        assertEquals(airline.getCode(), result.getCode());
        assertEquals(airline.getName(), result.getName());
        verify(airlineService, times(1)).addAirline(airline);
    }

    @Test
    public void testGetAirlineById() {
        // Arrange
        when(airlineService.getAirlineById(1L)).thenReturn(airline);

        // Act
        Airline result = airlineController.getAirlineById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(airline.getId(), result.getId());
        verify(airlineService, times(1)).getAirlineById(1L);
    }

    @Test
    public void testGetAirlineById_NotFound() {
        // Arrange
        when(airlineService.getAirlineById(1L)).thenReturn(null);

        // Act
        Airline result = airlineController.getAirlineById(1L);

        // Assert
        assertNull(result);
        verify(airlineService, times(1)).getAirlineById(1L);
    }

    @Test
    public void testGetAirlineByCode() {
        // Arrange
        when(airlineService.getAirlineByCode("AA123")).thenReturn(airline);

        // Act
        Airline result = airlineController.getAirlineByCode("AA123");

        // Assert
        assertNotNull(result);
        assertEquals(airline.getCode(), result.getCode());
        verify(airlineService, times(1)).getAirlineByCode("AA123");
    }

    @Test
    public void testGetAirlineByCode_NotFound() {
        // Arrange
        when(airlineService.getAirlineByCode("AA123")).thenReturn(null);

        // Act
        Airline result = airlineController.getAirlineByCode("AA123");

        // Assert
        assertNull(result);
        verify(airlineService, times(1)).getAirlineByCode("AA123");
    }
}
