package com.truck.deusemar.entrypoint.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.factory.DriverFactory;
import com.truck.deusemar.usecase.entity.Driver;

public class DriverEntryPointMapperTest {

	@Test
	public void testConvertDtotoCore() {
		// given
		var mapper = new DriverEntryPointMapper();
		DriverDTO driverDto = DriverFactory.generateRandomDTO();

		// when
		Driver driverCore = mapper.convertDtoToCore(driverDto);

		// then
		Assert.assertEquals(driverCore.toString(), driverDto.toString());
	}

	public void testConvertCoretoDto() {
		// given
		var mapper = new DriverEntryPointMapper();
		Driver driverCore = DriverFactory.generateRandomCore();

		// when
		DriverDTO driverDto = mapper.convertCoreToDto(driverCore);

		// then
		Assert.assertEquals(driverDto.toString(), driverCore.toString());

	}

}
