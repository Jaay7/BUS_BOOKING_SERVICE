package com.jay.bus_service.model;

import com.jay.bus_service.utils.enums.StopType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stops {
    @Id
    private String id;

    private String name;
    private LocalDateTime time;
    private StopType type;
}
