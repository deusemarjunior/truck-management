package com.truck.deusemar.factory;

import static io.github.benas.randombeans.api.EnhancedRandom.random;

import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.usecase.entity.Driver;

public class DriverFactory {

	public static Driver generateRandomCore() {
		return random(Driver.class);
	}

	public static DriverDTO generateRandomDTO() {
		return random(DriverDTO.class);
	}

}
