package com.jay.staff_service.controller;

import com.jay.staff_service.dto.StaffLoginDto;
import com.jay.staff_service.dto.CreateStaffDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface StaffController {

    @PostMapping("/create")
    ResponseEntity<?> createStaffApi(@RequestBody CreateStaffDto staffInput);

    @PostMapping("/login")
    ResponseEntity<?> loginStaffApi(@RequestBody StaffLoginDto loginInput);

    @GetMapping("/")
    ResponseEntity<?> currentStaffApi(@RequestHeader(name = "Authorization") String token);

}
