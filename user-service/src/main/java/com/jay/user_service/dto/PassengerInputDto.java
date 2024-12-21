package com.jay.user_service.dto;

import com.jay.user_service.utils.enums.GenderEnum;
import lombok.Data;

import java.sql.Date;

@Data
public class PassengerInputDto {
    private String name;
    private String email;
    private String phone;
    private GenderEnum gender;
    private String state;
    private Date dob;
}
