package com.jay.user_service.model;

import com.jay.user_service.utils.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private GenderEnum gender;
    private String state;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private Integer totaltrips;
    private Integer totaldistance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> passengers = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdAt;
}
