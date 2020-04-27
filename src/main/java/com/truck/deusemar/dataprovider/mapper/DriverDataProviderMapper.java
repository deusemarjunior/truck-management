package com.truck.deusemar.dataprovider.mapper;

import com.truck.deusemar.dataprovider.repository.entity.DriverEntity;
import com.truck.deusemar.usecase.entity.Driver;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverDataProviderMapper {

	public static Driver convertEntityToCore(DriverEntity driver) {
		return Driver.builder().age(driver.getAge()).gender(driver.getGender()).hasTruck(driver.isHasTruck())
				.id(driver.getId()).nome(driver.getNome()).truckType(driver.getTruckType()).build();
	}

	public static DriverEntity convertCoreToEntity(Driver driver) {
		return DriverEntity.builder().age(driver.getAge()).gender(driver.getGender()).hasTruck(driver.isHasTruck())
				.id(driver.getId()).nome(driver.getNome()).truckType(driver.getTruckType()).build();
	}
}
