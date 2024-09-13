package com.jay.bus_service.dto;

import com.jay.bus_service.utils.enums.SeatType;
import lombok.Data;

@Data
public class SeatsIn {
    private String number;
    private String price;
    private SeatType type;
}
