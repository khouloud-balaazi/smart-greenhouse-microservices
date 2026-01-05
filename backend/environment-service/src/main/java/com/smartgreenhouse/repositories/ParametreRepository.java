package com.smartgreenhouse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartgreenhouse.Enum.ParametreType;
import com.smartgreenhouse.entities.Parametre;

public interface ParametreRepository extends JpaRepository<Parametre, Long> {

    Optional<Parametre> findByType(ParametreType type);
}
