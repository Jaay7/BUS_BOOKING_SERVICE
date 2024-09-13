package com.jay.bus_service.dal.impl;

import com.jay.bus_service.dal.BusDAL;
import com.jay.bus_service.dto.BusInputDto;
import com.jay.bus_service.dto.StopsIn;
import com.jay.bus_service.model.*;
import com.jay.bus_service.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusDALImpl implements BusDAL {

    @Autowired
    private BusRepository busRepository;

    @Override
    public ResponseEntity<?> addBusData(String token, BusInputDto busInputDto) {
        try {
            Bus bus = new Bus();
            bus.setOperatorName(busInputDto.getOperatorName());
            bus.setPhone(busInputDto.getPhone());
            bus.setPriceRange(busInputDto.getPriceRange());
            bus.setTotalSeats(busInputDto.getTotalSeats());
            bus.setType(busInputDto.getType());
            bus.setImages(busInputDto.getImages());

            Route routeInput = new Route();
            routeInput.setTo(busInputDto.getRoute().getTo());
            routeInput.setFrom(busInputDto.getRoute().getFrom());
            routeInput.setArrivalTime(busInputDto.getRoute().getArrivalTime());
            routeInput.setDepartureTime(busInputDto.getRoute().getDepartureTime());

            bus.setRoute(routeInput);

            List<Stops> stopsInput = new ArrayList<>();
            busInputDto.getStops().forEach(stopItem -> {
                Stops stop = new Stops();
                stop.setName(stopItem.getName());
                stop.setTime(stopItem.getTime());
                stopsInput.add(stop);
            });

            bus.setStops(stopsInput);

            List<RestStops> restStopsInput = new ArrayList<>();
            busInputDto.getRestStops().forEach(restStopItem -> {
                RestStops restStop = new RestStops();
                restStop.setName(restStopItem.getName());
                restStop.setTime(restStopItem.getTime());
                restStop.setDuration(restStopItem.getDuration());
                restStopsInput.add(restStop);
            });

            bus.setRestStops(restStopsInput);

            List<Seats> seatsInput = new ArrayList<>();
            busInputDto.getSeats().forEach(seatItem -> {
                Seats seat = new Seats();
                seat.setNumber(seatItem.getNumber());
                seat.setPrice(seatItem.getPrice());
                seat.setType(seatItem.getType());
                seatsInput.add(seat);
            });

            bus.setSeats(seatsInput);
            bus.setAmenities(busInputDto.getAmenities());

            busRepository.save(bus);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Bus record saved successfully!");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
