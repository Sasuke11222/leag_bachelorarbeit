package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Kraftwerk;
import com.bezkoder.springjwt.models.Systemhersteller;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * systemherstellerRepository
 */
public interface SystemherstellerRepository extends JpaRepository<Systemhersteller, Long> {
    @Query(value= "SELECT * FROM SYSTEMHERSTELLER WHERE HERSTELLERNAME = ?1", nativeQuery = true)
    Systemhersteller findByNameContaining(String herstellername);

    @Query(value= "SELECT * FROM SYSTEMHERSTELLER WHERE KW_ID = ?1", nativeQuery = true)
    List<Systemhersteller> findAllByKwId(Kraftwerk kw_id);
}
