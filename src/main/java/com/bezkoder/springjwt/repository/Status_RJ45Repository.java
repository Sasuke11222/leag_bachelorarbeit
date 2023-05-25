package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Status_RJ45;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * status_rj45Repository
 */
public interface Status_RJ45Repository extends JpaRepository<Status_RJ45, Long> {
    @Query(value= "SELECT * FROM STATUS_RJ45 WHERE STATUS = ?1", nativeQuery = true)
    Status_RJ45 findByNameContaining(String status);
}