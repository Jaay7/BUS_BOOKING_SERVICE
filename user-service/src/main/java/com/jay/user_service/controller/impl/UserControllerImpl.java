package com.jay.user_service.controller.impl;

import com.jay.user_service.controller.UserController;
import com.jay.user_service.dto.LoginDto;
import com.jay.user_service.dto.UserDto;
import com.jay.user_service.logic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> createUserApi(UserDto userInput) {
        if (userInput != null) {
            return userService.createUser(userInput);
        } else {
            return new ResponseEntity<>("Details required to create User", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> sendOtpToPhoneApi(String number) throws Exception {
        if (number != null) {
            return userService.sendOtpToPhone(number);
        } else {
            return new ResponseEntity<>("Phone number required to send OTP.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> loginUserApi(LoginDto loginInput) {
        return userService.loginUser(loginInput);
    }

    @Override
    public ResponseEntity<?> currentUserApi(String token) {
        return userService.currentUser(token);
    }

    @Override
    public String store(String number) throws Exception {
        return userService.store(number);
    }

}
