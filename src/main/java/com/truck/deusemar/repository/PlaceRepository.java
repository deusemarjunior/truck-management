package com.truck.deusemar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.truck.deusemar.domain.Place;

public interface PlaceRepository extends MongoRepository<Place, String>{

}
