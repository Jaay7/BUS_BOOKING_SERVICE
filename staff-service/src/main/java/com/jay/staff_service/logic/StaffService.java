package com.jay.staff_service.logic;

import com.jay.staff_service.dto.StaffLoginDto;
import com.jay.staff_service.dto.CreateStaffDto;
import org.springframework.http.ResponseEntity;

public interface StaffService {
    ResponseEntity<?> createStaff(CreateStaffDto staffInput);

    ResponseEntity<?> loginStaff(StaffLoginDto loginInput);

    ResponseEntity<?> currentStaff(String token);

}
