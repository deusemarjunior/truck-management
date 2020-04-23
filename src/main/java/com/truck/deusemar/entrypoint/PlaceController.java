package com.truck.deusemar.entrypoint;

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

import com.truck.deusemar.domain.Place;
import com.truck.deusemar.repository.PlaceRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/places")
@Api(value = "Maintenance of places 'origin' and 'destinations'")
public class PlaceController {

	@Autowired
	private PlaceRepository placeRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable(value = "id", required = true) String id) {
		Optional<Place> place = placeRepository.findById(id);
		if (place.isPresent())
			return ResponseEntity.ok(place.get());
		else
			return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<?> list() {

		return this.placeRepository.count() == 0L ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(this.placeRepository.findAll());

	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Place place) {
		try {
			this.placeRepository.save(place);
			return ResponseEntity.status(HttpStatus.CREATED).body(place);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Place place) {
		try {
			this.placeRepository.save(place);
			return ResponseEntity.status(HttpStatus.OK).body(place);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) String id) {
		try {
			Optional<Place> place = placeRepository.findById(id);
			if (place.isPresent()) {
				this.placeRepository.delete(place.get());
				return ResponseEntity.status(HttpStatus.OK).body("");
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Errors");
		}
	}

}
