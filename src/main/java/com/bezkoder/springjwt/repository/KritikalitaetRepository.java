package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Kritikalitaet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * kritikalitaetsRepository
 */
public interface KritikalitaetRepository extends JpaRepository<Kritikalitaet, Long> {
    @Query(value= "SELECT * FROM KRITIKALITAET WHERE KRITIKALITAET_NAME = ?1", nativeQuery = true)
    Kritikalitaet findByNameContaining(String kritikalitaet_name);
}
