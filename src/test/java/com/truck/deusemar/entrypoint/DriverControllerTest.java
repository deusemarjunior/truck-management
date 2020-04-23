package com.truck.deusemar.entrypoint;

import static com.truck.deusemar.entrypoint.mapper.DriverEntryPointMapper.convertDtoToCore;
import static com.truck.deusemar.factory.DriverFactory.generateRandomDTO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.repository.DriverRepository;
import com.truck.deusemar.usecase.DriverSaveUseCase;
import com.truck.deusemar.usecase.entity.Driver;

@RunWith(MockitoJUnitRunner.class)
public class DriverControllerTest {

	@Mock
	private DriverRepository driverRepository;

	@Mock
	private DriverSaveUseCase useCase;

	@Captor
	ArgumentCaptor<Driver> captorDrive;

	DriverController driverC;

	@Before
	public void setup() {
		driverC = new DriverController(driverRepository, useCase);
	}

	@Test
	public void saveTest() {
		// given
		DriverDTO driverDto = generateRandomDTO();
		var driverCore = convertDtoToCore(driverDto);

		when(useCase.saveDriver(Mockito.any(Driver.class))).thenReturn(driverCore);

		// when
		var driverJson = driverC.create(driverDto);

		// then
		assertEquals(driverJson.getBody().getNome(), driverDto.getNome());
		verify(useCase, times(1)).saveDriver(captorDrive.capture());
		assertEquals(captorDrive.getValue().getNome(), driverDto.getNome());

	}

}
