package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Systemhersteller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * systemherstellerRepository
 */
public interface SystemherstellerRepository extends JpaRepository<Systemhersteller, Long> {
    @Query(value= "SELECT * FROM SYSTEMHERSTELLER WHERE HERSTELLERNAME = ?1", nativeQuery = true)
    Systemhersteller findByNameContaining(String herstellername);

    @Query(value= "SELECT * FROM SYSTEMHERSTELLER WHERE HERSTELLERNAME = ?1", nativeQuery = true)
    Systemhersteller findByName(String herstellername);
}
