package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Status_USB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * status_usbRepository
 */
public interface Status_USBRepository extends JpaRepository<Status_USB, Long> {
    @Query(value= "SELECT * FROM STATUS_USB WHERE STATUS = ?1", nativeQuery = true)
    Status_USB findByNameContaining(String status);
}