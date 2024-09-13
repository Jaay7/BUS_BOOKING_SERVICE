package com.jay.bus_service.repository;

import com.jay.bus_service.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends MongoRepository<Bus, String> {
}
