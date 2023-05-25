package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Systemeinheit;
import com.bezkoder.springjwt.repository.SystemeinheitRepository;
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
public class SystemeinheitController {
    @Autowired
    SystemeinheitRepository systemeinheitRepository;

    //geht
    @GetMapping("/systemeinheit")
    public ResponseEntity<List<Systemeinheit>> getAllSystemeinheiten(@RequestParam(required = false) String systemeinheit_name) {
        try {
            List<Systemeinheit> systemeinheit= new ArrayList<Systemeinheit>();

            if (systemeinheit_name == null)
                systemeinheitRepository.findAll().forEach(systemeinheit::add);

            if (systemeinheit.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(systemeinheit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //geht
    @GetMapping("/systemeinheit/{systemeinheit_id}")
    public ResponseEntity<Systemeinheit> getSystemeinheitById(@PathVariable("systemeinheit_id") long systemeinheit_id) {
        Optional<Systemeinheit> SystemeinheitData = systemeinheitRepository.findById(systemeinheit_id);

        if (SystemeinheitData.isPresent()) {
            return new ResponseEntity<>(SystemeinheitData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/systemeinheit")
    public ResponseEntity<Systemeinheit> createSystemeinheit(@RequestBody Systemeinheit systemeinheit) {
        try {
            Systemeinheit _systemeinheit = systemeinheitRepository.save(new Systemeinheit(systemeinheit.getSystemeinheit_name()));
            return new ResponseEntity<>(_systemeinheit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/systemeinheit/{systemeinheit_id}")
    public ResponseEntity<Systemeinheit> updateSystemeinheit(@PathVariable("systemeinheit_id") long systemeinheit_id, @RequestBody Systemeinheit systemeinheit) {
        Optional<Systemeinheit> SystemeinheitData = systemeinheitRepository.findById(systemeinheit_id);

        if (SystemeinheitData.isPresent()) {
            Systemeinheit _systemeinheit = SystemeinheitData.get();
            _systemeinheit.setSystemeinheit_name(systemeinheit.getSystemeinheit_name());
            return new ResponseEntity<>(systemeinheitRepository.save(_systemeinheit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/systemeinheit/{systemeinheit_id}")
    public ResponseEntity<HttpStatus> deleteSystemeinheit(@PathVariable("systemeinheit_id") long systemeinheit_id) {
        try {
            systemeinheitRepository.deleteById(systemeinheit_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

