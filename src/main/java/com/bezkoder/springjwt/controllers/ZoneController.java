package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Zone;
import com.bezkoder.springjwt.repository.ZoneRepository;
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
public class ZoneController {


    @Autowired
    ZoneRepository zoneRepository;


    @GetMapping("/zone")
    public ResponseEntity<List<Zone>> getAllZonen(@RequestParam(required = false) String zone) {
        try {
            List<Zone> zonen = new ArrayList<Zone>();

            if (zone == null)
                zoneRepository.findAll().forEach(zonen::add);

            if (zonen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(zonen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/zone/{zonen_id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable("zone_id") long zone_id) {
        Optional<Zone> ZoneData = zoneRepository.findById(zone_id);

        if (ZoneData.isPresent()) {
            return new ResponseEntity<>(ZoneData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/zone")
    public ResponseEntity<Zone> createZone(@RequestBody Zone zonen) {
        try {
            Zone _zone = zoneRepository
                    .save(new Zone(zonen.getZone()));
            return new ResponseEntity<>(_zone, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/zone/{zonen_id}")
    public ResponseEntity<Zone> updateZone(@PathVariable("zonen_id") long zonen_id, @RequestBody Zone zonen) {
        Optional<Zone> ZoneData = zoneRepository.findById(zonen_id);

        if (ZoneData.isPresent()) {
            Zone _zone = ZoneData.get();
            _zone.setZone(zonen.getZone());
            return new ResponseEntity<>(zoneRepository.save(_zone), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/zone/{zonen_id}")
    public ResponseEntity<HttpStatus> deleteZone(@PathVariable("zonen_id") long zonen_id) {
        try {
            zoneRepository.deleteById(zonen_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

