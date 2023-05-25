package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.Nutzermanagment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * NutzermanagementRepository
 */
public interface NutzermanagmentRepository extends JpaRepository<Nutzermanagment, Long> {
    @Query(value= "SELECT * FROM NUTZERMANAGEMENT WHERE ID = ?1", nativeQuery = true)
    Nutzermanagment findByNameContaining(String ID);

    @Query (value= "SELECT passwort FROM NUTZERMANAGEMENT WHERE passwort = ?1", nativeQuery = true)
    Nutzermanagment findByPasswort(String passwort);

    @Query (value= "SELECT loginname FROM NUTZERMANAGEMENT WHERE loginname = ?1", nativeQuery = true)
    Nutzermanagment findByLoginname(String loginname);
}