package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Systemeinheit;
import com.bezkoder.springjwt.models.Systemhersteller;
import com.bezkoder.springjwt.repository.SystemherstellerRepository;
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
public class SystemherstellerController {


    @Autowired
    SystemherstellerRepository systemherstellerRepository;


    @GetMapping("/systemhersteller")
    public ResponseEntity<List<Systemhersteller>> getAllSystemhersteller(@RequestParam(required = false) String herstellername) {
        try {
            List<Systemhersteller> hersteller = new ArrayList<Systemhersteller>();

            if (herstellername == null)
                systemherstellerRepository.findAll().forEach(hersteller::add);

            if (hersteller.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hersteller, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/systemhersteller/{systemhersteller_id}")
    public ResponseEntity<Systemhersteller> getSystemherstellerById(@PathVariable("systemhersteller_id") long systemhersteller_id) {
        Optional<Systemhersteller> HerstellerData = systemherstellerRepository.findById(systemhersteller_id);

        if (HerstellerData.isPresent()) {
            return new ResponseEntity<>(HerstellerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //erstellt einen neuen Systemhersteller  --> geht noch nicht !!!! Internet Error
    @PostMapping("/systemhersteller")
    public ResponseEntity<Systemhersteller> createSystemhhersteller(@RequestBody Systemhersteller systemhersteller) {
        Optional<Systemhersteller> existingSystemhersteller = Optional.ofNullable(systemherstellerRepository.findByNameContaining(systemhersteller.getHerstellername()));

        if(existingSystemhersteller.isPresent()) {
            Systemhersteller _systemhersteller = existingSystemhersteller.get();

            _systemhersteller.setSystemhersteller_id(systemhersteller.getSystemhersteller_id());
            _systemhersteller.setHerstellername(systemhersteller.getHerstellername());

            systemherstellerRepository.save(_systemhersteller);
            return new ResponseEntity<>(_systemhersteller, HttpStatus.OK);
        }

        Systemhersteller _systemhersteller = systemherstellerRepository.save(new Systemhersteller(
                systemhersteller.getSystemhersteller_id(),
                systemhersteller.getHerstellername()
        ));
        return new ResponseEntity<>(_systemhersteller, HttpStatus.CREATED);
    }

    @PutMapping("/systemhersteller/{systemhersteller_id}")
    public ResponseEntity<Systemhersteller> updateSystemhersteller(@PathVariable("systemhersteller_id") long systemhersteller_id, @RequestBody Systemhersteller hersteller) {
        Optional<Systemhersteller> HerstellerData = systemherstellerRepository.findById(systemhersteller_id);

        if (HerstellerData.isPresent()) {
            Systemhersteller _hersteller = HerstellerData.get();
            _hersteller.setHerstellername(hersteller.getHerstellername());
            return new ResponseEntity<>(systemherstellerRepository.save(_hersteller), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/systemhersteller/{systemhersteller_id}")
    public ResponseEntity<HttpStatus> deleteSystemhersteller(@PathVariable("systemhersteller_id") long systemhersteller_id) {
        try {
            systemherstellerRepository.deleteById(systemhersteller_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
