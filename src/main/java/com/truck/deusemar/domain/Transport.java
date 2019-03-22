package com.truck.deusemar.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.truck.deusemar.domain.enums.StatusEnum;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@Document(collection = "transports")
@ApiModel
public class Transport {

	@Id
	private String id;

	@NonNull
	private Driver driver;
	
	private Place origin;

	private Place destiny;

	@NonNull
	private boolean truckHasLoad;
		
	private LocalDate date;

	private StatusEnum status;

	

	public Transport() {
	}



	public Transport(String id, Driver driver, Place origin, Place destiny, boolean truckHasLoad, LocalDate date,
			StatusEnum status) {
		this.id = id;
		this.driver = driver;
		this.origin = origin;
		this.destiny = destiny;
		this.truckHasLoad = truckHasLoad;
		this.date = date;
		this.status = status;
	}


	
	
	
}
