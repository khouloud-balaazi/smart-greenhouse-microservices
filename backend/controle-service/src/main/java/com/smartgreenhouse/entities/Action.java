package com.smartgreenhouse.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "action")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipement_id", nullable = false)
    private Equipement equipement;

    // juste l'ID du paramètre distant
    @Column(name = "parametre_id", nullable = false)
    private Long parametreId;

    @Column(name = "type_action", nullable = false)
    private String typeAction;

    @Column(name = "date_execution", nullable = false)
    private LocalDateTime dateExecution;

    // Champ pour communication Feign, non persistant
    @Transient
    private ParametreDTO parametre;
}
