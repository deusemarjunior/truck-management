package com.truck.deusemar.entrypoint;

import static com.truck.deusemar.entrypoint.mapper.DriverEntryPointMapper.convertDtoToCore;
import static com.truck.deusemar.factory.DriverFactory.generateRandomDTO;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truck.deusemar.configuration.ConfigurationIntegrationTest;
import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.repository.DriverRepository;
import com.truck.deusemar.usecase.DriverSaveUseCase;
import com.truck.deusemar.usecase.entity.Driver;

public class DriverControllerIntegrationTest extends ConfigurationIntegrationTest {

	private MockMvc mockMvc;

	@Mock
	private DriverRepository driverRepository;

	@Mock
	private DriverSaveUseCase useCase;

	@InjectMocks
	private DriverController controller;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testSaveEndPoint() throws Exception {

		// given
		DriverDTO driverDto = generateRandomDTO();
		String driverJson = new ObjectMapper().writeValueAsString(driverDto);

		when(useCase.saveDriver(Mockito.any(Driver.class))).thenReturn(convertDtoToCore(driverDto));

		// when
		ResultActions dataReturn = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/drivers").contentType(APPLICATION_JSON).content(driverJson));

		// then
		dataReturn.andExpect(MockMvcResultMatchers.status().isCreated());

	}

}
