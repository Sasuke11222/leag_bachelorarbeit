package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Systemeinheit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * systemeinheitRepository
 */
public interface SystemeinheitRepository extends JpaRepository<Systemeinheit, Long> {
    @Query(value= "SELECT * FROM SYSTEMEINHEIT WHERE SYSTEMEINHEIT_NAME = ?1", nativeQuery = true)
    Systemeinheit findByNameContaining(String systemeinheit_name);
}