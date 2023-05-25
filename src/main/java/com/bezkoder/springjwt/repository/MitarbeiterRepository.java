package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * MitarbeiterRepository
 */
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {
    @Query(value= "SELECT * FROM MITARBEITER WHERE MITARBEITER_ID = ?1", nativeQuery = true)
    Mitarbeiter findByNameContaining(Long mitarbeiter_id);
}