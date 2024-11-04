package com.OmreonStudyCase.OSCProject.repository;

import com.OmreonStudyCase.OSCProject.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByCode(String code);
}