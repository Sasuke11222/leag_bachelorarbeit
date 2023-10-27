package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Kontotyp;
import com.bezkoder.springjwt.repository.KontotypRepository;
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
public class KontotypController {
    @Autowired
    KontotypRepository kontotypRepository;


    @GetMapping("/kontotyp")
    public ResponseEntity<List<Kontotyp>> getAllKontotypen(@RequestParam(required = false) String kontotyp) {
        try {
            List<Kontotyp> kontot= new ArrayList<Kontotyp>();

            if (kontotyp == null)
                kontotypRepository.findAll().forEach(kontot::add);

            if (kontot.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(kontot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/kontotyp/{kontotyp_id}")
    public ResponseEntity<Kontotyp> getKontotypById(@PathVariable("kontotyp_id") long kontotyp_id) {
        Optional<Kontotyp> KontotypData = kontotypRepository.findById(kontotyp_id);

        if (KontotypData.isPresent()) {
            return new ResponseEntity<>(KontotypData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //erstellt einen neuen Kontotyp
    @PostMapping("/kontotyp")
    public ResponseEntity<Kontotyp> createKontotyp(@RequestBody Kontotyp kontotyp) {
        Optional<Kontotyp> existingKontotyp = Optional.ofNullable(kontotypRepository.findByNameContaining(kontotyp.getKontotyp()));

        if(existingKontotyp.isPresent()) {
            Kontotyp _kontotyp = existingKontotyp.get();

            _kontotyp.setKontotyp_id(kontotyp.getKontotyp_id());
            _kontotyp.setKontotyp(kontotyp.getKontotyp());

            kontotypRepository.save(_kontotyp);
            return new ResponseEntity<>(_kontotyp, HttpStatus.OK);
        }

        Kontotyp _kontotyp = kontotypRepository.save(new Kontotyp(
                kontotyp.getKontotyp()));
        return new ResponseEntity<>(_kontotyp, HttpStatus.CREATED);
    }

    @PutMapping("/kontotyp/{kontotyp_id}")
    public ResponseEntity<Kontotyp> updateKontotyp(@PathVariable("kontotyp_id") long kontotyp_id, @RequestBody Kontotyp kontotyp) {
        Optional<Kontotyp> KontotypData = kontotypRepository.findById(kontotyp_id);

        if (KontotypData.isPresent()) {
            Kontotyp _kontotyp = KontotypData.get();
            _kontotyp.setKontotyp(kontotyp.getKontotyp());
            return new ResponseEntity<>(kontotypRepository.save(_kontotyp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/kontotyp/{kontotyp_id}")
    public ResponseEntity<HttpStatus> deleteKontotyp(@PathVariable("kontotyp_id") long kontotyp_id) {
        try {
            kontotypRepository.deleteById(kontotyp_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
