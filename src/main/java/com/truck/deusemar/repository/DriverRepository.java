package com.truck.deusemar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.truck.deusemar.domain.Driver;

public interface DriverRepository extends MongoRepository<Driver, String>{

}
