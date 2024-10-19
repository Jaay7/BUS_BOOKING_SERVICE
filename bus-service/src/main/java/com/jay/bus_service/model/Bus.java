package com.jay.bus_service.model;

import com.jay.bus_service.utils.enums.BusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("buses")
public class Bus {
    @Id
    private String id;
    private Long staffId;
    private String operatorName;
    private String phone;

    private Map<String, Integer> priceRange = new HashMap<>();
    private Integer totalSeats;
    private BusType type;
    private List<String> images = new ArrayList<>();

    private Route route;
    private List<Stops> stops = new ArrayList<>();
    private List<RestStops> restStops = new ArrayList<>();

    private List<Seats> seats = new ArrayList<>();

    private List<String> amenities = new ArrayList<>();

}
