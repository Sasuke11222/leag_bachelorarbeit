package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Kontotyp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * kontotypRepository
 */
public interface KontotypRepository extends JpaRepository<Kontotyp, Long> {
    @Query(value= "SELECT * FROM KONTOTYP WHERE KONTOTYP = ?1", nativeQuery = true)
    Kontotyp findByNameContaining(String KONTOTYP);
}
