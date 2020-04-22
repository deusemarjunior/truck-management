package com.truck.deusemar.controller;

import java.util.List;
import java.util.Optional;

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
import com.truck.deusemar.repository.DriverRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/drivers")
@Api(value = "Maintenance of drivers")
public class DriverController {

	@Autowired
	private DriverRepository driverRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable(value = "id", required = true) String id) {
		Optional<Driver> driver = driverRepository.findById(id);
		if (driver.isPresent())
			return ResponseEntity.ok(driver.get());
		else
			return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<?> list() {

		return this.driverRepository.count() == 0L ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(this.driverRepository.findAll());

	}

	@GetMapping("/owners")
	@ApiOperation("List of drivers owners of trucks")
	public ResponseEntity<?> listDriverOwnerTruck() {
		Driver example = new Driver();
		example.setHasTruck(true);
		List<Driver> drivers = driverRepository.findAll(Example.of(example));
		return drivers == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(drivers);

	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Driver driver) {
		try {
			this.driverRepository.save(driver);
			return ResponseEntity.status(HttpStatus.CREATED).body(driver);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Driver driver) {
		try {
			this.driverRepository.save(driver);
			return ResponseEntity.status(HttpStatus.OK).body(driver);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) String id) {
		try {
			Optional<Driver> drive = driverRepository.findById(id);
			if (drive.isPresent()) {
				this.driverRepository.delete(drive.get());
				return ResponseEntity.status(HttpStatus.OK).body("");
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

}
