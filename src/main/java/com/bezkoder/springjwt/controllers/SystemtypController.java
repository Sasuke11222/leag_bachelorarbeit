package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.Systemtyp;
import com.bezkoder.springjwt.repository.SystemtypRepository;
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
public class SystemtypController {
    @Autowired
    SystemtypRepository systemtypRepository;


    //geht
    @GetMapping("/systemtyp")
    public ResponseEntity<List<Systemtyp>> getAllSystemtypen(@RequestParam(required = false) String systemtyp_name) {
        try {
            List<Systemtyp> systemtyp= new ArrayList<Systemtyp>();

            if (systemtyp_name == null)
                systemtypRepository.findAll().forEach(systemtyp::add);

            if (systemtyp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(systemtyp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //geht
    @GetMapping("/systemtyp/{systemtyp_id}")
    public ResponseEntity<Systemtyp> getSystemtypById(@PathVariable("systemtyp_id") long systemtyp_id) {
        Optional<Systemtyp> SystemtypData = systemtypRepository.findById(systemtyp_id);

        if (SystemtypData.isPresent()) {
            return new ResponseEntity<>(SystemtypData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/systemtyp")
    public ResponseEntity<Systemtyp> createSystemtyp(@RequestBody Systemtyp systemtyp) {
        try {
            Systemtyp _systemtyp = systemtypRepository.save(new Systemtyp(systemtyp.getSystemtyp_name()));
            return new ResponseEntity<>(_systemtyp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/systemtyp/{systemtyp_id}")
    public ResponseEntity<Systemtyp> updateSystemtyp(@PathVariable("systemtyp_id") long systemtyp_id, @RequestBody Systemtyp systemtyp) {
        Optional<Systemtyp> SystemtypData = systemtypRepository.findById(systemtyp_id);

        if (SystemtypData.isPresent()) {
            Systemtyp _systemtyp = SystemtypData.get();
            _systemtyp.setSystemtyp_name(systemtyp.getSystemtyp_name());
            return new ResponseEntity<>(systemtypRepository.save(_systemtyp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/systemtyp/{systemtyp_id}")
    public ResponseEntity<HttpStatus> deleteSystemtyp(@PathVariable("systemtyp_id") long systemtyp_id) {
        try {
            systemtypRepository.deleteById(systemtyp_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

