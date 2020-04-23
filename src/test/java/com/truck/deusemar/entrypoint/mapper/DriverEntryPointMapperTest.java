package com.truck.deusemar.entrypoint.mapper;

import static com.truck.deusemar.entrypoint.mapper.DriverEntryPointMapper.convertCoreToDto;
import static com.truck.deusemar.entrypoint.mapper.DriverEntryPointMapper.convertDtoToCore;

import org.junit.Assert;
import org.junit.Test;

import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.factory.DriverFactory;
import com.truck.deusemar.usecase.entity.Driver;

public class DriverEntryPointMapperTest {

	@Test
	public void testConvertDtotoCore() {
		// given
		DriverDTO driverDto = DriverFactory.generateRandomDTO();

		// when
		Driver driverCore = convertDtoToCore(driverDto);

		// then
		Assert.assertEquals(driverCore.toString(), driverDto.toString());
	}

	public void testConvertCoretoDto() {
		// given
		Driver driverCore = DriverFactory.generateRandomCore();

		// when
		DriverDTO driverDto = convertCoreToDto(driverCore);

		// then
		Assert.assertEquals(driverDto.toString(), driverCore.toString());

	}

}
