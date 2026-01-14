package com.smartgreenhouse.entities;

import com.smartgreenhouse.Enum.ParametreType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parametres")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Parametre {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
   @Enumerated(EnumType.STRING)
   @Column(nullable = false, unique = true)
    private ParametreType type;

    @Column(nullable = false)
    private Double seuilMin;

    @Column(nullable = false)
    private Double seuilMax;
	

}
