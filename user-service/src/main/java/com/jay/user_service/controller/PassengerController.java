package com.jay.user_service.controller;

import com.jay.user_service.dto.PassengerInputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PassengerController {

    @PostMapping("/add")
    ResponseEntity<?> addPassengersApi(
            @RequestBody List<PassengerInputDto> passengerInputDto,
            @RequestHeader(name = "Authorization") String token
    );

//    @GetMapping("/all")
//    ResponseEntity<?> getAllPassengersAll(@RequestHeader(name = "Authorization") String token);
//
//    @GetMapping("/")
//    ResponseEntity<?> getPassenger(@RequestParam(name = "p_id") Long id);
}
