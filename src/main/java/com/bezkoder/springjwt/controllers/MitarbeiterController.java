package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Kraftwerk;
import com.bezkoder.springjwt.models.Mitarbeiter;
import com.bezkoder.springjwt.repository.MitarbeiterRepository;

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
public class MitarbeiterController {

    @Autowired
    MitarbeiterRepository mitarbeiterRepository;

    @GetMapping("/mitarbeiter")
    public ResponseEntity<List<Mitarbeiter>> getAllMitarbeiter(@RequestParam(required = false) String nachname) {
        try {
            List<Mitarbeiter> mitarbeiter= new ArrayList<Mitarbeiter>();

            if (nachname == null)
                mitarbeiterRepository.findAll().forEach(mitarbeiter::add);

            if (mitarbeiter.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(mitarbeiter, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mitarbeiter/{mitarbeiter_id}")
    public ResponseEntity<Mitarbeiter> getMitarbeiterById(@PathVariable("mitarbeiter_id") long mitarbeiter_id) {
        Optional<Mitarbeiter> MitarbeiterData = mitarbeiterRepository.findById(mitarbeiter_id);

        if (MitarbeiterData.isPresent()) {
            return new ResponseEntity<>(MitarbeiterData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/mitarbeiter")
    public ResponseEntity<Mitarbeiter> createMitarbeiter(@RequestBody Mitarbeiter mitarbeiter) {
        Optional<Mitarbeiter> existingMitarbeiter = Optional.ofNullable(mitarbeiterRepository.findByNameContaining(mitarbeiter.getMitarbeiter_id()));

        if(existingMitarbeiter.isPresent()) {
            Mitarbeiter _mitarbeiter = existingMitarbeiter.get();
            _mitarbeiter.setNachname(mitarbeiter.getNachname());
            _mitarbeiter.setVorname(mitarbeiter.getVorname());
            _mitarbeiter.setAbteilung(mitarbeiter.getAbteilung());
            _mitarbeiter.setTelefon(mitarbeiter.getTelefon());
            _mitarbeiter.setMail(mitarbeiter.getMail());
            _mitarbeiter.setKw_id(mitarbeiter.getKw_id());

            mitarbeiterRepository.save(_mitarbeiter);
            return new ResponseEntity<>(_mitarbeiter, HttpStatus.OK);
        }

        Mitarbeiter _mitarbeiter = mitarbeiterRepository.save(new Mitarbeiter(
                mitarbeiter.getNachname(),
                mitarbeiter.getVorname(),
                mitarbeiter.getAbteilung(),
                mitarbeiter.getTelefon(),
                mitarbeiter.getMail(),
                mitarbeiter.getKw_id()));
        return new ResponseEntity<>(_mitarbeiter, HttpStatus.CREATED);
    }


    @PutMapping("/mitarbeiter/{mitarbeiter_id}")
    public ResponseEntity<Mitarbeiter> updateMitarbeiter(@PathVariable("mitarbeiter_id") long mitarbeiter_id, @RequestBody Mitarbeiter mitarbeiter) {
        Optional<Mitarbeiter> MitarbeiterData = mitarbeiterRepository.findById(mitarbeiter_id);

        if (MitarbeiterData.isPresent()) {
            Mitarbeiter _mitarbeiter = MitarbeiterData.get();
            _mitarbeiter.setNachname(mitarbeiter.getNachname());
            _mitarbeiter.setVorname(mitarbeiter.getVorname());
            _mitarbeiter.setAbteilung(mitarbeiter.getAbteilung());
            _mitarbeiter.setTelefon(mitarbeiter.getTelefon());
            _mitarbeiter.setMail(mitarbeiter.getMail());
            _mitarbeiter.setKw_id(mitarbeiter.getKw_id());
            return new ResponseEntity<>(mitarbeiterRepository.save(_mitarbeiter), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/mitarbeiter/{mitarbeiter_id}")
    public ResponseEntity<HttpStatus> deleteMitarbeiter(@PathVariable("mitarbeiter_id") long mitarbeiter_id) {
        try {
            mitarbeiterRepository.deleteById(mitarbeiter_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}