package com.jay.user_service.logic.impl;

import com.jay.user_service.dto.PassengerInputDto;
import com.jay.user_service.logic.PassengerService;
import com.jay.user_service.model.Passenger;
import com.jay.user_service.model.User;
import com.jay.user_service.repository.PassengerRepository;
import com.jay.user_service.repository.UserRepository;
import com.jay.user_service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UserRepository userRepository;

    public User loggedInUser(String token) {
        String phoneNumber = JwtUtil.extractSubject(token);
        return userRepository.findByPhone(phoneNumber);
    }

    @Override
    public ResponseEntity<?> addPassengers(List<PassengerInputDto> passengerInput, String token) {

        User user = loggedInUser(token);

        if (user.getId() != null) {
            passengerInput.forEach(passengerItem -> {
                Passenger passenger = new Passenger();
                passenger.setName(passengerItem.getName());
                passenger.setEmail(passengerItem.getEmail());
                passenger.setPhone(passengerItem.getPhone());
                passenger.setGender(passengerItem.getGender());
                passenger.setState(passengerItem.getState());
                passenger.setDob(passengerItem.getDob());
                passenger.setUser(user);

                passengerRepository.save(passenger);
            });

            return new ResponseEntity<>("Passengers added Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add Passengers added", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getPassengers() {
        return null;
    }

    @Override
    public ResponseEntity<?> getPassenger() {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePassengerInfo() {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePassenger() {
        return null;
    }
}
