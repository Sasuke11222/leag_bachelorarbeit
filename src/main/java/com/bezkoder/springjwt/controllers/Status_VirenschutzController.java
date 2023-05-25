package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Status_Virenschutz;
import com.bezkoder.springjwt.repository.Status_VirenschutzRepository;
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
public class Status_VirenschutzController {

    @Autowired
    Status_VirenschutzRepository status_virenschutzRepository;

    @GetMapping("/status_virenschutz")
    public ResponseEntity<List<Status_Virenschutz>> getAllVirenschutzstatuse(@RequestParam(required = false) String status) {
        try {
            List<Status_Virenschutz> virenschutz_status= new ArrayList<Status_Virenschutz>();

            if (status == null)
                status_virenschutzRepository.findAll().forEach(virenschutz_status::add);

            if (virenschutz_status.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(virenschutz_status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status_virenschutz/{status_virenschutz_id}")
    public ResponseEntity<Status_Virenschutz> getVirenschutzstatusById(@PathVariable("status_virenschutz_id") long status_virenschutz_id) {
        Optional<Status_Virenschutz> Status_VirenschutzData = status_virenschutzRepository.findById(status_virenschutz_id);

        if (Status_VirenschutzData.isPresent()) {
            return new ResponseEntity<>(Status_VirenschutzData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/status_virenschutz")
    public ResponseEntity<Status_Virenschutz> createVirenschutzstatus(@RequestBody Status_Virenschutz virenschutz_status) {
        try {
            Status_Virenschutz _status_virenschutz = status_virenschutzRepository.save(new Status_Virenschutz(virenschutz_status.getStatus()));
            return new ResponseEntity<>(_status_virenschutz, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status_virenschutz/{status_virenschutz_id}")
    public ResponseEntity<Status_Virenschutz> updateVirenschutzstatus(@PathVariable("status_virenschutz_id") long status_virenschutz_id, @RequestBody Status_Virenschutz status_virenschutz) {
        Optional<Status_Virenschutz> Status_VirenschutzData = status_virenschutzRepository.findById(status_virenschutz_id);

        if (Status_VirenschutzData.isPresent()) {
            Status_Virenschutz _status_virenschutz = Status_VirenschutzData.get();
            _status_virenschutz.setStatus(status_virenschutz.getStatus());
            return new ResponseEntity<>(status_virenschutzRepository.save(_status_virenschutz), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/status_virenschutz/{status_virenschutz_id}")
    public ResponseEntity<HttpStatus> deleteVirenschutzstatus(@PathVariable("status_virenschutz_id") long status_virenschutz_id) {
        try {
            status_virenschutzRepository.deleteById(status_virenschutz_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


