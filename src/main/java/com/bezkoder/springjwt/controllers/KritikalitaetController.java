package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Kritikalitaet;
import com.bezkoder.springjwt.repository.KritikalitaetRepository;
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
public class KritikalitaetController {

    @Autowired
    KritikalitaetRepository kritikalitaetRepository;

    @GetMapping("/kritikalitaet")
    public ResponseEntity<List<Kritikalitaet>> getAllKritikalitaeten(@RequestParam(required = false) String kritikalitaet_name) {
        try {
            List<Kritikalitaet> kritikalitaet= new ArrayList<Kritikalitaet>();

            if (kritikalitaet_name == null)
                kritikalitaetRepository.findAll().forEach(kritikalitaet::add);

            if (kritikalitaet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(kritikalitaet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/kritikalitaet/{kritikalitaet_id}")
    public ResponseEntity<Kritikalitaet> getKritikalitaetById(@PathVariable("kritikalitaet_id") long kritikalitaet_id) {
        Optional<Kritikalitaet> KritikalitaetData = kritikalitaetRepository.findById(kritikalitaet_id);

        if (KritikalitaetData.isPresent()) {
            return new ResponseEntity<>(KritikalitaetData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //erstellt eine neue Kritikalitaet
    @PostMapping("/kritikalitaet")
    public ResponseEntity<Kritikalitaet> createKritikalitaet(@RequestBody Kritikalitaet kritikalitaet) {
        try {
            Kritikalitaet _kritikalitaet = kritikalitaetRepository.save(new Kritikalitaet(
                    kritikalitaet.getKritikalitaet_name()));
            return new ResponseEntity<>(_kritikalitaet, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/kritikalitaet/{kritikalitaet_id}")
    public ResponseEntity<Kritikalitaet> updateBetriebssystem(@PathVariable("kritikalitaet_id") long kritikalitaet_id, @RequestBody Kritikalitaet kritikalitaet) {
        Optional<Kritikalitaet> KritikalitaetData = kritikalitaetRepository.findById(kritikalitaet_id);

        if (KritikalitaetData.isPresent()) {
            Kritikalitaet _kritikalitaet = KritikalitaetData.get();
            _kritikalitaet.setKritikalitaet_name(kritikalitaet.getKritikalitaet_name());
            return new ResponseEntity<>(kritikalitaetRepository.save(_kritikalitaet), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/kritikalitaet/{kritikalitaet_id}")
    public ResponseEntity<HttpStatus> deleteKritikalitaet(@PathVariable("kritikalitaet_id") long kritikalitaet_id) {
        try {
            kritikalitaetRepository.deleteById(kritikalitaet_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

