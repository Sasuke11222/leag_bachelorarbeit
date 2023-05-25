package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.Virenschutzhersteller;
import com.bezkoder.springjwt.repository.VirenschutzherstellerRepository;
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
public class VirenschutzherstellerController {

    @Autowired
    VirenschutzherstellerRepository virenschutzherstellerRepository;

    @GetMapping("/virenschutzhersteller")
    public ResponseEntity<List<Virenschutzhersteller>> getAllVirenschutzhersteller(@RequestParam(required = false) String herstellername) {
        try {
            List<Virenschutzhersteller> hersteller= new ArrayList<Virenschutzhersteller>();

            if (herstellername == null)
                virenschutzherstellerRepository.findAll().forEach(hersteller::add);

            if (hersteller.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hersteller, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/virenschutzhersteller/{virenschutz_hersteller_id}")
    public ResponseEntity<Virenschutzhersteller> getVirenschutzherstellerById(@PathVariable("virenschutz_hersteller_id") long virenschutz_hersteller_id) {
        Optional<Virenschutzhersteller> VirenschutzherstellerData = virenschutzherstellerRepository.findById(virenschutz_hersteller_id);

        if (VirenschutzherstellerData.isPresent()) {
            return new ResponseEntity<>(VirenschutzherstellerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/virenschutzhersteller")
    public ResponseEntity<Virenschutzhersteller> createVirenschutzhersteller(@RequestBody Virenschutzhersteller virenschutzhersteller) {
        try {
            Virenschutzhersteller _hersteller = virenschutzherstellerRepository.save(new Virenschutzhersteller(
                    virenschutzhersteller.getHerstellername(),
                    virenschutzhersteller.getVersion()));
            return new ResponseEntity<>(_hersteller, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/virenschutzhersteller/{virenschutz_hersteller_id}")
    public ResponseEntity<Virenschutzhersteller> updateVirenschutzhersteller(@PathVariable("virenschutz_hersteller_id") long virenschutz_hersteller_id, @RequestBody Virenschutzhersteller virenschutzhersteller) {
        Optional<Virenschutzhersteller> VirenschutzherstellerData = virenschutzherstellerRepository.findById(virenschutz_hersteller_id);

        if (VirenschutzherstellerData.isPresent()) {
            Virenschutzhersteller _hersteller = VirenschutzherstellerData.get();
            _hersteller.setHerstellername(virenschutzhersteller.getHerstellername());
            _hersteller.setVersion(virenschutzhersteller.getVersion());
            return new ResponseEntity<>(virenschutzherstellerRepository.save(_hersteller), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/virenschutzhersteller/{virenschutz_hersteller_id}")
    public ResponseEntity<HttpStatus> deleteVirenschutzhersteller(@PathVariable("virenschutz_hersteller_id") long virenschutz_hersteller_id) {
        try {
            virenschutzherstellerRepository.deleteById(virenschutz_hersteller_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


