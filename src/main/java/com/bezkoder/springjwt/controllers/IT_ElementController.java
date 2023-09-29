package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.exceptions.ResourceNotFoundException;

import com.bezkoder.springjwt.models.IT_Element;
import com.bezkoder.springjwt.models.Kraftwerk;
import com.bezkoder.springjwt.models.Systeme;
import com.bezkoder.springjwt.repository.IT_ElementRepository;
import com.bezkoder.springjwt.repository.KraftwerkRepository;
import com.bezkoder.springjwt.repository.SystemRepository;
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
public class IT_ElementController {

    @Autowired
    IT_ElementRepository it_elementRepository;

    @Autowired
    KraftwerkRepository kraftwerkRepository;

    @Autowired
    SystemRepository systemRepository;


    @GetMapping("/it_element")
    public ResponseEntity<List<IT_Element>> getAllIT_Elemente(@RequestParam(required = false) String kks) {
        try {
            List<IT_Element> it_elements= new ArrayList<IT_Element>();

            if (kks == null)
                it_elementRepository.findAll().forEach(it_elements::add);

            if (it_elements.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(it_elements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/it_element/kraftwerk/{kw_id}")
    public ResponseEntity<List<IT_Element>> getIT_Element(@PathVariable("kw_id") Long kw_id) {
        Optional<Kraftwerk> kraftwerk = kraftwerkRepository.findById(kw_id);
        if (kraftwerk.isPresent()) {
            List<IT_Element> IT_ElementData = it_elementRepository.findAllByKwId(kraftwerk.get());
            return new ResponseEntity<>(IT_ElementData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/it_element/{it_element_id}")
    public ResponseEntity<IT_Element> getIT_ElementById(@PathVariable("it_element_id") long it_element_id) {
        Optional<IT_Element> IT_ElementData = it_elementRepository.findById(it_element_id);

        if (IT_ElementData.isPresent()) {
            return new ResponseEntity<>(IT_ElementData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/it_element1")
    public ResponseEntity<IT_Element> createIT_Element1(@PathVariable(value = "kw_id") Long kw_id,
                                                        @RequestBody IT_Element it_element) {
        IT_Element comment = kraftwerkRepository.findById(kw_id).map(kraftwerk -> {
            it_element.setKw_id(kraftwerk);
            return it_elementRepository.save(it_element);
        }).orElseThrow(() -> new ResourceNotFoundException("Konnte Kraftwerk mit der ID = " + kw_id + " nicht finden!"));

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


    @PostMapping("/it_element")
    public ResponseEntity<IT_Element> createIT_Element(@RequestBody IT_Element it_element) {
        Optional<IT_Element> existingIT_Elemente = Optional.ofNullable(it_elementRepository.findByNameContaining(it_element.getKKS()));


        if(existingIT_Elemente.isPresent()) {
            IT_Element _it_element = existingIT_Elemente.get();
                    _it_element.setKw_id(it_element.getKw_id());
            _it_element.setKKS(it_element.getKKS());
                    _it_element.setKurztext(it_element.getKurztext());
                    _it_element.setSystemeinheit_id(it_element.getSystemeinheit_id());
                    _it_element.setSystem_id(it_element.getSystem_id());
                    _it_element.setStatus_usb_id(it_element.getStatus_usb_id());
                    _it_element.setVirenschutz_hersteller_id(it_element.getVirenschutz_hersteller_id());
                    _it_element.setStatus_rj45_id(it_element.getStatus_rj45_id());
                    _it_element.setBetriebssystem_id(it_element.getBetriebssystem_id());
                    _it_element.setStatus_firewall_id(it_element.getStatus_firewall_id());
                    _it_element.setStatus_virenschutz_id(it_element.getStatus_virenschutz_id());
                    _it_element.setSystemhersteller_id(it_element.getSystemhersteller_id());
                    _it_element.setModell(it_element.getModell());
                    _it_element.setFirmwareversion(it_element.getFirmwareversion());
                    _it_element.setOffice_id(it_element.getOffice_id());
                    _it_element.setIbs_datum(it_element.getIbs_datum());
                    _it_element.setSonstige_sw(it_element.getSonstige_sw());
                    _it_element.setCheckliste(it_element.getCheckliste());
                    _it_element.setBackup(it_element.getBackup());
                    _it_element.setBackup_test(it_element.getBackup_test());

            it_elementRepository.save(_it_element);
            return new ResponseEntity<>(_it_element, HttpStatus.OK);
        }
            IT_Element _it_element = it_elementRepository.save(new IT_Element(
                    it_element.getKw_id(),
                    it_element.getKKS(),
                    it_element.getKurztext(),
                    it_element.getSystemeinheit_id(),
                    it_element.getSystem_id(),
                    it_element.getStatus_usb_id(),
                    it_element.getVirenschutz_hersteller_id(),
                    it_element.getStatus_rj45_id(),
                    it_element.getBetriebssystem_id(),
                    it_element.getStatus_firewall_id(),
                    it_element.getStatus_virenschutz_id(),
                    it_element.getSystemhersteller_id(),
                    it_element.getModell(),
                    it_element.getFirmwareversion(),
                    it_element.getOffice_id(),
                    it_element.getIbs_datum(),
                    it_element.getSonstige_sw(),
                    it_element.getCheckliste(),
                    it_element.getBackup(),
                    it_element.getBackup_test()
            ));
            return new ResponseEntity<>(_it_element, HttpStatus.CREATED);
        }

    @PutMapping("/it_element/{it_element_id}")
    public ResponseEntity<IT_Element> updateIT_Element(@PathVariable("it_element_id") long it_element_id, @RequestBody IT_Element it_element) {
        Optional<IT_Element> IT_ElementData = it_elementRepository.findById(it_element_id);

        if (IT_ElementData.isPresent()) {
            IT_Element _it_element = IT_ElementData.get();
            _it_element.setKw_id(it_element.getKw_id());
            _it_element.setKKS(it_element.getKKS());
            _it_element.setKurztext(it_element.getKurztext());
            _it_element.setSystemeinheit_id(it_element.getSystemeinheit_id());
            _it_element.setSystem_id(it_element.getSystem_id());
            _it_element.setStatus_usb_id(it_element.getStatus_usb_id());
            _it_element.setVirenschutz_hersteller_id(it_element.getVirenschutz_hersteller_id());
            _it_element.setStatus_rj45_id(it_element.getStatus_rj45_id());
            _it_element.setBetriebssystem_id(it_element.getBetriebssystem_id());
            _it_element.setStatus_firewall_id(it_element.getStatus_firewall_id());
            _it_element.setStatus_virenschutz_id(it_element.getStatus_virenschutz_id());
            _it_element.setSystemhersteller_id(it_element.getSystemhersteller_id());
            _it_element.setModell(it_element.getModell());
            _it_element.setFirmwareversion(it_element.getFirmwareversion());
            _it_element.setOffice_id(it_element.getOffice_id());
            _it_element.setIbs_datum(it_element.getIbs_datum());
            _it_element.setSonstige_sw(it_element.getSonstige_sw());
            _it_element.setCheckliste(it_element.getCheckliste());
            _it_element.setBackup(it_element.getBackup());
            _it_element.setBackup_test(it_element.getBackup_test());
            return new ResponseEntity<>(it_elementRepository.save(_it_element), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/it_element/{it_element_id}")
    public ResponseEntity<HttpStatus> deleteIT_Element(@PathVariable("it_element_id") long it_element_id) {
        try {
            it_elementRepository.deleteById(it_element_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
