package com.truck.deusemar.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@Document(collection = "places")
@ApiModel
public class Place {

	@Id
	private String id;
	
	@NonNull
	private String nome;
	
	@NonNull
	private double lat;

	@NonNull
	private double lon;

	public Place(String id, String nome, double lat, double lon) {
		super();
		this.id = id;
		this.nome = nome;
		this.lat = lat;
		this.lon = lon;
	}

	public Place() {
	}
		
	
	
}
