package com.truck.deusemar.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.truck.deusemar.domain.enums.GenderEnum;
import com.truck.deusemar.domain.enums.TruckTypeEnum;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
