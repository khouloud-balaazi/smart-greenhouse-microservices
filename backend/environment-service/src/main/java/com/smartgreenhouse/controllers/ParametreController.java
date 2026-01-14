package com.smartgreenhouse.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartgreenhouse.entities.Parametre;
import com.smartgreenhouse.services.ParametreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/parametres")
@RequiredArgsConstructor
public class ParametreController {

    private final ParametreService parametreService;

    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Parametre parametre) {
        try {
            Parametre saved = parametreService.createOrUpdateParametre(parametre);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            // Retourne 400 avec le message
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // Liste des paramètres (dashboard)
    @GetMapping
    public List<Parametre> getAll() {
        return parametreService. getAllParametres();
    }
    
    @GetMapping("/{id}")
    public Parametre getById(@PathVariable Long id) {
        return parametreService.getById(id);
    }
}
