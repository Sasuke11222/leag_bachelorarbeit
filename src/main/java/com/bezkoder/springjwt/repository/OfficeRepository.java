package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * officeRepository
 */
public interface OfficeRepository extends JpaRepository<Office, Long> {
    @Query(value= "SELECT * FROM OFFICE WHERE VERSION = ?1", nativeQuery = true)
    Office findByNameContaining(String version);
}