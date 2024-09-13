package com.jay.bus_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RouteIn {
    private String from;
    private String to;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
