package com.smartgreenhouse.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.smartgreenhouse.enums.ParametreType;
//communication entre services
@Data
public class MeasureEvent {

	private ParametreType type;      
    private Double valeur;           
    private Double seuilMin;          
    private Double seuilMax;    
    // Résultat
    private boolean alert;            
    private LocalDateTime timestamp;  
}
