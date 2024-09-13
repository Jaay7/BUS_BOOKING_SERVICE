package com.jay.user_service.logic;

import com.jay.user_service.dto.LoginDto;
import com.jay.user_service.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(UserDto userInput);

    ResponseEntity<?> sendOtpToPhone(String number) throws Exception;

    ResponseEntity<?> loginUser(LoginDto loginInput);

    ResponseEntity<?> currentUser(String token);

    String store(String phone) throws Exception;
}
