package com.truck.deusemar.entrypoint.mapper;

import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.usecase.entity.Driver;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverEntryPointMapper {

	public static DriverDTO convertCoreToDto(Driver driver) {
		return DriverDTO.builder().age(driver.getAge()).gender(driver.getGender()).hasTruck(driver.isHasTruck())
				.id(driver.getId()).nome(driver.getNome()).truckType(driver.getTruckType()).build();
	}

	public static Driver convertDtoToCore(DriverDTO driver) {
		return Driver.builder().age(driver.getAge()).gender(driver.getGender()).hasTruck(driver.isHasTruck())
				.id(driver.getId()).nome(driver.getNome()).truckType(driver.getTruckType()).build();
	}

}
