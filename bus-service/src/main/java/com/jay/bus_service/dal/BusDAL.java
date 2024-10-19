package com.jay.bus_service.dal;

import com.jay.bus_service.dto.BusInputDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BusDAL {
    ResponseEntity<?> addBusData(String token, BusInputDto busInputDto);

    ResponseEntity<?> getBusDataByStaffId(String token);
}
