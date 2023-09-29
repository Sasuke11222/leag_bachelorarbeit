package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Kraftwerk;

import com.bezkoder.springjwt.repository.KraftwerkRepository;
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
public class KraftwerkController {
    @Autowired
    KraftwerkRepository kraftwerkRepository;


    @GetMapping("/kraftwerke")
    public ResponseEntity<List<Kraftwerk>> getAllKraftwerke(@RequestParam(required = false) String kraftwerk_name) {
        try {
            List<Kraftwerk> kraftwerke= new ArrayList<Kraftwerk>();

            if (kraftwerk_name == null)
                kraftwerkRepository.findAll().forEach(kraftwerke::add);

            if (kraftwerke.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(kraftwerke, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/kraftwerke/{kw_id}")
    public ResponseEntity<Kraftwerk> getKraftwerkById(@PathVariable("kw_id") long kw_id) {
        Optional<Kraftwerk> KraftwerkData = kraftwerkRepository.findById(kw_id);

        if (KraftwerkData.isPresent()) {
            return new ResponseEntity<>(KraftwerkData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/kraftwerke")
    public ResponseEntity<Kraftwerk> createKraftwerk(@RequestBody Kraftwerk kraftwerk) {
        Optional<Kraftwerk> existingKraftwerk = Optional.ofNullable(kraftwerkRepository.findByNameContaining(kraftwerk.getKraftwerk_name()));

        if(existingKraftwerk.isPresent()) {
            Kraftwerk _kraftwerk = existingKraftwerk.get();
            _kraftwerk.setKraftwerksleiter(kraftwerk.getKraftwerksleiter());
            _kraftwerk.setZoneninstanzbesitzer(kraftwerk.getZoneninstanzbesitzer());
            _kraftwerk.setSystemkoordinator(kraftwerk.getSystemkoordinator());

            kraftwerkRepository.save(_kraftwerk);
            return new ResponseEntity<>(_kraftwerk, HttpStatus.OK);
        }

        Kraftwerk _kraftwerk = kraftwerkRepository.save(new Kraftwerk(
                kraftwerk.getKw_id(),
                kraftwerk.getKraftwerk_name(),
                kraftwerk.getKraftwerksleiter(),
                kraftwerk.getZoneninstanzbesitzer(),
                kraftwerk.getSystemkoordinator()));
        return new ResponseEntity<>(_kraftwerk, HttpStatus.CREATED);
    }

    /*@PutMapping("/kraftwerke/{kw_id}")
    public ResponseEntity<Kraftwerk> updateKraftwerk(@PathVariable("kw_id") long kw_id, @RequestBody Kraftwerk kraftwerk) {
        Optional<Kraftwerk> KraftwerkData = kraftwerkRepository.findById(kw_id);

        if (KraftwerkData.isPresent()) {
            Kraftwerk _kraftwerk = KraftwerkData.get();
            _kraftwerk.setKraftwerk_name(kraftwerk.getKraftwerk_name());
            _kraftwerk.setKraftwerksleiter(kraftwerk.getKraftwerksleiter());
            _kraftwerk.setZoneninstanzbesitzer(kraftwerk.getZoneninstanzbesitzer());
            _kraftwerk.setSystemkoordinator(kraftwerk.getSystemkoordinator());
            return new ResponseEntity<>(kraftwerkRepository.save(_kraftwerk), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     */

    @PutMapping("/kraftwerke/{kw_id}")
    public ResponseEntity<HttpStatus> updateKraftwerk(@PathVariable("kw_id") Long kw_id, @RequestBody Kraftwerk updatedKraftwerk) {
        Optional<Kraftwerk> kraftwerkOptional = kraftwerkRepository.findById(kw_id);
        if (kraftwerkOptional.isPresent()) {
            Kraftwerk kraftwerk = kraftwerkOptional.get();
            kraftwerk.setKraftwerk_name(updatedKraftwerk.getKraftwerk_name());
            kraftwerk.setKraftwerksleiter(updatedKraftwerk.getKraftwerksleiter());
            kraftwerk.setZoneninstanzbesitzer(updatedKraftwerk.getZoneninstanzbesitzer());
            kraftwerk.setSystemkoordinator(updatedKraftwerk.getSystemkoordinator());
            kraftwerkRepository.save(kraftwerk);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/kraftwerke/{kw_id}")
    public ResponseEntity<HttpStatus> deleteKraftwerk(@PathVariable("kw_id") long kw_id) {
        try {
            kraftwerkRepository.deleteById(kw_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

