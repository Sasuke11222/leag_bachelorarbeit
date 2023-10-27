package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Kraftwerk;
import com.bezkoder.springjwt.repository.KraftwerkRepository;
import com.bezkoder.springjwt.repository.SystemRepository;
import com.bezkoder.springjwt.models.Systeme;

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
public class SystemController {


    @Autowired
    SystemRepository systemRepository;

    @Autowired
    KraftwerkRepository kraftwerkRepository;

    @GetMapping("/systeme")
    public ResponseEntity<List<Systeme>> getAllIT_Elemente(@RequestParam(required = false) String system_name) {
        try {
            List<Systeme> systeme = new ArrayList<Systeme>();

            if (system_name == null)
                systemRepository.findAll().forEach(systeme::add);

            if (systeme.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(systeme, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/systeme/{system_id}")
    public ResponseEntity<Systeme> getSystemById(@PathVariable("system_id") long system_id) {
        Optional<Systeme> SystemData = systemRepository.findById(system_id);

        if (SystemData.isPresent()) {
            return new ResponseEntity<>(SystemData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/systeme/kraftwerk/{kw_id}/{system_id}")
    public ResponseEntity<List<Systeme>> getSystemandKraftwerk(@PathVariable("kw_id") Long kw_id, @PathVariable("system_id") Long system_id) {
        Optional<Kraftwerk> kraftwerk = kraftwerkRepository.findById(kw_id);
        if (kraftwerk.isPresent()) {
            List<Systeme> SystemData = systemRepository.findAllByKwIdAndSysemId(kraftwerk.get(), system_id);
            return new ResponseEntity<>(SystemData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/systeme/kraftwerk/{kw_id}")
    public ResponseEntity<List<Systeme>> getSystem(@PathVariable("kw_id") Long kw_id) {
        Optional<Kraftwerk> kraftwerk = kraftwerkRepository.findById(kw_id);
        if (kraftwerk.isPresent()) {
            List<Systeme> SystemData = systemRepository.findAllByKwId(kraftwerk.get());
            return new ResponseEntity<>(SystemData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/systeme")
    public ResponseEntity<Systeme> createSystem(@RequestBody Systeme systeme) {
        try {
            Systeme _system = systemRepository
                    .save(new Systeme(
                        systeme.getSystem_name(),
                        systeme.getBeschreibung(),
                        systeme.getSystemtyp_id(),
                        systeme.getKritikalitaet_id(),
                        systeme.getZonen_id(),
                        systeme.getKw_id(),
                        systeme.getMitarbeiter_id(),
                        systeme.getSystemverantwortlicher_id(),
                        systeme.getBuerozugang(),
                        systeme.getFernzugang(),
                        systeme.getErrichter(),
                        systeme.getPdn(),
                        systeme.getZugangsart(),
                        systeme.getPdndate(),
                        systeme.getKsp_a(),
                        systeme.getKsp_b(),
                        systeme.getKsp_y(),
                        systeme.getBox_n(),
                        systeme.getBox_p(),
                        systeme.getBox_q(),
                        systeme.getBox_r(),
                        systeme.getBox_y(),
                        systeme.getJae_a(),
                        systeme.getJae_b(),
                        systeme.getJae_c(),
                        systeme.getJae_d(),
                        systeme.getJae_e(),
                        systeme.getJae_f(),
                        systeme.getJae_y(),
                        systeme.getLip_r(),
                        systeme.getLip_s(),
                        systeme.getLip_y(),
                        systeme.getIsms_Relevant(),
                        systeme.getIsms_Reduzierung(),
                        systeme.getIsms_Auswirkung(),
                        systeme.getIsms_Begruendung()
                        ));
            return new ResponseEntity<>(_system, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/systeme/{system_id}")
    public ResponseEntity<Systeme> updateSystem(@PathVariable("system_id") long system_id, @RequestBody Systeme systeme) {
        Optional<Systeme> SystemData = systemRepository.findById(system_id);

        if (SystemData.isPresent()) {
            Systeme _system = SystemData.get();
            _system.setSystem_name(systeme.getSystem_name());
            _system.setBeschreibung(systeme.getBeschreibung());
            _system.setErrichter(systeme.getErrichter());
            _system.setZonen_id(systeme.getZonen_id());
            return new ResponseEntity<>(systemRepository.save(_system), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/systeme/{system_id}")
    public ResponseEntity<HttpStatus> deleteSystem(@PathVariable("system_id") long system_id) {
        try {
            systemRepository.deleteById(system_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
