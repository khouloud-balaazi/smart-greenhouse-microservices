package com.smartgreenhouse.event;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.smartgreenhouse.dto.MeasureEvent;
import com.smartgreenhouse.entities.Mesure;
import com.smartgreenhouse.entities.Parametre;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMeasurementEventPublisher implements MeasurementEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Mesure mesure, boolean alert) {

        Parametre parametre = mesure.getParametre();

        // Création de l'objet MeasureEvent
        MeasureEvent event = new MeasureEvent();
        event.setType(parametre.getType());
        event.setValeur(mesure.getValeur());
        event.setSeuilMin(parametre.getSeuilMin());
        event.setSeuilMax(parametre.getSeuilMax());
        event.setAlert(alert);
        event.setTimestamp(LocalDateTime.now());

        // Envoi de l'événement via RabbitMQ
        rabbitTemplate.convertAndSend(
                RabbitMQConstants.EXCHANGE,
                RabbitMQConstants.ROUTING_KEY,
                event
        );
    }
}
