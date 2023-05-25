package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * zoneRepository
 */
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    @Query(value= "SELECT * FROM ZONE WHERE ZONE = ?1", nativeQuery = true)
    Zone findByNameContaining(String zone);

    @Query(value= "SELECT * FROM ZONE WHERE ZONEN_ID = ?1", nativeQuery = true)
    Zone findByNumber(int zonen_id);
}
