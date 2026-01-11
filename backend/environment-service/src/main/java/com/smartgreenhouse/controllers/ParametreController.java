package com.smartgreenhouse.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.smartgreenhouse.entities.Parametre;
import com.smartgreenhouse.services.ParametreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/parametres")
@RequiredArgsConstructor
public class ParametreController {

    private final ParametreService parametreService;

    // Création d’un paramètre (température, humidité, ...)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parametre create(@RequestBody Parametre parametre) {
        return parametreService.createParametre(parametre);
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
