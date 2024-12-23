package com.jay.staff_service.dto;

import com.jay.staff_service.utils.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
