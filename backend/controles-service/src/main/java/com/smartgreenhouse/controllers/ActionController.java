package com.smartgreenhouse.controllers;

import com.smartgreenhouse.entities.Action;
import com.smartgreenhouse.services.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actions")
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;

    @GetMapping
    public ResponseEntity<List<Action>> getAllActions() {
        return ResponseEntity.ok(actionService.getAllActions());
    }

    @PostMapping
    public ResponseEntity<Action> createAction(@RequestBody Action action) {
        // Le service gère la récupération du paramètre via Feign si nécessaire
        return ResponseEntity.ok(actionService.saveAction(action));
    }
}
