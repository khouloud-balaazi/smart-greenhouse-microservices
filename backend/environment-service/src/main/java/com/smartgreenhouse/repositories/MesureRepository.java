package com.smartgreenhouse.repositories;

import com.smartgreenhouse.entities.Parametre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartgreenhouse.entities.Mesure;

@Repository
public interface MesureRepository extends JpaRepository<Mesure, Long> {

    List<Mesure> findByParametre(Parametre parametre);

    List<Mesure> findByParametreId(Long parametreId);
}
