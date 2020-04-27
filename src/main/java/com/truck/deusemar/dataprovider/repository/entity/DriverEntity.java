package com.truck.deusemar.dataprovider.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.truck.deusemar.domain.enums.GenderEnum;
import com.truck.deusemar.domain.enums.TruckTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "driver")
public class DriverEntity {

	@Id
	private String id;

	@NonNull
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
