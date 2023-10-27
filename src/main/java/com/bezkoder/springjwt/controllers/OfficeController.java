package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Office;
import com.bezkoder.springjwt.repository.OfficeRepository;
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
public class OfficeController {

    @Autowired
    OfficeRepository officeRepository;

    @GetMapping("/office")
    public ResponseEntity<List<Office>> getAllOffice(@RequestParam(required = false) String kritikalitaet_name) {
        try {
            List<Office> office= new ArrayList<Office>();

            if (kritikalitaet_name == null)
                officeRepository.findAll().forEach(office::add);

            if (office.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(office, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/office/{office_id}")
    public ResponseEntity<Office> getOfficeById(@PathVariable("office_id") long office_id) {
        Optional<Office> OfficeData = officeRepository.findById(office_id);

        if (OfficeData.isPresent()) {
            return new ResponseEntity<>(OfficeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //erstellt ein neues Office
    @PostMapping("/office")
    public ResponseEntity<Office> createOffice(@RequestBody Office office) {
        Optional<Office> existingOffice = Optional.ofNullable(officeRepository.findByNameContaining(office.getVersion()));

        if(existingOffice.isPresent()) {
            Office _office = existingOffice.get();

            _office.setOffice_id(office.getOffice_id());
            _office.setVersion(office.getVersion());

            officeRepository.save(_office);
            return new ResponseEntity<>(_office, HttpStatus.OK);
        }

        Office _office = officeRepository.save(new Office(
                office.getVersion()));
        return new ResponseEntity<>(_office, HttpStatus.CREATED);
    }

    @PutMapping("/office/{office_id}")
    public ResponseEntity<Office> updateOffice(@PathVariable("office_id") long office_id, @RequestBody Office office) {
        Optional<Office> OfficeData = officeRepository.findById(office_id);

        if (OfficeData.isPresent()) {
            Office _office = OfficeData.get();
            _office.setVersion(office.getVersion());
            return new ResponseEntity<>(officeRepository.save(_office), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/office/{office_id}")
    public ResponseEntity<HttpStatus> deleteOffice(@PathVariable("office_id") long office_id) {
        try {
            officeRepository.deleteById(office_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


