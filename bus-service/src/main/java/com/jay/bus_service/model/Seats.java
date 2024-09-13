package com.jay.bus_service.model;

import com.jay.bus_service.utils.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seats {
    @Id
    private String id;
    private String number;
    private String price;
    private SeatType type;
}
