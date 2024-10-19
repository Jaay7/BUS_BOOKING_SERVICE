package com.jay.bus_service.dto;

import com.jay.bus_service.utils.enums.StopType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StopsIn {
    private String name;
    private LocalDateTime time;
    private StopType type;
}
