package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Status_RJ45;
import com.bezkoder.springjwt.repository.Status_RJ45Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class Status_RJ45Controller {

    @Autowired
    Status_RJ45Repository status_rj45Repository;

    @GetMapping("/status_rj45")
    public ResponseEntity<List<Status_RJ45>> getAllRJ45statuse(@RequestParam(required = false) String status) {
        try {
            List<Status_RJ45> status_rj45= new ArrayList<Status_RJ45>();

            if (status == null)
                status_rj45Repository.findAll().forEach(status_rj45::add);

            if (status_rj45.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(status_rj45, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status_rj45/{status_rj45_id}")
    public ResponseEntity<Status_RJ45> getRJ45statusById(@PathVariable("status_rj45_id") long status_rj45_id) {
        Optional<Status_RJ45> Status_RJ45Data = status_rj45Repository.findById(status_rj45_id);

        if (Status_RJ45Data.isPresent()) {
            return new ResponseEntity<>(Status_RJ45Data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/status_rj45")
    public ResponseEntity<Status_RJ45> createRJ45status(@RequestBody Status_RJ45 status_rj45) {
        try {
            Status_RJ45 _status_rj45 = status_rj45Repository.save(new Status_RJ45(status_rj45.getStatus()));
            return new ResponseEntity<>(_status_rj45, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status_rj45/{status_rj45_id}")
    public ResponseEntity<Status_RJ45> updateRJ45status(@PathVariable("status_rj45_id") long status_rj45_id, @RequestBody Status_RJ45 status_firewall) {
        Optional<Status_RJ45> Status_RJ45Data = status_rj45Repository.findById(status_rj45_id);

        if (Status_RJ45Data.isPresent()) {
            Status_RJ45 _status_rj45 = Status_RJ45Data.get();
            _status_rj45.setStatus(status_firewall.getStatus());
            return new ResponseEntity<>(status_rj45Repository.save(_status_rj45), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/status_rj45/{status_rj45_id}")
    public ResponseEntity<HttpStatus> deleteRJ45status(@PathVariable("status_rj45_id") long status_rj45_id) {
        try {
            status_rj45Repository.deleteById(status_rj45_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


