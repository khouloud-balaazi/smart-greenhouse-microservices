package com.smartgreenhouse.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.smartgreenhouse.Enum.ParametreType;
import com.smartgreenhouse.entities.Mesure;
import com.smartgreenhouse.services.MesureService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mesures")
@RequiredArgsConstructor
public class MesureController {

    private final MesureService mesureService;

    
 // Récupérer toutes les mesures
    @GetMapping
    public List<Mesure> getAllMesures() {
        return mesureService.getAllMesures();
    }

    // Ajouter une mesure (⚠️ vérifie seuil + publie event RabbitMQ)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mesure addMesure(@RequestBody MesureRequest request) {
        return mesureService.enregistrerMesure(
                request.getType(),
                request.getValeur()
        );
    }

    // Dashboard : mesures par paramètre
    @GetMapping("/parametre/{id}")
    public List<Mesure> getMesuresByParametre(@PathVariable Long id) {
        return mesureService.getMesuresByParametre(id);
    }

    // DTO interne pour POST
    @Data
    static class MesureRequest {
        private ParametreType type;
        private Double valeur;
    }
}
