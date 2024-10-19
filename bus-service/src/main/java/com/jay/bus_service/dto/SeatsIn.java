package com.jay.bus_service.dto;

import com.jay.bus_service.utils.enums.SeatType;
import lombok.Data;

@Data
public class SeatsIn {
    private Integer lowerPriceFistAndLast;
    private Integer upperPriceFistAndLast;

    private Integer lowerPriceMiddle;
    private Integer upperPriceMiddle;
}
