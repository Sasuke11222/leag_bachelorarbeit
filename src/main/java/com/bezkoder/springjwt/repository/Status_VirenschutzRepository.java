package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Status_Virenschutz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * status_virenschutzRepository
 */
public interface Status_VirenschutzRepository extends JpaRepository<Status_Virenschutz, Long> {
    @Query(value= "SELECT * FROM STATUS_VIRENSCHUTZ WHERE STATUS = ?1", nativeQuery = true)
    Status_Virenschutz findByNameContaining(String status);
}