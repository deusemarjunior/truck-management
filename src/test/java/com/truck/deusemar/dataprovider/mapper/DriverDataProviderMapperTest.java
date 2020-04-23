package com.truck.deusemar.dataprovider.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.truck.deusemar.factory.DriverFactory;

public class DriverDataProviderMapperTest {

	@Test
	public void testConvertEntityToCore() {
		// given
		var driverEntity = DriverFactory.generateRandomEntity();

		// when
		var driverCore = DriverDataProviderMapper.convertEntityToCore(driverEntity);

		// then
		assertEquals(driverEntity.toString(), driverCore.toString());
	}

	@Test
	public void testConvertCoreToEntity() {
		// given
		var driverCore = DriverFactory.generateRandomCore();

		// when
		var driverEntity = DriverDataProviderMapper.convertCoreToEntity(driverCore);

		// then
		assertEquals(driverCore.toString(), driverEntity.toString());
	}

}
