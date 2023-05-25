package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Kontoart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * kontoartRepository
 */
public interface KontoartRepository extends JpaRepository<Kontoart, Long> {
    @Query(value= "SELECT * FROM KONTOART WHERE KONTOART = ?1", nativeQuery = true)
    Kontoart findByNameContaining(String kontoart);
}
