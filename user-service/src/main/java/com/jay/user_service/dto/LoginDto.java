package com.jay.user_service.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String phone;
    private String otp;
}
