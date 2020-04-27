package com.truck.deusemar.dataprovider;

import static com.truck.deusemar.dataprovider.mapper.DriverDataProviderMapper.convertEntityToCore;
import static com.truck.deusemar.factory.DriverFactory.generateRandomEntity;
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

import com.truck.deusemar.dataprovider.repository.DriverRepositoryClean;
import com.truck.deusemar.dataprovider.repository.entity.DriverEntity;

@RunWith(MockitoJUnitRunner.class)
public class DriverSaveDataProviderTest {

	@Mock
	private DriverRepositoryClean repository;

	@Captor
	private ArgumentCaptor<DriverEntity> entityCaptor;

	private DriverSaveDataProvider dataProvider;

	@Before
	public void setup() {
		dataProvider = new DriverSaveDataProvider(repository);
	}

	@Test
	public void saveDriverEntity() {
		// given
		var driverEntity = generateRandomEntity();
		var driverCore = convertEntityToCore(driverEntity);

		when(repository.save(Mockito.any(DriverEntity.class))).thenReturn(driverEntity);

		// when
		var driverSaved = dataProvider.saveDriver(driverCore);

		// then
		verify(repository, times(1)).save(entityCaptor.capture());
		assertEquals(driverSaved.getNome(), driverCore.getNome());
		assertEquals(entityCaptor.getValue().isHasTruck(), driverCore.isHasTruck());

	}

}
