package com.truck.deusemar.domain.enums;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import io.swagger.annotations.ApiModel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = TruckTypeEnum.TruckTypeEnumDeserializer.class)
@ApiModel(description="Values avaliable -> {label = TRUCK_34 ,code = 1},{label =TRUCK_TOCO, code = 2},{label = TRUCK ,code = 3},{label = TRUCK_WAGON , code =4},{label = TRUCK_EXTENDED, code = 5}")
public enum TruckTypeEnum {

	TRUCK_34("Caminhão 3/4", 1),
	TRUCK_TOCO("Caminhão 3/4", 2),
	TRUCK("Caminhão Truck", 3),
	TRUCK_WAGON("Carreta Eixo", 4),
	TRUCK_EXTENDED("Carreta Eixo Extendido", 5);
	
	private String label;
	private int code;
	
	private TruckTypeEnum(String label, int code) {
		this.label = label;
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public static class TruckTypeEnumDeserializer extends StdDeserializer<TruckTypeEnum> {
		public TruckTypeEnumDeserializer() {
			super(TruckTypeEnum.class);
		}

		@Override
		public TruckTypeEnum deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
			final JsonNode jsonNode = jp.readValueAsTree();
			String label = jsonNode.get("label").asText();
			String code = jsonNode.get("code").asText();

			for (TruckTypeEnum me: TruckTypeEnum.values()) {
				if ( me.getCode() == Integer.parseInt(code) && me.getLabel().equals(label)) {
					return me;
				}
			}
			throw dc.mappingException("Cannot deserialize TruckTypeEnum from label " + label + " and code " + code);
		}
	}

	
	
}
