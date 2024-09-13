package com.jay.bus_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bus")
public class BusController {

    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("This is protected data");
    }
}
