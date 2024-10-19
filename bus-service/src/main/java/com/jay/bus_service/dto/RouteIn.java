package com.jay.bus_service.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class RouteIn {
    private String from;
    private String to;
    private List<String> via = new ArrayList<>();
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
