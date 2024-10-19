package com.jay.bus_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {
    @Id
    private String id;
    private String from;
    private String to;
    private List<String> via = new ArrayList<>();
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
