package com.smartgreenhouse.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.smartgreenhouse.Enum.ParametreType;
import com.smartgreenhouse.entities.Mesure;
import com.smartgreenhouse.entities.Parametre;
import com.smartgreenhouse.event.MeasurementEventPublisher;
import com.smartgreenhouse.repositories.MesureRepository;
import com.smartgreenhouse.repositories.ParametreRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MesureService {

    private final MesureRepository mesureRepository;
    private final ParametreRepository parametreRepository;
    private final MeasurementEventPublisher eventPublisher;

    public Mesure enregistrerMesure(ParametreType type, Double valeur) {
        Parametre parametre = parametreRepository.findByType(type)
                .orElseThrow(() -> new IllegalArgumentException("Parametre not found"));

        Mesure mesure = new Mesure();
        mesure.setParametre(parametre);
        mesure.setValeur(valeur);
        mesure.setDateMesure(LocalDateTime.now());

        Mesure saved = mesureRepository.save(mesure);

        boolean alert = valeur < parametre.getSeuilMin()
                     || valeur > parametre.getSeuilMax();

        eventPublisher.publish(saved, alert);

        return saved;
    }


    public List<Mesure> getMesuresByParametre(Long parametreId) {
        return mesureRepository.findByParametreId(parametreId);
    }
}
