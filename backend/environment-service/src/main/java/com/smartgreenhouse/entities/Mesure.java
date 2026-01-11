package com.smartgreenhouse.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mesures")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Mesure {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(optional = false)
	    @JoinColumn(name = "parametre_id")
	    private Parametre parametre;

	    @Column(nullable = false)
	    private Double valeur;

	    @Column(nullable = false)
	    private LocalDateTime dateMesure;

}
