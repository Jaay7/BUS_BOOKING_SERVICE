package com.jay.staff_service.controller.impl;

import com.jay.staff_service.controller.StaffController;
import com.jay.staff_service.dto.StaffLoginDto;
import com.jay.staff_service.dto.CreateStaffDto;
import com.jay.staff_service.logic.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
@CrossOrigin("*")
public class StaffControllerImpl implements StaffController {

    @Autowired
    private StaffService staffService;

    @Override
    public ResponseEntity<?> createStaffApi(CreateStaffDto staffInput) {
        if (staffInput != null) {
            return staffService.createStaff(staffInput);
        } else {
            return new ResponseEntity<>("Details required to create Staff", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> loginStaffApi(StaffLoginDto loginInput) {
        return staffService.loginStaff(loginInput);
    }

    @Override
    public ResponseEntity<?> currentStaffApi(String token) {
        return staffService.currentStaff(token);
    }

}
