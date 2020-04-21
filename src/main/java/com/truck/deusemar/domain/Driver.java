package com.truck.deusemar.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.truck.deusemar.domain.enums.GenderEnum;
import com.truck.deusemar.domain.enums.TruckTypeEnum;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@Document(collection = "drivers")
@ApiModel
public class Driver {

	@Id
	private String id;
	
	@NonNull
	private String nome;
	
	private Integer age;

	private GenderEnum gender;

	private boolean hasTruck;

    private TruckTypeEnum truckType;
	

	public Driver(String id, String nome, Integer age, GenderEnum gender, boolean hasTruck, TruckTypeEnum truckType) {
		super();
		this.id = id;
		this.nome = nome;
		this.age = age;
		this.gender = gender;
		this.hasTruck = hasTruck;
		this.truckType = truckType;
	}



	public Driver() {
	}
	
	
	
}
