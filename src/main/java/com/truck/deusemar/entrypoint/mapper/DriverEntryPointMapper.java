package com.truck.deusemar.entrypoint.mapper;

import org.springframework.stereotype.Component;

import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.usecase.entity.Driver;

@Component
public class DriverEntryPointMapper {

	public DriverDTO convertCoreToDto(Driver driver) {
		return DriverDTO.builder().age(driver.getAge()).gender(driver.getGender()).hasTruck(driver.isHasTruck())
				.id(driver.getId()).nome(driver.getNome()).truckType(driver.getTruckType()).build();
	}

	public Driver convertDtoToCore(DriverDTO driver) {
		return Driver.builder().age(driver.getAge()).gender(driver.getGender()).hasTruck(driver.isHasTruck())
				.id(driver.getId()).nome(driver.getNome()).truckType(driver.getTruckType()).build();
	}

}
