package com.jay.user_service.model;

import com.jay.user_service.utils.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private GenderEnum gender;
    private String state;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
