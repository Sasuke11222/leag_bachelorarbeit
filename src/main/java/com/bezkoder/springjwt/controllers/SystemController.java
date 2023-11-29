package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Kraftwerk;
import com.bezkoder.springjwt.models.Mitarbeiter;
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

    //erstellt ein neues System
    @PostMapping("/systeme")
    public ResponseEntity<Systeme> createSysteme(@RequestBody Systeme systeme) {
        Optional<Systeme> existingSysteme = Optional.ofNullable(systemRepository.findByNameContaining(systeme.getSystem_name()));

        if(existingSysteme.isPresent()) {
            Systeme _systeme = existingSysteme.get();
            _systeme.setSystem_id(systeme.getSystem_id());
            _systeme.setSystem_name(systeme.getSystem_name());
            _systeme.setBeschreibung(systeme.getBeschreibung());
            _systeme.setSystemtyp_id(systeme.getSystemtyp_id());
            _systeme.setKritikalitaet_id(systeme.getKritikalitaet_id());
            _systeme.setZonen_id(systeme.getZonen_id());
            _systeme.setKw_id(systeme.getKw_id());
            _systeme.setMitarbeiter_id(systeme.getMitarbeiter_id());
            _systeme.setSystemverantwortlicher_id(systeme.getSystemverantwortlicher_id());
            _systeme.setBuerozugang(systeme.getBuerozugang());
            _systeme.setFernzugang(systeme.getFernzugang());
            _systeme.setErrichter(systeme.getErrichter());
            _systeme.setPdn(systeme.getPdn());
            _systeme.setZugangsart(systeme.getZugangsart());
            _systeme.setPdndate(systeme.getPdndate());
            _systeme.setKsp_a(systeme.getKsp_a());
            _systeme.setKsp_b(systeme.getKsp_b());
            _systeme.setKsp_y(systeme.getKsp_y());
            _systeme.setBox_n(systeme.getBox_n());
            _systeme.setBox_p(systeme.getBox_p());
            _systeme.setBox_q(systeme.getBox_q());
            _systeme.setBox_r(systeme.getBox_r());
            _systeme.setBox_y(systeme.getBox_y());
            _systeme.setJae_a(systeme.getJae_a());
            _systeme.setJae_b(systeme.getJae_b());
            _systeme.setJae_c(systeme.getJae_c());
            _systeme.setJae_d(systeme.getJae_d());
            _systeme.setJae_e(systeme.getJae_e());
            _systeme.setJae_f(systeme.getJae_f());
            _systeme.setJae_y(systeme.getJae_y());
            _systeme.setLip_r(systeme.getLip_r());
            _systeme.setLip_s(systeme.getLip_s());
            _systeme.setLip_y(systeme.getLip_y());
            _systeme.setIsms_Relevant(systeme.getIsms_Relevant());
            _systeme.setIsms_Reduzierung(systeme.getIsms_Reduzierung());
            _systeme.setIsms_Auswirkung(systeme.getIsms_Auswirkung());
            _systeme.setIsms_Begruendung(systeme.getIsms_Begruendung());

            systemRepository.save(_systeme);
            return new ResponseEntity<>(_systeme, HttpStatus.OK);
        }
        Systeme _systeme = systemRepository.save(new Systeme(
                systeme.getSystem_id(),
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
        return new ResponseEntity<>(_systeme, HttpStatus.CREATED);
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
