package com.truck.deusemar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.truck.deusemar.domain.Transport;

public interface TransportRepository extends MongoRepository<Transport, String>{
	
}
