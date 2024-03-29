package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Status_Firewall;
import com.bezkoder.springjwt.repository.Status_FirewallRepository;
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
public class Status_FirewallController {

    @Autowired
    Status_FirewallRepository status_firewallRepository;

    //Fragt alle Firewallstatus ab
    @GetMapping("/status_firewall")
    public ResponseEntity<List<Status_Firewall>> getAllFirewallstatuse(@RequestParam(required = false) String status) {
        try {
            List<Status_Firewall> status_firewall= new ArrayList<Status_Firewall>();

            if (status == null)
                status_firewallRepository.findAll().forEach(status_firewall::add);

            if (status_firewall.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(status_firewall, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Fragt ein Firewallstatus anhand der ID ab
    @GetMapping("/status_firewall/{status_firewall_id}")
    public ResponseEntity<Status_Firewall> getFirewallstatusById(@PathVariable("status_firewall_id") long status_firewall_id) {
        Optional<Status_Firewall> Status_FirewallData = status_firewallRepository.findById(status_firewall_id);

        if (Status_FirewallData.isPresent()) {
            return new ResponseEntity<>(Status_FirewallData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //erstellt einen neuen Firewallstatus
    @PostMapping("/status_firewall")
    public ResponseEntity<Status_Firewall> createFirewallstatus(@RequestBody Status_Firewall status_firewall) {
        Optional<Status_Firewall> existingStatusFirewall = Optional.ofNullable(status_firewallRepository.findByNameContaining(status_firewall.getStatus()));

        if(existingStatusFirewall.isPresent()) {
            Status_Firewall _status_firewall = existingStatusFirewall.get();

            _status_firewall.setStatus_firewall_id(status_firewall.getStatus_firewall_id());
            _status_firewall.setStatus(status_firewall.getStatus());

            status_firewallRepository.save(_status_firewall);
            return new ResponseEntity<>(_status_firewall, HttpStatus.OK);
        }

        Status_Firewall _status_firewall = status_firewallRepository.save(new Status_Firewall(
                status_firewall.getStatus()));
        return new ResponseEntity<>(_status_firewall, HttpStatus.CREATED);
    }


    //bearbeitet Firewallstatus anhand der ID
    @PutMapping("/status_firewall/{status_firewall_id}")
    public ResponseEntity<Status_Firewall> updateFirewallstatus(@PathVariable("status_firewall_id") long status_firewall_id, @RequestBody Status_Firewall status_firewall) {
        Optional<Status_Firewall> Status_FirewallData = status_firewallRepository.findById(status_firewall_id);

        if (Status_FirewallData.isPresent()) {
            Status_Firewall _status_firewall = Status_FirewallData.get();
            _status_firewall.setStatus(status_firewall.getStatus());
            return new ResponseEntity<>(status_firewallRepository.save(_status_firewall), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //löscht ein Firewallstatus anhand der ID
    @DeleteMapping("/status_firewall/{status_firewall_id}")
    public ResponseEntity<HttpStatus> deleteFirewallstatus(@PathVariable("status_firewall_id") long status_firewall_id) {
        try {
            status_firewallRepository.deleteById(status_firewall_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


