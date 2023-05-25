package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Betriebssystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * BetriebssystemRepository
 */
public interface BetriebssystemRepository extends JpaRepository<Betriebssystem, Long> {
    @Query(value= "SELECT * FROM BETRIEBSSYSTEM WHERE BETRIEBSSYSTEM_NAME = ?1", nativeQuery = true)
    Betriebssystem findByNameContaining(String betriebssystem_name);

    @Query(value= "SELECT * FROM BETRIEBSSYSTEM WHERE BETRIEBSSYSTEM_NAME = ?1", nativeQuery = true)
    Betriebssystem findByName(String betriebssystem_name);
}
