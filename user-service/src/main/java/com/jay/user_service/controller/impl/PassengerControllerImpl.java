package com.jay.user_service.controller.impl;

import com.jay.user_service.controller.PassengerController;
import com.jay.user_service.dto.PassengerInputDto;
import com.jay.user_service.logic.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/passenger")
@CrossOrigin("*")
public class PassengerControllerImpl implements PassengerController {

    @Autowired
    private PassengerService passengerService;

    @Override
    public ResponseEntity<?> addPassengersApi(List<PassengerInputDto> passengerInputDto, String token) {
        return passengerService.addPassengers(passengerInputDto, token);
    }

}
