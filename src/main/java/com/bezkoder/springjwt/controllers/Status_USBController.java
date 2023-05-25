package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Status_USB;
import com.bezkoder.springjwt.repository.Status_USBRepository;
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
public class Status_USBController {

    @Autowired
    Status_USBRepository status_usbRepository;

    @GetMapping("/status_usb")
    public ResponseEntity<List<Status_USB>> getAllUSBstatuse(@RequestParam(required = false) String status) {
        try {
            List<Status_USB> status_usb= new ArrayList<Status_USB>();

            if (status == null)
                status_usbRepository.findAll().forEach(status_usb::add);

            if (status_usb.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(status_usb, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status_usb/{status_usb_id}")
    public ResponseEntity<Status_USB> getUSBstatusById(@PathVariable("status_usb_id") long status_usb_id) {
        Optional<Status_USB> Status_USBData = status_usbRepository.findById(status_usb_id);

        if (Status_USBData.isPresent()) {
            return new ResponseEntity<>(Status_USBData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/status_usb")
    public ResponseEntity<Status_USB> createUSBstatus(@RequestBody Status_USB status_usb) {
        try {
            Status_USB _status_rj45 = status_usbRepository.save(new Status_USB(status_usb.getStatus()));
            return new ResponseEntity<>(_status_rj45, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status_usb/{status_usb_id}")
    public ResponseEntity<Status_USB> updateUSBstatus(@PathVariable("status_usb_id") long status_usb_id, @RequestBody Status_USB status_usb) {
        Optional<Status_USB> Status_USBData = status_usbRepository.findById(status_usb_id);

        if (Status_USBData.isPresent()) {
            Status_USB _status_usb = Status_USBData.get();
            _status_usb.setStatus(status_usb.getStatus());
            return new ResponseEntity<>(status_usbRepository.save(_status_usb), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/status_usb/{status_usb_id}")
    public ResponseEntity<HttpStatus> deleteUSBstatus(@PathVariable("status_usb_id") long status_usb_id) {
        try {
            status_usbRepository.deleteById(status_usb_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


