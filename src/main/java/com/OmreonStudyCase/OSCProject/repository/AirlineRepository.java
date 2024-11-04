package com.OmreonStudyCase.OSCProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OmreonStudyCase.OSCProject.entity.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Airline findByCode(String code);
}

