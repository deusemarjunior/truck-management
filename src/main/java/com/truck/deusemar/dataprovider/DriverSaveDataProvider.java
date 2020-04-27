package com.truck.deusemar.dataprovider;

import static com.truck.deusemar.dataprovider.mapper.DriverDataProviderMapper.convertCoreToEntity;
import static com.truck.deusemar.dataprovider.mapper.DriverDataProviderMapper.convertEntityToCore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.truck.deusemar.dataprovider.repository.DriverRepositoryClean;
import com.truck.deusemar.gateway.DriverSaveGateway;
import com.truck.deusemar.usecase.entity.Driver;

@Component
public class DriverSaveDataProvider implements DriverSaveGateway {

	private DriverRepositoryClean repository;

	@Autowired
	public DriverSaveDataProvider(DriverRepositoryClean repository) {
		this.repository = repository;
	}

	@Override
	public Driver saveDriver(Driver driver) {
		var driverEntity = convertCoreToEntity(driver);
		return convertEntityToCore(repository.save(driverEntity));
	}

}
