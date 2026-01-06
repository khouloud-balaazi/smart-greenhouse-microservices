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
public class RabbitMeasurementEventPublisher  implements MeasurementEventPublisher {

    private final RabbitTemplate rabbitTemplate;

   

    @Override
    public void publish(Mesure mesure, boolean alert) {

        Parametre parametre = mesure.getParametre();

        MeasureEvent event = MeasureEvent.builder()
                .type(parametre.getType())
                .valeur(mesure.getValeur())
                .seuilMin(parametre.getSeuilMin())
                .seuilMax(parametre.getSeuilMax())
                .alert(alert)
                .timestamp(LocalDateTime.now())
                .build();

        rabbitTemplate.convertAndSend( RabbitMQConstants.EXCHANGE,
                RabbitMQConstants.ROUTING_KEY
                , event);
    }

	
}
