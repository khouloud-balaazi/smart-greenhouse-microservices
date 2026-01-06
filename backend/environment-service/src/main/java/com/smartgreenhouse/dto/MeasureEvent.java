package com.smartgreenhouse.dto;

import java.time.LocalDateTime;

import com.smartgreenhouse.Enum.ParametreType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//communication entre services
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeasureEvent {

	private ParametreType type;      
    private Double valeur;           
    private Double seuilMin;          
    private Double seuilMax;    
    // Résultat
    private Boolean alert;            
    private LocalDateTime timestamp;  
}
