package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.IT_Element;
import com.bezkoder.springjwt.models.Kraftwerk;
import com.bezkoder.springjwt.models.Systeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * IT_ElementRepository
 */
public interface IT_ElementRepository extends JpaRepository<IT_Element, Long> {
    @Query(value= "SELECT * FROM IT_ELEMENT WHERE KKS = ?1", nativeQuery = true)
    IT_Element findByNameContaining(String kks);
    @Query(value= "SELECT * FROM IT_ELEMENT WHERE KW_ID = ?1", nativeQuery = true)
    List<IT_Element> findAllByKwId(Kraftwerk kw_id);

}