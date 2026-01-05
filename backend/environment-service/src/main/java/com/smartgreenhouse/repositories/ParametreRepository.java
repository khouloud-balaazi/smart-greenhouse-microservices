package com.smartgreenhouse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartgreenhouse.Enum.ParametreType;
import com.smartgreenhouse.entities.Parametre;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {

    Optional<Parametre> findByType(ParametreType type);
}
