package com.jay.bus_service.controller;

import com.jay.bus_service.dal.BusDAL;
import com.jay.bus_service.dto.BusInputDto;
import com.jay.bus_service.dto.StaffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    public BusDAL busService;

    @GetMapping("/data")
    public ResponseEntity<?> getBusDataByStaffId(@RequestHeader(name = "Authorization") String token) {
        return busService.getBusDataByStaffId(token);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBusData(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody BusInputDto busInputDto
            ) {
        return busService.addBusData(token, busInputDto);
    }
}
