package com.OmreonStudyCase.OSCProject.repository;

import com.OmreonStudyCase.OSCProject.entity.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // İsteğe bağlı ek sorgular burada tanımlanabilir
}
