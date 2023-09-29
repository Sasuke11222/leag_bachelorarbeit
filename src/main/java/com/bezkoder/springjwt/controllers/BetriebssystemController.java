package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Betriebssystem;
import com.bezkoder.springjwt.repository.BetriebssystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600) //origins = alle akzeptierten Anfragesteller
@RestController
@RequestMapping("/api")
public class BetriebssystemController {

    @Autowired
    BetriebssystemRepository betriebssystemRepository;

    //gibt alle Betriebssyteme aus
    @GetMapping("/betriebssystem")
    public ResponseEntity<List<Betriebssystem>> getAllBetreibssysteme(@RequestParam(required = false) String betriebssystem_name) {
        try {
            List<Betriebssystem> betriebssystems= new ArrayList<Betriebssystem>();

            if (betriebssystem_name == null)
                betriebssystemRepository.findAll().forEach(betriebssystems::add);

            if (betriebssystems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(betriebssystems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //gibt Betriebssytem anhand der ID aus
    @GetMapping("/betriebssystem/{betriebssystem_id}")
    public ResponseEntity<Betriebssystem> getBetriebssystemById(@PathVariable("betriebssystem_id") long betriebssystem_id) {
        Optional<Betriebssystem> BetriebssystemData = betriebssystemRepository.findById(betriebssystem_id);

        if (BetriebssystemData.isPresent()) {
            return new ResponseEntity<>(BetriebssystemData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //erstellt Betriebssystem
    @PostMapping("/addBetriebssystem") //fügt ein neues Betriebssystem hinzu
    public @ResponseBody ResponseEntity<String> addBetriebssystem(@RequestParam String betriebssystem_name){
        if(betriebssystemRepository.findByName(betriebssystem_name) != null){
            return ResponseEntity.badRequest().body("Error: Betriebssystem " + betriebssystem_name + " already exists");
        }
        try{
            Betriebssystem b = new Betriebssystem();
            b.setBetriebssystem_name(betriebssystem_name);
            betriebssystemRepository.save(b);
            return ResponseEntity.ok().body("Betriebssystem " + betriebssystem_name + " created");
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error: Could not create Betriebssystem " + betriebssystem_name + " Exception: " + e.getMessage());
        }
    }

    //bearbeitet Betriebssytem anhand der ID
    @PutMapping("/betriebssystem/{betriebssystem_id}")
    public ResponseEntity<Betriebssystem> updateBetriebssystem(@PathVariable("betriebssystem_id") long betriebssystem_id, @RequestBody Betriebssystem betriebssystem) {
        Optional<Betriebssystem> BetreibssystemData = betriebssystemRepository.findById(betriebssystem_id);

        if (BetreibssystemData.isPresent()) {
            Betriebssystem _betriebssystem = BetreibssystemData.get();
            _betriebssystem.setBetriebssystem_name(betriebssystem.getBetriebssystem_name());
            return new ResponseEntity<>(betriebssystemRepository.save(_betriebssystem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //löscht Betriebssytem anhand der ID
    @DeleteMapping("/betriebssystem/{betriebssystem_id}")
    public ResponseEntity<HttpStatus> deleteBetriebssystem(@PathVariable("betriebssystem_id") long betriebssystem_id) {
        try {
            betriebssystemRepository.deleteById(betriebssystem_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
