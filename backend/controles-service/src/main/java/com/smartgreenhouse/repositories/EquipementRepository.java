package com.smartgreenhouse.repositories;

import com.smartgreenhouse.entities.Equipement;
import com.smartgreenhouse.enums.EquipementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {

    // Récupérer tous les équipements par type (ex : tous les ventilateurs)
    List<Equipement> findByType(EquipementType type);

    // Récupérer les équipements par état (ON/OFF/AUTO)
    List<Equipement> findByEtat(String etat);
}
