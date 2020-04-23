package com.truck.deusemar.usecase.entity;

import com.truck.deusemar.domain.enums.GenderEnum;
import com.truck.deusemar.domain.enums.TruckTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {

	private String id;

	private String nome;

	private Integer age;

	private GenderEnum gender;

	private boolean hasTruck;

	private TruckTypeEnum truckType;

	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome + ", age=" + age + ", gender=" + gender + ", hasTruck=" + hasTruck
				+ ", truckType=" + truckType + "]";
	}

}
