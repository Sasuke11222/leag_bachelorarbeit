package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Status_Firewall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * status_firewallRepository
 */
public interface Status_FirewallRepository extends JpaRepository<Status_Firewall, Long> {
    @Query(value= "SELECT * FROM STATUS_FIREWALL WHERE STATUS = ?1", nativeQuery = true)
    Status_Firewall findByNameContaining(String status);
}