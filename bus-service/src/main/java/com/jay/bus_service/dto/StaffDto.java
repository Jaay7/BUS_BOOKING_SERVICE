package com.jay.bus_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

enum RoleEnum {
    STAFF, ADMIN
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private RoleEnum role;
    private Date createdAt;
}
