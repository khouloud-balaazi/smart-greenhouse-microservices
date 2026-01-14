package com.smartgreenhouse.services;

import com.smartgreenhouse.entities.Action;
import com.smartgreenhouse.entities.ParametreDTO;
import com.smartgreenhouse.feignclients.EnvironnementClient;
import com.smartgreenhouse.repositories.ActionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;
    private final EnvironnementClient environnementClient;

    @Transactional
    public Action saveAction(Action action) {
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
