package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Systemtyp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * systemtypRepository
 */
public interface SystemtypRepository extends JpaRepository<Systemtyp, Long> {
    @Query(value= "SELECT * FROM SYSTEMTYP WHERE SYSTEMTYP_NAME = ?1", nativeQuery = true)
    Systemtyp findByNameContaining(String systemtyp_name);
}
