package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Virenschutzhersteller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * VirenschutzherstellerRepository
 */
public interface VirenschutzherstellerRepository extends JpaRepository<Virenschutzhersteller, Long> {
    @Query(value= "SELECT * FROM VIRENSCHUTZHERSTELLER WHERE HERSTELLERNAME = ?1", nativeQuery = true)
    Virenschutzhersteller findByNameContaining(String herstellername);
}