package com.truck.deusemar.entrypoint;

import static com.truck.deusemar.entrypoint.mapper.DriverEntryPointMapper.convertCoreToDto;
import static com.truck.deusemar.entrypoint.mapper.DriverEntryPointMapper.convertDtoToCore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truck.deusemar.domain.Driver;
import com.truck.deusemar.entrypoint.entity.DriverDTO;
import com.truck.deusemar.repository.DriverRepository;
import com.truck.deusemar.usecase.DriverSaveUseCase;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Maintenance of drivers")
public class DriverController {

	private DriverRepository driverRepository;
	private DriverSaveUseCase useCase;

	@Autowired
	public DriverController(DriverRepository driverRepository, DriverSaveUseCase useCase) {
		this.driverRepository = driverRepository;
		this.useCase = useCase;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Driver> getId(@PathVariable(value = "id", required = true) String id) {
		var driver = driverRepository.findById(id);
		if (driver.isPresent())
			return ResponseEntity.ok(driver.get());
		else
			return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Driver>> list() {

		return this.driverRepository.count() == 0L ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(this.driverRepository.findAll());

	}

	@GetMapping("/owners")
	@ApiOperation("List of drivers owners of trucks")
	public ResponseEntity<List<Driver>> listDriverOwnerTruck() {
		Driver example = new Driver();
		example.setHasTruck(true);
		List<Driver> drivers = driverRepository.findAll(Example.of(example));
		return drivers == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(drivers);

	}

	@PostMapping("/drivers")
	public ResponseEntity<DriverDTO> create(@RequestBody DriverDTO driverDTO) {
		var driverCore = this.useCase.saveDriver(convertDtoToCore(driverDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(convertCoreToDto(driverCore));
	}

	@PutMapping
	public ResponseEntity<Driver> update(@RequestBody Driver driver) {
		this.driverRepository.save(driver);
		return ResponseEntity.status(HttpStatus.OK).body(driver);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Driver> delete(@PathVariable(value = "id", required = true) String id) {
		var drive = driverRepository.findById(id);

		if (drive.isPresent()) {
			this.driverRepository.delete(drive.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
