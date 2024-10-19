package com.jay.bus_service.dal.impl;

import com.jay.bus_service.dal.BusDAL;
import com.jay.bus_service.dto.BusInputDto;
import com.jay.bus_service.dto.StaffDto;
import com.jay.bus_service.dto.StopsIn;
import com.jay.bus_service.model.*;
import com.jay.bus_service.repository.BusRepository;
import com.jay.bus_service.utils.enums.SeatType;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusDALImpl implements BusDAL {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public StaffDto authenticatedStaffData(String token) {
        String path = "http://STAFF-SERVICE/staff/?token=" + token;
        ResponseEntity<StaffDto> staffDtoResponseEntity = restTemplate.getForEntity(path, StaffDto.class);
        return staffDtoResponseEntity.getBody();
    }

    @Override
    public ResponseEntity<?> getBusDataByStaffId(String token) {
        StaffDto staff = authenticatedStaffData(token);
        if (staff != null) {
            Query query = new Query();
            query.addCriteria(Criteria.where("staffId").is(staff.getId()));
            return new ResponseEntity<>(mongoTemplate.find(query, Bus.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid user", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> addBusData(String token, BusInputDto busInputDto) {
        try {
            Bus bus = new Bus();
            StaffDto staff = authenticatedStaffData(token);
            if (staff != null) {
                bus.setStaffId(staff.getId());
            } else {
                return new ResponseEntity<>("Invalid user", HttpStatus.BAD_REQUEST);
            }
            bus.setOperatorName(busInputDto.getOperatorName());
            bus.setPhone(busInputDto.getPhone());
            bus.setPriceRange(busInputDto.getPriceRange());
            bus.setTotalSeats(busInputDto.getTotalSeats());
            bus.setType(busInputDto.getType());
            bus.setImages(busInputDto.getImages());

            Route routeInput = new Route();
            routeInput.setId(new ObjectId().toString());
            routeInput.setTo(busInputDto.getRoute().getTo());
            routeInput.setFrom(busInputDto.getRoute().getFrom());
            routeInput.setVia(busInputDto.getRoute().getVia());
            routeInput.setArrivalTime(busInputDto.getRoute().getArrivalTime());
            routeInput.setDepartureTime(busInputDto.getRoute().getDepartureTime());

            bus.setRoute(routeInput);

            List<Stops> stopsInput = new ArrayList<>();
            busInputDto.getStops().forEach(stopItem -> {
                Stops stop = new Stops();
                stop.setId(new ObjectId().toString());
                stop.setName(stopItem.getName());
                stop.setTime(stopItem.getTime());
                stop.setType(stopItem.getType());
                stopsInput.add(stop);
            });

            bus.setStops(stopsInput);

            List<RestStops> restStopsInput = new ArrayList<>();
            busInputDto.getRestStops().forEach(restStopItem -> {
                RestStops restStop = new RestStops();
                restStop.setId(new ObjectId().toString());
                restStop.setName(restStopItem.getName());
                restStop.setTime(restStopItem.getTime());
                restStop.setDuration(restStopItem.getDuration());
                restStopsInput.add(restStop);
            });

            bus.setRestStops(restStopsInput);

            List<Seats> seatsInput = new ArrayList<>();
            for(int seatNum = 1; seatNum < busInputDto.getTotalSeats() + 1; seatNum++) {
                Seats lowerSeat = new Seats();
                lowerSeat.setId(new ObjectId().toString());
                lowerSeat.setNumber(Integer.toString(seatNum));
                lowerSeat.setPrice(seatNum > 3 && seatNum < 28 ? busInputDto.getSeats().getLowerPriceMiddle() : busInputDto.getSeats().getLowerPriceFistAndLast());
                lowerSeat.setType(SeatType.LOWER);
                seatsInput.add(lowerSeat);

                Seats upperSeat = new Seats();
                upperSeat.setId(new ObjectId().toString());
                upperSeat.setNumber(Integer.toString(seatNum));
                upperSeat.setPrice(seatNum > 3 && seatNum < 28 ? busInputDto.getSeats().getUpperPriceMiddle() : busInputDto.getSeats().getUpperPriceFistAndLast());
                upperSeat.setType(SeatType.UPPER);
                seatsInput.add(upperSeat);
            }

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
