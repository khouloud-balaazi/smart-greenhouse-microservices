package com.smartgreenhouse.event;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.smartgreenhouse.dto.MeasureEvent;
import com.smartgreenhouse.entities.Action;
import com.smartgreenhouse.entities.Equipement;
import com.smartgreenhouse.enums.EquipementType;
import com.smartgreenhouse.enums.EtatEquipement;
import com.smartgreenhouse.enums.ParametreType;
import com.smartgreenhouse.feignclients.EnvironnementClient;
import com.smartgreenhouse.services.ActionService;

import jakarta.transaction.Transactional;

import com.smartgreenhouse.repositories.EquipementRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class MeasurementEventListener {

    private final EquipementRepository equipementRepository;
    private final ActionService actionService;

    @Transactional
    @RabbitListener(queues = RabbitMQConstants.QUEUE)
    public void consume(MeasureEvent event) {

        log.info("📥 EVENT RABBITMQ REÇU | {} = {}",
                event.getType(), event.getValeur());

        if (!event.isAlert()) {
            log.info("✅ Pas d'alerte → aucune action");
            return;
        }

        EquipementType equipementType = determineEquipementType(event.getType());

        Equipement equipement = equipementRepository
                .findByType(equipementType)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Équipement non trouvé : " + equipementType));

        String typeAction = determineAction(
                event.getType(),
                event.getValeur(),
                event.getSeuilMin(),
                event.getSeuilMax()
        );

        Action action = Action.builder()
                .equipement(equipement)
                .typeAction(typeAction)
                .dateExecution(LocalDateTime.now())
                .build();

        actionService.saveAction(action);

        equipement.setEtat(
                typeAction.equals("DEMARRER")
                        ? EtatEquipement.ON
                        : EtatEquipement.OFF
        );
        equipement.setDerniereAction(LocalDateTime.now());
        equipementRepository.save(equipement);

        log.warn("🚨 ACTION AUTO : {} | {} -> {}",
                typeAction,
                equipement.getType(),
                equipement.getEtat());
    }

    private EquipementType determineEquipementType(ParametreType type) {
        return switch (type) {
            case TEMPERATURE -> EquipementType.VENTILATEUR;
            case HUMIDITE -> EquipementType.POMPE;
            case LUMINOSITE -> EquipementType.CHAUFFAGE;
        };
    }

    private String determineAction(
            ParametreType type,
            Double valeur,
            Double min,
            Double max) {

        return switch (type) {
            case TEMPERATURE -> valeur > max ? "DEMARRER" : "ARRETER";
            case HUMIDITE, LUMINOSITE ->
                    valeur < min ? "DEMARRER" : "ARRETER";
        };
    }
}

