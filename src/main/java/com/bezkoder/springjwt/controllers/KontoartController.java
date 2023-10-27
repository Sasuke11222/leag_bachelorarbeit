package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Kontoart;
import com.bezkoder.springjwt.repository.KontoartRepository;

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
public class KontoartController {
    @Autowired
    KontoartRepository kontoartRepository;


    @GetMapping("/kontoart")
    public ResponseEntity<List<Kontoart>> getAllKontoarten(@RequestParam(required = false) String kontoart) {
        try {
            List<Kontoart> kontoa= new ArrayList<Kontoart>();

            if (kontoart == null)
                kontoartRepository.findAll().forEach(kontoa::add);

            if (kontoa.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(kontoa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/kontoart/{kontoart_id}")
    public ResponseEntity<Kontoart> getKontoartById(@PathVariable("kontoart_id") long kontoart_id) {
        Optional<Kontoart> KontoartData = kontoartRepository.findById(kontoart_id);

        if (KontoartData.isPresent()) {
            return new ResponseEntity<>(KontoartData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //erstellt eine neue Kontoart
    @PostMapping("/kontoart")
    public ResponseEntity<Kontoart> createKontoart(@RequestBody Kontoart kontoart) {
        Optional<Kontoart> existingKontoart = Optional.ofNullable(kontoartRepository.findByNameContaining(kontoart.getKontoart()));

        if(existingKontoart.isPresent()) {
            Kontoart _kontoart = existingKontoart.get();

            _kontoart.setKontoart_id(kontoart.getKontoart_id());
            _kontoart.setKontoart(kontoart.getKontoart());

            kontoartRepository.save(_kontoart);
            return new ResponseEntity<>(_kontoart, HttpStatus.OK);
        }

        Kontoart _kontoart = kontoartRepository.save(new Kontoart(
                kontoart.getKontoart()));
        return new ResponseEntity<>(_kontoart, HttpStatus.CREATED);
    }

    @PutMapping("/kontoart/{kontoart_id}")
    public ResponseEntity<Kontoart> updateKontoart(@PathVariable("kontoart_id") long kontoart_id, @RequestBody Kontoart kontoart) {
        Optional<Kontoart> KontoartData = kontoartRepository.findById(kontoart_id);

        if (KontoartData.isPresent()) {
            Kontoart _kontoart = KontoartData.get();
            _kontoart.setKontoart(kontoart.getKontoart());
            return new ResponseEntity<>(kontoartRepository.save(_kontoart), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/kontoart/{kontoart_id}")
    public ResponseEntity<HttpStatus> deleteKontoart(@PathVariable("kontoart_id") long kontoart_id) {
        try {
            kontoartRepository.deleteById(kontoart_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
