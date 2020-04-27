package com.truck.deusemar.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truck.deusemar.gateway.DriverSaveGateway;
import com.truck.deusemar.usecase.entity.Driver;

@Service
public class DriverSaveUseCase {

	private DriverSaveGateway saveSateway;

	@Autowired
	public DriverSaveUseCase(DriverSaveGateway saveSateway) {
		this.saveSateway = saveSateway;
	}

	public Driver saveDriver(Driver driver) {
		return saveSateway.saveDriver(driver);
	}

}
