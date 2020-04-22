package com.truck.deusemar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.truck.deusemar.domain.Transport;
import com.truck.deusemar.domain.enums.PeriodEnum;
import com.truck.deusemar.repository.TransportRepository;
import com.truck.deusemar.service.TransportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transports")
@Api(value = "Information about drivers origin destination places, if your truck is loaded")
public class TransportController {

	@Autowired
	private TransportRepository transportRepository;

	@Autowired
	private TransportService transportService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable(value = "id", required = true) String id) {
		Optional<Transport> transport = transportRepository.findById(id);
		if (transport.isPresent())
			return ResponseEntity.ok(transport.get());
		else
			return ResponseEntity.noContent().build();

	}

	@GetMapping
	public ResponseEntity<?> list() {

		return this.transportRepository.count() == 0L ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(this.transportRepository.findAll());

	}

	@GetMapping("/current")
	@ApiOperation("List transport current, driver origin destination enabled")
	public ResponseEntity<?> listCurrentActive() {
		List<Transport> transportList = this.transportService.findTransportActive();
		return transportList == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(transportList);

	}

	@GetMapping("/truks")
	@ApiOperation("List transport (Driver, Place origin, Place destination) Grouped by truck type ")
	public ResponseEntity<?> listTransportGruped() {
		List<?> list = this.transportService.getTransportGruped();
		return ResponseEntity.ok(list);

	}

	@GetMapping("/{idOrigin}/noloaded")
	@ApiOperation("Sum of drivers not loaded to back destination based on Origin")
	public ResponseEntity<?> getCurrentActiveLoaded(
			@PathVariable(value = "idOrigin", required = true) String idOrigin) {
		Integer sum = this.transportService.sumTransportActive(idOrigin);
		return ResponseEntity.ok(sum);

	}

	@GetMapping("/{idOrigin}/{period}/loaded")
	@ApiOperation("Sum of drivers loaded on origin by period")
	public ResponseEntity<?> listCurrentActiveLoadedByPeriod(
			@PathVariable(value = "idOrigin", required = true) String idOrigin,
			@PathVariable(value = "period", required = true) PeriodEnum period) {
		Integer sum = this.transportService.getCurrentActiveLoadedByPeriod(idOrigin, period);
		return ResponseEntity.ok(sum);

	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Transport transport) {
		try {
			this.transportRepository.save(transport);
			return ResponseEntity.status(HttpStatus.CREATED).body(transport);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Transport transport) {
		try {
			this.transportRepository.save(transport);
			return ResponseEntity.status(HttpStatus.OK).body(transport);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) String id) {
		try {
			Optional<Transport> transport = transportRepository.findById(id);
			if (transport.isPresent()) {
				this.transportRepository.delete(transport.get());
				return ResponseEntity.status(HttpStatus.OK).body("");
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

}
