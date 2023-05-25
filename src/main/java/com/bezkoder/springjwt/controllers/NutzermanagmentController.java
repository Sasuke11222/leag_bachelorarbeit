package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Nutzermanagment;
import com.bezkoder.springjwt.repository.NutzermanagmentRepository;
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
public class NutzermanagmentController {

    @Autowired
    NutzermanagmentRepository nutzermanagmentRepository;

    @GetMapping("/nutzermanagement")
    public ResponseEntity<List<Nutzermanagment>> getAllNutzer(@RequestParam(required = false) Long ID) {
        try {
            List<Nutzermanagment> nutzermanagments= new ArrayList<Nutzermanagment>();

            if (ID == null)
                nutzermanagmentRepository.findAll().forEach(nutzermanagments::add);

            if (nutzermanagments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(nutzermanagments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nutzermanagement/{ID}")
    public ResponseEntity<Nutzermanagment> getNutzerById(@PathVariable("ID") long ID) {
        Optional<Nutzermanagment> NutzermanagmentData = nutzermanagmentRepository.findById(ID);

        if (NutzermanagmentData.isPresent()) {
            return new ResponseEntity<>(NutzermanagmentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/nutzermanagement")
    public ResponseEntity<Nutzermanagment> createNutzer(@RequestBody Nutzermanagment nutzermanagment) {
        try {
            Nutzermanagment _nutzermanagment = nutzermanagmentRepository.save(new Nutzermanagment(
                    nutzermanagment.getSystem_id(),
                    nutzermanagment.getMitarbeiter_id(),
                    nutzermanagment.getKontotyp_id(),
                    nutzermanagment.getServicepartner(),
                    nutzermanagment.getLoginname(),
                    nutzermanagment.getBuerozugang(),
                    nutzermanagment.getKontoart_id(),
                    nutzermanagment.getBemerkung()
            ));
            return new ResponseEntity<>(_nutzermanagment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/nutzermanagement/{ID}")
    public ResponseEntity<Nutzermanagment> updateNutzer(@PathVariable("ID") long ID, @RequestBody Nutzermanagment nutzermanagment) {
        Optional<Nutzermanagment> NutzermanagmentData = nutzermanagmentRepository.findById(ID);

        if (NutzermanagmentData.isPresent()) {
            Nutzermanagment _nutzermanagment = NutzermanagmentData.get();
            _nutzermanagment.setSystem_id(nutzermanagment.getSystem_id());
            _nutzermanagment.setMitarbeiter_id(nutzermanagment.getMitarbeiter_id());
            _nutzermanagment.setKontotyp_id(nutzermanagment.getKontotyp_id());
            _nutzermanagment.setServicepartner(nutzermanagment.getServicepartner());
            _nutzermanagment.setLoginname(nutzermanagment.getLoginname());
            _nutzermanagment.setBuerozugang(nutzermanagment.getBuerozugang());
            _nutzermanagment.setKontoart_id(nutzermanagment.getKontoart_id());
            _nutzermanagment.setBemerkung(nutzermanagment.getBemerkung());

            return new ResponseEntity<>(nutzermanagmentRepository.save(_nutzermanagment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/nutzermanagement/{ID}")
    public ResponseEntity<HttpStatus> deleteNutzer(@PathVariable("ID") long ID) {
        try {
            nutzermanagmentRepository.deleteById(ID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


