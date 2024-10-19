package com.jay.user_service.logic;

import com.jay.user_service.dto.PassengerInputDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PassengerService {
    ResponseEntity<?> addPassengers(List<PassengerInputDto> passengerInputDto, String token);

    ResponseEntity<?> getPassengers();

    ResponseEntity<?> getPassenger();

    ResponseEntity<?> updatePassengerInfo();

    ResponseEntity<?> deletePassenger();
}
