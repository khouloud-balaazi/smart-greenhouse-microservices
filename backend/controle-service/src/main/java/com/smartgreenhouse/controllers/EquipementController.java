package com.smartgreenhouse.controllers;

import com.smartgreenhouse.entities.Equipement;
import com.smartgreenhouse.enums.EtatEquipement;
import com.smartgreenhouse.services.EquipementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final EquipementService equipementService;

    @GetMapping
    public ResponseEntity<List<Equipement>> getAllEquipements() {
        return ResponseEntity.ok(equipementService.getAllEquipements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getEquipementById(@PathVariable Long id) {
        return ResponseEntity.ok(equipementService.getEquipementById(id));
    }

    @PostMapping
    public ResponseEntity<Equipement> createEquipement(@RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.saveEquipement(equipement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> updateEquipementEtat(
            @PathVariable Long id,
            @RequestParam EtatEquipement etat
    ) {
        Equipement equipement = equipementService.getEquipementById(id);
        equipement.setEtat(etat);
        return ResponseEntity.ok(equipementService.saveEquipement(equipement));
    }
}
