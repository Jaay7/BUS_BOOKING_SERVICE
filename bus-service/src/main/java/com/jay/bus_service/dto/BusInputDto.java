package com.jay.bus_service.dto;

import com.jay.bus_service.utils.enums.BusType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class BusInputDto {
    private String operatorName;
    private String phone;
    private Map<String, Integer> priceRange = new HashMap<>();
    private Integer totalSeats;
    private BusType type;
    private List<String> images = new ArrayList<>();

    private RouteIn route;

    private List<StopsIn> stops = new ArrayList<>();
    private List<RestStopsIn> restStops = new ArrayList<>();
    private List<SeatsIn> seats = new ArrayList<>();

    private List<String> amenities = new ArrayList<>();
}
