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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MesureService {

    private final MesureRepository mesureRepository;
    private final ParametreRepository parametreRepository;
    private final MeasurementEventPublisher eventPublisher;

    
    public List<Mesure> getAllMesures() {
        return mesureRepository.findAll();
    }

    @Transactional
    public Mesure enregistrerMesure(ParametreType type, Double valeur) {

        Parametre parametre = parametreRepository.findByType(type)
                .orElseThrow(() -> new IllegalArgumentException("Paramètre introuvable"));

        Mesure mesure = new Mesure();
        mesure.setParametre(parametre);
        mesure.setValeur(valeur);
        mesure.setDateMesure(LocalDateTime.now());

        Mesure saved = mesureRepository.save(mesure);

        boolean alert =
                valeur < parametre.getSeuilMin()
             || valeur > parametre.getSeuilMax();

        // RabbitMQ = best effort (ne doit jamais casser l'API)
        try {
            eventPublisher.publish(saved, alert);
        } catch (Exception e) {
            log.error("RabbitMQ indisponible, événement ignoré", e);
        }

        return saved;
    }


    public List<Mesure> getMesuresByParametre(Long parametreId) {
        return mesureRepository.findByParametreId(parametreId);
    }
}
