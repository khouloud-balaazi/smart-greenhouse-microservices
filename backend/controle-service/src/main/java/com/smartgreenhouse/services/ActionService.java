package com.smartgreenhouse.services;

import com.smartgreenhouse.entities.Action;
import com.smartgreenhouse.entities.ParametreDTO;
import com.smartgreenhouse.feignclients.EnvironnementClient;
import com.smartgreenhouse.repositories.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;
    private final EnvironnementClient environnementClient;

    public Action saveAction(Action action) {
        // Récupérer paramètre via Feign pour garder action DTO synchronisée
        if (action.getParametreId() != null) {
            ParametreDTO param = environnementClient.getParametreById(action.getParametreId());
            action.setParametre(param); // @Transient
        }

        action.setDateExecution(LocalDateTime.now());
        return actionRepository.save(action);
    }

    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    public Action getActionById(Long id) {
        return actionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Action with id " + id + " not found"));
    }
}
