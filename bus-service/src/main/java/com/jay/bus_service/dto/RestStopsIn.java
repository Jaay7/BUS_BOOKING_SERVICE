package com.jay.bus_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestStopsIn {
    private String name;
    private LocalDateTime time;
    private String duration;
}
