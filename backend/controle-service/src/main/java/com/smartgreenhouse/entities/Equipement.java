package com.smartgreenhouse.entities;

import com.smartgreenhouse.enums.EquipementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "equipements") // nom exact de la table dans la BDD
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EquipementType type; // VENTILATEUR, POMPE, CHAUFFAGE

    @Column(nullable = false)
    private String etat; // ON / OFF / AUTO

    private LocalDateTime derniereAction;
}
