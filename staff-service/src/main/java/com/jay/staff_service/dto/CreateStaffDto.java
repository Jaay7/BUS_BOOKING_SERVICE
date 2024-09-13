package com.jay.staff_service.dto;

import lombok.Data;

@Data
public class CreateStaffDto {
    private String name;
    private String email;
    private String password;
    private String phone;
}
