package com.truck.deusemar.factory;

import static io.github.benas.randombeans.api.EnhancedRandom.random;

import com.truck.deusemar.dataprovider.repository.entity.DriverEntity;
import com.truck.deusemar.entrypoint.entity.DriverRequestDTO;
import com.truck.deusemar.entrypoint.entity.DriverResponseDTO;
import com.truck.deusemar.usecase.entity.Driver;

public class DriverFactory {

	public static Driver generateRandomCore() {
		return random(Driver.class);
	}

	public static DriverRequestDTO generateRandomDTORequest() {
		return random(DriverRequestDTO.class);
	}

	public static DriverResponseDTO generateRandomDTOResponse() {
		return random(DriverResponseDTO.class);
	}

	public static DriverEntity generateRandomEntity() {
		return random(DriverEntity.class);
	}

}
