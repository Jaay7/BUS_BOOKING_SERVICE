package com.jay.staff_service.logic.impl;

import com.jay.staff_service.model.Staff;
import com.jay.staff_service.utils.JwtUtil;
import com.jay.staff_service.dto.StaffLoginDto;
import com.jay.staff_service.dto.CreateStaffDto;
import com.jay.staff_service.logic.StaffService;
import com.jay.staff_service.repository.StaffRepository;
import com.jay.staff_service.utils.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Optional<Staff> staffAlreadyExist(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<?> createStaff(CreateStaffDto staffInput) {
        try {
            boolean isStaff = staffAlreadyExist(staffInput.getEmail()).isPresent();
            if (isStaff) {
                return new ResponseEntity<>("Staff with email " + staffInput.getEmail() + " is already exists.", HttpStatus.BAD_REQUEST);
            } else {
                Staff staff = new Staff();
                staff.setName(staffInput.getName());
                staff.setEmail(staffInput.getEmail());
                staff.setPassword(staffInput.getPassword());
                staff.setPhone(staffInput.getPhone());
                staff.setRole(RoleEnum.STAFF);
                staffRepository.save(staff);

                Map<String, String> response = new HashMap<>();
                response.put("message", "Staff created successfully!");
                response.put("token", JwtUtil.generateToken(staffInput.getPhone()));
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create Staff " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> loginStaff(StaffLoginDto loginInput) {
        try {
            boolean isStaff = staffAlreadyExist(loginInput.getEmail()).isPresent();
            if (isStaff) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Staff logged in successfully!");
                response.put("token", JwtUtil.generateToken(loginInput.getEmail()));
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to login Staff " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> currentStaff(String token) {
        String email = JwtUtil.extractSubject(token);
        if (staffAlreadyExist(email).isPresent()) {
            return new ResponseEntity<>(staffAlreadyExist(email).get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("Token is Invalid/Expired", HttpStatus.BAD_REQUEST);
        }
    }


}
