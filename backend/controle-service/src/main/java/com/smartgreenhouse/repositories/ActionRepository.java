package com.smartgreenhouse.repositories;

import com.smartgreenhouse.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    // Récupérer toutes les actions d'un équipement spécifique
    List<Action> findByEquipementId(Long equipementId);

    // Récupérer toutes les actions liées à un paramètre spécifique (parametreId)
    List<Action> findByParametreId(Long parametreId);

}
