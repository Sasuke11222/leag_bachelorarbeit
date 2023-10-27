package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Kraftwerk;
import com.bezkoder.springjwt.models.Systeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * systemRepository
 */
public interface SystemRepository extends JpaRepository<Systeme, Long> {

    @Query(value= "SELECT * FROM SYSTEME WHERE SYSTEM_NAME = ?1", nativeQuery = true)
    Systeme findByNameContaining(String system_name);

    @Query(value= "SELECT * FROM SYSTEME WHERE KW_ID = ?1", nativeQuery = true)
    List<Systeme> findAllByKwId(Kraftwerk kw_id);

    @Query(value= "SELECT * FROM SYSTEME WHERE KW_ID = ?1 AND  SysTEM_ID =?1", nativeQuery = true)
    List<Systeme> findAllByKwIdAndSysemId(Kraftwerk kw_id, Long system_id);
}
