package com.truck.deusemar.entrypoint.entity;

import java.io.Serializable;

import com.mongodb.lang.NonNull;
import com.truck.deusemar.domain.enums.GenderEnum;
import com.truck.deusemar.domain.enums.TruckTypeEnum;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class DriverRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NonNull
	private String nome;

	private Integer age;

	private GenderEnum gender;

	private boolean hasTruck;

	private TruckTypeEnum truckType;

}
