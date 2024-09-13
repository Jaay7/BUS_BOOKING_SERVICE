package com.jay.user_service.controller;

import com.jay.user_service.dto.LoginDto;
import com.jay.user_service.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface UserController {

    @PostMapping("/create")
    ResponseEntity<?> createUserApi(@RequestBody UserDto userInput);

    @PostMapping("/send-otp")
    ResponseEntity<?> sendOtpToPhoneApi(@RequestParam(name = "number") String number) throws Exception;

    @PostMapping("/login")
    ResponseEntity<?> loginUserApi(@RequestBody LoginDto loginInput);

    @GetMapping("/")
    ResponseEntity<?> currentUserApi(@RequestParam(name = "token") String token);

    @PostMapping("/store")
    String store(@RequestParam(name = "number") String number) throws Exception;

}
