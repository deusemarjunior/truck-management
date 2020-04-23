package com.truck.deusemar.entrypoint;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.entrypoint.mapper.DriverEntryPointMapper;
import com.truck.deusemar.factory.DriverFactory;
import com.truck.deusemar.repository.DriverRepository;
import com.truck.deusemar.usecase.DriverSaveUseCase;
import com.truck.deusemar.usecase.entity.Driver;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class DriverControllerTest {

	@Mock
	private DriverRepository driverRepository;
	@Mock
	private DriverSaveUseCase useCase;
	DriverController driverC;

	@Before
	private void setup() {
		driverC = new DriverController(driverRepository, useCase);
	}

	@Test
	public void saveTest() {
		DriverDTO driverDto = DriverFactory.generateRandomDTO();
		var driverCore = new DriverEntryPointMapper().convertDtoToCore(driverDto);
		
		when(useCase.saveDriver(Mockito.any(Driver.class))).thenReturn(driverCore);
		var driverJson =  driverC.create(driverDto); 
		
		assertEquals(driverJson.getBody().
		

	}

}
