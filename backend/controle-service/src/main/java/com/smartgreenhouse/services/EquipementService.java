package com.smartgreenhouse.services;

import com.smartgreenhouse.entities.Equipement;
import com.smartgreenhouse.repositories.EquipementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementService {

    private final EquipementRepository equipementRepository;

    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    public Equipement saveEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public Equipement getEquipementById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipement with id " + id + " not found"));
    }

    public void deleteEquipement(Long id) {
        equipementRepository.deleteById(id);
    }
}
