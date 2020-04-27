package com.truck.deusemar.entrypoint.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.truck.deusemar.entrypoint.entity.DriverRequestDTO;
import com.truck.deusemar.entrypoint.entity.DriverResponseDTO;
import com.truck.deusemar.factory.DriverFactory;
import com.truck.deusemar.usecase.entity.Driver;

public class DriverEntryPointMapperTest {

	@Test
	public void testConvertDtoRequesttoCore() {
		// given
		DriverRequestDTO driverDto = DriverFactory.generateRandomDTORequest();

		// when
		Driver driverCore = DriverEntryPointMapper.convertDtoRequestToCore(driverDto);

		// then
		Assert.assertEquals(driverCore.getAge(), driverDto.getAge());
		Assert.assertEquals(driverCore.getNome(), driverDto.getNome());
		Assert.assertEquals(driverCore.getGender(), driverDto.getGender());
		Assert.assertEquals(driverCore.getTruckType(), driverDto.getTruckType());
		Assert.assertEquals(driverCore.isHasTruck(), driverDto.isHasTruck());

	}

	public void testConvertCoretoDtoResponse() {
		// given
		Driver driverCore = DriverFactory.generateRandomCore();

		// when
		DriverResponseDTO driverDto = DriverEntryPointMapper.convertCoreToDtoResponse(driverCore);

		// then
		Assert.assertEquals(driverDto.toString(), driverCore.toString());

	}

}
