package com.smartgreenhouse.entities;

import com.smartgreenhouse.enums.ParametreType;

import lombok.Data;

@Data
public class ParametreDTO {
	 private Long id;
	    private ParametreType type;
	    private Double seuilMin;
	    private Double seuilMax;
}
