package com.truck.deusemar.entrypoint.mapper;

import com.truck.deusemar.entrypoint.entity.DriverRequestDTO;
import com.truck.deusemar.entrypoint.entity.DriverResponseDTO;
import com.truck.deusemar.usecase.entity.Driver;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverEntryPointMapper {

	public static DriverResponseDTO convertCoreToDtoResponse(Driver driver) {
		return DriverResponseDTO.builder().age(driver.getAge()).gender(driver.getGender()).hasTruck(driver.isHasTruck())
				.id(driver.getId()).nome(driver.getNome()).truckType(driver.getTruckType()).build();
	}

	public static Driver convertDtoRequestToCore(DriverRequestDTO driver) {
		return Driver.builder().age(driver.getAge()).gender(driver.getGender()).hasTruck(driver.isHasTruck())
				.nome(driver.getNome()).truckType(driver.getTruckType()).build();
	}

}
