package com.truck.deusemar.dataprovider.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.truck.deusemar.dataprovider.repository.entity.DriverEntity;

public interface DriverRepositoryClean extends MongoRepository<DriverEntity, String> {

}
