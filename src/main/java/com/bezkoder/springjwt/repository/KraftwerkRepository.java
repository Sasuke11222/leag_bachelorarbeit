package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Kraftwerk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * kraftwerkeRepository
 */
public interface KraftwerkRepository extends JpaRepository<Kraftwerk, Long> {
    @Query(value= "SELECT * FROM KRAFTWERKE WHERE kraftwerk_name = ?1", nativeQuery = true)
    Kraftwerk findByNameContaining(String kraftwerk_name);
}